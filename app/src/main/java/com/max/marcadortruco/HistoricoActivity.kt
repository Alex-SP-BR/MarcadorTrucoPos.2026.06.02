package com.max.marcadortruco

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HistoricoActivity : AppCompatActivity() {

    private var vitoriasEquipe1 = 0
    private var vitoriasEquipe2 = 0

    private var nomeEquipe1 = ""
    private var nomeEquipe2 = ""

    private lateinit var tvHistoricoEquipe1: TextView
    private lateinit var tvHistoricoEquipe2: TextView

    private lateinit var btVoltar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_historico)

        vitoriasEquipe1 = intent.getIntExtra("VITORIAS_EQUIPE_1", 0)
        vitoriasEquipe2 = intent.getIntExtra("VITORIAS_EQUIPE_2", 0)

        nomeEquipe1 = intent.getStringExtra("NOME_EQUIPE_1") ?: "Equipe 1"
        nomeEquipe2 = intent.getStringExtra("NOME_EQUIPE_2") ?: "Equipe 2"

        tvHistoricoEquipe1 = findViewById(R.id.tvHistoricoEquipe1)
        tvHistoricoEquipe2 = findViewById(R.id.tvHistoricoEquipe2)

        btVoltar = findViewById(R.id.btVoltar)

        tvHistoricoEquipe1.text = "$nomeEquipe1: $vitoriasEquipe1 ${getString(R.string.vitorias)}"

        tvHistoricoEquipe2.text = "$nomeEquipe2: $vitoriasEquipe2 ${getString(R.string.vitorias)}"
        btVoltar.setOnClickListener {

            finish()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}