package com.emma.pruebas

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.transform.BlurTransformation
import com.bumptech.glide.Glide
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.request.RequestOptions
import com.emma.pruebas.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur
import android.widget.ImageView

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Convierte el drawable en un bitmap
        val bitmap = (binding.back.drawable as BitmapDrawable).bitmap

        // Aplica el efecto de desenfoque al bitmap
        val blurredBitmap = blurBitmap(bitmap, 10f)

        // Establece el bitmap desenfocado como fondo del ImageView
        binding.back.setImageBitmap(blurredBitmap)

        /*val fm = supportFragmentManager
        val tr = fm.beginTransaction()
        tr.add(R.id.container, ProductFragment())
        tr.commitAllowingStateLoss()*/
    }

    /*fun addFragmentToFragment(fragment: Fragment) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.container, fragment, fragment.javaClass.name)
        ft.addToBackStack("")
        ft.commitAllowingStateLoss()
    }*/

    private fun blurBitmap(bitmap: Bitmap, radius: Float): Bitmap {
        // Crea una instancia de RenderScript
        val renderScript = RenderScript.create(this)

        // Crea un objeto Allocation a partir del bitmap
        val input = Allocation.createFromBitmap(renderScript, bitmap)

        // Crea un objeto Allocation para el bitmap desenfocado
        val output = Allocation.createTyped(renderScript, input.type)

        // Crea un objeto ScriptIntrinsicBlur para aplicar el efecto de desenfoque
        val script = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript))
        script.setInput(input)

        // Establece el radio de desenfoque
        script.setRadius(radius)

        // Ejecuta el script para aplicar el desenfoque
        script.forEach(output)

        // Copia el resultado a un nuevo bitmap
        val blurredBitmap = Bitmap.createBitmap(bitmap.width, bitmap.height, bitmap.config)
        output.copyTo(blurredBitmap)

        // Libera los recursos de RenderScript
        renderScript.destroy()

        return blurredBitmap
    }
}