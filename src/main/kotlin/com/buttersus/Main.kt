package com.buttersus

import com.buttersus.services.ConsoleService
import com.buttersus.services.ConsoleServiceImpl

fun main() {
    // Launch CLI service
    val consoleService : ConsoleService = ConsoleServiceImpl
    consoleService.work()
}
