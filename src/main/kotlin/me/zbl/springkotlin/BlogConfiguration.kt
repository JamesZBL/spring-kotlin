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