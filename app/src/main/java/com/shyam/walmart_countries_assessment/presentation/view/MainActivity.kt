package com.shyam.walmart_countries_assessment.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shyam.walmart_countries_assessment.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * If this is the first launch (savedInstanceState is null),
         * load the CountryListFragment into the container.
         * Prevents adding the fragment again on activity recreation.
         */
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, CountryListFragment())
                .commit()
        }
    }
}