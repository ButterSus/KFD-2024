package com.buttersus.services

interface FigureService {
    fun addFigure(property: Double, figure: String)
    fun getPerimeter(): Double
    fun getArea(): Double
}