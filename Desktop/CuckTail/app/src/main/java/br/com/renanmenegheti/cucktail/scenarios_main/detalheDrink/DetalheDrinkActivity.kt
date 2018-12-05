package br.com.renanmenegheti.cucktail.scenarios_main.detalheDrink

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ProgressBar
import android.widget.Toast
import br.com.renanmenegheti.cucktail.AppDatabase
import br.com.renanmenegheti.cucktail.R
import br.com.renanmenegheti.cucktail.entities.Drink
import br.com.renanmenegheti.cucktail.entities.DrinkList
import br.com.renanmenegheti.cucktail.utils.GlideApp
import br.com.renanmenegheti.cucktail.utils.MyAppGlideModule
import kotlinx.android.synthetic.main.activity_detalhe_drink.*
import kotlinx.android.synthetic.main.activity_lista_drinks.*
import org.jetbrains.anko.doAsync

class DetalheDrinkActivity : AppCompatActivity(), DetalheDrinkContract.View {


    var drink: Drink? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_drink)



        val presenter : DetalheDrinkContract.Presenter = DetalheDrinkPresenter(this)

        if (intent.getStringExtra("idDrink") == null)
        {
            presenter.getDrinkAleatorio()
        } else {
            presenter.getDrink(intent.getStringExtra("idDrink"))
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detalhe_drink, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when(item!!.itemId){
            R.id.menuFavoritar -> salvaDrink()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun salvaDrink() {
        val drinkDao = AppDatabase.getInstance(this).drinkDao()
        doAsync {
            drinkDao.insert(drink!!)
        }



    }


    override fun showDrink(drinks: List<Drink>) {

        drink = drinks[0]

        tvNomeDrinkDetalhe.text = "Nome do Drink: ${drinks[0].strDrink}"
        tvModoPreparo.text = "Modo de Preparo: ${drinks[0].strInstructions}"
        if (drinks[0].strIngredient1 != ""){

            tvIngredientes.text = "Ingredientes:\n${drinks[0].strMeasure1} ${drinks[0].strIngredient1}"

            if (drinks[0].strIngredient2 != ""){

                tvIngredientes.text = "${tvIngredientes.text}\n${drinks[0].strMeasure2} ${drinks[0].strIngredient2}"

                if (drinks[0].strIngredient3 != ""){

                    tvIngredientes.text = "${tvIngredientes.text}\n${drinks[0].strMeasure3} ${drinks[0].strIngredient3}"

                    if (drinks[0].strIngredient4 != ""){

                        tvIngredientes.text = "${tvIngredientes.text}\n${drinks[0].strMeasure4} ${drinks[0].strIngredient4}"

                        if (drinks[0].strIngredient5 != ""){

                            tvIngredientes.text = "${tvIngredientes.text}\n${drinks[0].strMeasure5} ${drinks[0].strIngredient5}"

                            if (drinks[0].strIngredient6 != ""){

                                tvIngredientes.text = "${tvIngredientes.text}\n${drinks[0].strMeasure6} ${drinks[0].strIngredient6}"

                                if (drinks[0].strIngredient7 != ""){

                                    tvIngredientes.text = "${tvIngredientes.text}\n${drinks[0].strMeasure7} ${drinks[0].strIngredient7}"

                                    if (drinks[0].strIngredient8 != ""){

                                        tvIngredientes.text = "${tvIngredientes.text}\n${drinks[0].strMeasure8} ${drinks[0].strIngredient8}"

                                        if (drinks[0].strIngredient9 != ""){

                                            tvIngredientes.text = "${tvIngredientes.text}\n${drinks[0].strMeasure9} ${drinks[0].strIngredient9}"

                                        }

                                    }

                                }

                            }
                        }
                    }
                }
            }

        }



        GlideApp.with(this)
            .load(drinks[0].strDrinkThumb)
            .placeholder(R.mipmap.ic_broken_image)
            .centerCrop()
            .into(imgDrink)


    }

    override fun showMessage(mensagem: String) {
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show()
    }


    override fun showLoading() {
        pbCarregandoDetalheDrink.visibility = ProgressBar.VISIBLE
    }

    override fun hideLoading() {
        pbCarregandoDetalheDrink.visibility = ProgressBar.INVISIBLE
    }
}
