package com.example.jsonplaceholder

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), UserCellClickListener {
    private lateinit var retrofit: Retrofit
    private lateinit var jsonPlaceholderAPI: JsonPlaceholderAPI
    private lateinit var userListRecyclerView: RecyclerView
    private var userList: List<User> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        userListRecyclerView = findViewById(R.id.userListRecyclerView)
        userListRecyclerView.layoutManager = LinearLayoutManager(this)

        retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        jsonPlaceholderAPI = retrofit.create(JsonPlaceholderAPI::class.java)

        jsonPlaceholderAPI.getUserList()?.enqueue(object : Callback<List<User?>?> {
            override fun onFailure(call: Call<List<User?>?>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call<List<User?>?>, response: Response<List<User?>?>) {
                userList = response.body() as List<User>
                initList()
            }
        })
    }

    private fun initList() {
        userListRecyclerView.adapter = UserListAdapter(this@MainActivity, userList, this)
    }

    override fun onCellClickListener(user: User) {
        val preview = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/users/${user.id}/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        jsonPlaceholderAPI = retrofit.create(JsonPlaceholderAPI::class.java)

        val intent = Intent(this, UserDetail::class.java)

        intent.putExtra("user", user)
        startActivity(intent)
    }
}
