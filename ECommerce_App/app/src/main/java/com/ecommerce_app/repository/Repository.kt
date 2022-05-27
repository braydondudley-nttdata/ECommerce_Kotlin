package com.ecommerce_app.repository

import android.annotation.SuppressLint
import android.content.Context
import com.ecommerce_app.model.Products
import android.net.NetworkInfo
import android.net.ConnectivityManager
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.ecommerce_app.model.ProductsItem
import com.ecommerce_app.room.ProductsDao
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class Repository @Inject constructor (var context: Context, val productDao: ProductsDao? = null) {

    lateinit var response: Response<Products>

    suspend fun fetchProductsFromRemote(): Products? {
        Log.d("MainActivity", "here")
        if(isNetWorkConnected(context)){
            Log.d("MainActivity", "here2")
            response = ProductService.getRetrofit().getProducts()
            if (response.isSuccessful) {
                Log.d("MainActivity", "here3")
                return response.body()
            } else {
                Log.d("MainActivity", "here4")
                showError()
            }
        }
        return null
    }

    internal suspend fun showError() {
        withContext(Dispatchers.Main) {
            Toast.makeText(context, "Error getting data from remote server", Toast.LENGTH_LONG)
                .show()
        }
    }

    val allProducts: LiveData<List<ProductsItem>>? = productDao?.getAll()


    suspend fun insert(product: ProductsItem) {
            productDao?.insert(product)
    }

    @SuppressLint("MissingPermission")
    internal fun isNetWorkConnected(context: Context): Boolean {
        var status = true
        try {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
            var netInfo = cm?.getNetworkInfo(0)
            if (netInfo != null && netInfo.state == NetworkInfo.State.CONNECTED) {
                status = true
            } else {
                netInfo = cm?.getNetworkInfo(1)
                if (netInfo != null && netInfo.state == NetworkInfo.State.CONNECTED) status = true
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
        return status
    }

    internal fun writeToSharedPref(name: String, value: String) {
        val sharedPreference = context.getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        var editor = sharedPreference?.edit()
        editor?.putString(name, value)
        editor?.commit()
    }

    internal fun getSharedPref() = context.getSharedPreferences("PREFERENCE_NAME",Context.MODE_PRIVATE)

}
