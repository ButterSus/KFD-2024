package com.buttersus.dsl

import com.buttersus.exceptions.StatusResponseMatchersException

@Suppress("unused")
class StatusMatchers(
    private val statusCode: Int
) {
    fun isOk() {
        if (statusCode != 200) {
            throw StatusResponseMatchersException("Expected status 200, but was $statusCode")
        }
    }

    fun isBadRequest() {
        if (statusCode != 400) {
            throw StatusResponseMatchersException("Expected status 400, but was $statusCode")
        }
    }

    fun isInternalServerError() {
        if (statusCode != 500) {
            throw StatusResponseMatchersException("Expected status 500, but was $statusCode")
        }
    }
}

