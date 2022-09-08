package com.compose.components

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.text.style.ScaleXSpan
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.compose.R
import com.compose.ui.BottomNavigationItem
import com.compose.ui.RecipeDto
import com.compose.ui.RecipeSearchResponse
import com.compose.ui.RecipeViewModel
import com.compose.ui.theme.RecipeAppTheme
import com.compose.ui.util.NetworkResult


@Composable
fun Recipe(navController: NavController, viewModel: RecipeViewModel) {

//    val itemList = remember {
//        mutableStateOf<List<RecipeDto>>(listOf())
//    }

    val selectedItem = remember {
        mutableStateOf<RecipeDto?>(null)
    }

    selectedItem.value = viewModel.selectedRecipe.value
//    Log.d("main", "Recipe: ${selectedItem.value}")
    when (viewModel.responseObj.value) {
        is NetworkResult.Loading -> {
//         ProgressBar(isLoading = true)
        }
        is NetworkResult.Success -> {
//            ProgressBar(isLoading = false)
//            itemList.value = (viewModel.responseObj.value as NetworkResult.Success<RecipeSearchResponse>).data!!.recipes
        }
        is NetworkResult.Error -> {
//            ProgressBar(isLoading = false)

        }
    }
    if (selectedItem.value != null) {
        navController.navigate("recipe_details") {
            popUpTo("recipe")
        }
    }

    Scaffold(modifier = Modifier.fillMaxHeight(), bottomBar = {
        BottomNavigation(
            navController,
            listOf(
                BottomNavigationItem(name = "Home", "recipe",R.drawable.ic_home),
                BottomNavigationItem(name = "Help", "recipe_details",R.drawable.ic_help),
                BottomNavigationItem(name = "Help", "recipe_details",R.drawable.ic_support)
            )
        )
    }) {
        Column(
            modifier = Modifier
                .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SearchView(null, viewModel)
            LazyRow(modifier = Modifier.padding(5.dp)) {
                items(
                    arrayListOf(
                        "Non-Veg",
                        "Veg",
                        "IceCream",
                        "Shakes",
                        "Drinks",
                        "Juice",
                        "Lemonade"
                    )
                ) { item ->
                    ItemFilter(filterName = item)
                }
            }
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxHeight()) {
                ProgressBar(viewModel = viewModel)
                RecipeList(null, viewModel, navController)
            }
        }
    }
}


@Composable
fun ProgressBar(viewModel: RecipeViewModel) {
//    val state by remember {
//        mutableStateOf(viewModel.mRecipeList.value.isNotEmpty())
//    }
//    Box(
//        modifier = Modifier.height(if (!state) 80.dp else 0.dp),
//        contentAlignment = Alignment.Center
//    ) {
//        CircularProgressIndicator(color = Color.Black)
//    }
}

@Composable
fun SearchView(itemList: MutableState<List<RecipeDto>>?, viewModel: RecipeViewModel) {
    var text by remember {
        mutableStateOf("")
    }

    TextField(
        value = text,
        onValueChange = { it ->
            text = it
//            itemList.value = viewModel.recipeList.value.filter {
//                it.title.contains(text,ignoreCase = true)
//            }
        },
        placeholder = { Text(text = "Search", color = Color.White, fontSize = 22.sp) },
        modifier = Modifier
            .fillMaxWidth(1f)
            .padding(10.dp),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        leadingIcon = {
            Icon(Icons.Default.Search, contentDescription = "Search Image")
        }
    )
}

@Composable
fun RecipeList(
    itemList: MutableState<List<RecipeDto>>?,
    viewModel: RecipeViewModel,
    navController: NavController
) {
    LazyColumn(contentPadding = PaddingValues(10.dp)) {
        viewModel.responseObj.value?.data?.let {
            items(it.recipes) { recipeDto ->
                RecipeItem(viewModel, recipeDto) {
                    navController.navigate("recipe_details")
                    viewModel.setData(recipeDto)
                }
            }
        }
    }
}

@Composable
fun LoadImage(url: String, defaultDrawable: Int): MutableState<Bitmap?> {

    val bitmap: MutableState<Bitmap?> = remember {
        mutableStateOf(null)
    }

    Glide.with(LocalContext.current)
        .asBitmap()
        .load(defaultDrawable)
        .into(object : CustomTarget<Bitmap>() {
            override fun onLoadCleared(placeholder: Drawable?) {}
            override fun onResourceReady(
                resource: Bitmap,
                transition: Transition<in Bitmap>?
            ) {
                bitmap.value = resource
            }
        })

    Glide.with(LocalContext.current)
        .asBitmap()
        .load(url)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                bitmap.value = resource
            }

            override fun onLoadCleared(placeholder: Drawable?) {
                TODO("Not yet implemented")
            }

        })
    return bitmap
}

@Composable
fun BottomNavigation(navController: NavController, itemList: List<BottomNavigationItem>) {

    val backStackEntry = navController.currentBackStackEntryAsState()
        BottomNavigation(modifier = Modifier.background(color = Color.Magenta)) {
            itemList.forEach {
                val selected = it.route == backStackEntry.value?.destination?.route
                BottomNavigationItem(selected = selected, onClick = { navController.navigate(route = it.route) }, icon = {
                    Icon(
                        painter = painterResource(id = it.icon),
                        contentDescription = it.name
                    )
                })
            }
        }
}