/*
 * Copyright 2019 ZHENG BAO LE
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package me.zbl.springkotlin

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

/**
 * @author ZHENG BAO LE
 * @since 2019-04-27
 */
@WebMvcTest
class BlogMockTest(@Autowired val mockMvc: MockMvc) {

    @MockkBean
    private lateinit var userRepository: UserRepository

    @MockkBean
    private lateinit var articleRepository: ArticleRepository

    @Test
    fun `list articles`() {
        val james = User("james", "James", "Zheng")
        val springArticle = Article("Spring in Action", "Spring is ...", "What is Spring", james)
        val dockerArticle = Article("Docker in Action", "Docker is ...", "What is Docker", james)
        every { articleRepository.findAllByOrderByAddedAtDesc() } returns listOf(springArticle, dockerArticle)
        mockMvc.perform(get("/article").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("\$.[0].author.login").value(james.login))
                .andExpect(jsonPath("\$.[0].slug").value(springArticle.slug))
                .andExpect(jsonPath("\$.[1].author.login").value(james.login))
                .andExpect(jsonPath("\$.[1].slug").value(dockerArticle.slug))
    }

    @Test
    fun `list users`() {
        val james = User("james", "James", "Zheng")
        val tom = User("tom", "Tom", "Lee")
        every { userRepository.findAll() } returns listOf(james, tom)
        mockMvc.perform(get("/user").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("\$.[0].firstName").value(james.firstName))
                .andExpect(jsonPath("\$.[1].firstName").value(tom.firstName))
    }

    @Test
    fun `assert notification is warning`() {
        mockMvc.perform(get("/notification").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("\$.title").value("Blog"))
                .andExpect(jsonPath("\$.banner.title").value("Warning"))
                .andExpect(jsonPath("\$.banner.content").isNotEmpty)
    }
}