package dev.tiagomarcial.fitnesstracker

enum class PhysicalActivityLevel (val multiplier: Double){
    Sedentary(1.2),
    Light (1.375),
    Moderate(1.55),
    Active(1.725),
    Extreme(1.9)
}