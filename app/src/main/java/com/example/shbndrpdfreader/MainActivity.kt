package com.example.shbndrpdfreader

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

// Class untuk halaman utama.
class MainActivity : AppCompatActivity() {
    // Callback ID untuk request permission.
    private val requestCodeReadExternalStorage = 1001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set judul aplikasi.
        setTitle(getString(R.string.app_name))

        // Inisialisasi views.
        initViews()

        // Cek device permission.
        checkPermission()
    }

    // Inisialisasi views.
    fun initViews() {
        var openFileLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data

                // Buat reader activity intent.
                val intent = Intent(this, ReaderActivity::class.java)

                // kirim data antar activity.
                intent.putExtra("fileToOpen", data?.dataString)

                // Start activity selanjutnya.
                startActivity(intent)
            }
        }

        // Find view dengan ID ini.
        var openFileButton: Button = findViewById<Button>(R.id.openFileButton)

        // Set click listener.
        openFileButton.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view: View?) {
                // Tampilkan file picker.
                val intent = Intent()
                    .setType("application/pdf")
                    .setAction(Intent.ACTION_GET_CONTENT)

                openFileLauncher.launch(intent)
            }
        })

        // Find view dengan ID ini.
        var showLicenseButton: Button = findViewById<Button>(R.id.showLicenseButton);

        // Set click listener.
        showLicenseButton.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view: View?) {
                // Start license activity.
                startActivity(Intent(applicationContext, LicenseActivity::class.java))
            }
        })
    }

    // Cek device permission
    fun checkPermission() {
        // Jika Read External Storage permission belum diberikan.
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            Toast.makeText(
                this,
                getString(R.string.permission_insufficient),
                Toast.LENGTH_SHORT
            ).show()

            // requestCodeReadExternalStorage adalah untuk callback ID.
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                requestCodeReadExternalStorage
            )
        } else {
            // Do nothing.
        }
    }

    // Request Permission Callback
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        // Jika request code cocok dengan requestCodeReadExternalStorage tadi.
        if(requestCode == requestCodeReadExternalStorage){
            // Jika izin diberikan.
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(
                    this,
                    getString(R.string.permission_granted),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}