package com.ar.maloba.paymarket.ui.payment

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ar.maloba.paymarket.R
import com.ar.maloba.paymarket.ui.BaseFragment
import com.ar.maloba.paymarket.utils.NumberTextWatcher
import com.ar.maloba.paymarket.utils.parseToAmount
import kotlinx.android.synthetic.main.fragment_amount.*
import kotlinx.android.synthetic.main.fragment_amount.view.*
import java.text.DecimalFormat
import java.text.ParseException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [AmountFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [AmountFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AmountFragment : BaseFragment() {
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
        val view = inflater.inflate(R.layout.fragment_amount, container, false)

        initialize(view)
        return view
    }

    fun initialize(viewRoot: View) {

        viewRoot.amountTextInputEditText.addTextChangedListener(NumberTextWatcher(viewRoot.amountTextInputEditText))

        viewRoot.continueButton.setOnClickListener {

            try {

                val amount = amountTextInputEditText.text.toString().parseToAmount()

                var bundle = bundleOf("amount" to amount)
                findNavController().navigate(
                    R.id.action_amountFragment_to_patmentMethodFragment,
                    bundle
                )
            } catch (nfe: NumberFormatException) { // do nothing?
                showToast(getString(R.string.error_amount_invalid))
            } catch (e: ParseException) { // do nothing?
                showToast(getString(R.string.error_amount_invalid))
            }

        }
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
         * @return A new instance of fragment AmountFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AmountFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
