package com.udacity.shoestore.shoe

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListingBinding
import com.udacity.shoestore.databinding.ShoeItemBinding
import timber.log.Timber

class ShoeListingFragment : Fragment() {

    private lateinit var binding: FragmentShoeListingBinding
    private val viewModel: ShoeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)

        val navController = findNavController()

        binding = FragmentShoeListingBinding.inflate(inflater)
        binding.shoeViewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.navigateToShoeDetail.observe(viewLifecycleOwner, Observer {
            if (it) {
                navController.navigate(R.id.action_shoeListingFragment_to_shoeDetailFragment)
                viewModel.onNavigatedShoeDetail()
            }
        })

        viewModel.navigateToLogin.observe(viewLifecycleOwner, Observer {
            if (it) {
                navController.navigate(R.id.action_shoeListingFragment_to_loginFragment)
            }
        })

        viewModel.shoeList.observe(viewLifecycleOwner, Observer {
            it.forEachIndexed { index, shoe ->
                Timber.d(shoe.toString())
                val shoeBinding: ShoeItemBinding =
                    DataBindingUtil.inflate(inflater, R.layout.shoe_item, container, false)
                shoeBinding.shoe = shoe
                shoeBinding.root.id = index
                binding.shoeList.addView(shoeBinding.root)
            }
        })

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.logout_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item,
            requireView().findNavController()
        ) || super.onOptionsItemSelected(item)

    }
}