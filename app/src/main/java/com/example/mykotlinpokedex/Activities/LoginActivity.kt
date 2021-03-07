package com.example.mykotlinpokedex.Activities

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mykotlinpokedex.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.editTextEmail
import kotlinx.android.synthetic.main.activity_login.editTextPassword

class LoginActivity : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sharedPreferences= getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)

        //UI Logic
        //Check credentials for input user on changetext
        editTextEmail.validate {
            editTextEmail.error = if(isValidEmail(it)) null else "Email is not valid"
        }

        editTextPassword.validate {
            editTextPassword.error = if(isValidPassword(it)) null else "Password should contain 1 number, 1 lowercase, 1 uppercase, 1 special character and 4 characters length at least."
        }
        buttonLogIn.setOnClickListener{
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()
            if(isValidEmail(email) && isValidPassword(password)){
                //if(email.equals("coder2021") && password.equals("sunwise")){ //Old credentials with format valid
                if(email.equals("coder@sunwise.io") && password.equals("Sunwis3@")){
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
                    toast("Credenciales incorrectas.")
                }
            }else{
                toast("Error de formato en password y/o contraseña, verifique para continuar.")
            }
        }
    }

    //Clear editTexts after register values on shared preferences
    private fun cleanEditTexts(){
        editTextEmail.setText("")
        editTextPassword.setText("")
    }
}