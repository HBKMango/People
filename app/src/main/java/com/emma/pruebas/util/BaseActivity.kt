package com.emma.pruebas.util

import android.content.Context
import android.content.res.Configuration
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.emma.pruebas.ui.LoaderFragment
import com.google.gson.Gson
import java.lang.reflect.Type
import java.text.NumberFormat
import java.util.Locale

abstract class BaseActivity : AppCompatActivity() {

    private val loaderMain = LoaderFragment()
    private var loaderTimer: CountDownTimer? = null

    val format: NumberFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())

    override fun attachBaseContext(newBase: Context?) {

        val newOverride = Configuration(newBase?.resources?.configuration)
        newOverride.fontScale = 1.0f
        applyOverrideConfiguration(newOverride)

        super.attachBaseContext(newBase)
    }

    fun showLoader() {
        if (!loaderMain.isAdded) {
            loaderMain.show(supportFragmentManager, "LoaderMain")
            startLoaderTimer()
        }
    }

    fun hideLoader() {
        if (loaderMain.isAdded) {
            loaderMain.dismiss()
            stopLoaderTimer()
        }
    }

    fun <T> convertErrorBody(errorBody: String, type: Type): T? {
        return try {
            val gson = Gson()
            gson.fromJson(errorBody, type)
        } catch (exception: Exception) {
            null
        }
    }

    private fun startLoaderTimer() {
        loaderTimer?.cancel()

        loaderTimer = object : CountDownTimer(120000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                hideLoader()
            }
        }.start()
    }

    private fun stopLoaderTimer() {
        loaderTimer?.cancel()
        loaderTimer = null
    }
}