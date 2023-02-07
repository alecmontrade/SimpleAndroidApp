package com.example.izipaytest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.izipaytest.pages.SinglePage
import com.example.izipaytest.ui.theme.IzipayTestTheme
import com.example.izipaytest.viewmodels.MovieViewModel
import com.example.izipaytest.viewmodels.MovieViewModelFactory

class MainActivity : ComponentActivity() {

    val movieViewModel by viewModels<MovieViewModel> {
        MovieViewModelFactory((application as MovieApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            IzipayTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    SinglePage(movieViewModel)
                }
            }
        }
    }
}