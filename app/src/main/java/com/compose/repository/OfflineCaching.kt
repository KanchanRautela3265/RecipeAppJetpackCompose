package com.compose.repository

import com.compose.ui.util.*
import kotlinx.coroutines.flow.*

inline fun <RecipeResult, RecipeReq> getRecipeData(
    crossinline queryDb: () -> Flow<RecipeReq>,
    crossinline fetch: suspend () -> RecipeResult,
    crossinline updateDb: suspend (RecipeResult) -> Unit
) =
    flow {
        val data = queryDb().first()
        val flow = try {
            emit(NetworkResult.Loading(data))
            updateDb(fetch())
            queryDb().map {
                NetworkResult.Success(it)
            }
        } catch (throwable: Throwable) {
            queryDb().map {
                NetworkResult.Error(data, throwable.localizedMessage)
            }
        }
        emitAll(flow = flow)
    }
