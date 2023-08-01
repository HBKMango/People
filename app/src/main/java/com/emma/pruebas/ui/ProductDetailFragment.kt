package com.emma.pruebas.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.emma.pruebas.data.Pro
import com.emma.pruebas.databinding.FragmentProductDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailFragment(private val item: Pro) : Fragment() {

    lateinit var binding: FragmentProductDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            product.text = item.title
            description.text = item.description
            image.load(item.thumbnail)
            price.text = "$ ${item.price}"

            back.setOnClickListener {
                requireActivity().onBackPressed()
            }
        }
    }
}