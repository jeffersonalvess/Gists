package com.jeffersonalvess.gists.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.jeffersonalvess.database.entities.Favorites
import com.jeffersonalvess.gists.R
import com.jeffersonalvess.gists.databinding.FragmentFavoritesBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesFragment : Fragment() {

    private val viewModel: FavoritesViewModel by viewModel()

    private var _binding: FragmentFavoritesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = FavoritesAdapter(::deleteFavorite)

        viewModel.favoritesList.observe(viewLifecycleOwner, {
            with(binding.recyclerView.adapter as FavoritesAdapter) {
                submitList(it)
            }
        })
    }

    private fun deleteFavorite(favorite: Favorites) {
        viewModel.deleteFavorite(favorite)

        Snackbar.make(
            binding.recyclerView,
            R.string.confirm_favorite_removal,
            Snackbar.LENGTH_SHORT
        ).show()
    }
}