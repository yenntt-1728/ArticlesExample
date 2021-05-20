package com.example.articlesexample

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.articlesexample.model.Article
import com.example.articlesexample.repository.ArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository : ArticleRepository) : ViewModel() {
    private var currentResultLiveData: LiveData<PagingData<Article>>? = null

    fun getArticleLiveData(): LiveData<PagingData<Article>> {
        val newResultLiveData: LiveData<PagingData<Article>> =
            repository.getArticleLiveData().cachedIn(viewModelScope)
        currentResultLiveData = newResultLiveData
        return newResultLiveData
    }
}