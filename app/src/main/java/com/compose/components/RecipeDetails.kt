package com.compose.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.compose.R
import com.compose.ui.RecipeViewModel

@Composable
fun RecipeDetails(navHostController: NavHostController,viewModel: RecipeViewModel) {
    val recipeDto = viewModel.detail.value
    Column(modifier = Modifier.padding(10.dp), verticalArrangement = Arrangement.Center) {
       Card(shape = RoundedCornerShape(15.dp)) {
           if (recipeDto != null) {
               LoadImage(url = recipeDto.featuredImage,R.drawable.food).value?.let {
                   Image(
                       bitmap = it.asImageBitmap(),
                       contentDescription = "Food",
                       modifier = Modifier
                           .height(150.dp)
                           .fillMaxWidth(),
                       contentScale = ContentScale.Crop
                   )
               }
           }
       }
        recipeDto?.let {
            RecipeDetailsText(text = "Recipe Name : ${it.title}")
//            RecipeDetailsText(text = "Ingredients : ${ it.ingredients }")
            RecipeDetailsText(text = "Publisher   : ${it.publisher}")
        }

    }
}
@Composable
fun RecipeDetailsText(text:String){
    Text(text = text, style = TextStyle(color = Color.Black, fontSize = 20.sp), modifier = Modifier.padding(start = 10.dp, top = 10.dp))
}

