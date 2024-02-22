package com.example.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.calculatorapp.Constants.MAX_DIGIT_RESULT
import com.example.calculatorapp.databinding.ActivityMainBinding
import org.mariuszgromada.math.mxparser.Expression
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bindView()
    }

    private fun bindView() {
        binding.rcvCalculation.apply {
            adapter = MainAdapter(mainViewModel.dataButton, this@MainActivity, onItemClick)
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(this@MainActivity, 4)
        }
    }

    private val onItemClick: (item: String) -> Unit = {
        val listInput = mainViewModel.dataInput
        if (it == "C") {
            binding.run {
                tvResult.text = "0"
                tvCalculation.text = ""
            }
        }

        if(it == "X") {
            val calculation = binding.tvCalculation.text
            if (calculation.isNotEmpty()) {
                binding.tvCalculation.text = calculation.dropLast(1)
            }
        }

        if (listInput.contains(it)) {
            binding.tvCalculation.text = addToTvCalculation(it)
        }

        if (it == "=") {
            showResult()
        }
    }

    private fun addToTvCalculation(buttonValue: String): String {
        return "${binding.tvCalculation.text}$buttonValue"
    }

    private fun getInputExpression(): String {
        return binding.tvCalculation.text.replace(Regex("x"), "*")
    }

    private fun showResult() {
        try {
            val expression = getInputExpression()
            val result = Expression(expression).calculate()
            if (result.isNaN()) {
                binding.tvResult.text = getString(R.string.error)
            } else {
                if (result.toString().length > MAX_DIGIT_RESULT) {
                    binding.tvResult.text = DecimalFormat("0.####E0").format(result).toString()
                } else {
                    binding.tvResult.text = DecimalFormat("0.######").format(result).toString()
                }
            }
        } catch (e: Exception) {
            binding.tvResult.text = getString(R.string.error)
        }
    }
}