package br.com.renanmenegheti.cucktail.scenarios_main.detalheDrink

import br.com.renanmenegheti.cucktail.entities.Drink
import br.com.renanmenegheti.cucktail.entities.DrinkList

interface DetalheDrinkContract {

    interface View{

        abstract fun showLoading()
        abstract fun showMessage(mensagem: String)
        fun hideLoading()
        abstract fun showDrink(drinks: List<Drink>)

    }

    interface Presenter{

        abstract fun getDrink(id: String)
        abstract fun getDrinkAleatorio()
        abstract fun salvaDrinkDispositivo(drink: Drink)


    }

}