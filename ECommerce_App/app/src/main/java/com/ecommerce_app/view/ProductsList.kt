package com.ecommerce_app.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ecommerce_app.R
import com.ecommerce_app.model.ProductsItem
import com.ecommerce_app.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsList : Fragment() {

    private val viewModel by viewModels<MainViewModel>()
    private lateinit var recView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_products, container, false)

        Toast.makeText(context,"Long click to add to cart",Toast.LENGTH_LONG).show()

        val progressBar = view.findViewById<ProgressBar>(R.id.product_progressbar)
        progressBar.visibility=View.VISIBLE
        recView = view.findViewById(R.id.products_recview)
        viewModel.productList.observe(viewLifecycleOwner, Observer {
            list ->
            if(list.isNotEmpty()) {
                run {
                    recView.adapter = ProductsRecyclerViewAdapter(list,this)
                    recView.layoutManager = LinearLayoutManager(this.context)
                }
                progressBar.visibility=View.GONE
            }
        })
        return view
    }

    fun addToCart(productsItem: ProductsItem) {
        viewModel.addToCart(productsItem)
    }
}