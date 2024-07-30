package com.example.finalproject

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class MultiConversionweight : AppCompatActivity() {

    private lateinit var spinner: Spinner
    private lateinit var spinner2: Spinner
    private lateinit var inputValueEditable3: EditText
    private lateinit var inputValueEditable4: EditText
    private lateinit var tvHistory: TextView
    private lateinit var convertButton: Button
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.conversion)

        setupViews()
        setupSpinners()
        setupTextWatchers()

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Weight"

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

    private fun setupViews() {
        inputValueEditable3 = findViewById(R.id.inputValueEditable3)
        inputValueEditable4 = findViewById(R.id.inputValueEditable4)
        tvHistory = findViewById(R.id.tvHistory2)
        convertButton = findViewById(R.id.Convert_button2)
    }

    private fun setupSpinners() {
        spinner = findViewById(R.id.spinner1)
        spinner2 = findViewById(R.id.spinner2)

        val units = arrayOf("Grams", "Ounces", "Kilograms", "Pounds")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, units)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner.adapter = adapter
        spinner2.adapter = adapter
    }

    private fun setupTextWatchers() {
        inputValueEditable3.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (start == 0 && count == 1) {
                    inputValueEditable4.setText("")
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        inputValueEditable4.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (start == 0 && count == 1) {
                    inputValueEditable3.setText("")
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun performConversion() {
        if (!inputValueEditable3.text.isBlank()) {
            convertAndDisplay(inputValueEditable3, inputValueEditable4, spinner, spinner2)
        } else if (!inputValueEditable4.text.isBlank()) {
            convertAndDisplay(inputValueEditable4, inputValueEditable3, spinner2, spinner)
        }
    }

    private fun convertAndDisplay(inputField: EditText, outputField: EditText, fromSpinner: Spinner, toSpinner: Spinner) {
        val inputText = inputField.text.toString()
        val inputValue = inputText.toDoubleOrNull()
        if (inputValue == null) {
            Toast.makeText(this, "Invalid input. Please enter a numeric value.", Toast.LENGTH_LONG).show()
            return
        }

        val fromUnit = fromSpinner.selectedItem.toString()
        val toUnit = toSpinner.selectedItem.toString()
        val result = convert(inputValue, fromUnit, toUnit).toOneDecimalPlace()
        outputField.setText(result.toString())

        updateHistory(inputValue, result, fromUnit, toUnit)
    }

    private fun convert(value: Double, fromUnit: String, toUnit: String): Double {
        // Conversion factors
        val gramsPerOunce = 28.3495
        val gramsPerKilogram = 1000.0
        val gramsPerPound = 453.592

        // Convert input to grams first
        val grams = when (fromUnit) {
            "Ounces" -> value * gramsPerOunce
            "Kilograms" -> value * gramsPerKilogram
            "Pounds" -> value * gramsPerPound
            else -> value  // Assuming the input is in grams if 'Grams' is selected
        }

        // Convert grams to the target unit
        return when (toUnit) {
            "Ounces" -> grams / gramsPerOunce
            "Kilograms" -> grams / gramsPerKilogram
            "Pounds" -> grams / gramsPerPound
            else -> grams  // Returning grams if 'Grams' is selected
        }
    }

    private fun Double.toOneDecimalPlace(): Double {
        return String.format("%.1f", this).toDouble()
    }


    private fun updateHistory(input: Double, result: Double, fromUnit: String, toUnit: String) {
        val historyEntry = "$fromUnit: $input  -->  $toUnit: $result \n"
        tvHistory.append(historyEntry)
    }

}
