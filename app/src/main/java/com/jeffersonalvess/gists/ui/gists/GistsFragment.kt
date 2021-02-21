package com.jeffersonalvess.gists.ui.gists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.jeffersonalvess.gists.databinding.FragmentGistsBinding
import com.jeffersonalvess.gists.extensions.loadingVisibility
import com.jeffersonalvess.network.dto.Gist
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class GistsFragment : Fragment() {

    private val viewModel: GistsViewModel by viewModel {
        parametersOf(::onErrorCallback)
    }

    private var _binding: FragmentGistsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGistsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = GistsAdapter(::goToDetails)

        viewModel.gistList.observe(viewLifecycleOwner, {
            with(binding.recyclerView.adapter as GistsAdapter) {
                submitList(it)
            }
        })

        viewModel.showProgress.observe(viewLifecycleOwner, {
            loadingVisibility(it)
        })
    }

    private fun goToDetails(gist: Gist) {
        val actionDetail = GistsFragmentDirections.actionGistsFragmentToDetailsFragment(gist)
        findNavController().navigate(actionDetail)
    }

    private fun loadingVisibility(shouldShow: Boolean) {
        binding.recyclerView.loadingVisibility(shouldShow)
        binding.loadingIndicator.loadingVisibility(!shouldShow)
    }

    private fun onErrorCallback() {
        binding.errorLayout.root.visibility = VISIBLE
        binding.recyclerView.visibility = GONE
        binding.loadingIndicator.visibility = GONE

        binding.errorLayout.retryButton.setOnClickListener {
            binding.errorLayout.root.visibility = GONE
            binding.recyclerView.visibility = VISIBLE
            binding.loadingIndicator.visibility = VISIBLE

            viewModel.retry()
        }
    }
}
