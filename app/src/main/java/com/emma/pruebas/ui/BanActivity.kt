package com.emma.pruebas.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.emma.pruebas.R
import com.emma.pruebas.data.User
import com.emma.pruebas.databinding.ActivityBanBinding
import com.emma.pruebas.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BanActivity : AppCompatActivity() {

    lateinit var binding: ActivityBanBinding

    private val viewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBanBinding.inflate(layoutInflater)
        setContentView(binding.root)


        viewModel.getProducts()

        binding.load.setOnClickListener {
            viewModel.getProducts()
        }

        lifecycleScope.launchWhenCreated {
            viewModel.resultUser.collect{

                when(it) {
                    is User.Master -> {
                        binding.apply {
                            name.text = it.data.results.first().name.first
                            mail.text = it.data.results.first().email
                            date.text = it.data.results.first().registered.date
                            address.text = "${it.data.results.first().location.city} ${it.data.results.first().location.state}"
                            phone.text = it.data.results.first().phone
                            pass.text = it.data.results.first().login.password

                            Glide.with(applicationContext)
                                .load(it.data.results.first().picture.medium)
                                .error(R.drawable.blue)
                                .into(img)
                        }
                    }

                    is User.Error -> {
                        Toast.makeText(applicationContext, it.error, Toast.LENGTH_SHORT).show()
                    }

                    is User.Exception -> {
                        Toast.makeText(applicationContext, it.exception, Toast.LENGTH_SHORT).show()
                    }

                    is User.Empty -> Unit
                }
            }
        }

    }
}