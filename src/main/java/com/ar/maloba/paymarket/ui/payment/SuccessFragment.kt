package com.ar.maloba.paymarket.ui.payment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.ar.maloba.paymarket.R
import com.ar.maloba.paymarket.ui.BaseFragment
import com.ar.maloba.paymarket.utils.toAmountString
import kotlinx.android.synthetic.main.fragment_success.view.*
import javax.inject.Inject


class SuccessFragment : BaseFragment() {

    @Inject
    lateinit var paymentMethodsViewModel: PaymentMethodsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().navigate(R.id.action_successFragment_to_welcomeFragment, null)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_success, container, false)
        initialize(view)
        return view
    }

    private fun initialize(view: View) {
        val payment = paymentMethodsViewModel.getPaymentResult()

        payment.let {
            view.amountTextView.text = it?.amount?.toAmountString()
            view.paymentMethodTextView.text = it?.paymentMethod?.name
            view.cardIssuerTextView.text = it?.cardIssuers?.name
            view.installmentTextView.text = it?.installments?.message
        }

    }

}
