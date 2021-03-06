package com.example.mykotlinpokedex.Adapters

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mykotlinpokedex.Interfaces.IItemClickListener
import com.example.mykotlinpokedex.Models.PokemonModel
import com.example.mykotlinpokedex.R
import com.example.mykotlinpokedex.Utils.Util
import kotlinx.android.synthetic.main.pokemon_list_row.view.*


class PokemonAdapter (internal var context: Context,
                      internal var Pokemon: ArrayList<PokemonModel>
) : RecyclerView.Adapter<PokemonAdapter.MyViewHolder>(){
    //private val mContext: Context = context

    // Allows to remember the last item shown on screen
    private var lastPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder{
        val itemView = LayoutInflater.from(context).inflate(R.layout.pokemon_list_row,parent,false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return Pokemon.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int){
        var indexID : Int
        indexID = position
        val model = Pokemon[position]
        holder.pokemonName!!.text = model.pokemonName
        holder.imagePokemon!!.setImageResource(model.pokemonIMG)
        holder.imagePokemon!!.drawable
        holder.itemView.setOnClickListener {
            holder.itemView.NamePokemon.setTextColor(Color.GREEN)
            //Toast.makeText(context, "Clicked at Pokemon: "+Pokemon[position].pokemonName,Toast.LENGTH_SHORT).show() //Funciona el click aquí
            LocalBroadcastManager.getInstance(context)
                .sendBroadcast(Intent(Util.KEY_ENABLE_HOME).putExtra("position",indexID))
        }
        //No funciona el click con la interface
        /*holder.setItemClickListener(object:IItemClickListener{
            override fun onClick(view: View, position: Int) {
            }
        })*/
        // Here you apply the animation when the view is bound
        setAnimation(holder.itemView, position);
    }

    inner class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        internal var imagePokemon = itemView.imagePokemon
        internal var pokemonName = itemView.NamePokemon

        internal var itemClickListener:IItemClickListener?=null

        fun setItemClickListener(IItemClickListener: IItemClickListener){
            this.itemClickListener = itemClickListener
        }

        init {
            itemView.setOnClickListener { view -> itemClickListener!!.onClick(view,adapterPosition)}
        }
    }

    /**
     * Here is the key method to apply the animation
     */
    private fun setAnimation(viewToAnimate: View, position: Int) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            val animation: Animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left)
            viewToAnimate.startAnimation(animation)
            lastPosition = position
        }
    }

}