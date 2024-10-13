package com.buttersus.dsl

import com.buttersus.exceptions.BodyResponseMatchersException

@Suppress("unused")
class BodyMatchers(
    private val body: String?
) {
    fun isNull() {
        if (body != null) {
            throw BodyResponseMatchersException("Expected body to be null, but was: $body")
        }
    }

    fun isNotNull() {
        if (body == null) {
            throw BodyResponseMatchersException("Expected body to be not null, but was null")
        }
    }
}
