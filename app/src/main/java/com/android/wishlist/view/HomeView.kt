package com.android.wishlist.view

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.android.wishlist.navigation.Screen
import com.android.wishlist.viewModel.WishViewModel
import com.android.wishlist.widgets.AppBarView
import com.android.wishlist.widgets.WishItem

@Composable
fun HomeView(
    navController: NavController,
    viewModel: WishViewModel
) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            AppBarView(
                title = "Wishlist",
                onBackNavClicked = Toast.makeText(context, "Button Clicked", Toast.LENGTH_SHORT)::show
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(20.dp),
                contentColor = Color.White,
                containerColor = Color.Black,
                onClick = { navController.navigate(Screen.AddScreen.route) }
            ) { Icon(imageVector = Icons.Default.Add, contentDescription = null) }
        },
        floatingActionButtonPosition = FabPosition.End,
        containerColor = Color.Red
    ) {
        val wishlist = viewModel.getAllWishes.collectAsState(initial = listOf())
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(it)) {
                items(wishlist.value) {
                    wish -> WishItem(wish = wish) {

                    }
                }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeViewPreview() = HomeView(navController = rememberNavController(), viewModel = viewModel())