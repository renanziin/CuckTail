package br.com.renanmenegheti.cucktail.network

import br.com.renanmenegheti.cucktail.entities.DrinkList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DrinksService {

    @GET("filter.php")
    fun getDrinksByAlcoholic(@Query("a") alcoholic: String): Call<DrinkList>

    @GET("lookup.php")
    fun getDrinksById(@Query("i") id: String): Call<DrinkList>

    @GET("random.php")
    fun getRandomDrink(): Call<DrinkList>


}