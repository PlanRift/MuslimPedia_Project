package com.example.muslimpedia.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.muslimpedia.data.model.NewsResponse
import com.example.muslimpedia.data.repository.NewsRepository

class NewsViewModel(private val repository: NewsRepository) : ViewModel() {


    val commonMuslimNews: LiveData<NewsResponse> = repository.commonMuslimNews
    val aboutAlQuranNews: LiveData<NewsResponse> = repository.aboutAlQuranNews
    val alJazeeraNews: LiveData<NewsResponse> = repository.alJazeeraNews
    val warningForMuslimNews: LiveData<NewsResponse> = repository.warningForMuslimNews
    val searchNews: LiveData<NewsResponse> = repository.searchNews
    val isLoading: LiveData<Boolean> = repository.isLoading

    fun getCommonMuslimNews() {
        repository.getCommonMuslimNews()
    }


    fun getAboutAlQuranNews() {
        repository.getAboutAlQuranNews()
    }


    fun getAlJazeraNews() {
        repository.getAlJazeeraNews()
    }


    fun getWarningNews() {
        repository.getWarningForMuslimNews()
    }


    fun getSearchNews(q: String) {
        repository.getSearchNews(q)
    }




}