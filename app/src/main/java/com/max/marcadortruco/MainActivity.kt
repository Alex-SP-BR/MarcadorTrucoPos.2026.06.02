package com.max.marcadortruco

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var tvEquipe1: TextView
    private lateinit var tvEquipe2: TextView

    private lateinit var tvPontosEquipe1: TextView
    private lateinit var tvPontosEquipe2: TextView

    private lateinit var btEquipe1Mais1: Button
    private lateinit var btEquipe1Mais3: Button
    private lateinit var btEquipe1Mais6: Button
    private lateinit var btEquipe1Mais9: Button
    private lateinit var btEquipe1Mais12: Button

    private lateinit var btEquipe2Mais1: Button
    private lateinit var btEquipe2Mais3: Button
    private lateinit var btEquipe2Mais6: Button
    private lateinit var btEquipe2Mais9: Button
    private lateinit var btEquipe2Mais12: Button

    private lateinit var btHistorico: Button

    private lateinit var btInformarNomes: Button

    private lateinit var btZerarHistorico: Button

    private var pontosEquipe1 = 0
    private var pontosEquipe2 = 0

    private var vitoriasEquipe1 = 0
    private var vitoriasEquipe2 = 0

    private val CODIGO_NOMES = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvEquipe1 = findViewById(R.id.tvEquipe1)
        tvEquipe2 = findViewById(R.id.tvEquipe2)

        tvPontosEquipe1 = findViewById(R.id.tvPontosEquipe1)
        tvPontosEquipe2 = findViewById(R.id.tvPontosEquipe2)

        btEquipe1Mais1 = findViewById(R.id.btEquipe1Mais1)
        btEquipe1Mais3 = findViewById(R.id.btEquipe1Mais3)
        btEquipe1Mais6 = findViewById(R.id.btEquipe1Mais6)
        btEquipe1Mais9 = findViewById(R.id.btEquipe1Mais9)
        btEquipe1Mais12 = findViewById(R.id.btEquipe1Mais12)


        btEquipe2Mais1 = findViewById(R.id.btEquipe2Mais1)
        btEquipe2Mais3 = findViewById(R.id.btEquipe2Mais3)
        btEquipe2Mais6 = findViewById(R.id.btEquipe2Mais6)
        btEquipe2Mais9 = findViewById(R.id.btEquipe2Mais9)
        btEquipe2Mais12 = findViewById(R.id.btEquipe2Mais12)

        btHistorico = findViewById(R.id.btHistorico)

        btInformarNomes = findViewById(R.id.btInformarNomes)

        btZerarHistorico = findViewById(R.id.btZerarHistorico)

        // --- RESTAURAR DADOS APÓS ROTACIONAR A TELA ---
        if (savedInstanceState != null) {
            pontosEquipe1 = savedInstanceState.getInt("PONTOS_1")
            pontosEquipe2 = savedInstanceState.getInt("PONTOS_2")
            vitoriasEquipe1 = savedInstanceState.getInt("VITORIAS_1")
            vitoriasEquipe2 = savedInstanceState.getInt("VITORIAS_2")

            tvEquipe1.text = savedInstanceState.getString("NOME_1", "Equipe 1")
            tvEquipe2.text = savedInstanceState.getString("NOME_2", "Equipe 2")

            tvPontosEquipe1.text = pontosEquipe1.toString()
            tvPontosEquipe2.text = pontosEquipe2.toString()
        }
         //termina aqui a sugestão da IA
        btHistorico.setOnClickListener {

            val intent = Intent(this, HistoricoActivity::class.java)

            intent.putExtra("VITORIAS_EQUIPE_1", vitoriasEquipe1)
            intent.putExtra("VITORIAS_EQUIPE_2", vitoriasEquipe2)

            intent.putExtra("NOME_EQUIPE_1", tvEquipe1.text.toString())
            intent.putExtra("NOME_EQUIPE_2", tvEquipe2.text.toString())

            startActivity(intent)
        }

        btEquipe1Mais1.setOnClickListener {

            pontosEquipe1++
            tvPontosEquipe1.text = pontosEquipe1.toString()

            verificarVencedor()
        }

        btEquipe1Mais3.setOnClickListener {

            pontosEquipe1 += 3
            tvPontosEquipe1.text = pontosEquipe1.toString()

            verificarVencedor()

        }

        btEquipe1Mais6.setOnClickListener {

            pontosEquipe1 += 6
            tvPontosEquipe1.text = pontosEquipe1.toString()

            verificarVencedor()
        }

        btEquipe1Mais9.setOnClickListener {

            pontosEquipe1 += 9
            tvPontosEquipe1.text = pontosEquipe1.toString()

            verificarVencedor()
        }

        btEquipe1Mais12.setOnClickListener {

            pontosEquipe1 += 12
            tvPontosEquipe1.text = pontosEquipe1.toString()

            verificarVencedor()
        }

        btEquipe2Mais1.setOnClickListener {

            pontosEquipe2++
            tvPontosEquipe2.text = pontosEquipe2.toString()

            verificarVencedor()
        }

        btEquipe2Mais3.setOnClickListener {

            pontosEquipe2 +=3
            tvPontosEquipe2.text = pontosEquipe2.toString()

            verificarVencedor()
        }

        btEquipe2Mais6.setOnClickListener {

            pontosEquipe2 += 6
            tvPontosEquipe2.text = pontosEquipe2.toString()

            verificarVencedor()
        }

        btEquipe2Mais9.setOnClickListener {

            pontosEquipe2 += 9
            tvPontosEquipe2.text = pontosEquipe2.toString()

            verificarVencedor()
        }

        btEquipe2Mais12.setOnClickListener {

            pontosEquipe2 += 12
            tvPontosEquipe2.text = pontosEquipe2.toString()

            verificarVencedor()
        }

        btInformarNomes.setOnClickListener {

            val tela = Intent(this, NomesActivity::class.java)

            startActivityForResult(tela, CODIGO_NOMES)
        }

        btZerarHistorico.setOnClickListener {

            vitoriasEquipe1 = 0
            vitoriasEquipe2 = 0
            pontosEquipe1 = 0  //sugestão da IA
            pontosEquipe2 = 0  //sugestão da IA

            tvEquipe1.text = "Equipe 1"
            tvEquipe2.text = "Equipe 2"
            tvPontosEquipe1.text = "0"  //sugestão da IA
            tvPontosEquipe2.text = "0"  //sugestão da IA

            Toast.makeText(
                this,
                getString(R.string.historico_zerado),
                Toast.LENGTH_SHORT
            ).show()
        }

    }

    private fun verificarVencedor() {

        if (pontosEquipe1 >= 12) {

            vitoriasEquipe1++

            AlertDialog.Builder(this)
                .setTitle(getString(R.string.fim_partida))
                .setMessage(
                    getString(
                        R.string.equipe_venceu,
                        tvEquipe1.text.toString()
                    )
                )
                .setPositiveButton(getString(R.string.ok), null)
                .show()

            pontosEquipe1 = 0
            pontosEquipe2 = 0

            tvPontosEquipe1.text = pontosEquipe1.toString()
            tvPontosEquipe2.text = pontosEquipe2.toString()
        }

        if (pontosEquipe2 >= 12) {

            vitoriasEquipe2++

            AlertDialog.Builder(this)
                .setTitle(getString(R.string.fim_partida))
                .setMessage(
                    getString(
                        R.string.equipe_venceu,
                        tvEquipe2.text.toString()
                    )
                )
                .setPositiveButton(getString(R.string.ok), null)
                .show()

            pontosEquipe1 = 0
            pontosEquipe2 = 0

            tvPontosEquipe1.text = pontosEquipe1.toString()
            tvPontosEquipe2.text = pontosEquipe2.toString()
        }
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CODIGO_NOMES &&
            resultCode == RESULT_OK &&
            data != null
        ) {

            val nomeEquipe1 =
                data.getStringExtra("NOME_EQUIPE_1")

            val nomeEquipe2 =
                data.getStringExtra("NOME_EQUIPE_2")


            tvEquipe1.text = nomeEquipe1

            tvEquipe2.text = nomeEquipe2

        }
    }

    // --- SALVAR DADOS ANTES DA TELA SER DESTRUÍDA ---
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("PONTOS_1", pontosEquipe1)
        outState.putInt("PONTOS_2", pontosEquipe2)
        outState.putInt("VITORIAS_1", vitoriasEquipe1)
        outState.putInt("VITORIAS_2", vitoriasEquipe2)
        outState.putString("NOME_1", tvEquipe1.text.toString())
        outState.putString("NOME_2", tvEquipe2.text.toString())
    }//termima aqui a sugestão da IA
}