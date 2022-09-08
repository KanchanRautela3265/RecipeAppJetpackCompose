package com.compose.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.R
import com.compose.ui.RecipeDto
import com.compose.ui.RecipeViewModel

@Composable
fun RecipeItem(viewModel: RecipeViewModel,itemName: RecipeDto, onClick : ()->Unit) {
    Card(
        modifier = Modifier
            .offset(y = 10.dp)
            .fillMaxWidth()
            .padding(bottom = 30.dp),
        elevation = 5.dp,
        shape = RoundedCornerShape(CornerSize(10.dp))
    ) {
        Column(modifier = Modifier.padding(bottom = 10.dp).clickable(enabled = true, onClick = {
            //viewModel.selectedRecipe.value = itemName
            onClick()
        })) {
            LoadImage(url = itemName.featuredImage,R.drawable.food).value?.let {
                Image(
                    bitmap = it.asImageBitmap(),
                    contentDescription = "Food",
                    modifier = Modifier
                        .height(150.dp)
                        .fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
            }
            Text(
                text = itemName.title,
                color = Color.Black,
                fontSize = 20.sp,
                modifier = Modifier.padding(10.dp),
                style = TextStyle(textAlign = TextAlign.Center)
            )
        }
    }

}

