package com.example.tiptime

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener { calculateTip() }
    }
    private fun calculateTip() {
            val stringInTextField = binding.costOfService.text.toString()
            val cost = stringInTextField.toDoubleOrNull()
        if (cost==null){
            binding.tipResult.text=""
            return
        }
        val tipPercentage = when (binding.tipOption.checkedRadioButtonId) {
                R.id.option_twenty_percent -> 0.20
                R.id.option_fifteen_percent -> 0.15
                else -> 0.10
            }
            var tip = tipPercentage * cost
            if (binding.roundUp.isChecked) {
                tip = kotlin.math.ceil(tip)
            }
            val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
            binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
        }
    }
