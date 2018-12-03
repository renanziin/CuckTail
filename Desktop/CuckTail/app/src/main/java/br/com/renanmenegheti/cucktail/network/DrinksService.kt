package br.com.renanmenegheti.cucktail.network

import retrofit2.http.GET

interface DrinksService {

    @GET("filter.php?a=Alcoholic")
    fun getAlcoholicDrinks(){



    }


}