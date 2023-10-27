package com.example.muslimpedia.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.muslimpedia.data.repository.NewsRepository
import com.example.muslimpedia.ui.NewsViewModel
import java.lang.IllegalArgumentException

class NewsViewModelFactory(private val repository: NewsRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsViewModel::class.java)){
            return NewsViewModel(repository)as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}