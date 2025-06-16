package dev.tiagomarcial.fitnesstracker

enum class Gender {
    Male, Female;

    companion object {
        fun selectedGender(gender: String): Gender {
            return when (gender) {
                "Masculino" -> Gender.Male
                "Feminino" -> Gender.Female
                else -> Gender.Male
            }
        }
    }
}