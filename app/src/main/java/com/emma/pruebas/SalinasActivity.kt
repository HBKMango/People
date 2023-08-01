package com.emma.pruebas

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.emma.pruebas.data.Audits
import com.emma.pruebas.data.ObtenerMisAuditoriasPorFechaResult
import com.emma.pruebas.databinding.ActivitySalinasBinding
import com.emma.pruebas.databinding.ItemAuditBinding
import com.emma.pruebas.util.GenericAdapter
import com.emma.pruebas.viewmodel.ProductsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class SalinasActivity : AppCompatActivity() {

    lateinit var binding: ActivitySalinasBinding
    private val viewModel: ProductsViewModel by viewModels()
    lateinit var adapter: GenericAdapter<ObtenerMisAuditoriasPorFechaResult, ItemAuditBinding>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySalinasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getAudit()

        adapter = GenericAdapter(
            bindingInflater = { inflater, parent, attachToParent ->
                ItemAuditBinding.inflate(inflater, parent, attachToParent)
            },
            onBind = { item, binding, _ ->
                binding.apply {
                    auto.text = item.auditoria
                    branch.text = item.sucursal
                    date1.text = item.fecha_inicio_plan
                    date2.text = item.fecha_inicio_real
                }
            }
        )

        //adapter.setOnItemClickListener(this)

        binding.recycler.adapter = adapter

        lifecycleScope.launchWhenCreated {
            viewModel.resultAuditDB.collect{
                when (it) {
                    is Audits.Master -> {
                        adapter.setData(it.data.obtenerMisAuditoriasPorFechaResult)
                        Toast.makeText(applicationContext, "Si", Toast.LENGTH_LONG).show()
                    }

                    is Audits.Error -> {
                        Toast.makeText(applicationContext, "Error", Toast.LENGTH_LONG).show()
                    }

                    is Audits.Exception -> {
                        Toast.makeText(applicationContext, "Exception", Toast.LENGTH_LONG).show()
                    }

                    is Audits.Empty -> Unit
                }
            }
        }
    }
}