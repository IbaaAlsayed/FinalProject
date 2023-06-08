package com.example.finalproject.activities

//import com.example.finalproject.viewmodel.CartViewModel
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.finalproject.R
import com.example.finalproject.databinding.ActivityShoppingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShoppingActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityShoppingBinding.inflate(layoutInflater)
    }

//    val viewModel by viewModels<CartViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val btn1= findViewById<CardView>(R.id.card_of_women)
        btn1.setOnClickListener {
            val intent= Intent(this,WomenClothesActivity::class.java)
            startActivity(intent)
        }
        val btn2= findViewById<CardView>(R.id.card_of_man)
        btn2.setOnClickListener {
            val intent= Intent(this,ManClothesActivity::class.java)
            startActivity(intent)
        }
        val btn3= findViewById<CardView>(R.id.card_of_kides)
        btn3.setOnClickListener {
            val intent= Intent(this,KidsClothesActivity::class.java)
            startActivity(intent)
        }
        val btn4= findViewById<CardView>(R.id.card_of_shoes)
        btn4.setOnClickListener {
            val intent= Intent(this,ShoesActivity::class.java)
            startActivity(intent)
        }

        val btn5= findViewById<CardView>(R.id.card_of_accessories)
        btn5.setOnClickListener {
            val intent= Intent(this,AccessoriesActivity::class.java)
            startActivity(intent)
        }
        val btn6= findViewById<CardView>(R.id.card_of_furniture)
        btn6.setOnClickListener {
            val intent= Intent(this,FurnitureActivity::class.java)
            startActivity(intent)
        }
//        val navController = findNavController(R.id.shoppingHostFragment)
//        binding.bottomNavigation.setupWithNavController(navController)
//
//        lifecycleScope.launchWhenStarted {
//            viewModel.cartProducts.collectLatest {
//                when (it) {
//                    is Resource.Success -> {
//                        val count = it.data?.size ?: 0
//                        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNavigation)
//                        bottomNavigation.getOrCreateBadge(R.id.cartFragment).apply {
//                            number = count
//                            backgroundColor = resources.getColor(R.color.g_blue)
//                        }
//                    }
//                    else -> Unit
//                }
//            }
//        }
    }

}