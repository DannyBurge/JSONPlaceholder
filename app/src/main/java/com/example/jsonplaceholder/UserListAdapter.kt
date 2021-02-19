package com.example.jsonplaceholder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jsonplaceholder.dataClasses.User

class UserListAdapter(
    context: Context,
    private val users: List<User>,
    private val cellClickListener: UserCellClickListener
) :
    RecyclerView.Adapter<UserListAdapter.ViewHolder>() {
    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = inflater.inflate(R.layout.user_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        users[position].let { holder.bind(it) }

        holder.itemView.setOnClickListener {
            users[position].let { it1 -> cellClickListener.onCellClickListener(it1) }
        }
    }

    override fun getItemCount(): Int {
        return users.size
    }

    inner class ViewHolder constructor(view: View) : RecyclerView.ViewHolder(view) {
        private val username: TextView = view.findViewById(R.id.usernameText)
        private val name: TextView = view.findViewById(R.id.nameText)

        fun bind(user: User) {
            username.text = user.username
            name.text = user.name
        }
    }
}