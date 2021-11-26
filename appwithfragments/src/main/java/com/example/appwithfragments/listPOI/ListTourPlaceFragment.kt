package com.example.appwithfragments.listPOI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appwithfragments.databinding.FragmentListTourPlaceBinding
import com.example.appwithfragments.models.TourPlace
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException
import java.nio.charset.Charset


class ListTourPlaceFragment : Fragment() {

    private lateinit var listTourPlaceBinding: FragmentListTourPlaceBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        listTourPlaceBinding = FragmentListTourPlaceBinding.inflate(inflater, container, false)
        return listTourPlaceBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
    }

    private fun initRecycler(){
        val recycler: RecyclerView = listTourPlaceBinding.listTourPlace
        val listAdapter= TourPlaceAdapter(createListTourPLace(), onClick={openDetailPlace(it)})
        recycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
            setHasFixedSize(false)
        }
    }
    private fun openDetailPlace(tourPlace: TourPlace) {
        findNavController().navigate(
            ListTourPlaceFragmentDirections
                .actionListTourPlaceFragmentToDetailTourPlaceFragment(tourPlace))
    }
    private fun getJSONFromAssets() : String?{
        var json: String? = null
        val charset: Charset = Charsets.UTF_8
        try {
            val myListTourPlace = context?.assets?.open("list.json")
            val size = myListTourPlace?.available()
            val buffer = size?.let { ByteArray(it) }
            myListTourPlace?.read(buffer)
            myListTourPlace?.close()
            json= buffer?.let { String(it,charset) }
        }catch (ex: IOException){
            ex.printStackTrace()
            return null
        }
        return json
    }

    private fun createListTourPLace() : ArrayList<TourPlace> {
        val listTourPlace : ArrayList<TourPlace> = ArrayList()
        try {
            val placesArray = JSONArray(getJSONFromAssets()!!)
            for (i in 0 until placesArray.length()){
                val placeInfo = placesArray.getJSONObject(i)
                val name = placeInfo.getString("name")
                val description = placeInfo.getString("description")
                val rating = placeInfo.getDouble("rating")
                val img = placeInfo.getString("img")
                val temperature = placeInfo.getString("temperature")
                val location = placeInfo.getString("location")
                val recommendedPlaces = placeInfo.getString("recommendedPlaces")
                val placeDetails= TourPlace(img,name,description,rating,temperature,location,recommendedPlaces)
                listTourPlace.add(placeDetails)
            }
        }catch (e: JSONException){
            e.printStackTrace()
        }
        return listTourPlace
    }
}