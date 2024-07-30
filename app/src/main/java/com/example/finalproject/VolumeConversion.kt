package com.example.finalproject


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class VolumeConversion : AppCompatActivity() {

    private lateinit var textview: TextView
    private lateinit var textview4: TextView
    private lateinit var inputValueEditable: EditText
    private lateinit var inputValueEditable2: EditText
    private lateinit var tvHistory: TextView
    private lateinit var convertButton: Button
    private lateinit var toolbar: Toolbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.conversion2)

        textview = findViewById(R.id.textView3)
        inputValueEditable = findViewById(R.id.inputValueEditable)
        inputValueEditable2 = findViewById(R.id.inputValueEditable2)
        textview4 = findViewById(R.id.textView4)
        tvHistory = findViewById(R.id.tvHistory2)
        convertButton = findViewById(R.id.Convert_button)

        textview.text = "Cups"
        textview4.text = "Milliliters"
        setupTextWatchers()

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Volume"

        convertButton.setOnClickListener {
            performConversion()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish() // Close the current activity and go back to the previous one
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    private fun setupTextWatchers() {
        inputValueEditable.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (start == 0 && count == 1) {
                    inputValueEditable2.setText("")
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        inputValueEditable2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (start == 0 && count == 1) {
                    inputValueEditable.setText("")
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun performConversion() {
        val CupsInput = inputValueEditable.text.toString().toDoubleOrNull()
        val MillilitersInput = inputValueEditable2.text.toString().toDoubleOrNull()

        if (CupsInput != null) {
            val kmResult = millilitersToCups(CupsInput)
            inputValueEditable2.setText(String.format("%.1f", kmResult))
            updateHistory(CupsInput, kmResult, "Mil", "Cups")

        } else if (MillilitersInput != null) {
            val milesResult = cupsToMilliliters(MillilitersInput)
            inputValueEditable.setText(String.format("%.1f", milesResult))
            updateHistory(MillilitersInput, milesResult, "Cups", "Mil")
        }
    }

    private fun millilitersToCups(ml: Double): Double {
        return (ml / 240).toOneDecimalPlace()
    }
    private fun cupsToMilliliters(cups: Double): Double {
        return (cups * 240).toOneDecimalPlace()
    }


    private fun updateHistory(input: Double, result: Double, inputUnit: String, resultUnit: String) {
        val historyEntry = "${inputUnit}: $input --> ${resultUnit}: $result\n"
        tvHistory.text = historyEntry + tvHistory.text
    }


    private fun Double.toOneDecimalPlace(): Double {
        return String.format("%.1f", this).toDouble()
    }
}