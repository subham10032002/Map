package com.example.map

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.location.Location
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*


class NavFragment : Fragment() {

     var currentLocation : Location? = null
    var fusedLocationProviderClient: FusedLocationProviderClient?  = null
    var REQUEST_CODE = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val rootView = inflater.inflate(R.layout.fragment_nav, container, false)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext())

        fetchLocation()


        return rootView
    }

    private fun fetchLocation() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),REQUEST_CODE)
            return
        }

        val task = fusedLocationProviderClient!!.lastLocation
        task.addOnSuccessListener {location ->
            if(location != null)
            {
                currentLocation = location
                val mapFragment =
                    childFragmentManager.findFragmentById(R.id.mapFrg) as SupportMapFragment?  //use SuppoprtMapFragment for using in fragment instead of activity  MapFragment = activity   SupportMapFragment = fragment
                mapFragment!!.getMapAsync { mMap ->
                    mMap.mapType = GoogleMap.MAP_TYPE_NORMAL

                    mMap.clear() //clear old markers

                    val latLng = LatLng(currentLocation!!.latitude, currentLocation!!.longitude)
                    val markerOptions = MarkerOptions().position(latLng).title("I Am Here!")
                    mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng))
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,15f))
                    mMap.addMarker(markerOptions)



                }
            }

        }

    }



    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            REQUEST_CODE -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    fetchLocation()
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }





}