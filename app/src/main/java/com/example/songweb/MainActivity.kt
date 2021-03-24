package com.example.songweb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.github.kittinunf.fuel.core.Parameters
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.fuel.gson.responseObject
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val buttonAction = findViewById<Button>(R.id.btn1);
        buttonAction.setOnClickListener {

            val artistN = (findViewById<EditText>(R.id.et1)).text.toString();
            var url = "http://10.0.2.2:3000/artist/artistN"
            url.httpGet().response { request, response, result ->

                when (result) {
                    is Result.Success -> {
                        // result.get() gives ByteArray, decode to string
                        val tv1 = findViewById<TextView>(R.id.tv1);
                        val byteArray: ByteArray = result.get()
                        tv1.text = byteArray.decodeToString();
                    }

                    is Result.Failure -> {
                        // is failure if HTTP error
                        findViewById<TextView>(R.id.tv1).text = "ERROR ${result.error.message}"
                    }
                }
            }
        }
    }
}