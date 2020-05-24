package com.alicja.sklepallegro.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.alicja.sklepallegro.R
import com.alicja.sklepallegro.adapter.OfferItem
import com.alicja.sklepallegro.json.JsonHandler
import com.alicja.sklepallegro.json.VolleyListener
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity(), VolleyListener {
    private var jsonHandler: JsonHandler? = null

    companion object {
        const val OFFER_KEY = "offer"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        jsonHandler = JsonHandler(this)
        jsonHandler!!.makeRequest()

    }

    private fun setAdapter() {
        val offersList = jsonHandler?.responseList
        val adapter = GroupAdapter<GroupieViewHolder>()
        offers_recyclerView.adapter = adapter

        offers_recyclerView.addItemDecoration(DividerItemDecoration(applicationContext, VERTICAL))
        adapterOnItemClickListener(adapter)


        if (offersList != null) {
            for (offer in offersList) {
                adapter.add(OfferItem(offer))
            }
        }
    }

    override fun onBackPressed() {
        finish()
    }

    override fun requestFinished(isFinished: Boolean) {
        if (isFinished) {
            setAdapter()
        }
    }

    private fun adapterOnItemClickListener(adapter: GroupAdapter<GroupieViewHolder>) {
        adapter.setOnItemClickListener { item, view ->
            val intent = Intent(this, DetailsActivity::class.java)
            val row = item as OfferItem
            intent.putExtra(OFFER_KEY, row.offerItem)
            startActivity(intent)
        }

    }
}
