package com.ar.maloba.paymarket.ui

import android.widget.Toast
import dagger.android.support.DaggerFragment

abstract class BaseFragment : DaggerFragment() {

    fun showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(activity, message, duration).show()
    }
}