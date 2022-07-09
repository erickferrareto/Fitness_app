package com.example.fitness_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class IMC_Activity : AppCompatActivity() {

    private lateinit var editPeso : EditText
    private lateinit var editAltura : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc)

        var btnCalculate : Button = findViewById(R.id.btn_calculate)
        editPeso = findViewById(R.id.editPeso)
        editAltura = findViewById(R.id.editAltura)

        btnCalculate.setOnClickListener{
            validateFormu()
        }

    }

    private fun validateFormu(){
        //Validando os dados
        if (
            editPeso.text.toString().isEmpty()
            || editAltura.text.toString().isEmpty()
            || editPeso.text.toString().startsWith("0")
            || editAltura.text.toString().startsWith("0")
        ){
            Toast.makeText(this, "Valor inserido errado", Toast.LENGTH_SHORT).show()
        } else {
            calculoImc(editPeso.text.toString().toInt(), editAltura.text.toString().toInt())
        }
    }

    private fun calculoImc(peso : Int, altura : Int) : Double{
        val resposta = peso / ( (altura / 100.0) * (altura / 100.0) )
        return resposta.toDouble()
    }

}