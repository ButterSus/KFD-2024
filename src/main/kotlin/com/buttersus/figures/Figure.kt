package com.buttersus.figures

// Inheritors must be data classes
sealed class Figure {
    // Radius or side
    abstract val property: Double
}