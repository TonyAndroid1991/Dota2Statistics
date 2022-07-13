package com.example.dota2statistics

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dota2statistics.data.models.byID.Profile
import com.example.dota2statistics.databinding.ItemUserProfileBinding

class UsersRecyclerAdapter() :
    RecyclerView.Adapter<UsersRecyclerAdapter.UsersViewHolder>() {

    lateinit var usersBinding: ItemUserProfileBinding

    private val callBack = object : DiffUtil.ItemCallback<Profile>() {
        override fun areItemsTheSame(oldItem: Profile, newItem: Profile): Boolean {
            return oldItem.steamid == newItem.steamid
        }

        override fun areContentsTheSame(oldItem: Profile, newItem: Profile): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, callBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        usersBinding =
            ItemUserProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UsersViewHolder(usersBinding)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val profile = differ.currentList[position]
        holder.bindElements(profile)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class UsersViewHolder(private val usersBinding: ItemUserProfileBinding) :
        RecyclerView.ViewHolder(usersBinding.root) {

        fun bindElements(user: Profile) {
            usersBinding.apply {
                userName.text = user.personaname
                userId.text = user.steamid
                Glide.with(itemView).load(user.avatarfull).into(userAvatar)
            }

            usersBinding.root.setOnClickListener {
                onItemClickListener?.let {
                    it(user)
                }
            }
        }
    }
    private var onItemClickListener: ((Profile) -> Unit)? = null

    fun setOnItemClickListener(listener: (Profile) -> Unit) {
        onItemClickListener = listener
    }
}