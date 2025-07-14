package dev.tiagomarcial.fitnesstracker.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CalcDao {

    @Insert
    fun insert(calc: Calc)

    @Query("SELECT * FROM Calc WHERE type = :type")
    fun getRegisterByType(type: String) : List<Calc>

    @Query("SELECT * FROM calc WHERE id = :id")
    fun getById(id: Int): Calc

    @Update
    fun update(calc: Calc)

    @Delete
    fun delete(calc: Calc)

}