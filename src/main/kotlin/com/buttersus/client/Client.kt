package com.buttersus.client

import com.buttersus.dsl.ResponseActions

@Suppress("unused", "MemberVisibilityCanBePrivate")
class Client {
    fun perform(statusCode: Int, body: String?): ResponseActions {
        return ResponseActions(Response(statusCode, body))
    }
}