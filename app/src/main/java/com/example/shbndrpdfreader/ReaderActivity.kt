package com.example.shbndrpdfreader

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.github.barteksc.pdfviewer.PDFView

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