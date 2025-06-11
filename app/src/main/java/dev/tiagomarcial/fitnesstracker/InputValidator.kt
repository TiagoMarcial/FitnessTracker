package dev.tiagomarcial.fitnesstracker

object InputValidator {
    fun validate(input: String): Boolean {
        return !input.isNullOrEmpty()
    }
}