package br.com.renanmenegheti.cucktail.scenarios.listaDrinks

import android.content.Context
import br.com.renanmenegheti.cucktail.entities.Drink

interface ListaDrinksContract {

    interface View{

        abstract fun showMessage(mensagem: String)
        abstract fun showList(drinks: List<Drink>)
        abstract fun showLoading()
        abstract fun hideLoading()

    }

    interface Presenter{

        abstract fun onGetListaAlcoholic()
        abstract fun onGetDrinksSalvos(context: Context)
        abstract fun onLimparDrinksSalvos(context: Context)
    }

}