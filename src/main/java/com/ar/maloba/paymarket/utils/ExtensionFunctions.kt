package com.ar.maloba.paymarket.utils

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ar.maloba.paymarket.BuildConfig.COUNTRY_COD
import com.ar.maloba.paymarket.ui.BaseActivity
import kotlinx.android.synthetic.main.fragment_amount.*
import java.text.NumberFormat
import java.util.*

fun ViewGroup.inflate(layoutRes: Int, attachToRoot: Boolean = false): View =
    LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)

fun String.parseToAmount(symbol: String = COUNTRY_COD): Double {

    if(this.isEmpty()) return 0.0

    val format: NumberFormat = NumberFormat.getCurrencyInstance()
    format.setMaximumFractionDigits(2)
    format.setCurrency(Currency.getInstance(symbol))

    return format.parse(this).toDouble()
}
