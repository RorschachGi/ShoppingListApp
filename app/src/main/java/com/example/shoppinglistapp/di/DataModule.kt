package com.example.shoppinglistapp.di

import android.app.Application
import com.example.shoppinglistapp.data.AppDatabase
import com.example.shoppinglistapp.data.ShopListDao
import com.example.shoppinglistapp.data.ShopListRepositoryImpl
import com.example.shoppinglistapp.domain.ShopListRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindRepository(impl: ShopListRepositoryImpl): ShopListRepository

    companion object{
        @Provides
        fun provideShopListDao(
            application: Application
        ): ShopListDao{
            return AppDatabase.getInstance(application).shopListDao()
        }
    }

}