package com.dipesh.finalassignment.ui

import android.content.Intent
import android.content.SharedPreferences
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.android.material.snackbar.Snackbar
import com.dipesh.finalassignment.R
import com.dipesh.rblibrary.repository.UserRepo
import com.dipesh.rblibrary.api.ServiceBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity() , SensorEventListener {
    private lateinit var sensorManager: SensorManager
    private var sensor: Sensor? = null
    private lateinit var btnLogin: Button
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var tvForgotPass: TextView
    private lateinit var tvSignup: TextView
    private lateinit var rootLayout: LinearLayout
    private lateinit var checkBox: CheckBox
    var isRemembered = false
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin = findViewById(R.id.btnLogin)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        tvForgotPass = findViewById(R.id.tvForgotPass)
        tvSignup = findViewById(R.id.tvSignup)
        rootLayout = findViewById(R.id.rootLayout)
        checkBox = findViewById(R.id.checkBox)
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        sharedPref = getSharedPreferences("MyPref", MODE_PRIVATE)
        isRemembered = sharedPref.getBoolean("checked", false)
        if (!checkSensor())
            return
        else {
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
        }
        if (isRemembered){
            startActivity(Intent(this@LoginActivity, DashboardActivity::class.java))
        }

        btnLogin.setOnClickListener {
            validate()
            login()
        }

        tvSignup.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
    }
    private fun checkSensor(): Boolean {
        var flag = true
        if (sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY) == null) {
            flag = false
        }
        return flag
    }
    private fun passData(){

    }

    private fun login() {
        val email = etEmail.text.toString()
        val password = etPassword.text.toString()


        CoroutineScope(Dispatchers.IO).launch {
            try {
                val repository = UserRepo()
                val response = repository.loginUser(email, password)
                if (response.success == true) {
                    val id = response.data?._id

                saveEmailPassword()
                    ServiceBuilder.token = "Bearer " + response.token
                    ServiceBuilder.id = id
                    startActivity(
                            Intent(
                                    this@LoginActivity,
                                    DashboardActivity::class.java
                            )
                    )
                    finish()
                } else {
                    withContext(Dispatchers.Main) {
                        val snack =
                                Snackbar.make(
                                        rootLayout,
                                        "Invalid credentials",
                                        Snackbar.LENGTH_LONG
                                )
                        snack.setAction("OK") {
                            snack.dismiss()
                        }
                        snack.show()
                    }
                }

            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                            this@LoginActivity,
                            ex.toString(), Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun saveEmailPassword() {
        val email = etEmail.text.toString()
        val password = etPassword.text.toString()
        val checked = checkBox.isChecked
        val editor:SharedPreferences.Editor = sharedPref.edit()
        editor.putString("email", email)
        editor.putString("password", password)
        editor.putBoolean("checked", checked)
        editor.apply()
    }

    private fun validate(): Boolean {
        if(etEmail.text.toString().isEmpty()){
            etEmail.error = "Blank Email"
            etEmail.requestFocus()
            return false
        }
        else if(etPassword.text.toString().isEmpty()){
            etPassword.error = "Blank Password"
            etPassword.requestFocus()
            return false
        }
        return true
    }

    override fun onSensorChanged(event: SensorEvent?) {
        val values = event!!.values[0]

        if(values<=4)
       login()
        else
          return

    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

}