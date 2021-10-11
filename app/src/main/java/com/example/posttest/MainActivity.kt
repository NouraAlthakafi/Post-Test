package com.example.posttest

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var buttonNew: Button
    lateinit var rvUsers: RecyclerView

    lateinit var allTheUsers: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonNew = findViewById(R.id.buttonNew)
        rvUsers = findViewById(R.id.rvUsers)
        allTheUsers = ArrayList()

        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        val call: Call<List<UsersList?>> = apiInterface!!.showInfo()


        rvUsers.adapter = RVusers(allTheUsers)
        rvUsers.layoutManager = LinearLayoutManager(this)

        call?.enqueue(object: Callback <List<UsersList?>>{
            override fun onResponse(
                call: Call<List<UsersList?>>,
                response: Response<List<UsersList?>>
            ) {
                val resource: List<UsersList?>? = response.body()
                if (resource != null){
                    for (i in resource){
                        val userName = i?.name
                        val userLocation = i?.location
                        allTheUsers.add("${userName.toString()}" + " " + "${userLocation.toString()}")
                        rvUsers.adapter?.notifyDataSetChanged()
                        rvUsers.scrollToPosition(allTheUsers.size-1)
                    }
                }
            }

            override fun onFailure(call: Call<List<UsersList?>>, t: Throwable) {
                call.cancel()
            }

        })
        buttonNew.setOnClickListener{
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }
}