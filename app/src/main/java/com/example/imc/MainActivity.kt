package com.example.imc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.text.DecimalFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { Calculadora() }
    }
}

@Composable
fun Calculadora() {
    var peso by remember { mutableStateOf("") }
    var estatura by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }
    var indice by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Calculadora de IMC", style = MaterialTheme.typography.titleLarge)

        OutlinedTextField(
            value = peso,
            onValueChange = { peso = it },
            label = { Text("Peso (kg)") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = estatura,
            onValueChange = { estatura = it },
            label = { Text("Estatura (m)") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                resultado = calcularResultado(peso, estatura)
                indice = obtenerIndice(resultado)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calcular")
        }

        Text("Resultado: $resultado", modifier = Modifier.padding(top = 16.dp))
        Text("Índice: $indice", modifier = Modifier.padding(top = 16.dp))
    }
}

fun calcularResultado(peso: String, estatura: String): String {
    return try {
        val pesoDouble = peso.toDouble()
        val estaturaDouble = estatura.toDouble()
        val resultadoDouble = pesoDouble / (estaturaDouble * estaturaDouble)
        DecimalFormat("#.##").format(resultadoDouble)
    } catch (e: Exception) {
        "Error: Ingrese valores válidos"
    }
}

fun obtenerIndice(resultado: String): String {
    return try {
        val resultadoDouble = resultado.toDouble()
        var indice = ""

        if (resultadoDouble < 16.00) {
            indice = "Delgadez severa"
        } else if (resultadoDouble in 16.00..16.99) {
            indice = "Delgadez moderada"
        } else if (resultadoDouble in 17.00..18.49) {
            indice = "Delgadez leve"
        } else if (resultadoDouble in 18.5..24.99) {
            indice = "Peso normal"
        } else if (resultadoDouble in 25.00..29.99) {
            indice = "Preobeso"
        } else if (resultadoDouble in 30.00..34.99) {
            indice = "Obesidad leve"
        } else if (resultadoDouble in 35.00..39.99) {
            indice = "Obesidad media"
        } else {
            indice = "Obesidad mórbida"
        }

        indice
    } catch (e: Exception) {
        "Error: No es un número válido"
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewIMCCalculatorApp() {
    Calculadora()
}



