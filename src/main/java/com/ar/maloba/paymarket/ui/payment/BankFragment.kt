package com.ar.maloba.paymarket.ui.payment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ar.maloba.paymarket.R
import com.ar.maloba.paymarket.repository.entity.CardIssuersEntity
import com.ar.maloba.paymarket.repository.entity.PaymentMethodEntity
import com.ar.maloba.paymarket.ui.BaseFragment
import com.ar.maloba.paymarket.utils.Status
import kotlinx.android.synthetic.main.fragment_bank.*
import kotlinx.android.synthetic.main.fragment_patment_method.view.*
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [BankFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [BankFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BankFragment : BaseFragment() {

    private var cardIssuersList: MutableList<CardIssuersEntity> = mutableListOf()
    private var cardIssuer: CardIssuersEntity? = null
    @Inject
    lateinit var paymentMethodsViewModel: PaymentMethodsViewModel

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_bank, container, false)
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
         * @return A new instance of fragment BankFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BankFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun initialize(view: View) {

        paymentMethodsViewModel.getCardIssuersFor("visa")

        paymentMethodsViewModel.getCardIssuers.observe(this, Observer {
            when (it!!.status) {
                Status.SUCCESS -> {
                    cardIssuersTextInputLayout.isEnabled = true
                    cardIssuersList.clear()
                    cardIssuersList.addAll(it.data!!)
                    val adapter = it.data?.let { data ->
                        ArrayAdapter(
                            context!!,
                            R.layout.dropdown_menu_popup_item,
                            data.map { issuer -> issuer.name }
                        )
                    }
                    card_issuer_filled_exposed_dropdown.setAdapter(adapter)
                    card_issuer_filled_exposed_dropdown.setOnItemClickListener { adapterView, view, i, l -> cardIssuer = cardIssuersList[i] }
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
