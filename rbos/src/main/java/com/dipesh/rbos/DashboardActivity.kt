package com.dipesh.rbos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.dipesh.rblibrary.api.ServiceBuilder
import com.dipesh.rblibrary.repository.CartRepo
import com.dipesh.rblibrary.repository.UserRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DashboardActivity : AppCompatActivity() {

    private lateinit var name: TextView
    private lateinit var back: ImageButton
    private lateinit var number: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        name = findViewById(R.id.name)
        back = findViewById(R.id.back)
        number = findViewById(R.id.number)

        loadUserDetails()
        loadItems()

        back.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            }

    }

    private fun loadUserDetails() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val userRepo = UserRepo()
                val response = userRepo.getMe()
                if (response.success == true){
                    val fName = response.data?.firstName
//                    val profilePic = response.data?.photo
                    val imagePath = ServiceBuilder.loadImagePath() + response.data?.photo
                    withContext(Dispatchers.Main){
                        name.text = fName
                    }
                }
            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@DashboardActivity,
                            "Error : ${ex.toString()}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun loadItems() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val cartRepo = CartRepo()
                val response = cartRepo.getCartItems()
                if (response.success == true){
                    val items = response.count
                    withContext(Dispatchers.Main){
                        number.text = items.toString()
                    }
                }
            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@DashboardActivity,
                            "Error : ${ex.toString()}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}