package com.alicja.sklepallegro.json

import android.content.Context
import android.widget.Toast
import com.alicja.sklepallegro.model.Offer
import com.alicja.sklepallegro.model.Offers
import com.alicja.sklepallegro.model.Price
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject
import java.math.BigDecimal


class JsonHandler (private val context: Context) {

    companion object {
        private const val URL = "https://private-987cdf-allegromobileinterntest.apiary-mock.com/allegro/offers"
        private const val TAG = "JSON"
    }
        private var offersList: Offers = Offers()
        private var volleyListener = context as VolleyListener
        var responseList : MutableList<Offer>?=  null


    fun makeRequest() {
            val requestQueue = Volley.newRequestQueue(context)
            val request =
                JsonObjectRequest(
                    Request.Method.GET,
                    URL, null,
                    Response.Listener { response: JSONObject ->
                        try {
                            val jsonArray = response.getJSONArray("offers")

                            for (i in 0 until jsonArray.length()) {
                                val jsonOffer = jsonArray.getJSONObject(i)
                                val id = jsonOffer.getString("id")
                                val name = jsonOffer.getString("name")
                                val thumbnailUrl = jsonOffer.getString("thumbnailUrl")
                                val price = jsonOffer.getJSONObject("price")
                                val amount  = price.getDouble("amount")
                                val currency = price.getString("currency")
                                val description = jsonOffer.getString("description")


                                val offer = Offer(
                                    id,
                                    name,
                                    thumbnailUrl,
                                    Price(amount, currency),
                                    description
                                )
                                offersList.addSelectedAndSorted(offer)
                            }
                            setResponse(offersList.getOffersList())

                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    },
                    Response.ErrorListener { error: VolleyError ->
                        volleyListener.requestFinished(false)
                        error.printStackTrace()
                        Toast.makeText(context, "Connection problem occurred", Toast.LENGTH_SHORT)
                            .show()
                    }
                )
            requestQueue.add(request)
        }

        private fun setResponse(offersList: MutableList<Offer>){
            responseList = offersList
            volleyListener.requestFinished(true)
        }


}





