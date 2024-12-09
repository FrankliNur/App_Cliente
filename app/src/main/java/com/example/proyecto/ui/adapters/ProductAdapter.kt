package com.example.proyecto.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyecto.databinding.ItemProductBinding
import com.example.proyecto.models.Product

class ProductAdapter(private val products: List<Product>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int = products.size

    inner class ProductViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            binding.productNameTextView.text = product.name
            binding.productDescriptionTextView.text = product.description
            binding.productPriceTextView.text = "Bs. ${product.price}"

            // Usar Glide para cargar la imagen del producto
            Glide.with(binding.root.context)
                .load(product.image)
                .into(binding.productImageView)
        }
    }
}
