package br.com.renanmenegheti.cucktail.scenarios_main.listaDrinks

import br.com.renanmenegheti.cucktail.entities.DrinkList
import br.com.renanmenegheti.cucktail.network.RetrofitInicializer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListaDrinksPresenter(val view: ListaDrinksContract.View): ListaDrinksContract.Presenter {



    override fun getListaAlcoholic(){

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