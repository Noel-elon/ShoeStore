package com.udacity.shoestore.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {

    private lateinit var binding: FragmentWelcomeBinding
    private lateinit var viewModel: OnBoardingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWelcomeBinding.inflate(inflater)
        viewModel = ViewModelProvider(this).get(OnBoardingViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.navigateToInstructions.observe(viewLifecycleOwner, Observer {
            if (it) {
                val navController = findNavController()
                navController.navigate(R.id.action_welcomeFragment_to_instructionsFragment)
                viewModel.onNavigatedToInstructions()
            }
        })

        return binding.root
    }

}