package ru.samara.giftshop;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
public abstract class BaseTest extends AbstractTestNGSpringContextTests {

    @Autowired
    protected ObjectMapper jsonMapper;
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected JdbcTemplate jdbcTemplate;
    @Autowired
    protected DataPreparer dataPreparer;

    private static final Logger log = LoggerFactory.getLogger(BaseTest.class);

    protected void clearTable(String... tables) {
        clearTable(false, tables);
    }

    private void clearTable(boolean truncate, String... tables) {
        for (String table : tables) {
            log.debug("Clear table = {}", table);
            jdbcTemplate.update("DELETE FROM " + table);
            if (truncate)
                jdbcTemplate.update("ALTER TABLE " + table + " AUTO_INCREMENT=1");
        }
    }
}
