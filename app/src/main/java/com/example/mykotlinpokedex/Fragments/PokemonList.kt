package com.example.mykotlinpokedex.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mykotlinpokedex.Adapters.PokemonAdapter
import com.example.mykotlinpokedex.Activities.MainActivity
import com.example.mykotlinpokedex.Models.PokemonCatched
import com.example.mykotlinpokedex.Models.PokemonModel
import com.example.mykotlinpokedex.Models.ability
import com.example.mykotlinpokedex.R
import com.example.mykotlinpokedex.Utils.ItemOffsetDecoration
import com.example.mykotlinpokedex.Utils.Util
import kotlinx.android.synthetic.main.fragment_pokemon_list.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PokemonList.newInstance] factory method to
 * create an instance of this fragment.
 */
class PokemonList : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var pokemon_listview: RecyclerView
    var pokemonList = ArrayList<PokemonModel>()
    var pokemonDetails = ArrayList<PokemonCatched>()
    private  var adapter: PokemonAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment PokedexList
        val itemView = inflater.inflate(R.layout.fragment_pokemon_list, container, false)

        pokemon_listview = itemView.findViewById(R.id.PokedexList) as RecyclerView
        pokemon_listview.layoutManager = GridLayoutManager(activity, 2)
        pokemon_listview.setHasFixedSize(true)
        val itemDecoration = ItemOffsetDecoration(activity!!,R.dimen.spacing)
        pokemon_listview.addItemDecoration(itemDecoration)
        adapter = PokemonAdapter(activity!!, pokemonList)
        pokemon_listview.adapter = adapter// set adapter

        LoadMyPokedex()

        return itemView
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PokemonList.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PokemonList().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    //Function LoadPokedex(With name of pokemon´s
    private fun LoadMyPokedex() {
        //bulbasaur name=overgrow,chlorophyll
        //ivysaur name=overgrow,chlorophyll
        //venusaur name=overgrow,chlorophyll
        //charmander name=blaze,solar-power
        //charmeleon name=overgrow,chlorophyll
        //charizard name=overgrow,chlorophyll
        //squirtle name=torrent,rain-dish
        //wartortle name=torrent,rain-dish
        //blastoise name=torrent,rain-dish
        //caterpie name=shield-dust","run-away"
        //eevee name=run-away,adaptability,anticipation"
        //snorlax name=immunity,thick-fat,gluttony

        //Data class con los objetos de tipo pokemon
        pokemonDetails.add( PokemonCatched("1","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png","bulbasaur",7,69,"grass"))
        pokemonDetails.add( PokemonCatched("2","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/2.png",  "ivysaur",10,130,"grass"))
        pokemonDetails.add( PokemonCatched("3","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/3.png",  "venusaur",20,1000,"grass"))
        pokemonDetails.add( PokemonCatched("4","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/4.png",  "charmander",6,85,"fire"))
        pokemonDetails.add( PokemonCatched("5","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/5.png",  "charmeleon",11,190,"fire"))
        pokemonDetails.add( PokemonCatched("6","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/6.png",  "charizard",17,905,"fire"))
        pokemonDetails.add( PokemonCatched("7","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/7.png",  "squirtle",5,90,"water"))
        pokemonDetails.add( PokemonCatched("8","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/8.png",  "wartortle",10,225,"water"))
        pokemonDetails.add( PokemonCatched("9","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/9.png",  "blastoise",16,855,"water"))
        pokemonDetails.add( PokemonCatched("10","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/10.png",  "caterpie",3,29,"bug"))
        pokemonDetails.add( PokemonCatched("133","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/133.png",  "eevee",3,65,"normal"))
        pokemonDetails.add( PokemonCatched("143","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/143.png",  "snorlax",21,4600,"normal"))

        //Nombres en el Pokedex
        pokemonList.add( PokemonModel("1",R.drawable.pokebola,  "bulbasaur"))
        pokemonList.add( PokemonModel("2",R.drawable.pokebola,  "ivysaur"))
        pokemonList.add( PokemonModel("3",R.drawable.pokebola,  "venusaur"))
        pokemonList.add( PokemonModel("4",R.drawable.pokebola, "charmander"))
        pokemonList.add( PokemonModel("5",R.drawable.pokebola,  "charmeleon"))
        pokemonList.add( PokemonModel("6",R.drawable.pokebola,  "charizard"))
        pokemonList.add( PokemonModel("7",R.drawable.pokebola,  "squirtle"))
        pokemonList.add( PokemonModel("8",R.drawable.pokebola,  "wartortle"))
        pokemonList.add( PokemonModel("9",R.drawable.pokebola,  "blastoise"))
        pokemonList.add( PokemonModel("10",R.drawable.pokebola,  "caterpie"))
        pokemonList.add( PokemonModel("11",R.drawable.pokebola,  "eevee"))
        pokemonList.add( PokemonModel("12",R.drawable.pokebola,  "snorlax"))

        adapter!!.notifyDataSetChanged()

        //Igualamos al arreglo global declarado en Utils con el arreglo pokemonList
        Util.globalarray = pokemonDetails
    }
}