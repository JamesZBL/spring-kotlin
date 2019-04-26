package me.zbl.springkotlin

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono

/**
 * @author ZHENG BAO LE
 * @since 2019-04-26
 */
@RestController
class MessageEndpoint {

    @GetMapping("/message")
    fun hello(): Mono<String> {
        return "Hello, Kotlin".toMono()
    }

    @GetMapping("/messages",produces = ["application/json"])
    fun messages(): Mono<List<String>> {
        return listOf("message from James", "message from Tom", "message from Nick").toMono()
    }
}
