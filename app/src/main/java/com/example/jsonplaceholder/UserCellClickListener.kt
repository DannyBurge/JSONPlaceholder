package com.example.jsonplaceholder

import com.example.jsonplaceholder.dataClasses.User

interface UserCellClickListener {
    fun onCellClickListener(user: User)
}