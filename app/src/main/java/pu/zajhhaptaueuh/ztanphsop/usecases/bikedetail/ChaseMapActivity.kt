package pu.zajhhaptaueuh.ztanphsop.usecases.bikedetail

import android.os.Bundle
import android.view.View
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.annotations.MarkerViewOptions
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.maps.MapView
import pu.zajhhaptaueuh.ztanphsop.R
import pu.zajhhaptaueuh.ztanphsop.Secrets
import pu.zajhhaptaueuh.ztanphsop.usecases.BaseActivity


/* Copyright (Constants) million hunters GmbH - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential.
 * Created by Franz Benthin <franz.benthin@fahrradjaeger.de>, 12 2017
 */
class ChaseMapActivity : BaseActivity() {

    data class MapMarkerData(val latitude: Double, val longitude: Double, val title: String, val snippet: String)

    private var mapView: MapView? = null

    private var markers = listOf(
            MapMarkerData(54.074756, 12.139372, "Alpha 1", "Text for the 1 - more text"),
            MapMarkerData(54.087842, 12.133889, "Beta 2", "Text for the 2 - more text"),
            MapMarkerData(54.088811, 12.130595, "Gamma 3", "Text for the 3 - more text"),
            MapMarkerData(54.0913436, 12.1231855, "Delta 4", "Text for the 4 - more text"),
            MapMarkerData(54.0928127, 12.118514, "Epsilon 5", "Text for the 5 - more text"),
            MapMarkerData(54.0891027, 12.0947807, "Zeta 6", "Text for the 6 - more text"),
            MapMarkerData(54.097130, 12.085860, "Eta 7", "Text for the 7 - more text")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Mapbox.getInstance(this, Secrets.MAPBOX_ACCESS_TOKEN)
        setContentView(R.layout.bike_chase_map)
        mapView = findViewById<View>(R.id.mapView) as MapView
        mapView!!.onCreate(savedInstanceState)

        initCamera()
        initMarkers()
        setupInfoWindow()

        setupActionBar()
    }

    private fun setupInfoWindow() {
//        mapboxMap.setInfoWindowAdapter(MapboxMap.InfoWindowAdapter { marker ->
//            // The info window layout is created dynamically, parent is the info window
//            // container
//            val parent = LinearLayout(this@ChaseMapActivity)
//            parent.layoutParams = LinearLayout.LayoutParams(
//                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
//            parent.orientation = LinearLayout.VERTICAL
//
//            // Depending on the marker latitude, the correct image source is used. If you
//            // have many markers using different images, extending Marker and
//            // baseMarkerOptions, adding additional options such as the image, might be
//            // a better choice.
//            val countryFlagImage = ImageView(this@ChaseMapActivity)
//
//            if (TextUtils.equals(marker.title, getString(R.string.custom_window_marker_title_spain))) {
//                countryFlagImage.setImageDrawable(ContextCompat.getDrawable(
//                        this@ChaseMapActivity, R.drawable.flag_of_spain))
//            } else if (TextUtils.equals(marker.title, getString(R.string.custom_window_marker_title_egypt))) {
//                countryFlagImage.setImageDrawable(ContextCompat.getDrawable(
//                        this@ChaseMapActivity, R.drawable.flag_of_egypt))
//            } else {
//                // By default all markers without a matching latitude will use the
//                // Germany flag
//                countryFlagImage.setImageDrawable(ContextCompat.getDrawable(
//                        this@ChaseMapActivity, R.drawable.flag_of_germany))
//            }
//
//            // Set the size of the image
//            countryFlagImage.setLayoutParams(android.view.ViewGroup.LayoutParams(150, 100))
//
//            // add the image view to the parent layout
//            parent.addView(countryFlagImage)
//
//            return parent
//        })

    }

    private fun initCamera() {
//        mapView?.cameraDistance
    }

    private fun initMarkers() {
        mapView?.getMapAsync({ mapboxMap ->
            // One way to add a marker view
            markers.forEach {

                mapboxMap.addMarker(MarkerViewOptions()
                        .position(LatLng(it.latitude, it.longitude))
                        .title(it.title)
                        .snippet(it.snippet)

                )
            }

        })

    }

    public override fun onStart() {
        super.onStart()
        mapView!!.onStart()
    }

    public override fun onResume() {
        super.onResume()
        mapView!!.onResume()
    }

    public override fun onPause() {
        super.onPause()
        mapView!!.onPause()
    }

    public override fun onStop() {
        super.onStop()
        mapView!!.onStop()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView!!.onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView!!.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        mapView!!.onSaveInstanceState(outState!!)
    }
}