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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension::class)
class IntegrationTest(@Autowired val restTemplate: TestRestTemplate) {

    @BeforeAll
    fun setup() {
        println(">> before all")
    }

    @Test
    fun contextLoads() {
    }

    @Test
    fun `assert hello kotlin`() {
        val msg = restTemplate.getForEntity<String>("/message")
        assertThat(msg.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(msg.body)
                .contains("Hello")
                .contains("Kotlin")
                .containsIgnoringCase("hello")
                .containsIgnoringCase("kotlin")
    }

    @Test
    fun `assert messages size`() {
        val messages = restTemplate.getForEntity<List<String>>("/messages")
        println(messages)
        assertThat(messages).asList().asString().contains("message")
    }

    @AfterAll
    fun teardown() {
        println(">> after all")
    }
}
