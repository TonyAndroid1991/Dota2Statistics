package com.example.dota2statistics

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dota2statistics.data.models.byID.Profile
import com.example.dota2statistics.databinding.ItemUserProfileBinding

class UsersRecyclerAdapter(private val usersList: Array<Profile>) :
    RecyclerView.Adapter<UsersRecyclerAdapter.UsersViewHolder>() {

    lateinit var usersBinding: ItemUserProfileBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        usersBinding =
            ItemUserProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UsersViewHolder(usersBinding)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.bindElements(usersList[position])
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    class UsersViewHolder(private val usersBinding: ItemUserProfileBinding) :
        RecyclerView.ViewHolder(usersBinding.root) {

        fun bindElements(user: Profile) {
            usersBinding.apply {
                userName.text = user.personaname
                userId.text = user.steamid
                Glide.with(itemView).load(user.avatarfull).into(userAvatar)
            }
        }

    }
}