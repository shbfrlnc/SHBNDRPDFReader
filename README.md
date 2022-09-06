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

Kali ini, saya akan memberikan contoh aplikasi Android yang dapat membuka dan menampilkan file PDF.

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