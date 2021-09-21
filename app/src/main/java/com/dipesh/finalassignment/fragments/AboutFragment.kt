package com.dipesh.finalassignment.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.dipesh.finalassignment.R
import com.dipesh.finalassignment.ui.AboutActivity
import com.dipesh.finalassignment.ui.FeedbackActivity
import com.dipesh.finalassignment.ui.GoogleMapsActivity
import com.dipesh.finalassignment.ui.ThemeActivity

class AboutFragment : Fragment() {

    private lateinit var about: TextView
    private lateinit var maplocation: TextView
    private lateinit var feedbacks: TextView
    private lateinit var theme: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_about, container, false)

        about = view.findViewById(R.id.about)
        maplocation = view.findViewById(R.id.maplocation)
        feedbacks = view.findViewById(R.id.feedbacks)
        theme = view.findViewById(R.id.theme)

        about.setOnClickListener {
            val intent = Intent(activity, AboutActivity::class.java)
            startActivity(intent);
        }

        feedbacks.setOnClickListener{
            val intent = Intent(activity, FeedbackActivity::class.java)
            startActivity(intent)
        }

        theme.setOnClickListener{
            val intent = Intent(activity, ThemeActivity::class.java)
            startActivity(intent)
        }

        maplocation.setOnClickListener{
            val intent = Intent(activity, GoogleMapsActivity::class.java)
            startActivity(intent)
        }

        return view
    }

}