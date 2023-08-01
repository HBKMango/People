package com.emma.pruebas.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

class GenericAdapter<T, VB : ViewBinding>(
    private val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> VB,
    private val onBind: (item: T, viewBinding: VB, position: Int) -> Unit
) : RecyclerView.Adapter<GenericAdapter<T, VB>.GenericViewHolder>() {

    private var data: List<T> = emptyList()
    private var listener: GenericListener<T>? = null

    fun setData(newData: List<T>) {
        val diffResult = DiffUtil.calculateDiff(GenericDiffCallback(data, newData))
        data = newData
        diffResult.dispatchUpdatesTo(this)
    }

    fun setOnItemClickListener(listener: GenericListener<T>) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = bindingInflater(inflater, parent, false)
        return GenericViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GenericViewHolder, position: Int) {
        val item = data[position]
        onBind(item, holder.binding, position)

        holder.itemView.setOnClickListener {
            listener?.onItemClick(item)
        }
    }

    override fun getItemCount(): Int = data.size

    inner class GenericViewHolder(val binding: VB) : RecyclerView.ViewHolder(binding.root)
}

interface GenericListener<T> {
    fun onItemClick(item: T)
}

class GenericDiffCallback<T>(private val oldList: List<T>, private val newList: List<T>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]
}