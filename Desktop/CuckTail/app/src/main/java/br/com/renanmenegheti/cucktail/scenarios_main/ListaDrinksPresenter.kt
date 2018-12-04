package br.com.renanmenegheti.cucktail.scenarios_main

import br.com.renanmenegheti.cucktail.entities.DrinkList
import br.com.renanmenegheti.cucktail.network.RetrofitInicializer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class ListaDrinksPresenter(val view: ListaDrinksContract.View): ListaDrinksContract.Presenter {

    // função para carregar a lista apenas com os Non Alcoholics
    override fun carregaListaNonAlcoholic(){
        view.showLoading()

        val drinksService = RetrofitInicializer().createDrinksService()

        var call = drinksService.getDrinksByAlcoholic("Non_Alcoholic")

        call.enqueue(object : Callback<DrinkList> {
            override fun onResponse(call: Call<DrinkList>, response: Response<DrinkList>) {
                view.hideLoading()

                if(response.body()!= null){
                    view.showList(response.body()!!.drinks)
                } else{
                    view.showMessage("Sem drinks para hoje.")
                }
            }

            override fun onFailure(call: Call<DrinkList>, t: Throwable) {
                view.hideLoading()
                view.showMessage("Falha na conexao")
            }


        })
    }

    override fun carregaListaOptionalAlcohol(){

        view.showLoading()

        val drinksService = RetrofitInicializer().createDrinksService()

        var call = drinksService.getDrinksByAlcoholic("Optional_Alcohol")

        call.enqueue(object : Callback<DrinkList> {
            override fun onResponse(call: Call<DrinkList>, response: Response<DrinkList>) {
                view.hideLoading()

                if(response.body()!= null){
                    view.showList(response.body()!!.drinks)
                } else{
                    view.showMessage("Sem drinks para hoje.")
                }
            }

            override fun onFailure(call: Call<DrinkList>, t: Throwable) {
                view.hideLoading()
                view.showMessage("Falha na conexao")
            }


        })

    }

    override fun carregaListaAlcoholic(){

        view.showLoading()

        val drinksService = RetrofitInicializer().createDrinksService()

        var call = drinksService.getDrinksByAlcoholic("Alcoholic")

        call.enqueue(object : Callback<DrinkList> {
            override fun onResponse(call: Call<DrinkList>, response: Response<DrinkList>) {
                view.hideLoading()

                if(response.body()!= null){
                    view.showList(response.body()!!.drinks)
                } else{
                    view.showMessage("Sem drinks para hoje.")
                }
            }

            override fun onFailure(call: Call<DrinkList>, t: Throwable) {
                view.hideLoading()
                view.showMessage("Falha na conexao")
            }


        })

    }




}