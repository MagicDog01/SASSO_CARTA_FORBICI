package com.example.Sasso_carta_forbici

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random
import kotlinx.coroutines.*

fun scelta_bot(testo_scelta_pc : TextView, ph_forbici_bot: ImageView,ph_carta_bot:ImageView, ph_sasso_bot : ImageView ): String{

    val numero_casuale = Random.nextInt(1,4)
    var scelta_bot : String

    fun mani_invisibili_bot (ph_sasso_bot : ImageView, ph_carta_bot : ImageView, ph_forbici_bot : ImageView,){
        ph_sasso_bot.visibility= View.GONE
        ph_carta_bot.visibility= View.GONE
        ph_forbici_bot.visibility = View.GONE
    }

    scelta_bot= when (numero_casuale){
        1 -> "SASSO"
        2 -> "CARTA"
        3 -> "FORBICI"
        else -> "ERRORE"
    }

    when (scelta_bot) {
        "SASSO" -> {
            Handler(Looper.getMainLooper()).postDelayed({
                ph_sasso_bot.visibility= View.VISIBLE
                ph_carta_bot.visibility= View.GONE
                ph_forbici_bot.visibility = View.GONE
            }, 350)

            mani_invisibili_bot(ph_sasso_bot, ph_carta_bot, ph_forbici_bot)

        }
        "CARTA" -> {
            Handler(Looper.getMainLooper()).postDelayed({
                ph_sasso_bot.visibility= View.GONE
                ph_carta_bot.visibility= View.VISIBLE
                ph_forbici_bot.visibility = View.GONE
            }, 350)

            mani_invisibili_bot(ph_sasso_bot, ph_carta_bot, ph_forbici_bot)
        }
        "FORBICI" -> {
            Handler(Looper.getMainLooper()).postDelayed({
                ph_sasso_bot.visibility= View.GONE
                ph_carta_bot.visibility= View.GONE
                ph_forbici_bot.visibility = View.VISIBLE
            }, 350)

            mani_invisibili_bot(ph_sasso_bot, ph_carta_bot, ph_forbici_bot)
        }
    }

    testo_scelta_pc.text = "IL BOT HA SCELTO $scelta_bot"

    return scelta_bot
}

fun vincitore(scelta_bot: String, scelta_player : String, name : String?): String{

    return when{
        scelta_bot == scelta_player -> "PAREGGIO"
                (scelta_player == "SASSO" && scelta_bot == "FORBICI") ||
                (scelta_player == "CARTA" && scelta_bot == "SASSO") ||
                (scelta_player == "FORBICI" && scelta_bot == "CARTA") -> "$name"
        else-> "BOT"
    }

}
fun button_invisibili(button_sasso : Button, button_carta : Button, button_forbici : Button){
    button_sasso.visibility = View.GONE
    button_carta.visibility = View.GONE
    button_forbici.visibility = View.GONE
}
fun mani_invisibili_player (ph_sasso_player : ImageView, ph_carta_player : ImageView, ph_forbici_player : ImageView,){
    ph_sasso_player.visibility= View.GONE
    ph_carta_player.visibility= View.GONE
    ph_forbici_player.visibility = View.GONE
}

class second_activity : AppCompatActivity() {

      @SuppressLint("SuspiciousIndentation")
      override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

          var name = intent.getStringExtra("player_name") // variabile passata dal MainActivity
          if(name == null) name = intent.getStringExtra("nome_player_actWin")

        var max_score : Int = intent.getIntExtra("max_score", 0)
          if(max_score == 0) max_score = intent.getIntExtra("max_score_actWin", 1)

        val ph_carta_player= findViewById<ImageView>(R.id.ph_carta_player)
        val ph_carta_bot= findViewById<ImageView>(R.id.ph_carta_bot)

        val ph_sasso_bot= findViewById<ImageView>(R.id.ph_sasso_bot)
        val ph_sasso_player= findViewById<ImageView>(R.id.ph_sasso_player)

        val ph_forbici_bot= findViewById<ImageView>(R.id.ph_forbici_bot)
        val ph_forbici_player= findViewById<ImageView>(R.id.ph_forbici_player)

        val player_name = findViewById<TextView>(R.id.player_score)

        val testo_scelta_pc= findViewById<TextView>(R.id.scelta_pc)

        val punteggio_player = findViewById<TextView>(R.id.punteggio_player_numero)
        val punteggio_bot = findViewById<TextView>(R.id.punteggio_bot_numero)

        var int_player = 0
        var int_bot = 0

        val button_sasso = findViewById<Button>(R.id.button_sasso)
        val button_carta = findViewById<Button>(R.id.button_carta)
        val button_forbici = findViewById<Button>(R.id.button_forbici)

        var scelta_player : String
        var scelta_bot : String

        player_name.text = "$name"


