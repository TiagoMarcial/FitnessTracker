package dev.tiagomarcial.fitnesstracker.utils.validators

object InputValidator {
    fun validate(input: String): Boolean {
        return !input.isNullOrEmpty()
    }
}