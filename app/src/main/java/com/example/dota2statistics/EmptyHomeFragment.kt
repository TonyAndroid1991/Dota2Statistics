package com.example.dota2statistics

import android.os.Bundle
import android.view.View
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.dota2statistics.databinding.EmptyHomeFragmentBinding

class EmptyHomeFragment : Fragment(R.layout.empty_home_fragment) {

    private lateinit var binding: EmptyHomeFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = EmptyHomeFragmentBinding.bind(view)
        binding.lifecycleOwner = this

        binding.searchButton.setOnClickListener {
            search()
        }
    }

    private fun search() {
        val userName: String = binding.userInput.text.toString()
        when {
            userName.isEmpty() -> {
                Toast.makeText(context, "You must input your User Name or User ID", LENGTH_LONG)
                    .show()
            }
            else -> {
                val bundle: Bundle = Bundle().apply { putSerializable("user", userName) }
                findNavController().navigate(R.id.action_emptyHomeFragment_to_selectUserFragment, bundle)
            }
        }
    }
}