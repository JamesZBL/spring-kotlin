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