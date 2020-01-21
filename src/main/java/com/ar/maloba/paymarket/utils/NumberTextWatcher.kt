package com.ar.maloba.paymarket.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.ParseException


class NumberTextWatcher(et: EditText) : TextWatcher {

    private val df: DecimalFormat
    private val et: EditText
    private var zeroPreviousValue: Boolean

    init {
        val dfs = DecimalFormatSymbols()
        dfs.decimalSeparator = ','
        dfs.groupingSeparator = '.'

        df = DecimalFormat("#,###.##", dfs)
        df.isDecimalSeparatorAlwaysShown = true

        this.et = et
        zeroPreviousValue = true
    }

    override fun afterTextChanged(s: Editable) {
        et.removeTextChangedListener(this)
        try {
            var sv = s.toString()
            if(zeroPreviousValue) sv = sv.replace("0","")
            zeroPreviousValue = false
            sv = sv.replace(",","").replace(".","")
            var d = sv.toFloat()

            if(d != 0F) d /= 100

            et.setText(df.format(d))

        } catch (nfe: NumberFormatException) { // do nothing?
            et.setText("0,00")
        } catch (e: ParseException) { // do nothing?
            et.setText("0,00")
        } finally {
            et.setSelection(et.text.length)
            et.addTextChangedListener(this)
        }

    }

    override fun beforeTextChanged(
        s: CharSequence,
        start: Int,
        count: Int,
        after: Int
    ) {
        if(df.parse(s.toString()).toFloat() == 0F) {
            zeroPreviousValue = true
        }
    }

    override fun onTextChanged(
        s: CharSequence,
        start: Int,
        before: Int,
        count: Int
    ) {

    }
}