package com.example.Sasso_carta_forbici

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class VincitoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_vincitore)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val nome_player_actWin = intent.getStringExtra("nome_player")
        val max_score_actWin = intent.getIntExtra("max_score",1)

        val vincitore_nome = intent.getStringExtra("vincitore")
        val vincitore_testo = findViewById<TextView>(R.id.vincitore_testo)

        val button_ripeti = findViewById<Button>(R.id.button_ripeti)
        val button_home = findViewById<Button>(R.id.button_home)

        val ph_bot = findViewById<ImageView>(R.id.ph_bot)
        val ph_human = findViewById<ImageView>(R.id.ph_human)


        when (vincitore_nome) {
            "BOT"-> {vincitore_testo.text = "VINCE BOT"
            ph_bot.visibility = View.VISIBLE}

            else -> {vincitore_testo.text = "HAI VINTO"
                ph_human.visibility = View.VISIBLE
            }
        }

        button_ripeti.setOnClickListener(){
            val intent = Intent(this, second_activity::class.java)
            intent.putExtra("nome_player_actWin", nome_player_actWin)
            intent.putExtra("max_score_actWin", max_score_actWin)
            startActivity(intent)
            finish()
        }
        button_home.setOnClickListener(){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }


    }
}