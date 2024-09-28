package com.buttersus.services

import com.buttersus.exceptions.BadPropertyException
import com.buttersus.exceptions.WrongOperationTypeException
import com.buttersus.figures.*
import kotlin.math.*

object FigureServiceImpl : FigureService {
    // Even though it's immutable (val), its fields are mutable
    private val figures = mutableListOf<Figure>()

    override fun addFigure(property: Double, figure: String) {
        if (property <= 0.0 || property.isNaN()) throw BadPropertyException(property)
        when (figure.lowercase()) {
            "circle" -> figures.add(Circle(property))
            "square" -> figures.add(Square(property))
            else -> throw WrongOperationTypeException(figure)
        }
    }

    override fun getPerimeter(): Double {
        return figures.sumOf {
            when (it) {
                is Circle -> 2 * PI * it.property
                is Square -> 4 * it.property
            }
        }
    }

    override fun getArea(): Double {
        return figures.sumOf {
            when (it) {
                is Circle -> PI * it.property.pow(2.0)
                is Square -> it.property.pow(2.0)
            }
        }
    }
}