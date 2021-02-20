package com.jeffersonalvess.gists.ui.gists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.HORIZONTAL
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.android.material.snackbar.Snackbar
import com.jeffersonalvess.gists.R
import com.jeffersonalvess.gists.databinding.FragmentGistsBinding
import com.jeffersonalvess.network.dto.Gist
import org.koin.androidx.viewmodel.ext.android.viewModel

class GistsFragment : Fragment() {

    private val viewModel by viewModel<GistsViewModel>()

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
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = GistsAdapter(::goToDetails)
        binding.recyclerView.addItemDecoration(DividerItemDecoration(context, HORIZONTAL))

        viewModel.gistList.observe(viewLifecycleOwner, {
            (binding.recyclerView.adapter as GistsAdapter).apply {
                submitList(it)

                if (itemCount != 0) {
                    showPage()
                }
            }
        })
    }

    private fun goToDetails(gist: Gist) {
       // val actionDetail = GistsFragmentDirections.actionGistsFragmentToDetailsFragment(gist)
       // findNavController().navigate(actionDetail)
    }

    private fun showPage() {
        binding.loadingIndicator.visibility = View.GONE
        binding.recyclerView.visibility = View.VISIBLE
    }

    private fun onErrorCallback() {
        //if (binding.recyclerView.adapter?.itemCount != 0) {
           // findNavController().navigate(R.id.action_homeFragment_to_errorFragment)
        //} else {
            Snackbar.make(
                binding.recyclerView,
                R.string.error_loading_gists,
                Snackbar.LENGTH_SHORT
            ).show()
       // }
    }
}