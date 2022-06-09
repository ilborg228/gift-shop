package ru.samara.giftshop;

import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.samara.giftshop.dto.CategoryDTO;
import ru.samara.giftshop.helpers.OrderBy;
import ru.samara.giftshop.helpers.OrderByType;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class CategoryControllerTest extends BaseTest{

    @BeforeClass
    public void before() {
        for (int i = 0; i < 15; i++) {
            dataPreparer.addCategory();
        }
    }

    @AfterClass
    public void after() {
        clearTable("categories","products");
    }

    @Test
    public void getAll() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("order_by_type", OrderByType.ASC.name());
        params.add("order_by", OrderBy.CATEGORY_NAME.name());
        params.add("page", String.valueOf(0));
        params.add("page_size", String.valueOf(10));

        ResultActions receivedResponse = this.mockMvc
                .perform(get("/categories").params(params))
                .andExpect(status().is(HttpStatus.OK.value()));
        List<CategoryDTO> res =
                jsonMapper.readValue(receivedResponse.andReturn().getResponse().getContentAsString(),
                        new TypeReference<List<CategoryDTO>>() {});
    }
}
