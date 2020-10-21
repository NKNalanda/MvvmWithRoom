package com.nknalanda.mvvmwithroom.ui

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.nknalanda.mvvmwithroom.R
import com.nknalanda.mvvmwithroom.data.model.MovieEntity
import com.nknalanda.mvvmwithroom.databinding.FragmentAddMovieBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddMovieFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddMovieFragment : Fragment(), View.OnClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var binding: FragmentAddMovieBinding? = null
    private var viewModel: MovieViewModel? = null
    private var adapter: ViewMovieAdapter? = null
    private var editMovieEntity: MovieEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        Log.e("AddMovieFragment", "Inside onCreate()")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddMovieBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(MovieViewModel::class.java)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        viewModel?.getAllMovies()?.observe(viewLifecycleOwner, Observer {
            adapter?.updateMovieList(it)
        })
        binding?.addMovie?.setOnClickListener(this)
        binding?.btnAddMovie?.setOnClickListener(this)
        binding?.updateMovie?.setOnClickListener(this)
    }

    private fun setUpRecyclerView() {
        adapter = ViewMovieAdapter(object : ViewMovieAdapter.IOnItemClickListener {
            override fun onClickDelete(item: MovieEntity) {
                viewModel?.deleteMovie(item)
            }

            override fun onClickEdit(item: MovieEntity) {
                binding!!.layoutAddMovie.visibility = View.VISIBLE
                binding!!.rvMovies.visibility = View.GONE
                binding!!.addMovie.visibility = View.GONE
                binding?.updateMovie?.visibility = View.VISIBLE
                binding?.btnAddMovie?.visibility = View.GONE
                binding!!.etMovieName.setText(item.movieName)
                binding!!.etMovieYear.setText(item.movieYear)
                editMovieEntity = item
            }
        })
        binding?.rvMovies?.layoutManager = LinearLayoutManager(context)
        binding?.rvMovies?.adapter = adapter
    }

    override fun onClick(view: View) {
        when (view.id) {
            binding?.addMovie?.id -> {
                binding!!.layoutAddMovie.visibility = View.VISIBLE
                binding!!.rvMovies.visibility = View.GONE
                binding!!.addMovie.visibility = View.GONE
                binding?.btnAddMovie?.visibility = View.VISIBLE
                binding?.updateMovie?.visibility = View.GONE
            }

            binding?.btnAddMovie?.id -> {
                if (binding!!.etMovieName.text.isNullOrEmpty()) {
                    binding!!.etMovieName.error = "This field cannot be empty"
                } else if (binding!!.etMovieYear.text.isNullOrEmpty()) {
                    binding!!.etMovieYear.error = "This field cannot be empty"
                } else {
                    binding!!.layoutAddMovie.visibility = View.GONE
                    binding!!.rvMovies.visibility = View.VISIBLE
                    binding!!.addMovie.visibility = View.VISIBLE
                    hidekeyboard(view)
                    viewModel?.onAddClick(
                        MovieEntity(
                            0,
                            binding!!.etMovieName.text.toString(),
                            binding!!.etMovieYear.text.toString()
                        )
                    )
                    binding!!.etMovieName.setText("")
                    binding!!.etMovieYear.setText("")
                }
            }

            binding?.updateMovie?.id -> {
                if (binding!!.etMovieName.text.isNullOrEmpty()) {
                    binding!!.etMovieName.error = "This field cannot be empty"
                } else if (binding!!.etMovieYear.text.isNullOrEmpty()) {
                    binding!!.etMovieYear.error = "This field cannot be empty"
                } else {
                    binding!!.layoutAddMovie.visibility = View.GONE
                    binding!!.rvMovies.visibility = View.VISIBLE
                    binding!!.addMovie.visibility = View.VISIBLE
                    hidekeyboard(view)
                    editMovieEntity?.movieName = binding!!.etMovieName.text.toString()
                    editMovieEntity?.movieYear = binding!!.etMovieYear.text.toString()
                    viewModel?.updateMovie(editMovieEntity!!)
                    binding!!.etMovieName.setText("")
                    binding!!.etMovieYear.setText("")
                }
            }
        }
    }

    private fun hidekeyboard(view: View) {
        val inputMethodManager =
            context!!.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddMovieFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddMovieFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}