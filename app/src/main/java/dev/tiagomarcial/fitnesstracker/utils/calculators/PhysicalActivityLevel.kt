package dev.tiagomarcial.fitnesstracker.utils.calculators

enum class PhysicalActivityLevel(val multiplier: Double) {
    Sedentary(1.2),
    Light(1.375),
    Moderate(1.55),
    Active(1.725),
    Extreme(1.9);

    companion object {
        fun fromLabel(level: String): PhysicalActivityLevel {
            return when (level) {

                "Sedentário" -> Sedentary

                "Leve" -> Light

                "Moderado" -> Moderate

                "Ativo" -> Active

                "Extremo" -> Extreme

                else -> Sedentary
            }
        }
    }
}