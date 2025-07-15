package dev.tiagomarcial.fitnesstracker.model

enum class Gender {
    Male, Female;

    companion object {
        fun selectedGender(gender: String): Gender {
            return when (gender) {
                "Masculino" -> Male
                "Feminino" -> Female
                else -> Male
            }
        }
    }
}