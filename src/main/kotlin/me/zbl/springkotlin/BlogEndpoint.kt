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

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

/**
 * @author ZHENG BAO LE
 * @since 2019-04-27
 */
@RestController
class BlogEndpoint @Autowired constructor(
        private val articleRepository: ArticleRepository,
        private val userRepository: UserRepository,
        private val blogProperties: BlogProperties) {

    @GetMapping("/article")
    fun allArticles() = articleRepository.findAllByOrderByAddedAtDesc()

    @GetMapping("/article/{slug}")
    fun allArticlesBySlug(@PathVariable slug: String) = articleRepository.findBySlug(slug)

    @GetMapping("/user")
    fun allUser(): Iterable<User> = userRepository.findAll()

    @GetMapping("/user/{login}")
    fun allUserByLogin(@PathVariable login: String) = userRepository.findByLogin(login)

    @GetMapping("/notification")
    fun notification() = this.blogProperties
}