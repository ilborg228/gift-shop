package ru.samara.giftshop.helpers;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

public class ExceptionHandlerUtil {
    private static final Logger log = LoggerFactory.getLogger(ExceptionHandlerUtil.class);

    public static void logRequest(HttpServletRequest req) {
        if (req == null) {
            return;
        }

        log.debug("====Dump request details ====");
        log.debug("{} {}", req.getMethod(), req.getRequestURI());
        log.debug("Request Headers:");
        try {
            for (String headerName : Collections.list(req.getHeaderNames())) {
                log.debug(headerName + " : " + req.getHeader(headerName));
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public static void logRequest(WebRequest request) {
        logRequest(((ServletWebRequest) request).getNativeRequest(HttpServletRequest.class));
    }
}
