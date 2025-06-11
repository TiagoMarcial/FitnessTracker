package dev.tiagomarcial.fitnesstracker

object Calculator {
    fun calculateImc(weight: Int, height: Int): Double {
        return weight / ( (height / 100.0) * (height / 100.0)  )
    }
}