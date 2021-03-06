package com.example.mykotlinpokedex.Utils

import com.example.mykotlinpokedex.Models.PokemonCatched

object Util {
    fun findPokemonByNum(num: String?): PokemonCatched? {
        for(pokemon in Util.globalarray)
            if(pokemon.pokemonID.equals(num))
                return pokemon
                return null
    }

    var globalarray:List<PokemonCatched> = ArrayList()
    val KEY_ENABLE_HOME = "position"

}