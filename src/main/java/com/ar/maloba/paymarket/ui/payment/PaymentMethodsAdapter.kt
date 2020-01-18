package com.ar.maloba.paymarket.ui.payment

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ar.maloba.paymarket.R
import com.ar.maloba.paymarket.repository.entity.PaymentMethodEntity
import com.ar.maloba.paymarket.utils.inflate
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_payment_method.view.*


class PaymentMethodsAdapter(private val listPaymentMethodsEntity: MutableList<PaymentMethodEntity>,
                            val onPaymentMethodItemClickListener: (PaymentMethodEntity) -> Unit)
    : RecyclerView.Adapter<PaymentMethodsAdapter.PaymentMethodViewHolder>() {


    inner class PaymentMethodViewHolder(viewGroup: ViewGroup) :
        RecyclerView.ViewHolder(viewGroup.inflate(R.layout.item_payment_method)) {

        private lateinit var methodEntity: PaymentMethodEntity

        init {
            itemView.setOnClickListener {
                onPaymentMethodItemClickListener(methodEntity)
            }
        }

        fun bind(methodEntity: PaymentMethodEntity) {
            this.methodEntity = methodEntity
            itemView.card_title.text = "${this.methodEntity.name}"
            itemView.card_subtitle.text = "${this.methodEntity.id} - ${this.methodEntity.type}"
            Picasso.get().load(this.methodEntity.thumbnail).into(itemView.card_thumbnail)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PaymentMethodViewHolder(parent)

    override fun getItemCount() = listPaymentMethodsEntity.size

    override fun onBindViewHolder(holder: PaymentMethodViewHolder, position: Int) {
        holder.bind(listPaymentMethodsEntity[position])
    }

    fun addPaymentMethods(listPaymentMethods: List<PaymentMethodEntity>) {
        listPaymentMethodsEntity.clear()
        listPaymentMethodsEntity.addAll(listPaymentMethods)
        notifyDataSetChanged()
    }
}