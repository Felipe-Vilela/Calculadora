package br.edu.ifsp.scl.ads.prdm.sc303898x.calculadora

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc303898x.calculadora.databinding.ActivityMainBinding

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
                calculoTv.append("/")
            }


            multiplicacaoBt.setOnClickListener {
                calculoTv.append("*")
            }

            somaBt.setOnClickListener {
                calculoTv.append("+")
            }

            subtracaoBt.setOnClickListener {
                calculoTv.append("-")
            }

            deleteBt.setOnClickListener {
                calculoTv.text = calculoTv.text.dropLast(1)
            }

            acBt.setOnClickListener {
                calculoTv.text = ""
            }

        }
    }
}