package com.example.githubusers.ui.userlist

import android.content.Intent
import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubusers.Constants
import com.example.githubusers.R
import com.example.githubusers.model.User
import com.example.githubusers.ui.ProfileActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_user.view.*

class UserListAdapter : RecyclerView.Adapter<UserListAdapter.UserListViewHolder>() {
    val userList = ArrayList<User>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_user, parent, false)
        return UserListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        val user = userList.get(position)
        holder.bind(user)
    }

    class UserListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val view = itemView

        fun bind(user: User) {
            view.user_login.text = user.login
            Picasso.get()
                .load(user.avatarUrl)
                .into(view.user_profile)
            view.setOnClickListener {
                val intent = Intent(itemView.context, ProfileActivity::class.java)
                intent.putExtra(Constants.KEY_USER_LOGIN, user.login)
                itemView.context.startActivity(intent)
            }
        }
    }

    fun addData(data: List<User>) {
        userList.addAll(data)
        notifyDataSetChanged()
    }
}