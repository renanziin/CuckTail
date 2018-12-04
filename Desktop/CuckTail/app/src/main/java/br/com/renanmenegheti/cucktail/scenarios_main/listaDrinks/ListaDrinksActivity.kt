package br.com.renanmenegheti.cucktail.scenarios_main.listaDrinks

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.ProgressBar
import android.widget.Toast
import br.com.renanmenegheti.cucktail.R
import br.com.renanmenegheti.cucktail.entities.Drink
import kotlinx.android.synthetic.main.activity_lista_drinks.*

class ListaDrinksActivity : AppCompatActivity(),
    ListaDrinksContract.View {


    var listaTodosDrinks: MutableList<Drink> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_drinks)


        val presenter: ListaDrinksContract.Presenter =
            ListaDrinksPresenter(this)

        presenter.carregaListaAlcoholic()



    }

    override fun showList(drinks: List<Drink>) {



        Log.d("eoq",drinks.toString())



        val adapter = DrinkAdapter(this, drinks)
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
