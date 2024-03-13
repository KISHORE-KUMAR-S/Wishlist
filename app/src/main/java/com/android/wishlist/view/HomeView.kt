package com.android.wishlist.view

import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
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

@OptIn(ExperimentalMaterial3Api::class)
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
                onClick = { navController.navigate(Screen.AddScreen.route + "/0L") }
            ) { Icon(imageVector = Icons.Default.Add, contentDescription = null) }
        },
        floatingActionButtonPosition = FabPosition.End,
        containerColor = Color.Red
    ) {
        val wishlist = viewModel.getAllWishes.collectAsState(initial = listOf())

        if(wishlist.value.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "No wishlist have been found",
                    color = Color.White
                )
            }
        } else {
            LazyColumn(modifier = Modifier
                .fillMaxSize()
                .padding(it)) {
                items(wishlist.value, key = { wishlist -> wishlist.id }) {
                        wish ->
                    val dismissState = rememberDismissState(
                        confirmValueChange = {
                                dismissValue ->
                            if(dismissValue == DismissValue.DismissedToEnd || dismissValue == DismissValue.DismissedToStart) {
                                viewModel.deleteWish(wish)
                            }
                            true
                        }
                    )

                    SwipeToDismiss(
                        state = dismissState,
                        background = {
                            val color by animateColorAsState(
                                targetValue = if (dismissState.dismissDirection == DismissDirection.EndToStart) {
                                    Color.Black
                                } else {
                                    Color.Transparent
                                }, label = ""
                            )
                            val alignment = Alignment.CenterEnd
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(color)
                                    .padding(horizontal = 20.dp),
                                contentAlignment = alignment) {
                                Icon(
                                    Icons.Default.Delete,
                                    contentDescription = "Delete Icon",
                                    tint = Color.White
                                )
                            }
                        },
                        dismissContent = {
                            WishItem(wish = wish) {
                                val id = wish.id
                                navController.navigate(Screen.AddScreen.route + "/$id")
                            }
                        },
//                        directions = setOf(DismissDirection.StartToEnd, DismissDirection.EndToStart),
                        directions = setOf(DismissDirection.EndToStart)
                    )

//                    WishItem(wish = wish) {
//                        val id = wish.id
//                        navController.navigate(Screen.AddScreen.route + "/$id")
//                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeViewPreview() = HomeView(navController = rememberNavController(), viewModel = viewModel())