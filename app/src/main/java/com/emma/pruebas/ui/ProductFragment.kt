package com.emma.pruebas.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.emma.pruebas.MainActivity
import com.emma.pruebas.R
import com.emma.pruebas.data.Pro
import com.emma.pruebas.data.Products
import com.emma.pruebas.databinding.FragmentProductBinding
import com.emma.pruebas.viewmodel.ProductsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductFragment : Fragment(), TermDetailListener {

    lateinit var binding: FragmentProductBinding
    private val viewModel: ProductsViewModel by viewModels()
    lateinit var adapter: ProductAdapter

    lateinit var activity: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity = requireActivity() as MainActivity
        adapter = ProductAdapter()
        adapter.listener = this

        viewModel.getProducts()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply { recycler.adapter = adapter }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.resultProducts.collect {

                when (it) {
                    is Products.Master -> {
                        adapter.swapData(it.data.products)
                    }

                    is Products.Error -> {
                        Toast.makeText(
                            requireContext(),
                            "Error en la operación",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    is Products.Exception -> {
                        Toast.makeText(
                            requireContext(),
                            "Excepcion en la operación",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    is Products.Empty -> {}
                }
            }
        }
    }

    override fun onItemSelected(item: Pro) {
        //activity.addFragmentToFragment(ProductDetailFragment(item))
    }
}