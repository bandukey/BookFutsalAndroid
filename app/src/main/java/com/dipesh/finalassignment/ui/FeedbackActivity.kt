package com.dipesh.finalassignment.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.dipesh.finalassignment.R
import com.dipesh.rblibrary.entity.Feedback
import com.dipesh.rblibrary.repository.FeedbackRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FeedbackActivity : AppCompatActivity() {

    private lateinit var recipient: EditText
    private lateinit var subject: EditText
    private lateinit var message: EditText
    private lateinit var btnsubmit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)

        recipient = findViewById(R.id.recipient)
        subject = findViewById(R.id.subject)
        message = findViewById(R.id.message)
        btnsubmit = findViewById(R.id.btnsubmit)

        btnsubmit.setOnClickListener {

            val sub = subject.text.toString()
            val message = message.text.toString()

            val feedback = Feedback(subject= sub,message= message)

            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val feedRepo = FeedbackRepo()
                    val response = feedRepo.addFeedback(feedback)
                    if(response.success == true){

                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                this@FeedbackActivity,
                                "Feedback Added", Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                } catch (ex: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@FeedbackActivity,
                            ex.toString(), Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
}