package com.alicja.sklepallegro.adapter

import com.alicja.sklepallegro.R
import com.alicja.sklepallegro.helper.Round
import com.alicja.sklepallegro.model.Offer
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.row_offer.view.*


class OfferItem (private val offer: Offer) : Item <GroupieViewHolder>(){
    val offerItem : Offer = offer

    override fun getLayout() = R.layout.row_offer

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.offer_name.text = offer.name

        val roundedAmount: String = Round.roundTo2Decimals(offer.price!!.amount)

        val priceText = "$roundedAmount ${offer.price?.currency}"
        viewHolder.itemView.offer_price.text = priceText
        Picasso.get().load(offer.thumbnailUrl).into(viewHolder.itemView.offer_image)
    }

}