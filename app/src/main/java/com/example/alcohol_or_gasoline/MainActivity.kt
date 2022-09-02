package com.example.alcohol_or_gasoline

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    private lateinit var inputAlcohol: TextInputEditText
    private lateinit var inputGasoline: TextInputEditText

    private lateinit var buttonCalculate: Button

    private lateinit var textAnswer: TextView

    private lateinit var toastSuccess: Toast
    private lateinit var toastError: Toast

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toastSuccess = Toast.makeText(applicationContext, getString(R.string.toast_success), Toast.LENGTH_LONG)
        toastError = Toast.makeText(applicationContext, getString(R.string.toast_error), Toast.LENGTH_LONG)

        findAllViews()
        setListeners()
    }

    private fun calculatePrice(){
        val alcoholPriceText = inputAlcohol.text.toString()
        val gasolinePriceText = inputGasoline.text.toString()

        val isFieldsValidated = validateFields(alcoholPriceText, gasolinePriceText)


        if (isFieldsValidated){
            val alcoholPrice = alcoholPriceText.toDouble()
            val gasolinePrice = gasolinePriceText.toDouble()

            toastSuccess.show()
            val gasolineBetter = ((alcoholPrice / gasolinePrice) >= 0.7)
            if (gasolineBetter)
                textAnswer.text = getString(R.string.main_answer_result, getString(R.string.text_gasoline))
            else
                textAnswer.text = getString(R.string.main_answer_result, getString(R.string.text_alcohol))
        }else{
            toastError.show()
            textAnswer.text = getString(R.string.main_answer_empty)
        }
    }

    private fun validateFields(alcoholPrice: String?, gasolinePrice: String?): Boolean{
        if (alcoholPrice == null || alcoholPrice == ""){
            return false
        }
        if (gasolinePrice == null || gasolinePrice == ""){
            return false
        }

        return true
    }

    private fun findAllViews(){
        inputAlcohol = findViewById(R.id.input_alcohol)
        inputGasoline = findViewById(R.id.input_gasoline)

        buttonCalculate = findViewById(R.id.button_calculate)

        textAnswer = findViewById(R.id.text_answer)
    }

    private fun setListeners(){
        buttonCalculate.setOnClickListener {
            calculatePrice()
        }
    }
}