package com.alicja.sklepallegro.model

import kotlin.collections.ArrayList
import kotlin.collections.MutableList


class Offers {
   private val offersList: MutableList<Offer> = ArrayList<Offer>()

    private fun add(offer : Offer){
        offersList.add(offer)
    }

    private fun isAmountIsGreaterThan50AndLessThan1000(amount: Double): Boolean {
        return amount in 50.0..1000.0
    }

    fun addSelectedAndSorted(offer:Offer){
        if(offer.price?.amount?.let { isAmountIsGreaterThan50AndLessThan1000(it) }!!){
            add(offer)
        }
        offersList.sortBy { it.price?.amount }
    }

    fun getOffersList() : MutableList<Offer>{
        return offersList
    }

}