package com.compose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ItemFilter(filterName: String) {
    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier.padding(5.dp).background(Color.Cyan),
        shape = RoundedCornerShape(5.dp)
    ) {
        Text(text = filterName, style = TextStyle(color = Color.White, fontSize = 22.sp))
    }

}