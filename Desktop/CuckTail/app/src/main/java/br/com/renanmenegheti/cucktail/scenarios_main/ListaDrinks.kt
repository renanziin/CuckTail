package br.com.renanmenegheti.cucktail.scenarios_main

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import br.com.renanmenegheti.cucktail.R
import br.com.renanmenegheti.cucktail.entities.Drink
import kotlinx.android.synthetic.main.activity_lista_drinks.*

class ListaDrinks : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_drinks)


        val d1 = Drink("1", "Vodka Meme", "Trollar muito até ficar bom",
            "lmao", "yikes", "ayy", "","","",
            "2", "4", "17", "","","",
            "https://www.thecocktaildb.com/images/media/drink/xwxyux1441254243.jpg")

        val d2 = Drink("2", "Vodka Fizz", "tilt",
            "lmao", "yikes", "ayy", "","","",
            "2", "4", "17", "","","",
            "https://www.thecocktaildb.com/images/media/drink/wpxpvu1439905379.jpg")

        val testList = listOf(d1, d2)

        exibeLista(testList)
    }

    fun exibeLista(list: List<Drink>) {

        val adapter = DrinkAdapter(this, list)
        adapter.setOnClickListener {position ->
            val openBrowser = Intent(Intent.ACTION_VIEW)
            openBrowser.data = Uri.parse(list.get(position).strDrinkThumb)
            startActivity(openBrowser)
        }

        rvListaDrinks.adapter = adapter
        rvListaDrinks.layoutManager = LinearLayoutManager(this)
    }
}
