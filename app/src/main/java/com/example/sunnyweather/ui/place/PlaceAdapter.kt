package com.example.sunnyweather.ui.place

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.sunnyweather.R
import com.example.sunnyweather.logic.model.Place
import kotlinx.android.synthetic.main.place_item.view.*

class PlaceAdapter(
    private val fragment: Fragment
    , private val placeList: List<Place>
) : RecyclerView.Adapter<PlaceAdapter.PlaceViewHodler>() {

    inner class PlaceViewHodler(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var placeName = itemView.findViewById<TextView>(R.id.placeName)
        var placeAddress = itemView.findViewById<TextView>(R.id.placeAddress)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHodler {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.place_item, parent, false)
        return PlaceViewHodler(view)
    }

    override fun getItemCount(): Int {
        return placeList.size
    }

    override fun onBindViewHolder(holder: PlaceViewHodler, position: Int) {
        val place = placeList[position]
        holder.placeName.text = place.name
        holder.placeAddress.text = place.address
    }
}