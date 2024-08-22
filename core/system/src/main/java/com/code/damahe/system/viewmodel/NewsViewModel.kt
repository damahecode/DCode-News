package com.code.damahe.system.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.code.damahe.system.data.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val newsRepository: NewsRepository): ViewModel() {

    val homeNews = newsRepository.getNews(
        sources = listOf("bbc-news", "abc-news")
    ).cachedIn(viewModelScope)

}