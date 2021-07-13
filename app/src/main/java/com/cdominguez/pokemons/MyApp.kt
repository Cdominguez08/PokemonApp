package com.cdominguez.pokemons

import android.app.Application
import com.cdominguez.pokemons.di.DaggerPokemonsComponent
import com.cdominguez.pokemons.di.PokemonsComponent

class MyApp : Application() {

    lateinit var appComponent: PokemonsComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = initComponent()
    }

    fun initComponent() = DaggerPokemonsComponent.factory().create(this)
}