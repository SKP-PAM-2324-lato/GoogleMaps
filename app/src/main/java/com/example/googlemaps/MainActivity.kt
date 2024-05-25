package com.example.googlemaps

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var latitudeInput: EditText
    private lateinit var longitudeInput: EditText
    private lateinit var updateLocationButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        latitudeInput = findViewById(R.id.latitude)
        longitudeInput = findViewById(R.id.longitude)
        updateLocationButton = findViewById(R.id.button)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        updateLocationButton.setOnClickListener {
            val latString = latitudeInput.text.toString()
            val longString = longitudeInput.text.toString()

            val latitude = latString.toDoubleOrNull()
            val longitude = longString.toDoubleOrNull()

            latitude?.let { a ->
                longitude?.let {b->
                    val newLocation = LatLng(a, b)
                    mMap.addMarker(MarkerOptions().position(newLocation))
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(newLocation, 3.0F))
                }
            }

        }
    }

    override fun onMapReady(p0: GoogleMap) {
        mMap = p0
        val wroclaw = LatLng(51.1, 17.05)
        mMap.addMarker(MarkerOptions().position(wroclaw))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(wroclaw, 10.0F))
    }
}