package com.example.finalproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var weight_conversion_button: ImageButton
    private lateinit var distance_conversion_button: ImageButton
    private lateinit var time_conversion_button: ImageButton
    private lateinit var temperature_conversion_button: ImageButton
    private lateinit var length_conversion_button: ImageButton
    private lateinit var volume_conversion_button: ImageButton
    private lateinit var exit_button: Button


    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)


        weight_conversion_button = findViewById(R.id.imageButtonWeight)
        distance_conversion_button = findViewById(R.id.imageButtonDistance)
        time_conversion_button = findViewById(R.id.imageButtonTime)
        temperature_conversion_button = findViewById(R.id.imageButtonTemperature)
        length_conversion_button = findViewById(R.id.imageButtonLength)
        volume_conversion_button = findViewById(R.id.imageButtonVolume)
        exit_button = findViewById(R.id.exit_button)



        weight_conversion_button.setOnClickListener {
           val intent = Intent(this@MainActivity, MultiConversionweight::class.java)
            startActivity(intent)
       }

        distance_conversion_button.setOnClickListener {
            val intent = Intent(this@MainActivity, DistanceConversion::class.java)
            startActivity(intent)
        }

        time_conversion_button.setOnClickListener {
            val intent = Intent(this@MainActivity, TimeConversion::class.java)
            startActivity(intent)
        }

        temperature_conversion_button.setOnClickListener {
            val intent = Intent(this@MainActivity, TempConversion::class.java)
            startActivity(intent)
        }
        length_conversion_button.setOnClickListener {
            val intent = Intent(this@MainActivity, MultiConversionlength::class.java)
            startActivity(intent)
        }
        volume_conversion_button.setOnClickListener {
            val intent = Intent(this@MainActivity, VolumeConversion::class.java)
            startActivity(intent)
        }

        exit_button.setOnClickListener {
            finishAffinity()
        }

}
}
