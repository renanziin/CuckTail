package br.com.renanmenegheti.cucktail.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import br.com.renanmenegheti.cucktail.entities.Drink

@Dao
interface DrinkDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(drink: Drink)

    @Query("SELECT * FROM Drink")
    fun getAll(): List<Drink>

    @Query("DELETE FROM Drink")
    fun limparDrinksSalvos()


}