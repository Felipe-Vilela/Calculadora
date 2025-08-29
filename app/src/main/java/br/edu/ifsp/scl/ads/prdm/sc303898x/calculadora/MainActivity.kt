package br.edu.ifsp.scl.ads.prdm.sc303898x.calculadora

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc303898x.calculadora.databinding.ActivityMainBinding
import org.mariuszgromada.math.mxparser.Expression

class MainActivity : AppCompatActivity() {
    private val amb: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(amb){
            setContentView(root)

            zeroBt.setOnClickListener { calculoTv.append("0") }
            umBt.setOnClickListener { calculoTv.append("1") }
            doisBt.setOnClickListener { calculoTv.append("2") }
            tresBt.setOnClickListener { calculoTv.append("3") }
            quatroBt.setOnClickListener { calculoTv.append("4") }
            cincoBt.setOnClickListener { calculoTv.append("5") }
            seisBt.setOnClickListener { calculoTv.append("6") }
            seteBt.setOnClickListener { calculoTv.append("7") }
            oitoBt.setOnClickListener { calculoTv.append("8") }
            noveBt.setOnClickListener { calculoTv.append("9") }

            pontoBt.setOnClickListener { calculoTv.append(".") }
            divisaoBt.setOnClickListener { onOperatorClick('/') }
            multiplicacaoBt.setOnClickListener { onOperatorClick('*') }
            somaBt.setOnClickListener { onOperatorClick('+') }
            subtracaoBt.setOnClickListener { onOperatorClick('-') }
            deleteBt.setOnClickListener { calculoTv.text = calculoTv.text.dropLast(1) }
            acBt.setOnClickListener { calculoTv.text = "" }

            resultadoBt.setOnClickListener {
                val expressao = calculoTv.text.toString()
                val resultadoCalculado = Expression(calculoTv.text.toString()).calculate()

                when{
                    expressao.endsWith("/0") -> {
                        Toast.makeText(this@MainActivity, "Não é possível dividir por zero", Toast.LENGTH_LONG).show()
                        calculoTv.text = ""
                    }

                    resultadoCalculado.isNaN() -> {
                        Toast.makeText(this@MainActivity, "Expressão inválida", Toast.LENGTH_LONG).show()
                        calculoTv.text = ""
                    }

                    else -> {calculoTv.text = resultadoCalculado.toString()}
                }
            }
        }
    }

    private fun onOperatorClick(novoOperador: Char) {
        val text = amb.calculoTv.text.toString()
        if (text.isEmpty()) {
            if (novoOperador == '-') amb.calculoTv.append("-")
            return
        }
        if (text.length == 1 && text[0] == '-') {
            if (novoOperador == '-') return
            return
        }

        val ultimoChar = text.last()
        val isOperador = { c: Char -> c == '+' || c == '-' || c == '*' || c == '/' }

        if (text.length >= 2) {
            val penult = text[text.length - 2]
            if ((penult == '+' || penult == '*' || penult == '/') && ultimoChar == '-') {
                when (novoOperador) {
                    '-' -> {
                        return
                    }
                    '+' -> {
                        amb.calculoTv.text = text.dropLast(1) // "/-" -> "/"
                        return
                    }
                    '*', '/' -> {
                        amb.calculoTv.text = text.dropLast(2) + novoOperador
                        return
                    }
                }
            }
        }

        if (isOperador(ultimoChar)) {
            when (novoOperador) {
                '-' -> {
                    if (ultimoChar == '*' || ultimoChar == '/') {
                        amb.calculoTv.text = text + "-"
                    } else {
                        amb.calculoTv.text = text.dropLast(1) + "-"
                    }
                }
                else -> {
                    amb.calculoTv.text = text.dropLast(1) + novoOperador
                }
            }
            return
        }
        amb.calculoTv.append(novoOperador.toString())
    }
}