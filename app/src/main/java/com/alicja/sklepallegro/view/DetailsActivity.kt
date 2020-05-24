package com.alicja.sklepallegro.view

import android.os.Bundle
import android.util.Base64
import androidx.appcompat.app.AppCompatActivity
import com.alicja.sklepallegro.R
import com.alicja.sklepallegro.helper.Round
import com.alicja.sklepallegro.model.Offer
import com.alicja.sklepallegro.view.HomeActivity.Companion.OFFER_KEY
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    private var offer: Offer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        supportActionBar?.hide()

        exit_arrow.setOnClickListener {
            finish()
        }

        offer = intent.getParcelableExtra<Offer>(OFFER_KEY)
        setDataIntoFields()

    }

    private fun setDataIntoFields() {

        offer_name_details.text = offer?.name
        Picasso.get().load(offer?.thumbnailUrl).into(offer_image_details)

        val roundedAmount = Round.roundTo2Decimals(offer?.price!!.amount)
        val priceText = "$roundedAmount ${offer?.price?.currency}"
        offer_price_details.text = priceText

        val encodedDescription = Base64.encodeToString(
            offer?.description?.toByteArray(),
            Base64.NO_PADDING
        )

        offer_description_details.loadData(encodedDescription, "text/html", "base64")

    }

}
