package br.com.renanmenegheti.cucktail.scenarios_main

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.webkit.ConsoleMessage
import android.widget.ProgressBar
import android.widget.Toast
import br.com.renanmenegheti.cucktail.R
import br.com.renanmenegheti.cucktail.entities.Drink
import kotlinx.android.synthetic.main.activity_lista_drinks.*

class ListaDrinksActivity : AppCompatActivity(), ListaDrinksContract.View {


    var listaTodosDrinks: MutableList<Drink> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_drinks)


        val presenter: ListaDrinksContract.Presenter = ListaDrinksPresenter(this)
        presenter.carregaListaOptionalAlcohol()
        presenter.carregaListaNonAlcoholic()
        presenter.carregaListaAlcoholic()

//        val d1 = Drink("1", "Vodka Meme", "Trollar muito até ficar bom",
//            "lmao", "yikes", "ayy", "","","",
//            "2", "4", "17", "","","",
//            "https://www.thecocktaildb.com/images/media/drink/xwxyux1441254243.jpg", "Alcoholic")
//
//        val d2 = Drink("2", "Vodka Fizz", "tilt",
//            "lmao", "yikes", "ayy", "","","",
//            "2", "4", "17", "","","",
//            "https://www.thecocktaildb.com/images/media/drink/wpxpvu1439905379.jpg", "Alcoholic")
//
//        val testList = listOf(d1, d2)
//
//        showList(testList)



    }

    override fun showList(drinks: List<Drink>) {



        listaTodosDrinks.addAll(drinks)


        Log.e("eoq",listaTodosDrinks.toString())



        val adapter = DrinkAdapter(this, listaTodosDrinks)
        adapter.setOnClickListener{position ->
            Toast.makeText(this, drinks.get(position).toString(), Toast.LENGTH_LONG).show()

        }

//        adapter.setOnClickListener {position ->
//            val openBrowser = Intent(Intent.ACTION_VIEW)
//            openBrowser.data = Uri.parse(drinks.get(position).strDrinkThumb)
//            startActivity(openBrowser)
//        }


        rvListaDrinks.adapter = adapter
        rvListaDrinks.layoutManager = LinearLayoutManager(this)
    }


    override fun showMessage(mensagem: String) {
        Toast.makeText(this, mensagem,Toast.LENGTH_SHORT).show()
    }


    override fun showLoading() {
        pbCarregando.visibility = ProgressBar.VISIBLE
    }

    override fun hideLoading() {
        pbCarregando.visibility = ProgressBar.INVISIBLE
    }

}
