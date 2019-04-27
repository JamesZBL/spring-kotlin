package me.zbl.springkotlin

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit.jupiter.SpringExtension

/**
 * @author ZHENG BAO LE
 * @since 2019-04-27
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension::class)
class BlogTest(@Autowired val restTemplate: TestRestTemplate) {

    @BeforeAll
    fun setup() {
        println(">> setup")
    }

    @Test
    fun `assert article title, content and author`() {
        val articles = restTemplate.getForEntity<Array<Article>>("/article")
        assertThat(articles.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(articles.body?.get(0)).matches({ m -> m?.title?.contains("Docker")!! }, "Docker")
        assertThat(articles.body?.get(1)).matches({ m -> m?.title?.contains("Spring")!! }, "Spring")
        assertThat(articles.body).allMatch { m -> m.author.firstName == "James" }
    }

    @AfterAll
    fun teardown() {
        println(">> teardown")
    }
}