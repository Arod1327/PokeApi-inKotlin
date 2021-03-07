package com.example.mykotlinpokedex.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mykotlinpokedex.Models.PokemonCatched
import com.example.mykotlinpokedex.R
import com.example.mykotlinpokedex.Utils.Util
import kotlinx.android.synthetic.main.fragment_pokemon_detail.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PokemonDetail.newInstance] factory method to
 * create an instance of this fragment.
 */
class PokemonDetail : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    internal lateinit var pokemon_img: ImageView
    internal lateinit var pokemon_name: TextView
    internal lateinit var pokemon_height: TextView
    internal lateinit var pokemon_weight: TextView
    internal lateinit var pokemon_type: TextView

    lateinit var recycler_ability:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val itemView = inflater.inflate(R.layout.fragment_pokemon_detail, container, false)

        val pokemon: PokemonCatched?
        if(arguments!!.getString("num") == null)
            pokemon = Util.globalarray[arguments!!.getInt("position")]
        else
            pokemon = Util.findPokemonByNum(arguments!!.getString("position"))

            pokemon_img = itemView.findViewById(R.id.pokemon_image)
            pokemon_name = itemView.findViewById(R.id.name) as TextView
            pokemon_height = itemView.findViewById(R.id.height) as TextView
            pokemon_weight = itemView.findViewById(R.id.weight) as TextView
            pokemon_type = itemView.findViewById(R.id.type) as TextView

        setDetailPokemon(pokemon)

        return itemView
    }

    private fun setDetailPokemon(pokemon: PokemonCatched?) {
        Glide.with(activity!!).load(pokemon!!.pokemonURL).into(pokemon_img)

        pokemon_name.text = pokemon.pokemonName
        pokemon_height.text = "Height: "+ pokemon.pokemonHeight +" m"
        pokemon_weight.text = "Weight: "+pokemon.pokemonWeight +" kg"
        pokemon_type.text = "Type: "+pokemon.pokemonType
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PokemonDetail.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                PokemonDetail().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}