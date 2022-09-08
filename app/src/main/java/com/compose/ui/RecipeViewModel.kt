package com.compose.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.repository.NetworkRepository
import com.compose.ui.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val networkRepository: NetworkRepository,
) : ViewModel() {
    private val recipeList = mutableStateOf<NetworkResult<List<RecipeDto>>?>(null)
    val selectedRecipe = mutableStateOf<RecipeDto?>(null)
    val mRecipeList:State<NetworkResult<List<RecipeDto>>?> = recipeList
    val responseObj : MutableState<NetworkResult<RecipeSearchResponse>?> = mutableStateOf(null)
    init {
     search()
    }

    private val _details:MutableState<RecipeDto?> = mutableStateOf(null)
    val detail:State<RecipeDto?> = _details

    fun setData(data:RecipeDto){
        _details.value = data
    }


    private fun search(){
        responseObj.value = NetworkResult.Loading()
        viewModelScope.launch {
//            try {
//                val response =   apiService.search(token,1,"")
//                if(response.count>0){
//                    recipeList.value = response.recipes
//                    responseObj.value = NetworkResult.Success(response)
//                }
//            }catch (exception:Exception){
//              responseObj.value = NetworkResult.Error(exception.message)
//            }

            networkRepository.flow.collect {
                recipeList.value = it
            }

        }
    }

}


interface ApiService {

    @GET("search")
    suspend fun search(
        @Header("Authorization") token: String,
        @Query("page") page: Int,
        @Query("query") query: String
    ): RecipeSearchResponse

}