package com.example.muslimpedia.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.muslimpedia.adapter.NewsAdapter
import com.example.muslimpedia.data.repository.NewsRepository
import com.example.muslimpedia.databinding.FragmentAlJazeeraBinding
import com.example.muslimpedia.ui.NewsViewModel
import com.example.muslimpedia.utils.NewsViewModelFactory

class AlJazeeraFragment : Fragment() {

    lateinit var binding: FragmentAlJazeeraBinding
    private val AlJazeeraViewModel : NewsViewModel by viewModels{
        NewsViewModelFactory(NewsRepository())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAlJazeeraBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mAdapter = NewsAdapter()

        AlJazeeraViewModel.getAlJazeraNews()

        AlJazeeraViewModel.alJazeeraNews.observe(viewLifecycleOwner){dataNews ->
            mAdapter.setData(dataNews.articles)
            binding.rvAlJazeeraNews.apply {
                adapter = mAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }
        }

        AlJazeeraViewModel.isLoading.observe(viewLifecycleOwner){
            isLoading(it)
        }
    }

    private fun isLoading(isLoading: Boolean) {
        if(isLoading){
            binding.loadingView.root.visibility = View.VISIBLE
        } else {
            binding.loadingView.root.visibility = View.GONE
        }
    }
}