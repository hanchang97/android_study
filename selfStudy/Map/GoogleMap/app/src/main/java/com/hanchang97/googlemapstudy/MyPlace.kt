package com.hanchang97.googlemapstudy

import com.google.android.gms.maps.model.LatLng

data class MyPlace(
    val latLng: LatLng,
    val price: Int
)