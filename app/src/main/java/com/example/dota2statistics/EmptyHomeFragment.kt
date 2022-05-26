package com.example.dota2statistics

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.dota2statistics.data.models.byID.Profile
import com.example.dota2statistics.databinding.EmptyHomeFragmentBinding
import com.example.dota2statistics.presentation.viewmodels.EmptyHomeViewModel
import com.example.dota2statistics.presentation.viewmodels.EmptyHomeViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EmptyHomeFragment : Fragment(R.layout.empty_home_fragment) {

    private lateinit var binding: EmptyHomeFragmentBinding

    @Inject
    lateinit var emptyHomeViewModelFactory: EmptyHomeViewModelFactory
    private lateinit var emptyHomeViewModel: EmptyHomeViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = EmptyHomeFragmentBinding.bind(view)
        binding.lifecycleOwner = this
        emptyHomeViewModel =
            ViewModelProvider(this, emptyHomeViewModelFactory).get(EmptyHomeViewModel::class.java)
        binding.bindingEmptyHomeViewModel = emptyHomeViewModel


        binding.searchButton.setOnClickListener {
            search()
        }
    }

    private fun search() {
        val text = binding.userInput.text.toString()
        var bundle: Bundle

        when {
            text.isEmpty() -> {
                Toast.makeText(context, "You must input your User Name or User ID", LENGTH_LONG)
                    .show()
                findNavController().navigate(
                    R.id.action_emptyHomeFragment_to_selectUserFragment
                )
            }
            TextUtils.isDigitsOnly(binding.userInput.text) -> {
                emptyHomeViewModel.getPlayerProfileByID(text.toInt())
                emptyHomeViewModel.playerByIDLiveData.observe(viewLifecycleOwner) { profile ->
                    if (profile != null) {
                        val profile = arrayOf(profile)
                        bundle = Bundle().apply {
                            putSerializable("user", profile)
                        }
                        findNavController().navigate(
                            R.id.action_emptyHomeFragment_to_selectUserFragment,
                            bundle
                        )
                    } else {
                        Toast.makeText(context, "No results found for such User ID", LENGTH_LONG)
                            .show()
                    }
                }
            }
            else -> {
                emptyHomeViewModel.getPlayersListByName(text)
                emptyHomeViewModel.listOfPlayersByNameLiveData.observe(viewLifecycleOwner) { playersByName ->
                    emptyHomeViewModel.getPlayersProfileByName(playersByName)
                    emptyHomeViewModel.listOfProfilesByID.observe(viewLifecycleOwner) { profiles ->
                        if (profiles != null) {
                            val list: Array<Profile> = profiles.toTypedArray()
                            bundle = Bundle().apply {
                                putSerializable("user", list)
                            }
                            findNavController().navigate(
                                R.id.action_emptyHomeFragment_to_selectUserFragment,
                                bundle
                            )
                        }
                    }
                }
            }
        }
    }
}