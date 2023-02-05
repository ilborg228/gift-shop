package ru.samara.giftshop.config;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Aspect
@Configuration
public class ControllerLoggingAspect {

    private final ObjectMapper jsonMapper;
    private final Logger logger = LoggerFactory.getLogger(ControllerLoggingAspect.class);

    public ControllerLoggingAspect(ObjectMapper jsonMapper) {
        this.jsonMapper = jsonMapper.copy().disable(SerializationFeature.INDENT_OUTPUT);
    }

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *) || within(@org.springframework.stereotype.Controller *)")
    public void controllerInterceptor() {
    }

    @Before("controllerInterceptor()")
    public void logActionEntry(JoinPoint joinPoint) {
        try {
            logger.warn("Called API {} with parameters: {}", joinPoint.getSignature().toShortString(),
                    jsonMapper.writeValueAsString(getPayload(joinPoint)));
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @AfterReturning(pointcut = "controllerInterceptor() ", returning = "apiResult")
    public void logActionResult(JoinPoint joinPoint, Object apiResult) {
        String log = "Empty response body";
        if (apiResult != null) {
            if (apiResult instanceof String) {
                log = apiResult.toString().replaceAll("\\\n", "");
                log = log.replaceAll("\\s+", "");
            } else
                try {
                    log = jsonMapper.writeValueAsString(apiResult);

                } catch (IOException e) {
                    //logger.error(e.getMessage(), e);
                }
            logger.warn("API {} result : {}", joinPoint.getSignature().toShortString(), log);
        } else {
            logger.warn("API {} completed", joinPoint.getSignature().toShortString());
        }
    }

    private HashMap<String, Object> getPayload(JoinPoint joinPoint) {
        CodeSignature signature = (CodeSignature) joinPoint.getSignature();
        HashMap<String, Object> params = new HashMap<>();
        for (int i = 0; i < joinPoint.getArgs().length; i++) {
            String parameterName = signature.getParameterNames()[i];
            Object val = joinPoint.getArgs()[i];
            if (val instanceof HttpServletRequest || val instanceof HttpServletResponse
                    || val instanceof InputStream || val instanceof MultipartFile)
                continue;
            params.put(parameterName, val != null ? val : "");
        }
        return params;
    }

}