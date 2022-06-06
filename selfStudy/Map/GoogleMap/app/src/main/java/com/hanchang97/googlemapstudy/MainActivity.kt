package com.hanchang97.googlemapstudy

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.*
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.ktx.awaitMap
import com.google.maps.android.ktx.awaitMapLoad
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private val places: List<Place> by lazy {
        PlacesReader(this).read()
    }

    private val myPlaces = ArrayList<MyPlace>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myPlaces.add(MyPlace(LatLng(37.48439707576801, 127.03563843382487), 21000))
        myPlaces.add(MyPlace(LatLng(37.4849879809378, 127.03307774731533), 32000))
        myPlaces.add(MyPlace(LatLng(37.483685728052585, 127.0341173714008), 54000))

        val mapFragment = supportFragmentManager.findFragmentById(
            R.id.map_fragment
        ) as? SupportMapFragment

        /* mapFragment?.getMapAsync { googleMap ->
             addMarkers(googleMap)

             // 해당 위,경도 로 지도 이동 && 확대 정도? 정할 수 있음
             googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myPlaces[0].latLng, 16f))
         }

         // getMapAsync 메서드
         // = Sets a callback object which will be triggered when the GoogleMap instance is ready to be used.*/


        // map KTX
        lifecycleScope.launchWhenCreated {
            // Get map
            val googleMap = mapFragment?.let { it.awaitMap() }

            googleMap?.let {
                // Wait for map to finish loading
                it.awaitMapLoad()

                // Ensure all places are visible in the map
                addMarkers(it)
                it.animateCamera(CameraUpdateFactory.newLatLngZoom(myPlaces[0].latLng, 16f))
            }

        }

    }

    private fun addMarkers(googleMap: GoogleMap?) {

        val markerRootView = LayoutInflater.from(this).inflate(R.layout.marker_layout, null)
        val tvMarker = markerRootView.findViewById<TextView>(R.id.tv_price)

        val format = DecimalFormat("₩#,###")

        myPlaces.forEach { place ->
            val marker = googleMap?.let {

                tvMarker.text = format.format(place.price)

                it.addMarker(
                    MarkerOptions()
                        .icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(this, markerRootView)))
                        .position(place.latLng)
                )
            }

        }
    }

    private fun createDrawableFromView(context: Context, view: View): Bitmap {
        /*  val displayMetrics = DisplayMetrics()
          context.display?.getMetrics(displayMetrics)*/

        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager

        val width = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val windowMetrics = wm.currentWindowMetrics
            val insets = windowMetrics.windowInsets
                .getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
            windowMetrics.bounds.width() - insets.left - insets.right
        } else {
            val displayMetrics = DisplayMetrics()
            wm.defaultDisplay.getMetrics(displayMetrics)
            displayMetrics.widthPixels
        }

        val height = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val windowMetrics = wm.currentWindowMetrics
            val insets = windowMetrics.windowInsets
                .getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
            windowMetrics.bounds.height() - insets.bottom - insets.top
        } else {
            val displayMetrics = DisplayMetrics()
            wm.defaultDisplay.getMetrics(displayMetrics)
            displayMetrics.heightPixels
        }

        ////

        view.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        view.measure(width, height)
        view.layout(0, 0, width, height)
        view.buildDrawingCache(false)

        val bitmap = Bitmap.createBitmap(view.measuredWidth, view.measuredHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)

        view.draw(canvas)

        return bitmap
    }

    private fun setCustomMarkerView() {
        val markerRootView = LayoutInflater.from(this).inflate(R.layout.marker_layout, null)
        val tvMarker = markerRootView.findViewById<TextView>(R.id.tv_price)
    }
}