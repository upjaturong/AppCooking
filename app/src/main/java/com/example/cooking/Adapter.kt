package com.example.cooking


import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_information.view.*
import kotlinx.android.synthetic.main.model.view.*
import android.content.Context as Context1


class Adapter (
    var Namefood :Array<String>,
    var Imgfood : Array<Int>,
    var Price : Array<Int>,
    var context: Context1
    ) : RecyclerView.Adapter<Adapter.ViewHolderl>()  {

    class ViewHolderl(view: View): RecyclerView.ViewHolder(view) {
        var price = view.promo_price
        var namefoods = view.namefoods
        var Imagefood = view.foodImg
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderl {
        var view = LayoutInflater.from(context).inflate(R.layout.model,parent,false)
        return ViewHolderl(view)
    }
    override fun getItemCount(): Int {
        return Namefood.size
    }
    override fun onBindViewHolder(holder: ViewHolderl, position: Int) {
        holder.namefoods.text = Namefood[position]
        holder.price.setText(Price[position])
        holder.Imagefood?.setBackgroundResource(Imgfood[position])
        holder.itemView.setOnClickListener {
            Toast.makeText(context, "Click: "+Namefood.get(position), Toast.LENGTH_LONG).show()
            var intent = Intent(context, InformationActivity::class.java)
            intent.putExtra("NAMEFOOD",""+ Namefood.get(position))
            intent.putExtra("IMAGE",Imgfood.get(position))
            intent.putExtra("TEXTFOOD",Price.get(position))
            context.startActivity(intent)
        }

    }
}







