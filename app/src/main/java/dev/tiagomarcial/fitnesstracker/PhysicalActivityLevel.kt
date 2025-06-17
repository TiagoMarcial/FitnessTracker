package dev.tiagomarcial.fitnesstracker

enum class PhysicalActivityLevel(val multiplier: Double) {
    Sedentary(1.2),
    Light(1.375),
    Moderate(1.55),
    Active(1.725),
    Extreme(1.9);

    companion object {
        fun fromLabel(level: String): PhysicalActivityLevel {
            return when (level) {

                "Sedentário" -> PhysicalActivityLevel.Sedentary

                "Leve" -> PhysicalActivityLevel.Light

                "Moderado" -> PhysicalActivityLevel.Moderate

                "Ativo" -> PhysicalActivityLevel.Active

                "Extremo" -> PhysicalActivityLevel.Extreme

                else -> Sedentary
            }
        }
    }
}