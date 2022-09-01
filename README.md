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
	
