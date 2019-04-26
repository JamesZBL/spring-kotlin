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
class BlogEndpoint(
        @Autowired val articleRepository: ArticleRepository,
        @Autowired val userRepository: UserRepository) {

    @GetMapping("/article")
    fun allArticles(): Iterable<Article> = articleRepository.findAllOrderByAddedAtDesc()

    @GetMapping("/article/{slug}")
    fun allArticlesBySlug(@PathVariable slug: String): Article = articleRepository.findBySlug(slug)

    @GetMapping("/user")
    fun allUser(): Iterable<User> = userRepository.findAll()

    @GetMapping("/user/{login}")
    fun allUserByLogin(@PathVariable login: String): User = userRepository.findByLogin(login)
}