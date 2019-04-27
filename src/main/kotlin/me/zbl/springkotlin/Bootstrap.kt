package me.zbl.springkotlin

import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

/**
 * Spring-Kotlin application bootstrap
 *
 * @author ZHENG BAO LE
 */
@SpringBootApplication
@EnableConfigurationProperties(BlogProperties::class)
class Bootstrap

fun main(args: Array<String>) {
    runApplication<Bootstrap>(*args) {
        setBannerMode(Banner.Mode.CONSOLE)
    }
}
