package com.example.sunnyweather.ui.place

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sunnyweather.R
import com.example.sunnyweather.Tool.showToast
import kotlinx.android.synthetic.main.fragment_place.*

class PlaceFragment : Fragment() {
    val viewModel by lazy {
        ViewModelProvider(this).get(PlaceViewModel::class.java)
    }

    private lateinit var adapter: PlaceAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_place, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val manager = LinearLayoutManager(activity)
        recyclerView.layoutManager = manager

        adapter = PlaceAdapter(this, viewModel.placeList)
        recyclerView.adapter = adapter

       searchPlaceEdit.addTextChangedListener(object : TextWatcher{
           override fun afterTextChanged(s: Editable?) {

           }

           override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
           }
           override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
               val content = searchPlaceEdit.text.toString()
               if (content.isNotEmpty()){
                   viewModel.searchPlaces(content)
               }else{
                   recyclerView.visibility = View.GONE
                   bgImageView.visibility = View.VISIBLE
                   viewModel.placeList.clear()
                   adapter.notifyDataSetChanged()
               }
           }
       })

        viewModel.placeLiveData.observe(viewLifecycleOwner, Observer { result ->
            val places = result.getOrNull()
            if (places != null) {
                recyclerView.visibility = View.VISIBLE
                bgImageView.visibility = View.GONE
                viewModel.placeList.clear()
                viewModel.placeList.addAll(places)
                adapter.notifyDataSetChanged()
            } else {
                "未能查询到任何地点".showToast()
                result.exceptionOrNull()?.printStackTrace()
            }
        })
    }
}