# ANDPDFRDR - Aplikasi Android untuk Menampilkan File PDF

## Software Apakah Ini?

ANDPDFRDR adalah aplikasi Android untuk menampilkan file PDF.

## Screenshot

![ScreenShot](.readme-assets/ANDPDFRDR-Screenshot_20220829_203556.png?raw=true)

## Cara Mencoba Kode Ini

Untuk mencoba kode ini, Anda memerlukan Android Studio.

Bukalah folder ini di Android Studio.

Kemudian, build dan jalankan.

## Pendahuluan

Kali ini, kita akan belajar membuat aplikasi Android yang dapat membuka dan menampilkan file PDF.

File PDF nya bisa dibuka melalui sebuah activity yang menampilkan daftar file.

## Cara Kerja

Aplikasi ini menggunakan library yang bernama android-pdf-viewer untuk menampilkan file PDF yang bisa kita dapatkan di sini:

https://github.com/barteksc/AndroidPdfViewer

Selain itu, untuk keperluan menampilkan daftar license dari library yang kita gunakan dalam aplikasi ini, kita menggunakan aboutlibraries, yang bisa kita dapatkan di sini:

https://github.com/mikepenz/AboutLibraries

Pada saat aplikasi ini dibuka, aplikasi ini menampilkan dua buah button.

Button yang pertama adalah untuk membuka file explorer untuk memilih dan membuka file PDF.

Button yang kedua adalah untuk menampilkan license activity untuk menampilkan license dari library yang kita gunakan.

## Struktur Project

Struktur project aplikasi ini terdiri dari banyak jenis file, namun hanya beberapa saja yang perlu kita perhatikan.

### File-File Kotlin

File-file Kotlin yang ada dalam project ini dan perlu diperhatikan adalah:

- LicenseActivity.kt
	- File ini berfungsi untuk menampilkan License Activity yang menggunakan aboutlibraries.
- ReaderActivity.kt
	- File ini berfungsi untuk menampilkan file PDF yang didapat melalui file explorer Android.
- MainActivity.kt
	- File ini berfungsi untuk menampilkan dua buah Button untuk keperluan tadi.
	
### File-File Resources

File-file resource yang ada dalam project ini dan perlu diperhatikan adalah:

- activity_license.xml
	- Ini adalah file layout untuk License Activity.
- activity_reader.xml
	- Ini adalah file layout untuk Reader Activity.
- activity_main.xml
	- Ini adalah file layout untuk Main Activity.
	
### File-File Gradle

File-file Gradle yang ada dalam project ini dan perlu diperhatikan adalah:

- build.gradle yang ada di folder Root atau folder utama.
- build.gradle yang ada di folder app.
	
### File Manifest

Selain itu, ada file AndroidManifest.xml yang tujuan utamanya adalah untuk mendapatkan izin READ_EXTERNAL_STORAGE.

File tersebut bernama AndroidManifest.xml.

## Penjelasan

Penjelasan kode ini adalah melalui komentar.

Di bawah ini saya tampilkan file-file yang tadi saya tulis dengan komentarnya.

### File LicenseActivity.kt

```
// file: LicenseActivity.kt

package com.example.andpdfrdr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mikepenz.aboutlibraries.LibsBuilder
import com.mikepenz.aboutlibraries.ui.LibsActivity

// Class untuk keperluan legalitas.
class LicenseActivity : LibsActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Berdasarkan dokumentasi, tapi beri judul.
        intent = LibsBuilder()
            .withEdgeToEdge(true)
            .withSearchEnabled(true)
            .withActivityTitle(getString(R.string.third_party_licenses))
            .intent(this)

        super.onCreate(savedInstanceState)
    }
}
```

### File ReaderActivity.kt

