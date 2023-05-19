package com.edwinacubillos.moviedb2023.ui.favorites

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.edwinacubillos.moviedb2023.R
import com.edwinacubillos.moviedb2023.databinding.FragmentFavoritesBinding
import com.edwinacubillos.moviedb2023.local.model.LocalMovie

class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private lateinit var favoritesViewModel : FavoritesViewModel
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        favoritesViewModel = ViewModelProvider(this)[FavoritesViewModel::class.java]
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val favoritesMoviesList = ArrayList<LocalMovie>()
        val moviesFavoritesAdapter = MoviesFavoritesAdapter(
            favoritesMoviesList,
            onItemClicked = { },
            onItemLongClicked = { localMovie ->
                deleteFavoriteMovie(localMovie)
            })

        binding.moviesFavoritesRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@FavoritesFragment.requireContext())
            adapter = moviesFavoritesAdapter
            setHasFixedSize(false)
        }

        favoritesViewModel.loadFavoritesMovies()

        favoritesViewModel.favoritesMovies.observe(viewLifecycleOwner){ favoritesMoviesList ->
            moviesFavoritesAdapter.appendItems(favoritesMoviesList)
        }

        return root
    }

    private fun deleteFavoriteMovie(localMovie: LocalMovie) {
        val alertDialog: AlertDialog? = activity?.let{
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setMessage("Desea eliminar la pelicula ${localMovie.title} de sus favoritos?")
                setPositiveButton(R.string.accept){ dialog, id ->
                    favoritesViewModel.deleteFavoriteMovie(localMovie)
                    favoritesViewModel.loadFavoritesMovies()
                }
                setNegativeButton(R.string.cancel){ dialog, id ->

                }
            }
            builder.create()
        }
        alertDialog?.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}