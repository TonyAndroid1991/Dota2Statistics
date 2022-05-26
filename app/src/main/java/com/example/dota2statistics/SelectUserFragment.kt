package com.example.dota2statistics

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dota2statistics.databinding.FragmentSelectUserBinding

class SelectUserFragment : Fragment(R.layout.fragment_select_user) {

    lateinit var binding: FragmentSelectUserBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSelectUserBinding.bind(view)
        binding.lifecycleOwner = this

        val args : SelectUserFragmentArgs by navArgs()
        val profilesList = args.user
        Log.i("Profiles", "onViewCreated: ${profilesList[0].personaname} ================")

        binding.usersRecycler.apply {
            adapter = UsersRecyclerAdapter(profilesList)
            layoutManager = LinearLayoutManager(activity)
        }
    }

}