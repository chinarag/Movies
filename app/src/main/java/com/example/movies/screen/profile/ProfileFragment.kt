package com.example.movies.screen.profile

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.movies.R
import com.example.movies.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {
lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textViewLogOut.setOnClickListener {
            logout()
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToLoginFragment())

        }
    }

    private fun logout(){
        val sp = requireContext().getSharedPreferences("local_shared", Context.MODE_PRIVATE)

        sp.edit().putBoolean("isLogin", false).apply()
    }
}