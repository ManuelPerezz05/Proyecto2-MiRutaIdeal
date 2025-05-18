package com.example.mirutaideal

import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import android.util.Log
import com.tomtom.sdk.location.GeoLocation

class MyLocationListener(
    private val geocoder: Geocoder,  // Pass geocoder as dependency for reverse geocoding
) : LocationListener {

    override fun onLocationChanged(location: Location) {
        // Log the location or update the UI
        Log.d("Location", "Location changed: ${location.latitude}, ${location.longitude}")

        // Run reverse geocoding in a background thread to avoid blocking the main UI thread
        Thread {
            try {
                // Use the Geocoder to get the address from latitude and longitude
                val addresses: List<Address>? =
                    geocoder.getFromLocation(location.latitude, location.longitude, 1)

                if (addresses != null && addresses.isNotEmpty()) {
                    val address = addresses[0] // Get the first address from the list

                    // Log or update the UI with the formatted address
                    Log.d("Location", "Address: ${address.getAddressLine(0)}")


                } else {
                    Log.d("Location", "No address found for the location")
                }
            }catch (e: Exception){
                Log.e("MyLocationListener", "Error in reverse geocoding", e)
            }

        }
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}

    override fun onProviderEnabled(provider: String) {}

    override fun onProviderDisabled(provider: String) {}
}
