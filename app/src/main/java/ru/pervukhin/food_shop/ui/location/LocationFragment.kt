package ru.pervukhin.food_shop.ui.location

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.*
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.pervukhin.food_shop.MainActivity
import ru.pervukhin.food_shop.R
import java.util.*


class LocationFragment : Fragment(), LocationListener {
    private lateinit var viewModel: LocationViewModel
    private lateinit var locationTextView: TextView

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_location, container, false)
        viewModel = ViewModelProvider(this).get(LocationViewModel::class.java)
        locationTextView = view.findViewById(R.id.location)
        val date = view.findViewById<TextView>(R.id.date)
        val profile = view.findViewById<ImageView>(R.id.profile)

        val dateText = StringBuffer()
        val dateArray = viewModel.getDate().split(".")
        dateText.append(dateArray[0])
        dateText.append(" ")

        when(dateArray[1]){
            "01" -> dateText.append(resources.getString(R.string.january))
            "02" -> dateText.append(resources.getString(R.string.february))
            "03" -> dateText.append(resources.getString(R.string.march))
            "04" -> dateText.append(resources.getString(R.string.april))
            "05" -> dateText.append(resources.getString(R.string.may))
            "06" -> dateText.append(resources.getString(R.string.june))
            "07" -> dateText.append(resources.getString(R.string.juli))
            "08" -> dateText.append(resources.getString(R.string.august))
            "09" -> dateText.append(resources.getString(R.string.september))
            "10" -> dateText.append(resources.getString(R.string.october))
            "11" -> dateText.append(resources.getString(R.string.november))
            "12" -> dateText.append(resources.getString(R.string.december))
        }

        dateText.append(", ")
        dateText.append(dateArray[2])

        date.text = dateText.toString()
        profile.setImageResource(viewModel.getResId())


        (activity as MainActivity).let {
            context?.let { context ->
                if (ActivityCompat.checkSelfPermission(
                        context,
                        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(
                        context,
                        Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                ) {
                    try {
                        val locationManager =
                            it.getSystemService(Context.LOCATION_SERVICE) as LocationManager
                        locationManager.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER,
                            1,
                            5F,
                            this
                        )

                        locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)?.let {
                            locationTextView.text = getCityByLocation(it)
                        }
                        if (!locationManager.isLocationEnabled){
                            locationTextView.text = resources.getString(R.string.on_gps)
                        }
                    }catch (e: Exception){
                        e.printStackTrace()
                    }
                }else{
                    ActivityCompat.requestPermissions(
                        activity as MainActivity, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION),
                        1
                    )
                }

            }

        }

        return view
    }

    override fun onLocationChanged(p0: Location) {
        locationTextView.text = getCityByLocation(p0)
    }

    private fun getCityByLocation(location: Location): String{
        try {
            val geocoder = Geocoder(context, Locale.getDefault())
            val addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1)
            return addresses[0].subAdminArea.replace("город", "")
        }catch (e: Exception){
            e.printStackTrace()
            return resources.getString(R.string.went_wrong)
        }
    }
}