package com.buttersus.dsl

import com.buttersus.client.Response

@Suppress("unused")
class ResponseActions(
    @Suppress("MemberVisibilityCanBePrivate") val response: Response
) {
    fun andExpect(block: ResponseMatchers.() -> Unit): ResponseActions {
        ResponseMatchers(response).apply(block)
        return this
    }

    fun andDo(action: (Response) -> Unit): ResponseActions {
        action(response)
        return this
    }
}
