package com.example.mykotlinpokedex.Activities

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mykotlinpokedex.R
import com.example.mykotlinpokedex.goToActivity
import com.example.mykotlinpokedex.toast
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sharedPreferences= getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)

        //UI Logic
        buttonLogIn.setOnClickListener{
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()
            if(email.equals("coder2021") && password.equals("sunwise")){
                //Set values to Shared Preferences
                val checked: Boolean = checkBox.isChecked
                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                editor.putString("mailuser",email)
                editor.putString("passworduser",password)
                editor.putBoolean("CHECKBOX",checked)
                editor.apply()
                cleanEditTexts()
                goToActivity<MainActivity>()
                overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right)
            }else{
                toast("Error de password o contraseña, verifique para continuar.")
            }
        }
    }

    //Clear editTexts after register values on shared preferences
    private fun cleanEditTexts(){
        editTextEmail.setText("")
        editTextPassword.setText("")
    }
}