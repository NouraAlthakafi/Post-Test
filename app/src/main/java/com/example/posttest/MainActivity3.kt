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

class MainActivity3 : AppCompatActivity() {
    lateinit var etNumberId: EditText
    lateinit var etNameUpdate: EditText
    lateinit var etLocationUpdate: EditText
    lateinit var buttonDelete: Button
    lateinit var buttonUpdate: Button
    lateinit var buttonReturn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        etNumberId = findViewById(R.id.etNumberId)
        etNameUpdate = findViewById(R.id.etNameUpdate)
        etLocationUpdate = findViewById(R.id.etLocationUpdate)
        buttonDelete = findViewById(R.id.buttonDelete)
        buttonUpdate = findViewById(R.id.buttonUpdate)
        buttonReturn = findViewById(R.id.buttonReturn)

        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        val userIDPk = etNumberId.text

        buttonDelete.setOnClickListener{
            apiInterface?.deleteInfo(userIDPk.toString().toInt())?.enqueue(object: Callback<Void>{
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    Toast.makeText(this@MainActivity3, "User Deleted", Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(this@MainActivity3, "No User Deleted", Toast.LENGTH_LONG).show()
                }

            })
        }
        buttonUpdate.setOnClickListener{
            apiInterface?.updateInfo(
                userIDPk.toString().toInt(),
                UsersList(
                    userIDPk.toString().toInt(),
                    etNameUpdate.text.toString(),
                    etLocationUpdate.text.toString()
                )
            )?.enqueue(object: Callback <List<UsersList>>{
                override fun onResponse(
                    call: Call<List<UsersList>>,
                    response: Response<List<UsersList>>
                ) {
                    Toast.makeText(this@MainActivity3, "User Updated", Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call: Call<List<UsersList>>, t: Throwable) {
                    Toast.makeText(this@MainActivity3, "User Updated", Toast.LENGTH_LONG).show()
                }

            })
        }
        buttonReturn.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
