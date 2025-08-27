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

            zeroBt.setOnClickListener {
                calculoTv.append("0")
            }

            umBt.setOnClickListener {
                calculoTv.append("1")
            }

            doisBt.setOnClickListener {
                calculoTv.append("2")
            }

            tresBt.setOnClickListener {
                calculoTv.append("3")
            }

            quatroBt.setOnClickListener {
                calculoTv.append("4")
            }

            cincoBt.setOnClickListener {
                calculoTv.append("5")
            }

            seisBt.setOnClickListener {
                calculoTv.append("6")
            }

            seteBt.setOnClickListener {
                calculoTv.append("7")
            }

            oitoBt.setOnClickListener {
                calculoTv.append("8")
            }

            noveBt.setOnClickListener {
                calculoTv.append("9")
            }

            pontoBt.setOnClickListener {
                calculoTv.append(".")
            }


            divisaoBt.setOnClickListener {
                onGeneralOperatorClick('/')
            }


            multiplicacaoBt.setOnClickListener {
                onGeneralOperatorClick('*')
            }

            somaBt.setOnClickListener {
                onGeneralOperatorClick('+')
            }

            subtracaoBt.setOnClickListener {
                onMinusOperatorClick()
            }

            deleteBt.setOnClickListener {
                calculoTv.text = calculoTv.text.dropLast(1)
            }

            acBt.setOnClickListener {
                calculoTv.text = ""
            }

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

    private fun onMinusOperatorClick() {
        val textoAtual = amb.calculoTv.text.toString()
        val ultimoChar = textoAtual.lastOrNull()
        val operadorSubstituir = setOf('+')
        val operadoresComplementar = setOf('*', '/')

        when {
            ultimoChar == null -> {
                amb.calculoTv.append("-")
            }
            operadorSubstituir.contains(ultimoChar) -> {
                amb.calculoTv.text = textoAtual.dropLast(1) + "-"
            }
            operadoresComplementar.contains(ultimoChar) -> {
                amb.calculoTv.append("-")
            }
            else -> {
                amb.calculoTv.append("-")
            }
        }
    }

    private fun onGeneralOperatorClick(operadorNovo: Char) {
        val textoAtual = amb.calculoTv.text.toString()
        val ultimoChar = textoAtual.lastOrNull()
        val operadores = setOf('+', '-', '*', '/')


        if (ultimoChar != null && operadores.contains(ultimoChar)) {
            amb.calculoTv.text = textoAtual.dropLast(1) + operadorNovo.toString()
        } else {
            amb.calculoTv.append(operadorNovo.toString())
        }
    }
}