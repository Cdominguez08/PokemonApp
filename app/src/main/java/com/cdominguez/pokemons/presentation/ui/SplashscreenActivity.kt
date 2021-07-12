package com.cdominguez.pokemons.presentation.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.cdominguez.pokemons.R
import com.cdominguez.pokemons.utils.Constants
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import java.lang.Thread.sleep

class SplashscreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)


        val sharedPreferences = getSharedPreferences(getString(R.string.sharedPreferences), Context.MODE_PRIVATE)

        Handler()
            .postDelayed({

                sharedPreferences
                    .edit()
                    .putBoolean(Constants.SHOW_SPLASHSCREEN,true)
                    .apply()

                goToMainActivity()
            },3000)
    }

    fun goToMainActivity(){
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }
}