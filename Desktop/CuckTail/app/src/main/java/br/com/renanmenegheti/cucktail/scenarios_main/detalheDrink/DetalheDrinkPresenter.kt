package br.com.renanmenegheti.cucktail.scenarios_main.detalheDrink

import br.com.renanmenegheti.cucktail.entities.Drink
import br.com.renanmenegheti.cucktail.entities.DrinkList
import br.com.renanmenegheti.cucktail.network.RetrofitInicializer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class DetalheDrinkPresenter(val view: DetalheDrinkContract.View): DetalheDrinkContract.Presenter{


    override fun salvaDrinkDispositivo(drink: Drink) {

    }


    override fun getDrinkAleatorio() {

        view.showLoading()

        val drinkService = RetrofitInicializer().createDrinksService()

        var call = drinkService.getRandomDrink()

        call.enqueue(object: Callback<DrinkList>{

            override fun onFailure(call: Call<DrinkList>, t: Throwable) {
                view.hideLoading()
                view.showMessage("Falha na conexao")
            }

            override fun onResponse(call: Call<DrinkList>, response: Response<DrinkList>) {
                view.hideLoading()
                if (response.body() != null){
                    view.showDrink(response.body()!!.drinks)
                } else{
                    view.showMessage("Ocorreu um problema com o Drink")
                }
            }

        })

    }


    override fun getDrink(id: String) {

        view.showLoading()

        val drinkService = RetrofitInicializer().createDrinksService()

        var call = drinkService.getDrinksById(id)

        call.enqueue(object: Callback<DrinkList>{

            override fun onFailure(call: Call<DrinkList>, t: Throwable) {
                view.hideLoading()
                view.showMessage("Falha na conexao")
            }

            override fun onResponse(call: Call<DrinkList>, response: Response<DrinkList>) {
                view.hideLoading()
                if (response.body() != null){
                    view.showDrink(response.body()!!.drinks)
                } else{
                    view.showMessage("Ocorreu um problema com o Drink")
                }
            }

        })

    }


}