package com.example.shoppinglistapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.lifecycle.ViewModelProvider
import com.example.shoppinglistapp.domain.ShopItem
import com.example.shoppinglistapp.presentation.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    private lateinit var llShopList: LinearLayout




    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        llShopList = findViewById(R.id.ll_shop_list)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopList.observe(this){
            Log.d("MainActivityTest", it.toString())
            showList(it)
        }

    }

    /*
        Создание списка с помощью добавления view в LinearLayout
        Нерекомендуемый способ реалзиации списка, т.к для каждого элемента списка
        создается новый view, что приводит к потере производительности.
        inflate - ресурсоемкая операция
     */
    private fun showList(list: List<ShopItem>){
        llShopList.removeAllViews()
        for(shopItem in list){
            val layoutId = if(shopItem.enabled){
                R.layout.item_shop_enabled
            } else{
                R.layout.item_shop_disabled
            }

            //Получаем view из xml файла
            val view = LayoutInflater.from(this).inflate(layoutId, llShopList, false)
            val tvName = view.findViewById<TextView>(R.id.tv_name)
            val tvCount = view.findViewById<TextView>(R.id.tv_count)

            tvName.text = shopItem.name
            tvCount.text = shopItem.count.toString()
            view.setOnLongClickListener {
                viewModel.changeEnabledState(shopItem)
                true
            }
            llShopList.addView(view)
        }
    }
}