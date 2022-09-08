package com.example.fitness_app

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.fitness_app.model.App
import com.example.fitness_app.model.Calc
import kotlin.math.roundToInt

class IMC_Activity : AppCompatActivity() {

    private lateinit var editPeso : EditText
    private lateinit var editAltura : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc)

        val btnCalculate : Button = findViewById(R.id.btn_calculate)
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
            val calcImc = calculoImc(editPeso.text.toString().toInt(), editAltura.text.toString().toInt())
            val respostaUser = respostImc(calcImc.toFloat())

            val dialog = AlertDialog.Builder(this)
                .setTitle("Seu IMC é: $calcImc")
                .setMessage(respostaUser)
                .setPositiveButton(android.R.string.ok, object : DialogInterface.OnClickListener{
                    override fun onClick(p0: DialogInterface?, p1: Int) {

                    }
                })
                .setNegativeButton(R.string.save) { dialog, which ->
                    Thread{
                        var app = application as App
                        var dao = app.db.calcDao()
                        dao.insert(Calc(tipo = "IMC", res = calcImc))

                        runOnUiThread {
                            Toast.makeText(this@IMC_Activity, R.string.salved, Toast.LENGTH_LONG).show()
                        }
                    }.start()

                }
                .create()
                .show()
        }
    }

    private fun calculoImc(peso : Int, altura : Int) : Float{
        val resposta = (peso / ( (altura / 100.0) * (altura / 100.0) )).roundToInt() / 1.00
        return resposta.toFloat()
    }

    private fun respostImc(peso : Float) : Int{
        when {
            peso < 15.0 -> return R.string.severamente_abaixo_peso
            peso < 16.0 -> return R.string.muito_abixo_peso
            peso < 18.5 -> return R.string.abaixo_peso
            peso < 25.0 -> return R.string.ideal
            peso < 30.0 -> return R.string.acima_peso
            peso < 35.0 -> return R.string.muito_acima_peso
            peso < 40.0 -> return R.string.severamente_acima_peso
            else -> return R.string.extremamente_peso
        }
    }

}