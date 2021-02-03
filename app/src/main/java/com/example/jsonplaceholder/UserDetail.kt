package com.example.jsonplaceholder

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.jsonplaceholder.databinding.ActivityUserDetailBinding

class UserDetail : AppCompatActivity() {
    lateinit var binding: ActivityUserDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val user = intent.extras?.get("user") as User

        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_detail)

        binding.usernameText.text = user.username
        binding.nameText.text = user.name
        binding.emailText.text = user.email
        binding.phoneText.text = user.phone
        binding.websiteText.text = user.website
        binding.companyNameText.text = user.company.name
        binding.companyBsText.text = user.company.bs
        binding.companyCatchPhraseText.text = user.company.catchPhrase
    }
}