        button_sasso.setOnClickListener(){
            val animation = AnimationUtils.loadAnimation(this, R.anim.translate)
            ph_sasso_player.startAnimation(animation)

            val animation_bot = AnimationUtils.loadAnimation(this, R.anim.translate_bot)
            ph_sasso_bot.startAnimation(animation_bot)

            Handler(Looper.getMainLooper()).postDelayed({
                ph_sasso_player.visibility= View.VISIBLE
                ph_carta_player.visibility= View.GONE
                ph_forbici_player.visibility = View.GONE
            }, 300)

            mani_invisibili_player(ph_sasso_player, ph_carta_player, ph_forbici_player)

            scelta_player = "SASSO"
            scelta_bot = scelta_bot(testo_scelta_pc, ph_forbici_bot,ph_carta_bot, ph_sasso_bot)

            val vincitore = vincitore(scelta_bot, scelta_player, name)

                when (vincitore) {
                    "$name" -> int_player++
                    "BOT" -> int_bot++
                }


            Handler(Looper.getMainLooper()).postDelayed({
                punteggio_player.text = int_player.toString()
                punteggio_bot.text = int_bot.toString()
            }, 300)


            if(int_player == max_score || int_bot == max_score){

                button_invisibili(button_sasso,button_forbici,button_carta)

                val intent = Intent(this, VincitoreActivity::class.java)
                intent.putExtra("vincitore", vincitore)
                intent.putExtra("nome_player", name)
                intent.putExtra("max_score", max_score)

                Handler(Looper.getMainLooper()).postDelayed({
                    startActivity(intent)
                    finish()
                }, 800)

                }
        }
        button_carta.setOnClickListener(){
            val animation = AnimationUtils.loadAnimation(this, R.anim.translate)
            ph_sasso_player.startAnimation(animation)

            val animation_bot = AnimationUtils.loadAnimation(this, R.anim.translate_bot)
            ph_sasso_bot.startAnimation(animation_bot)

            Handler(Looper.getMainLooper()).postDelayed({
                ph_sasso_player.visibility= View.GONE
                ph_carta_player.visibility= View.VISIBLE
                ph_forbici_player.visibility = View.GONE
            }, 300)

            mani_invisibili_player(ph_sasso_player, ph_carta_player, ph_forbici_player)


            scelta_player = "CARTA"
            scelta_bot = scelta_bot(testo_scelta_pc, ph_forbici_bot,ph_carta_bot, ph_sasso_bot)

            val vincitore = vincitore(scelta_bot, scelta_player, name)

            when (vincitore) {
                "$name" -> int_player++
                "BOT" -> int_bot++
            }
            Handler(Looper.getMainLooper()).postDelayed({
                punteggio_player.text = int_player.toString()
                punteggio_bot.text = int_bot.toString()
            }, 300)

            if(int_player == max_score || int_bot == max_score){
                button_invisibili(button_sasso,button_forbici,button_carta)

                val intent = Intent(this, VincitoreActivity::class.java)
                intent.putExtra("vincitore", vincitore)
                intent.putExtra("nome_player", name)
                intent.putExtra("max_score", max_score)

                Handler(Looper.getMainLooper()).postDelayed({
                    startActivity(intent)
                    finish()
                }, 800)
            }

        }
        button_forbici.setOnClickListener(){
            val animation = AnimationUtils.loadAnimation(this, R.anim.translate)
            ph_sasso_player.startAnimation(animation)

            val animation_bot = AnimationUtils.loadAnimation(this, R.anim.translate_bot)
            ph_sasso_bot.startAnimation(animation_bot)

            Handler(Looper.getMainLooper()).postDelayed({
                ph_sasso_player.visibility= View.GONE
                ph_carta_player.visibility= View.GONE
                ph_forbici_player.visibility = View.VISIBLE
            }, 300)

            mani_invisibili_player(ph_sasso_player, ph_carta_player, ph_forbici_player)


            scelta_player = "FORBICI"
            scelta_bot = scelta_bot(testo_scelta_pc, ph_forbici_bot,ph_carta_bot, ph_sasso_bot)

            val vincitore = vincitore(scelta_bot, scelta_player, name)

            when (vincitore) {
                "$name" -> int_player++
                "BOT" -> int_bot++
            }
            Handler(Looper.getMainLooper()).postDelayed({
                punteggio_player.text = int_player.toString()
                punteggio_bot.text = int_bot.toString()
            }, 300)

            if(int_player == max_score || int_bot == max_score){
                button_invisibili(button_sasso,button_forbici,button_carta)

                val intent = Intent(this, VincitoreActivity::class.java)
                intent.putExtra("vincitore", vincitore)
                intent.putExtra("nome_player", name)
                intent.putExtra("max_score", max_score)

                Handler(Looper.getMainLooper()).postDelayed({
                    startActivity(intent)
                    finish()
                }, 800)
            }

        }
      }
    }
