package com.example.mykotlinpokedex.Activities

import android.content.*
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.mykotlinpokedex.Fragments.PokemonDetail
import com.example.mykotlinpokedex.R
import com.example.mykotlinpokedex.Utils.Util
import com.example.mykotlinpokedex.goToActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    lateinit var preferences: SharedPreferences
    internal lateinit var BtnLogOut: Button

    //Create Broadcast handle
    private val showDetail = object: BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent != null) {
                if(intent.action!!.toString() == Util.KEY_ENABLE_HOME){

                    supportActionBar!!.setDisplayHomeAsUpEnabled(true)
                    supportActionBar!!.setDisplayShowHomeEnabled(true)

                    //Replace Fragment
                    val detailFragment = PokemonDetail.newInstance("","")
                    val position = intent.getIntExtra("position",-1)
                    val bundle = Bundle()
                    bundle.putInt("position",position)
                    detailFragment.arguments = bundle

                    val fragmentTransaction = supportFragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.list_pokemon_fragment, detailFragment)
                    fragmentTransaction.addToBackStack("detail")
                    fragmentTransaction.commit()

                    //Set Pokemon Name for Toolbar
                    val pokemonselected = Util.globalarray[position]
                    toolbar.title = pokemonselected.pokemonName
                }
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar.setTitle("Pokemon´s Catched")
        setSupportActionBar(toolbar)

        //Register Broadcast
        LocalBroadcastManager.getInstance(this)
            .registerReceiver(showDetail, IntentFilter(Util.KEY_ENABLE_HOME))

        preferences= getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)

        BtnLogOut = findViewById<Button>(R.id.btnlogout)
        BtnLogOut.setOnClickListener{
            val email = preferences.getString("mailuser", "")
            val password = preferences.getString("passworduser", "")
            val editor: SharedPreferences.Editor = preferences.edit()
            editor.clear()
            editor.apply()
            goToActivity<LoginActivity>{
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item!!.itemId){
            android.R.id.home -> {
                toolbar.title = "Pokemon´s Catched"

                //Clear all fragment in stack with name 'detail'
                supportFragmentManager.popBackStack("detail",FragmentManager.POP_BACK_STACK_INCLUSIVE)

                supportActionBar!!.setDisplayShowHomeEnabled(false)
                supportActionBar!!.setDisplayHomeAsUpEnabled(false)
            }

        }
        return true
    }
}