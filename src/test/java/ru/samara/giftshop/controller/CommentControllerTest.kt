package ru.samara.giftshop.controller

import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import ru.samara.giftshop.entity.Comment
import ru.samara.giftshop.entity.Product

@SpringBootTest
@AutoConfigureMockMvc
class CommentControllerTest : BaseTest() {

    @Test
    fun addComment() {
//        val comment = Comment()
//        comment.text = "asdf"
//        comment.product = productsRepository.save(Product())
//        mockMvc.perform(post("/comment")
//            .content(jsonMapper.writeValueAsString(comment))
//            .contentType(MediaType.APPLICATION_JSON))
//            .andExpect(status().`is`(HttpStatus.CREATED.value()))
    }
}
