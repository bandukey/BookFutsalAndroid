package com.dipesh.finalassignment.ui

import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.dipesh.finalassignment.R
import com.dipesh.finalassignment.fragments.*
import java.lang.Exception

class DashboardActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var sensorManager: SensorManager
    private var sensor: Sensor? = null
    private lateinit var tvLight: TextView
    private val permissions = arrayOf(
            android.Manifest.permission.CAMERA,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.ACCESS_FINE_LOCATION
    )


    private lateinit var botomNav: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        botomNav = findViewById(R.id.botomNav)
        tvLight = findViewById(R.id.light)

        currentFragment(HomeFragment())

        botomNav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home -> currentFragment(HomeFragment())
                R.id.account -> currentFragment(ProfileFragment())
                R.id.basket -> currentFragment(AddToCartFragment())
//                R.id.basket -> startActivity(Intent(this, InsertFutsalActivity::class.java))
                R.id.aboutus -> currentFragment(AboutFragment())
            }
            true
        }

        if (!hasPermission()) {
            requestPermission()
        }
        if (!checkSensor())
            return
        else {
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    private fun checkSensor(): Boolean {
        var flag = true
        if (sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT) == null) {
            flag = false
        }
        return flag

    }

    private fun hasPermission(): Boolean {
        var hasPermission = true
        for (permission in permissions) {
            if (ActivityCompat.checkSelfPermission(
                            this,
                            permission
                    ) != PackageManager.PERMISSION_GRANTED
            ) {
                hasPermission = false
            }
        }
        return hasPermission
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
                this,
                permissions, 123
        )
    }

    private fun currentFragment(fragment: Fragment) {
        try {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.container, fragment)
                addToBackStack(null)
                commit()
            }
        }
        catch (ex:Exception){
            Toast.makeText(this, ex.message, Toast.LENGTH_LONG).show()
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        val values = event!!.values[0]
        tvLight.text = values.toString()

    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }
}