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
 * Integration test
 *
 * @author ZHENG BAO LE
 */
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
        assertThat(messages.body).asList().allMatch { (it as String).contains("message") }
    }

    @AfterAll
    fun teardown() {
        println(">> after all")
    }
}
