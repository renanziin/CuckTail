package br.com.renanmenegheti.cucktail.scenarios.detalheDrink

import android.content.Context
import br.com.renanmenegheti.cucktail.entities.Drink

interface DetalheDrinkContract {

    interface View{

        abstract fun showLoading()
        abstract fun showMessage(mensagem: String)
        abstract fun hideLoading()
        abstract fun showDrink(drinks: List<Drink>)

    }

    interface Presenter{

        abstract fun onGetDrink(id: String)
        abstract fun onGetDrinkAleatorio()
        abstract fun onSalvaDrinkDispositivo(context: Context, drink: Drink)


    }

}