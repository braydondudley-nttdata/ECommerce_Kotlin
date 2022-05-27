package com.ecommerce_app.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ecommerce_app.R
import com.ecommerce_app.model.ProductsItem
import com.squareup.picasso.Picasso

class ProductsRecyclerViewAdapter(list: MutableList<ProductsItem>, val fragment: ProductsList? = null) :
    RecyclerView.Adapter<ProductsRecyclerViewAdapter.ProductsViewHolder>() {

    var mutList = mutableListOf<ProductsItem>()

    init {
        mutList.clear()
        mutList = list
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ProductsViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.products_recview, viewGroup, false)
        return ProductsViewHolder(fragment,v)
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.bind(mutList[position])
    }

    override fun getItemCount(): Int {
        return mutList.size
    }

    class ProductsViewHolder(val fragmnet: ProductsList?, val v: View) : RecyclerView.ViewHolder(v) {

        var itemDescription: TextView = v.findViewById(R.id.product_description)
        var itemImage: ImageView = v.findViewById(R.id.product_image)

        fun bind(productItem: ProductsItem){

            v.setOnLongClickListener{
                fragmnet?.addToCart(productItem)
                true
            }

            itemDescription.setText(productItem.description)
            if(!productItem.image.isNullOrEmpty())
                Picasso.get().load(productItem.image).into(itemImage);
        }
    }
}