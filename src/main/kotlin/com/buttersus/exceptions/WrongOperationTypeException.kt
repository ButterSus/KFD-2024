package com.buttersus.exceptions

class WrongOperationTypeException(
    operation: String,
) : Exception("Wrong operation type: \"${operation}\"")