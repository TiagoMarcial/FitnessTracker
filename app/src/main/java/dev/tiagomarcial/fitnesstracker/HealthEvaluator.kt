package dev.tiagomarcial.fitnesstracker

object HealthEvaluator {
    fun imcResponse(imc: Double): Int = when {
        imc < 15.0 -> R.string.imc_severely_low_weight
        imc < 16.0 -> R.string.imc_very_low_weight
        imc < 18.5 -> R.string.imc_low_weight
        imc < 25.0 -> R.string.imc_normal
        imc < 30.0 -> R.string.imc_hight_weight
        imc < 35.0 -> R.string.imc_so_hight_weight
        imc < 40.0 -> R.string.imc_severely_hight_weight
        else -> R.string.imc_extreme_weight
    }

    fun tmbResponse(tmb: Double): Int = when {
        tmb < 1200 -> R.string.tmb_severely_low_weight
        tmb < 1499 -> R.string.tmb_low_weight
        tmb < 1799 -> R.string.tmb_normal
        tmb < 2200 -> R.string.tmb_hight_weight
        else -> R.string.tmb_severely_hight_weight
    }
}
