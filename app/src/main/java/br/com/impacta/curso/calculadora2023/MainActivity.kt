package br.com.impacta.curso.calculadora2023

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import br.com.impacta.curso.calculadora2023.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val somar: (Double,Double) -> Double = {a, b -> a + b}
    private val subtrair = {a: Double, b: Double -> a - b}
    private val multiplicar = {a: Double, b: Double -> a * b}
    private val dividir = {a: Double, b: Double -> a / b}
    private val porcentagem = {a: Double, b: Double -> a * dividir(b, 100.0)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button0.setOnClickListener { digitaNumero(it) }
        binding.button1.setOnClickListener { digitaNumero(it) }
        binding.button2.setOnClickListener { digitaNumero(it) }
        binding.button3.setOnClickListener { digitaNumero(it) }
        binding.button4.setOnClickListener { digitaNumero(it) }
        binding.button5.setOnClickListener { digitaNumero(it) }
        binding.button6.setOnClickListener { digitaNumero(it) }
        binding.button7.setOnClickListener { digitaNumero(it) }
        binding.button8.setOnClickListener { digitaNumero(it) }
        binding.button9.setOnClickListener { digitaNumero(it) }
        binding.buttonPonto.setOnClickListener { digitaNumero(it) }

        binding.buttonApagar.setOnClickListener { apagarUltimoNumero() }
        binding.buttonApagar.setOnLongClickListener {
            apagarTodoNumero()
            true
        }

        binding.buttonAdicao.setOnClickListener { digitaOperacao(it) }
        binding.buttonSubtracao.setOnClickListener { digitaOperacao(it) }
        binding.buttonMultiplicacao.setOnClickListener { digitaOperacao(it) }
        binding.buttonDivisao.setOnClickListener { digitaOperacao(it) }
        binding.buttonPorcentagem.setOnClickListener { digitaOperacao(it) }

        binding.buttonIgual.setOnClickListener {
            val resultado = calcularResultado(binding.textViewLinhaSuperior.text.toString(), binding.textViewLinhaInferior.text.toString())
            binding.textViewLinhaSuperior.text = ""
            binding.textViewLinhaInferior.text = resultado.toString()
        }
    }

    fun digitaNumero(view: View) {
        val botao = view as Button
        if (botao == binding.button0 && binding.textViewLinhaInferior.text.isEmpty())
            return

        var prefixo = binding.textViewLinhaInferior.text.toString()
        if (prefixo == "0") {
            prefixo = ""
        }

        if (botao == binding.buttonPonto) {
            if (validarPonto())
                return
            if (prefixo.isEmpty()) {
                prefixo = "0"
            }
        }

        binding.textViewLinhaInferior.text = "${prefixo}${botao.text}"
    }

    fun validarPonto(): Boolean {
        return binding.textViewLinhaInferior.text.toString().contains(".")
    }

    fun apagarUltimoNumero() {
        binding.textViewLinhaInferior.text =
            binding.textViewLinhaInferior.text.toString().dropLast(1)
    }

    fun apagarTodoNumero() {
        binding.textViewLinhaInferior.text = ""
    }

    fun digitaOperacao(view: View) {
        val botao = view as Button

        binding.textViewLinhaSuperior.text = "${binding.textViewLinhaInferior.text} ${botao.text}"
        binding.textViewLinhaInferior.text = ""
    }

    fun realizaCalculo(a: Double, b: Double, operacao: (Double, Double) -> Double): Double {
        return operacao(a, b)
    }

    fun calcularResultado(linhaSuperior: String, linhaInferior: String): Double {
        val pedacos = linhaSuperior.split(" ")
        val operando1 = pedacos[0].toDouble()
        val operando2 = linhaInferior.toDouble()
        val operacao = when (pedacos[1]) {
            "+" -> somar
            "-" -> subtrair
            "*" -> multiplicar
            "/" -> dividir
            else -> porcentagem
        }
        return realizaCalculo(operando1, operando2, operacao)

    }
}