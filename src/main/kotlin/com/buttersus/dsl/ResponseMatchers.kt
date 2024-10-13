package com.buttersus.dsl

import com.buttersus.client.Response

@Suppress("unused")
class ResponseMatchers(
    private val response: Response
) {
    fun status(block: StatusMatchers.() -> Unit) {
        StatusMatchers(response.statusCode).apply(block)
    }

    fun body(block: BodyMatchers.() -> Unit) {
        BodyMatchers(response.body).apply(block)
    }
}
