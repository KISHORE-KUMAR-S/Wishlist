package com.android.wishlist.widgets

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.wishlist.ui.theme.WishlistTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarView(
    title : String,
    onBackNavClicked : () -> Unit = {}
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                modifier = Modifier.padding(start = 4.dp)
            )
        },
        navigationIcon = {
            if(!title.contains("Wishlist")) {
                IconButton(onClick = { onBackNavClicked() }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Red,
            titleContentColor = Color.White,
            navigationIconContentColor = Color.White
        ),
    )

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AppBarPreview() {
    WishlistTheme {
        AppBarView(title = "App Bar")
    }
}