package com.ar.maloba.paymarket.ui.payment

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ar.maloba.paymarket.R
import com.ar.maloba.paymarket.repository.entity.PaymentMethodEntity
import com.ar.maloba.paymarket.ui.BaseFragment
import com.ar.maloba.paymarket.utils.Status
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_amount.*
import kotlinx.android.synthetic.main.fragment_patment_method.view.*
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "amount"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [patmentMethodFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [patmentMethodFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PaymentMethodsFragment : BaseFragment() {


    private lateinit var methodAdapter: PaymentMethodsAdapter

    private var paymentMethodsList: MutableList<PaymentMethodEntity> = mutableListOf()
    @Inject
    lateinit var paymentMethodsViewModel: PaymentMethodsViewModel

    // TODO: Rename and change types of parameters
    private var amount: Float? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            amount = it.getFloat(ARG_PARAM1, 0F)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_patment_method, container, false)
        initialize(view)

        return view
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment patmentMethodFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PaymentMethodsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


    private fun initialize(view: View) {

        methodAdapter = PaymentMethodsAdapter(
            paymentMethodsList
        ) {
            // navigate to next step
            paymentMethodsViewModel.selectedPaymentMethod(it)

            var bundle = bundleOf("amount" to amount,
                "paymentMethodId" to it.id)
            findNavController().navigate(R.id.action_patmentMethodFragment_to_bankFragment, bundle)

        }

        view.paymentMethodsRecyclerView.also {
            it.layoutManager = LinearLayoutManager(activity)
            it.adapter = methodAdapter
        }
        amount?.let { paymentMethodsViewModel.setAmount(it) }
        paymentMethodsViewModel.loadAllPaymentMethods(true)

        paymentMethodsViewModel.getAllPaymentMethods.observe(this, Observer {
            when (it!!.status) {
                Status.SUCCESS -> {
                    methodAdapter.addPaymentMethods(it.data!!)
                }
                Status.ERROR -> {
                    showToast(it.message!!)
                }
                Status.LOADING -> {
                    showToast(getString(R.string.loading))
                }
            }
        })
    }
}
