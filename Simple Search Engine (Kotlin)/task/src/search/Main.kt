package search

import java.io.File
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    val people = File(args[1]).readLines()
    while (true) {
        when(menu()) {
            0 -> println("Bye!").also { exitProcess(0) }
            1 -> {
                val choice = readln().lowercase()
                val text = readln().lowercase().split(" ")
                println(people.search(text, choice))
            }
            else -> println(people.joinToString("\n"))
        }
    }
}

fun menu() : Int {
    while (true) {
        println("=== Menu ===\n" +
                "1. Find a person\n" +
                "2. Print all people\n" +
                "0. Exit")
        val input = readln()
        if (input !in listOf("1", "2", "0")) println(
            "Incorrect option! Try again."
        ) else return input.toInt()
    }
}

fun List<String>.search(text: List<String>, choice: String): String {
    val people = when (choice) {
        "all" -> {
            this
            .map { it.split(" ") }
            .filter { it.all { str -> str.lowercase() in text } }
        }
        "any" -> {
            this
                .map { it.split(" ") }
                .filter { it.any { str -> str.lowercase() in text } }
        }
        else -> {
            this
                .map { it.split(" ") }
                .filter { it.none { str -> str.lowercase() in text } }
        }
    }
    return if (people.isEmpty()) "No matching people found."
    else "People found:\n" +
            people.joinToString("\n") {
        it.joinToString(" ")
    }
}