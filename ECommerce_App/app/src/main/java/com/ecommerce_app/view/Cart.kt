package com.ecommerce_app.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ecommerce_app.R
import com.ecommerce_app.model.ProductsItem
import com.ecommerce_app.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Cart : Fragment() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var recView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_cart, container, false)
        recView = view.findViewById(R.id.cart_recview)
        viewModel.repo.allProducts?.observe(viewLifecycleOwner, Observer {
            //update rec view
                list ->
            run {
                val mutList = mutableListOf<ProductsItem>()
                //avoid this for loop use ProductsItem
                for(l in list) {
                    mutList.add(ProductsItem(description = l.description, image = l.image))
                }
                recView.adapter = ProductsRecyclerViewAdapter(mutList)
                recView.layoutManager = LinearLayoutManager(this.context)
            }
        })

        return view
    }

}