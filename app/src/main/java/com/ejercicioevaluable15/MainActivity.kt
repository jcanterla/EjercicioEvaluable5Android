package com.ejercicioevaluable15

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var mostrarPantalla: TextView
    private var primerValor: Double = 0.0
    private var operacionActual: String = ""
    private var operacionClickada: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        mostrarPantalla = findViewById(R.id.mostrarPantalla)

        val numerosId = intArrayOf(
            R.id.boton0, R.id.boton1, R.id.boton2, R.id.boton3, R.id.boton4,
            R.id.boton5, R.id.boton6, R.id.boton7, R.id.boton8, R.id.boton9
        )

        val numeroPulsado = { v: android.view.View ->
            val boton = v as Button
            var textoActual = mostrarPantalla.text.toString()
            if (operacionClickada || textoActual == "0") {
                textoActual = ""
            }
            mostrarPantalla.text = textoActual + boton.text
            operacionClickada = false
        }

        for (id in numerosId) {
            findViewById<Button>(id).setOnClickListener(numeroPulsado)
        }

        val operacionesBotonesId = intArrayOf(
            R.id.botonSumar, R.id.botonRestar, R.id.botonMultiplicar, R.id.botonDividir
        )

        val operacionPulsado = { v: android.view.View ->
            val boton = v as Button
            primerValor = mostrarPantalla.text.toString().toDouble()
            operacionActual = boton.text.toString()
            operacionClickada = true
        }

        for (id in operacionesBotonesId) {
            findViewById<Button>(id).setOnClickListener(operacionPulsado)
        }

        findViewById<Button>(R.id.botonIgual).setOnClickListener {
            val segundoValor = mostrarPantalla.text.toString().toDouble()
            var resultado = 0.0
            when (operacionActual) {
                "+" -> resultado = primerValor + segundoValor
                "-" -> resultado = primerValor - segundoValor
                "*" -> resultado = primerValor * segundoValor
                "/" -> {
                    if (segundoValor != 0.0) {
                        resultado = primerValor / segundoValor
                    } else {
                        mostrarPantalla.text = "Error"
                        return@setOnClickListener
                    }
                }
            }
            mostrarPantalla.text = resultado.toString()
            operacionClickada = true
        }

        findViewById<Button>(R.id.botonDecimal).setOnClickListener {
            val textoActual = mostrarPantalla.text.toString()
            if (!textoActual.contains(".")) {
                mostrarPantalla.text = textoActual + "."
            }
        }

        findViewById<Button>(R.id.botonBorrar).setOnClickListener {
            mostrarPantalla.text = "0"
            primerValor = 0.0
            operacionActual = ""
            operacionClickada = false
        }


    }
}