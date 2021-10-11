package com.example.posttest

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity2 : AppCompatActivity() {
    lateinit var etName: EditText
    lateinit var etLocation: EditText
    lateinit var buttonSave: Button
    lateinit var buttonView: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        etName = findViewById(R.id.etName)
        etLocation = findViewById(R.id.etLocation)
        buttonSave = findViewById(R.id.buttonSave)
        buttonView = findViewById(R.id.buttonView)

        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)

        buttonSave.setOnClickListener{
            val na = etName.text.toString()
            val lo = etLocation.text.toString()

            apiInterface?.addInfo(AddUsers(na, lo))?.enqueue(object:
                Callback<AddUsers> {
                override fun onResponse(call: Call<AddUsers>, response: Response<AddUsers>){
                    Toast.makeText(this@MainActivity2, "New User Added", Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call:Call<AddUsers>, t: Throwable){
                    Toast.makeText(this@MainActivity2, "No User Added", Toast.LENGTH_LONG).show()
                }
            })
            etName.text.clear()
            etName.clearFocus()
            etLocation.text.clear()
            etLocation.clearFocus()
        }

        buttonView.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}