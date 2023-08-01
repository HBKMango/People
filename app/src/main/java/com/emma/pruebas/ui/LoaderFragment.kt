package com.emma.pruebas.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.emma.pruebas.databinding.FragmentLoaderBinding
import com.emma.pruebas.util.BaseDialog

class LoaderFragment : BaseDialog() {

    lateinit var binding: FragmentLoaderBinding
    var message = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentLoaderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (message.isNotEmpty()){
            binding.message.text = message
        } else {
            binding.message.visibility = View.GONE
        }
    }
}