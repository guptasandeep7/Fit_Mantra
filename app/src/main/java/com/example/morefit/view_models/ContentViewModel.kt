package com.example.morefit.view_models

import androidx.lifecycle.*
import com.example.morefit.model.database.Content
import com.example.morefit.repositories.ContentRepo
import kotlinx.coroutines.launch

@Suppress("UNCHECKED_CAST")
class ContentViewModel(private val repository: ContentRepo) : ViewModel() {
    val allWords: LiveData<List<Content>> = repository.allWords.asLiveData()
    fun insert(word: ArrayList<Content>) = viewModelScope.launch {
        repository.insert(word)
    }
    fun deleteAll()=viewModelScope.launch {
        repository.delteAll()
    }
}

class WordViewModelFactory(private val repository: ContentRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ContentViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ContentViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}