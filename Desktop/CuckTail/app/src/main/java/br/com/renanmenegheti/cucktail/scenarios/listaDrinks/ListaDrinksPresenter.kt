package br.com.renanmenegheti.cucktail.scenarios.listaDrinks

import android.content.Context
import br.com.renanmenegheti.cucktail.database.AppDatabase
import br.com.renanmenegheti.cucktail.entities.Drink
import br.com.renanmenegheti.cucktail.entities.DrinkList
import br.com.renanmenegheti.cucktail.network.RetrofitInicializer
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListaDrinksPresenter(val view: ListaDrinksContract.View): ListaDrinksContract.Presenter {

    override fun onLimparDrinksSalvos(context: Context) {

        val drinkDao = AppDatabase.getInstance(context).drinkDao()

        doAsync {
            drinkDao.limparDrinksSalvos()

        }

    }

    override fun onGetDrinksSalvos(context: Context) {

        val drinkDao = AppDatabase.getInstance(context).drinkDao()

        var listaDrinks: List<Drink>

        doAsync {
            listaDrinks = drinkDao.getAll()

            uiThread {
                view.showList(listaDrinks)
            }
        }

    }


    override fun onGetListaAlcoholic(){

        view.showLoading()

        val drinksService = RetrofitInicializer().createDrinksService()

        val call = drinksService.getDrinksByAlcoholic("Alcoholic")

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