package com.example.andpdfrdr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mikepenz.aboutlibraries.LibsBuilder
import com.mikepenz.aboutlibraries.ui.LibsActivity

// Class untuk keperluan license.
class LicenseActivity : LibsActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Caranya berdasarkan dokumentasi, tapi beri judul.
        intent = LibsBuilder()
            .withEdgeToEdge(true)
            .withSearchEnabled(true)
            .withActivityTitle(getString(R.string.third_party_licenses))
            .intent(this)

        super.onCreate(savedInstanceState)
    }
}