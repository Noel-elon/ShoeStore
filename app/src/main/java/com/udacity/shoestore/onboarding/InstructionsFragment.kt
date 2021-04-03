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
import com.udacity.shoestore.databinding.FragmentInstructionsBinding

class InstructionsFragment : Fragment() {
    private lateinit var binding: FragmentInstructionsBinding
    private lateinit var viewModel: OnBoardingViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInstructionsBinding.inflate(inflater)
        viewModel = ViewModelProvider(this).get(OnBoardingViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.navigateToShoeList.observe(viewLifecycleOwner, Observer {
            if (it) {
                val navController = findNavController()
                navController.navigate(R.id.action_instructionsFragment_to_shoeListingFragment)
                viewModel.onNavigatedToShoeList()
            }
        })

        return binding.root
    }

}