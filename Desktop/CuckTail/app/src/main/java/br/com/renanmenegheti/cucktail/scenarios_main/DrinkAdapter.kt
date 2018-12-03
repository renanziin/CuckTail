package br.com.renanmenegheti.cucktail.scenarios_main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.renanmenegheti.cucktail.R
import br.com.renanmenegheti.cucktail.entities.Drink
import br.com.renanmenegheti.cucktail.utils.GlideApp
import kotlinx.android.synthetic.main.drinks_item.view.*

class DrinkAdapter(val context: Context, val drinks: List<Drink>): RecyclerView.Adapter<DrinkAdapter.ViewHolder>() {

    var itemClickListener: ((index: Int) -> Unit)? = null

    fun setOnClickListener(clique: ((index: Int) -> Unit)){
        this.itemClickListener = clique
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.drinks_item,parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return drinks.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(context, drinks[position], itemClickListener)
    }



    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        fun bindView(context: Context, drink: Drink, itemClickListener: ((index: Int) -> Unit)?) {
            itemView.tvNomeDrink.text = drink.strDrink

            GlideApp.with(context)
                .load(drink.strDrinkThumb)
                .placeholder(R.mipmap.ic_broken_image)
                .centerCrop()
                .into(itemView.imgDrink)

            if(itemClickListener != null) {
                itemView.setOnClickListener {
                    itemClickListener.invoke(adapterPosition)
                }
            }

        }

    }
}