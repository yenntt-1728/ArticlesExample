package com.example.articlesexample.categories

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.articlesexample.model.DataCategories
import com.example.articlesexample.repository.ArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(private val articleRepository: ArticleRepository) :
    ViewModel() {
    val dataCategories = MutableLiveData<DataCategories>()

    fun getCategories() {
        MainScope().launch {
            val data = articleRepository.getCategories()
            dataCategories.postValue(data)
        }
    }
}