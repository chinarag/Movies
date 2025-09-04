package com.example.movies.screen.splash

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.movies.R
import com.example.movies.databinding.FragmentSplashBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashFragment : Fragment() {
   lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkFlow()
    }



    private fun checkFlow(){

        val sp = requireContext().getSharedPreferences("local_shared", Context.MODE_PRIVATE)
        val isOnboardingCompleted = sp.getBoolean("isCompleted", false)
        val isLogin = sp.getBoolean("isLogin", false)

        lifecycleScope.launch {
            delay(3000)

            if(!isOnboardingCompleted){
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToOnboardingFragment())
            } else if(isLogin){
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
            } else {
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment())
            }

        }



    }
}