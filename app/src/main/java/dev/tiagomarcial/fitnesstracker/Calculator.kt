package dev.tiagomarcial.fitnesstracker

object Calculator {
    fun calculateImc(weight: Int, height: Int): Double {
        return weight / ( (height / 100.0) * (height / 100.0)  )
    }
    fun calculateTmb(weight: Int, height: Int, age: Int, gender: Gender): Double {
        return when (gender) {
            Gender.Male -> 88.36 + (13.4 * weight) + (4.8 * height) - (5.7 * age)
            Gender.Female -> 447.6 + (9.2 * weight) + (3.1 * height) - (4.3 * age)
        }
    }
    fun calculateGet( tmb: Double, nivel: PhysicalActivityLevel): Double {
        return tmb * nivel.multiplier
    }
    fun calculatePgc (imc: Double, age: Int, gender: Gender): Double {
        return when (gender) {
            Gender.Male -> (1.20 * imc) + (0.23 * age) - 16.2
            Gender.Female -> (1.20 * imc) + (0.23 * age) - 5.4
        }
    }
}