```
// file: ReaderActivity.kt

package com.example.andpdfrdr

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.github.barteksc.pdfviewer.PDFView
import java.io.File

// Class untuk menampilkan PDF.
class ReaderActivity : AppCompatActivity() {
    private lateinit var myPDFView: PDFView;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reader)

        // Dapatkan data yang ditransfer dari activity sebelumnya.
        val path = intent.getStringExtra("fileToOpen")

        // Tampilkan data tadi di dalam Toast.
        Toast.makeText(
            this,
            path,
            Toast.LENGTH_SHORT
        ).show()

        // Temukan PDF View dari XML layout.
        myPDFView = findViewById<PDFView>(R.id.pdfView);

        // Tampilkan PDF melalui URI.
        myPDFView.fromUri(Uri.parse(path))
            .defaultPage(0)
            .enableSwipe(true)
            .swipeHorizontal(false)
            .enableAnnotationRendering(true)
            .load();
    }
}
```

### File MainActivity.kt

```
// file: MainActivity.kt

package com.example.andpdfrdr

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
```

### File activity_license.xml

```
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".LicenseActivity">

</androidx.constraintlayout.widget.ConstraintLayout>
```

File ini adalah tempat aboutlibraries menampilkan daftar license.

Tidak diperlukan widget tambahan di situ.

### File activity_reader.xml

```
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".ReaderActivity">

    <com.github.barteksc.pdfviewer.PDFView
        android:id="@+id/pdfView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>

</androidx.constraintlayout.widget.ConstraintLayout>
```

File ini adalah tempat android-pdf-viewer menempatkan file PDF untuk ditampilkan.

Untuk menempatkan file PDF untuk ditampilkan, diperlukan:

```
<com.github.barteksc.pdfviewer.PDFView
        android:id="@+id/pdfView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
```

### File activity_main.xml

```
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@drawable/background"
    tools:context=".MainActivity">

    <!--<com.github.barteksc.pdfviewer.PDFView-->
    <!--    android:id="@+id/pdfView"-->
    <!--    android:layout_width="match_parent"-->
    <!--    android:layout_height="match_parent"/>-->

    <Button
        android:id="@+id/showLicenseButton"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:text="@string/third_party_licenses"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.497"
        app:layout_constraintStart_toStartOf="parent" />

    <Button
        android:id="@+id/openFileButton"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:text="@string/open_file"
        app:layout_constraintBottom_toTopOf="@+id/showLicenseButton"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.498"
        app:layout_constraintStart_toStartOf="parent" />
</androidx.constraintlayout.widget.ConstraintLayout>
```

File ini adalah tempat kedua Button didefinisikan.

Jangan pedulikan bagian ini (bisa Anda hapus):

```
<!--<com.github.barteksc.pdfviewer.PDFView-->
    <!--    android:id="@+id/pdfView"-->
    <!--    android:layout_width="match_parent"-->
    <!--    android:layout_height="match_parent"/>-->
```

### File build.gradle (Root)

```
// Top-level build file where you can add configuration options common to all sub-projects/modules.
// Perhatikan bahwa kita menyertakan aboutlibraries di sini.
plugins {
    id 'com.android.application' version '7.1.2' apply false
    id 'com.android.library' version '7.1.2' apply false
    id 'org.jetbrains.kotlin.android' version '1.6.21' apply false
    id 'com.mikepenz.aboutlibraries.plugin' version "10.1.0"
}

task clean(type: Delete) {
    delete rootProject.buildDir
}
```

### File build.gradle (App)

```
// Perhatikan bahwa kita menyertakan aboutlibraries di sini.
plugins {
    id 'com.android.application'
    id 'org.jetbrains.kotlin.android'
    id 'com.mikepenz.aboutlibraries.plugin'
}

android {
    compileSdk 32

    defaultConfig {
        applicationId "com.example.andpdfrdr"
        minSdk 21
        targetSdk 32
        versionCode 1
        versionName "1.0"

        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = '1.8'
    }
}

// Perhatikan bahwa kita menyertakan aboutlibraries dan android-pdf-viewer di sini.
dependencies {

    implementation 'androidx.core:core-ktx:1.7.0'
    implementation 'androidx.appcompat:appcompat:1.5.0'
    implementation 'com.google.android.material:material:1.6.1'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
    testImplementation 'junit:junit:4.13.2'
    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'

    implementation 'com.github.barteksc:android-pdf-viewer:2.8.2'
    implementation "com.mikepenz:aboutlibraries-core:10.1.0"
    implementation "com.mikepenz:aboutlibraries:10.1.0"
    implementation "com.mikepenz:aboutlibraries-compose:10.1.0"
}
```
