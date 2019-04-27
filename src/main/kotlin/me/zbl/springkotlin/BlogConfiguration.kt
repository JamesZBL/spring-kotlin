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

import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * @author ZHENG BAO LE
 * @since 2019-04-27
 */
@Configuration
class BlogConfiguration {

    private fun createUser(): User = User("james", "James", "Zheng")

    @Bean
    fun dataInitializer(userRepository: UserRepository, articleRepository: ArticleRepository) = ApplicationRunner {
        val james = createUser()
        userRepository.save(james)
        articleRepository.saveAll(listOf(
                Article("Spring in Action", "Spring is ...", "What is Spring", james),
                Article("Docker in Action", "Docker is ...", "What is Docker", james)))
    }
}