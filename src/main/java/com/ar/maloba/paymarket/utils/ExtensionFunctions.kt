package com.ar.maloba.paymarket.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ar.maloba.paymarket.BuildConfig.COUNTRY_COD
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat

fun ViewGroup.inflate(layoutRes: Int, attachToRoot: Boolean = false): View =
    LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)

fun String.parseToAmount(): Float {

    if(this.isEmpty()) return 0F

    val dfs = DecimalFormatSymbols()
    dfs.decimalSeparator = ','
    dfs.groupingSeparator = '.'

    val df = DecimalFormat("#,###.##", dfs)

    return df.parse(this).toFloat()
}

fun Float.toAmountString(symbol: String = COUNTRY_COD): String {

    val format: NumberFormat = NumberFormat.getCurrencyInstance()
    format.setMaximumFractionDigits(2)

    return format.format(this.toDouble())
}