package com.buttersus.exceptions

class BadPropertyException(
    property: Double
) : Exception("Property is invalid: \"${property}\"")