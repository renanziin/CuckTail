package br.com.renanmenegheti.cucktail.scenarios.detalheDrink

import android.content.Context
import br.com.renanmenegheti.cucktail.database.AppDatabase
import br.com.renanmenegheti.cucktail.entities.Drink
import br.com.renanmenegheti.cucktail.entities.DrinkList
import br.com.renanmenegheti.cucktail.network.RetrofitInicializer
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetalheDrinkPresenter(val view: DetalheDrinkContract.View): DetalheDrinkContract.Presenter{



    override fun onSalvaDrinkDispositivo(context: Context, drink: Drink) {


        val drinkDao = AppDatabase.getInstance(context).drinkDao()
        doAsync {
            drinkDao.insert(drink)
        }




    }


    override fun onGetDrinkAleatorio() {

        view.showLoading()

        val drinkService = RetrofitInicializer().createDrinksService()

        val call = drinkService.getRandomDrink()

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


    override fun onGetDrink(id: String) {

        view.showLoading()

        val drinkService = RetrofitInicializer().createDrinksService()

        val call = drinkService.getDrinksById(id)

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