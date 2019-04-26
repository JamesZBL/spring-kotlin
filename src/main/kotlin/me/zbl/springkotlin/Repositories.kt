package me.zbl.springkotlin

import org.springframework.data.repository.CrudRepository

/**
 * @author ZHENG BAO LE
 * @since 2019-04-26
 */
interface ArticleRepository : CrudRepository<Article, Long> {
    fun findBySlug(slug: String): Article
    fun findAllOrderByAddedAtDesc(): Iterable<Article>
}

interface UserRepository : CrudRepository<User, Long> {
    fun findByLogin(login: String): User
}