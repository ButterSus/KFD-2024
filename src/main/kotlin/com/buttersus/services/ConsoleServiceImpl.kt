package com.buttersus.services

import com.buttersus.exceptions.WrongOperationTypeException

object ConsoleServiceImpl : ConsoleService {
    private var isActive = true
    override fun work() {
        while (isActive) {
            print("Enter an operation: ")
            val operation = readln().trim()
            val operationType = Operation.getOperation(operation) ?: throw WrongOperationTypeException(operation)
            when (operationType) {
                Operation.INSERT -> {
                    print("Enter figure: ")
                    val figure = readln().trim()
                    print("Enter property: ")
                    val property = readln().trim().toDouble()
                    FigureServiceImpl.addFigure(property, figure)
                }
                Operation.GET_AREA -> println(FigureServiceImpl.getArea())
                Operation.GET_PERIMETER -> println(FigureServiceImpl.getPerimeter())
                Operation.EXIT -> isActive = false
            }
        }
        println("Terminating...")
    }
}