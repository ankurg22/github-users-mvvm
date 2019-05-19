package com.example.githubusers.ui.userlist

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubusers.R
import com.example.githubusers.model.User
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_user.view.*

class UserListAdapter(val userList: List<User>) : RecyclerView.Adapter<UserListAdapter.UserListViewHolder>() {

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

    class UserListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        private val view = itemView

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            Log.d("UserListAdapter", "Item clicked")
        }

        fun bind(user: User) {
            view.user_login.text = user.login
            Picasso.get()
                .load(user.avatarUrl)
                .into(view.user_profile)
        }


    }
}