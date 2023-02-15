package br.com.impacta.curso.calculadora2023

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import br.com.impacta.curso.calculadora2023.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

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
}