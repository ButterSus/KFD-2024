package com.buttersus.services

enum class Operation(
    private val matchRegex: Regex
) {
    INSERT(Regex("insert", RegexOption.IGNORE_CASE)),
    GET_PERIMETER(Regex("get[_ ]?perimeter", RegexOption.IGNORE_CASE)),
    GET_AREA(Regex("get[_ ]?area", RegexOption.IGNORE_CASE)),
    EXIT(Regex("exit", RegexOption.IGNORE_CASE));

    companion object {
        fun getOperation(input: String): Operation? {
            return entries.find { it.matchRegex.matches(input) }
        }
    }
}