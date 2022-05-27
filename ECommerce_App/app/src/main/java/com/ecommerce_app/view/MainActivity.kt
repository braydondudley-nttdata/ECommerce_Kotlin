package com.ecommerce_app.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ecommerce_app.R
import androidx.databinding.DataBindingUtil
import com.ecommerce_app.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.framelayout, Profile()).commit()
        binding.navigation.selectedItemId = R.id.nav_profile
        binding.navigation.setOnNavigationItemSelectedListener { item ->
            var selectedFragment: Fragment? = null
            if(binding.navigation.selectedItemId != item.itemId) {
                when (item.itemId) {
                    R.id.nav_product_list -> selectedFragment = ProductsList()
                    R.id.nav_profile -> selectedFragment = Profile()
                    R.id.nav_cart -> selectedFragment = Cart()
                }
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.framelayout, selectedFragment!!)
                transaction.commit()
            }
            true
        }
    }
}
