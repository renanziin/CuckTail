package br.com.renanmenegheti.cucktail

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import br.com.renanmenegheti.cucktail.entities.Drink

@Database(entities = arrayOf(Drink::class),version = 1)
public abstract class AppDatabase: RoomDatabase() {


    companion object {

        private val DB_NAME = "drink.db"
        private var instance: AppDatabase? = null

        private fun create(context: Context): AppDatabase?{

            return Room.databaseBuilder(context, AppDatabase::class.java,DB_NAME).build()

        }

        public fun getInstance(context: Context): AppDatabase{

            if (instance == null){
                instance = create(context)
            }

            return instance!!

        }

    }



    public abstract fun drinkDao(): DrinkDao


}