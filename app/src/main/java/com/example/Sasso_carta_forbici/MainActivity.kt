package com.example.Sasso_carta_forbici


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val button_start = findViewById<Button>(R.id.button_start)

        val edit_name = findViewById<EditText>(R.id.edit_name)

        val button_piu = findViewById<Button>(R.id.button_piu)
        val button_meno = findViewById<Button>(R.id.button_meno)

        val max_score = findViewById<TextView>(R.id.max_score)
        var max_score_int : Int

        button_meno.setOnClickListener(){
            if(max_score.text.toString().toInt() > 1){
                max_score.text = (max_score.text.toString().toInt() - 1).toString()
                max_score_int = max_score.text.toString().toInt()
            }
        }
        button_piu.setOnClickListener(){
            max_score.text = (max_score.text.toString().toInt() + 1).toString()
            max_score_int = max_score.text.toString().toInt()
        }

        max_score_int = max_score.text.toString().toInt()

       button_start.setOnClickListener(){

           if(edit_name.text.isNotEmpty()){ // se non viene inserito un nome, non si puo' andare avanti

               val name = edit_name.text.toString()

               val intent = Intent(this, second_activity::class.java)

               intent.putExtra("player_name", name)
               intent.putExtra("max_score", max_score_int)

               Toast.makeText(this, "VINCE CHI ARRIVA A ${max_score.text} PER PRIMO ", Toast.LENGTH_SHORT).show()

               startActivity(intent)
               finish()
           }
           else {
              // avviso di inserire il nome
               Toast.makeText(this, "Inserisci un nome", Toast.LENGTH_SHORT).show()
           }

        }


    }
}