package com.android.wishlist.widgets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults.colors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun WishTextField(
    label : String,
    value : String,
    onValueChanged : (String) -> Unit
) = OutlinedTextField(
    value = value,
    onValueChange = onValueChanged,
    label = { Text(text = label, color = Color.White) },
    modifier = Modifier.fillMaxWidth(),
    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    colors = colors(
        cursorColor = Color.White,
        focusedBorderColor = Color.White,
        focusedTextColor = Color.White,
        unfocusedBorderColor = Color.Black,
        focusedLabelColor = Color.White,
        unfocusedLabelColor = Color.Black
    )
)

@Preview(showBackground = true) 
@Composable
fun WishTextFieldPreview() = WishTextField(label = "ABC", value = "DEF", onValueChanged = {})