package com.example.jsonplaceholder

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jsonplaceholder.dataClasses.Album
import com.example.jsonplaceholder.dataClasses.Post
import com.example.jsonplaceholder.dataClasses.User
import kotlinx.coroutines.*
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
        val adapter = UserListAdapter(this@MainActivity, userList, this)
        userListRecyclerView.adapter = adapter
    }

    override fun onCellClickListener(user: User) {
        var userAlbums: List<Album?>? = null
        var userPosts: List<Post?>? = null
        val isLoading = arrayOf(true, true)

        // Получаем список альбомов и постов пользователя
        jsonPlaceholderAPI.getUserAlbums()?.enqueue(object : Callback<List<Album?>?> {
            override fun onResponse(call: Call<List<Album?>?>, response: Response<List<Album?>?>) {
                userAlbums = response.body()
                isLoading[0] = false
            }

            override fun onFailure(call: Call<List<Album?>?>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
//        jsonPlaceholderAPI.getUserPosts(user.id)?.enqueue(object : Callback<List<Post?>?> {
//            override fun onResponse(call: Call<List<Post?>?>, response: Response<List<Post?>?>) {
//                userPosts = response.body()
//                isLoading[1] = false
//            }
//
//            override fun onFailure(call: Call<List<Post?>?>, t: Throwable) {
//                TODO("Not yet implemented")
//            }
//        })

        val intent = Intent(this, UserDetail::class.java)

        intent.putExtra("user", user)
//        while (true) {
//        Log.i("isLoading", "${isLoading[0]} ${isLoading[1]}")
//            if (!isLoading[0] ) { //and !isLoading[1]
//                intent.putExtra("userAlbum1", userAlbums?.get(0))
//                intent.putExtra("userAlbum2", userAlbums?.get(1))
//                intent.putExtra("userAlbum3", userAlbums?.get(2))

//                intent.putExtra("userPost1", userPosts?.get(0))
//                intent.putExtra("userPost2", userPosts?.get(1))
//                intent.putExtra("userPost3", userPosts?.get(2))
//                break
//            }
//        }
        startActivity(intent)
    }
}
