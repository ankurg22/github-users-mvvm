package com.example.githubusers.ui.userlist

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.githubusers.R
import com.example.githubusers.databinding.ListItemUserBinding
import com.example.githubusers.model.User
import com.example.githubusers.ui.ProfileActivity
import com.example.githubusers.ui.UserViewModel
import com.example.githubusers.utils.Constants
import com.squareup.picasso.Picasso

class UserListAdapter : RecyclerView.Adapter<UserListAdapter.UserListViewHolder>() {
    private var userList = ArrayList<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        val binding: ListItemUserBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.list_item_user, parent, false
        )

        return UserListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        val user = userList.get(position)
        holder.bind(user)
    }

    class UserListViewHolder(private val binding: ListItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel = UserViewModel()

        fun bind(user: User) {
            viewModel.bind(user)
            binding.viewModel = viewModel
            binding.root.setOnClickListener {
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

    fun getLastUserId(): Int {
        return userList.last().id
    }
}