package com.example.izipaytest.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.*
import com.example.izipaytest.data.LikedMovie
import com.example.izipaytest.data.Movie
import com.example.izipaytest.repository.MovieRepository
import kotlinx.coroutines.launch

class MovieViewModel(
    private val repository: MovieRepository
) : ViewModel() {

    var movie: Movie? by mutableStateOf(null)

    val allLikedMovie: LiveData<List<LikedMovie>> = repository.allLikedMovies.asLiveData()

    fun insert(likedMovie: LikedMovie) = viewModelScope.launch {
        repository.insert(likedMovie)
    }

    fun clearDatabase() = viewModelScope.launch {
        repository.clear()
    }

    fun findMovie(name:String) = viewModelScope.launch {
        movie = repository.fetchMovie(name)
    }
}

class MovieViewModelFactory(private val repository: MovieRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MovieViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}