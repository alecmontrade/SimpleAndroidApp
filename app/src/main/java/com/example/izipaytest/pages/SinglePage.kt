package com.example.izipaytest.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.izipaytest.data.LikedMovie
import com.example.izipaytest.viewmodels.MovieViewModel

@Composable
fun SinglePage(movieViewModel: MovieViewModel) {

    val searchedMovie = movieViewModel.movie
    val likedMovies by movieViewModel.allLikedMovie.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchField(movieViewModel)

        if (searchedMovie != null) {
            Text(text = "Film title : ${searchedMovie.Title}")
            Image(
                painter = rememberAsyncImagePainter(searchedMovie.Poster),
                contentDescription = null,
            )
            Button(onClick = {
                movieViewModel.insert(LikedMovie(searchedMovie.Title))
            }) {
                Text(text = "Add to list")
            }
        } else {
            Text(text = "Please search an existing movie")
        }

        likedMovies?.let { moviesList ->
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxWidth(0.8f)
                    .background(Color.DarkGray)
            ) {
                for (likedMovie in moviesList) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = likedMovie.name,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        if((likedMovies?.size ?: 0) > 0) {
            Button(onClick = {
                movieViewModel.clearDatabase()
            }) {
                Text(text = "Clear Data")
            }
        }
    }
}

@Composable
fun SearchField(movieViewModel: MovieViewModel) {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    TextField(
        value = text,
        onValueChange = { newText ->
            text = newText
        },
        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center)
    )

    Button(onClick = {
        movieViewModel.findMovie(text.text)
    }) {
        Text(text = "Search")
    }
}