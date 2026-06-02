package com.max.marcadortruco

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class NomesActivity : AppCompatActivity() {

    private lateinit var etEquipe1: EditText
    private lateinit var etEquipe2: EditText

    private lateinit var btConfirmar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_nomes)

        etEquipe1 = findViewById(R.id.etEquipe1)
        etEquipe2 = findViewById(R.id.etEquipe2)

        btConfirmar = findViewById(R.id.btConfirmar)

        btConfirmar.setOnClickListener {

            val nomeEquipe1 = etEquipe1.text.toString()

            val nomeEquipe2 = etEquipe2.text.toString()

            val tela = Intent()

            tela.putExtra("NOME_EQUIPE_1", nomeEquipe1)

            tela.putExtra("NOME_EQUIPE_2", nomeEquipe2)

            setResult(RESULT_OK, tela)

            finish()

        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}