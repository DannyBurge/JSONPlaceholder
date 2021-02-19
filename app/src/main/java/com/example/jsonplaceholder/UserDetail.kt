package com.example.jsonplaceholder

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.jsonplaceholder.dataClasses.Album
import com.example.jsonplaceholder.dataClasses.Post
import com.example.jsonplaceholder.dataClasses.User
import com.example.jsonplaceholder.databinding.ActivityUserDetailBinding

class UserDetail : AppCompatActivity() {
    lateinit var binding: ActivityUserDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val user = intent.extras?.get("user") as User

//        val album1 = intent.extras?.get("userAlbum1") as Album
//        val album2 = intent.extras?.get("userAlbum2") as Album
//        val album3 = intent.extras?.get("userAlbum3") as Album
//
//        val post1 = intent.extras?.get("userPost1") as Post
//        val post2 = intent.extras?.get("userPost2") as Post
//        val post3 = intent.extras?.get("userPost3") as Post

        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_detail)

        binding.usernameText.text = user.username
        binding.nameText.text = user.name
        binding.emailText.text = user.email
        binding.phoneText.text = user.phone
        binding.websiteText.text = user.website
        binding.companyNameText.text = user.company.name
        binding.companyBsText.text = user.company.bs
        binding.companyCatchPhraseText.text = user.company.catchPhrase

//        binding.albumTitle1.text = album1.title
//        binding.albumTitle2.text = album2.title
//        binding.albumTitle3.text = album3.title
//
//        binding.postTitle1.text = post1.title
//        binding.postTitle2.text = post2.title
//        binding.postTitle3.text = post3.title
    }
}