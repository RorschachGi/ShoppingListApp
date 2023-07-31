package com.example.shoppinglistapp.presentation

import android.app.Application
import com.example.shoppinglistapp.di.DaggerApplicationComponent

class ShopListApp: Application() {

    val component by lazy {
        DaggerApplicationComponent.factory()
            .create(this)
    }
}