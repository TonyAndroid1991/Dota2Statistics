package com.example.dota2statistics

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dota2statistics.data.models.byID.Profile
import com.example.dota2statistics.databinding.FragmentSelectUserBinding
import com.example.dota2statistics.presentation.viewmodels.EmptyHomeViewModel
import com.example.dota2statistics.presentation.viewmodels.EmptyHomeViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import java.time.Duration
import javax.inject.Inject

@AndroidEntryPoint
class SelectUserFragment : Fragment(R.layout.fragment_select_user) {

    lateinit var binding: FragmentSelectUserBinding

    @Inject
    lateinit var emptyHomeViewModelFactory: EmptyHomeViewModelFactory
    private lateinit var emptyHomeViewModel: EmptyHomeViewModel
    private lateinit var recyclerAdapter: UsersRecyclerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSelectUserBinding.bind(view)
        binding.lifecycleOwner = this
        emptyHomeViewModel =
            ViewModelProvider(this, emptyHomeViewModelFactory).get(EmptyHomeViewModel::class.java)

        val args: SelectUserFragmentArgs by navArgs()
        val userName = args.user
        recyclerAdapter = UsersRecyclerAdapter()
        recyclerAdapter.setOnItemClickListener {
            val bundle = Bundle().apply { putSerializable("profile", it) }
            findNavController().navigate(R.id.action_selectUserFragment_to_userTabbedInfo, bundle)
        }
        makeRequest(userName)
    }

    private fun makeRequest(userName: String) {
        when {
            TextUtils.isDigitsOnly(userName) -> {
                emptyHomeViewModel.getPlayerProfileByID(userName.toInt())
                emptyHomeViewModel.playerByIDLiveData.observe(viewLifecycleOwner) { profile ->
                    if (profile != null) {
                        val listOfProfiles = listOf(profile)
                        launchRecycler(listOfProfiles)
                    }
                }
            }
            else -> {
                emptyHomeViewModel.getPlayersListByName(userName)
                emptyHomeViewModel.listOfPlayersByNameLiveData.observe(viewLifecycleOwner) { playersByName ->
                    emptyHomeViewModel.getPlayersProfileByName(playersByName)
                    emptyHomeViewModel.listOfProfilesByID.observe(viewLifecycleOwner) { profiles ->
                        if (profiles != null) {
                            launchRecycler(profiles)
                        }
                    }
                }
            }
        }
    }

    private fun launchRecycler(listOfProfiles: List<Profile>) {
        recyclerAdapter.differ.submitList(listOfProfiles)
        binding.usersRecycler.apply {
            adapter = recyclerAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}