package com.example.todo.presentation.home_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.todo.R
import com.example.todo.presentation.MainViewModel
import com.example.todo.presentation.home_screen.components.Home_Scaffold


@Composable
fun HomeScreen(
    mainViewModel: MainViewModel,
    onUpdate: (id: Int) -> Unit
) {

    Box(modifier = Modifier.fillMaxSize())
    Image(
        painter = painterResource(id = R.drawable.background_image),
        contentDescription = null,
        contentScale = ContentScale.FillBounds,
        modifier = Modifier
            .fillMaxSize()
            .alpha(0.1f) // Adjust alpha as needed
    )
    Home_Scaffold(mainViewModel, onUpdate)





}