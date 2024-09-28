package com.buttersus.exceptions

class BadFigureException(
    figure: String
) : Exception("Figure is invalid: \"${figure}\"")
