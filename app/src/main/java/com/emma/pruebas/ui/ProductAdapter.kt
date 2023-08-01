package com.emma.pruebas.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.emma.pruebas.data.Pro
import com.emma.pruebas.databinding.ProductItemBinding

class ProductAdapter : RecyclerView.Adapter<ProductViewHolder>() {

    private var data: List<Pro> = ArrayList()
    lateinit var listener: TermDetailListener

    fun swapData(newData: List<Pro>) {
        val oldSize = data.size
        data = newData
        val newSize = data.size
        notifyItemRangeInserted(oldSize, newSize - oldSize)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) =
        holder.bind(data[position], listener)
}

class ProductViewHolder(private val binding: ProductItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Pro, listener: TermDetailListener) = with(binding) {

        name.text = item.title
        description.text = item.description
        price.text = "$ ${item.price}"

        root.setOnClickListener {
            listener.onItemSelected(item)
        }
    }
}

interface TermDetailListener {
    fun onItemSelected(item: Pro)
}