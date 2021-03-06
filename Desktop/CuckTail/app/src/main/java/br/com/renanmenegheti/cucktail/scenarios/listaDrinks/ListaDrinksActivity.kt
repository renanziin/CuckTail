package br.com.renanmenegheti.cucktail.scenarios.listaDrinks

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.ProgressBar
import android.widget.Toast
import br.com.renanmenegheti.cucktail.R
import br.com.renanmenegheti.cucktail.entities.Drink
import br.com.renanmenegheti.cucktail.scenarios.detalheDrink.DetalheDrinkActivity
import kotlinx.android.synthetic.main.activity_lista_drinks.*

class ListaDrinksActivity : AppCompatActivity(),
    ListaDrinksContract.View {



    val presenter: ListaDrinksContract.Presenter = ListaDrinksPresenter(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_drinks)

        presenter.onGetListaAlcoholic()

        btnDrinkAleatorio.setOnClickListener(){

            val buscarDrinkAleatorio = Intent(this, DetalheDrinkActivity::class.java)
            startActivity(buscarDrinkAleatorio)
        }


        btnDrinksSalvos.setOnClickListener(){

            showLoading()
            presenter.onGetDrinksSalvos(this)

        }



        btnDrinksCocktailDB.setOnClickListener(){

            showLoading()
            presenter.onGetListaAlcoholic()

        }


    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_lista_drinks,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            R.id.menuLimparDrinksSalvos -> presenter.onLimparDrinksSalvos(this)
        }

        return super.onOptionsItemSelected(item)
    }



    override fun showList(drinks: List<Drink>) {

        val adapter = DrinkAdapter(this, drinks)
        adapter.setOnClickListener {position ->
            val abrirActivityDetalheDrink = Intent(this, DetalheDrinkActivity::class.java)
            abrirActivityDetalheDrink.putExtra("idDrink", drinks[position].idDrink)
            startActivity(abrirActivityDetalheDrink)
        }

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
