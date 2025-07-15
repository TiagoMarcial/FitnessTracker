package dev.tiagomarcial.fitnesstracker.utils.calculators

import dev.tiagomarcial.fitnesstracker.R
import dev.tiagomarcial.fitnesstracker.model.Gender

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

    fun getResponse (get: Double): Int = when {
        get < 1500 -> R.string.get_nivel_sedentary
        get < 1800 -> R.string.get_nivel_low
        get < 2200 -> R.string.get_nivel_normal
        get < 2800 -> R.string.get_nivel_hight
        else -> R.string.get_nivel_severely_hight
    }

    fun pgcResponse (pgc: Double, gender: Gender): Int = when {
        //man
        pgc < 10 && gender == Gender.Male -> R.string.pgc_man_excelent
        pgc < 14 && gender == Gender.Male -> R.string.pgc_man_good
        pgc < 19 && gender == Gender.Male -> R.string.pgc_man_acceptable
        pgc <= 24 && gender == Gender.Male -> R.string.pgc_man_bad
        pgc > 24 && gender == Gender.Male -> R.string.pgc_man_very_bad
        //woman
        pgc < 18 && gender == Gender.Female -> R.string.pgc_woman_excelent
        pgc < 22 && gender == Gender.Female -> R.string.pgc_woman_good
        pgc < 29 && gender == Gender.Female -> R.string.pgc_woman_acceptable
        pgc < 34 && gender == Gender.Female -> R.string.pgc_woman_bad
        else -> R.string.pgc_woman_very_bad

    }
}
