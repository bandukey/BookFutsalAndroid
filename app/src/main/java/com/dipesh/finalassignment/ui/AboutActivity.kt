package com.dipesh.finalassignment.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import com.dipesh.finalassignment.R

class AboutActivity : AppCompatActivity() {

    private lateinit var web: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        web = findViewById(R.id.web)

        web.loadUrl("https://www.facebook.com/dhukufutsal/")
    }
}