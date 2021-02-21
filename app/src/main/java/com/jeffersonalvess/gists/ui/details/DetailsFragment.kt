package com.jeffersonalvess.gists.ui.details

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.jeffersonalvess.gists.databinding.FragmentDetailsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DetailsFragment : Fragment() {

    private val viewModel:DetailsViewModel by viewModel {
        val gist = arguments?.let { DetailsFragmentArgs.fromBundle(it).gist }
        parametersOf(gist)
    }

    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)

        binding = FragmentDetailsBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> {
                navigateHome()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    fun navigateHome() {
        val action = DetailsFragmentDirections.actionDetailsFragmentToGistsFragment()
        findNavController().navigate(action)
    }
}