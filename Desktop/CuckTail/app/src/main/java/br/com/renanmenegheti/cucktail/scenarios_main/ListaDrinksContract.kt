package br.com.renanmenegheti.cucktail.scenarios_main

import br.com.renanmenegheti.cucktail.entities.Drink

interface ListaDrinksContract {

    interface View{
        abstract fun showMessage(mensagem: String)
        abstract fun showList(drinks: List<Drink>)
        abstract fun showLoading()
        abstract fun hideLoading()


    }

    interface Presenter{

//        fun onLoadList()

        fun carregaListaNonAlcoholic()
        fun carregaListaOptionalAlcohol()
        fun carregaListaAlcoholic()
    }

}