package com.example.konversiair

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rgDari = findViewById<RadioGroup>(R.id.rgDari)
        val rgKe = findViewById<RadioGroup>(R.id.rgKe)
        val etNilai = findViewById<EditText>(R.id.etNilai)
        val btnHitung = findViewById<Button>(R.id.btnHitung)
        val tvHasil = findViewById<TextView>(R.id.tvHasil)

        btnHitung.setOnClickListener {
            val nilai = etNilai.text.toString()
                .trim()
                .replace(",", ".")
                .toDoubleOrNull()

            if (nilai == null) {
                etNilai.error = "Masukkan nilai terlebih dahulu"
                return@setOnClickListener
            }

            if (nilai <0) {
                etNilai.error = "Nilai tidak boleh negatif"
                return@setOnClickListener
            }

            val pilihanDari = rgDari.checkedRadioButtonId
            val pilihanKe = rgKe.checkedRadioButtonId

            val hasil : Double
            val satuanHasil : String

            if (pilihanDari == R.id.rbDariVolume && pilihanKe == R.id.rbKeMassa) {
                //massa = volume x massa jenis air
                hasil = nilai * 1.0
                satuanHasil = "gram"
            }else if(pilihanDari == R.id.rbDariMassa && pilihanKe == R.id.rbKeVolume) {
                //Volume = massa dibagi massa jenis air
                hasil = nilai /1.0
                satuanHasil = "mL"
            }else{
                Toast.makeText(
                    this, "Pilih jenis konversi yang berbeda",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            val formatAngka = DecimalFormat("0.##")
            val hasilTampil = formatAngka.format(hasil)

            tvHasil.text = "Hasil: $hasilTampil $satuanHasil"
        }
    }
}