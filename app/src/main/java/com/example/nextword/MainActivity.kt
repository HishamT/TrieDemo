package com.example.nextword

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private val t = "MainActivity"
    var words: Trie = Trie()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        words.insertWord("hisham")
        words.insertWord("high")
        words.insertWord("hit")
        words.insertWord("hello")
        words.insertWord("hit")
        words.insertWord("hit")
        words.insertWord("hit")
        words.insertWord("halcyon")
        words.insertWord("halcyon")
        val input = findViewById<EditText>(R.id.input)
        val w1 = findViewById<TextView>(R.id.word1)
        val w2 = findViewById<TextView>(R.id.word2)
        val w3 = findViewById<TextView>(R.id.word3)

        input.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(p0!!.isNotEmpty()) {
                    var wordList = words.getRestOfWord(p0.toString())
                    w1.text = wordList[0]
                    if(wordList[1] == (wordList[0])){
                        w2.text = " "
                    }else{
                        w2.text = wordList[1]
                    }
                    if(wordList[2] == wordList[0] || wordList[2] == wordList[1]){
                        w3.text = " "
                    }else{
                        w3.text = wordList[2]
                    }

                }else{
                    w1.text = ""
                    w2.text = ""
                    w3.text = ""
                }
            }

        })
    }
}
