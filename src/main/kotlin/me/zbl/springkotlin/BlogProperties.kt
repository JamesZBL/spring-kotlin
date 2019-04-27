package me.zbl.springkotlin

import org.springframework.boot.context.properties.ConfigurationProperties

/**
 * @author ZHENG BAO LE
 * @since 2019-04-27
 */
@ConfigurationProperties(prefix = "blog")
class BlogProperties {

    lateinit var title: String
    val banner = Banner()

    class Banner {
        var title: String? = null
        lateinit var content: String
    }
}