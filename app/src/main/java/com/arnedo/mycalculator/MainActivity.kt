package com.arnedo.mycalculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity




class MainActivity : ComponentActivity() {
    private lateinit var TvResult : TextView
    private var currentNumber = ""
    private var currentOperator = ""
    private var lastNumber = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        TvResult = findViewById(R.id.TvResult)

        val buttons = listOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
            R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9,
            R.id.btnPlus, R.id.btnMinus, R.id.btnMultiply, R.id.btnDivide, R.id.btnEquals,
            R.id.btnDecimal,R.id.btnClear,R.id.btnPercentage
        )

        for(btnId in buttons){
            val button = findViewById<Button>(btnId)
            button.setOnClickListener{
                onButtonClicked(it as Button) }
        }
    }

    private fun onButtonClicked(button: Button) {
        val buttonText = button.text.toString()

        when(buttonText){
            "0", "1","2","3","4","5","6","7","8","9" ->{
                currentNumber += buttonText
                TvResult.text = currentNumber
            }
            "%" ->{
                if(currentNumber.isNotEmpty()){
                    val num = currentNumber.toDouble()
                    val result = num / 100
                    currentNumber = result.toString()
                    TvResult.text = currentNumber
                }
            }
            "." ->{
                if(!currentNumber.contains(".")){
                    currentNumber += "."
                    TvResult.text = currentNumber
                }
            }
            "+","-","*","/" ->{
                if(currentNumber.isNotEmpty()){
                    lastNumber = currentNumber
                    currentNumber = ""
                    currentOperator = buttonText
                }
            }
            "=" -> {
                if(currentNumber.isNotEmpty() && lastNumber.isNotEmpty()){
                    val num1 = lastNumber.toDouble()
                    val num2 = currentNumber.toDouble()
                    val result = when(currentOperator){
                        "+" -> num1 + num2
                        "-" -> num1 - num2
                        "*" -> num1 * num2
                        "/" -> num1 / num2
                        else -> 0.0
                    }
                    TvResult.text = result.toString()
                    currentNumber = result.toString()
                    lastNumber = ""
                    currentOperator = ""
                }
            }
            "C" ->{
                currentNumber = ""
                lastNumber = ""
                currentOperator = ""
                TvResult.text = "0"

            }
        }
    }
}