package br.com.renanmenegheti.cucktail.scenarios_main.listaDrinks

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ProgressBar
import android.widget.Toast
import br.com.renanmenegheti.cucktail.AppDatabase
import br.com.renanmenegheti.cucktail.DrinkDao
import br.com.renanmenegheti.cucktail.R
import br.com.renanmenegheti.cucktail.entities.Drink
import br.com.renanmenegheti.cucktail.scenarios_main.detalheDrink.DetalheDrinkActivity
import br.com.renanmenegheti.cucktail.scenarios_main.detalheDrink.DetalheDrinkContract
import kotlinx.android.synthetic.main.activity_lista_drinks.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class ListaDrinksActivity : AppCompatActivity(),
    ListaDrinksContract.View {






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_drinks)


        val presenter: ListaDrinksContract.Presenter = ListaDrinksPresenter(this)

        presenter.getListaAlcoholic()


        btnDrinkAleatorio.setOnClickListener(){
            val buscarDrinkAleatorio = Intent(this, DetalheDrinkActivity::class.java)
            startActivity(buscarDrinkAleatorio)

        }

        btnDrinksSalvos.setOnClickListener(){

            val drinkDao = AppDatabase.getInstance(this).drinkDao()

            var listaDrinks = mutableListOf<Drink>()

            doAsync {
                listaDrinks = drinkDao.getAll() as MutableList<Drink>

                uiThread {
                    val adapter = DrinkAdapter(it, listaDrinks)
                    adapter.setOnClickListener {position ->
                        val abrirActivityDetalheDrink = Intent(it, DetalheDrinkActivity::class.java)
                        abrirActivityDetalheDrink.putExtra("idDrink", listaDrinks[position].idDrink)
                        startActivity(abrirActivityDetalheDrink)
                    }


                    rvListaDrinks.adapter = adapter
                    rvListaDrinks.layoutManager = LinearLayoutManager(it)
                }
            }





        }

        btnDrinksCocktailDB.setOnClickListener(){

            showLoading()

            val presenter: ListaDrinksContract.Presenter = ListaDrinksPresenter(this)



            presenter.getListaAlcoholic()



        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_lista_drinks,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            R.id.menuLimparDrinksSalvos -> limparDrinksSalvos()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun limparDrinksSalvos() {

        val drinkDao = AppDatabase.getInstance(this).drinkDao()


        doAsync {
            drinkDao.limparDrinksSalvos()

        }

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
