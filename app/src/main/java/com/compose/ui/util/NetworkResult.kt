package com.compose.ui.util

//sealed class ApiResponse<K>(data: K? = null, msg: String? = null) {
//    class Loading : ApiResponse<Loading>()
//    class Success<L>(data: L?) : ApiResponse<Success<L>>()
//    class Error(throwable: Throwable) : ApiResponse<Error>(msg = throwable.message)
//}


sealed class NetworkResult<T>(
    val data: T? = null,
    val message: String? = null
) {

    class Success<T>(data: T) : NetworkResult<T>(data)

    class Error<T>(data: T? = null,message: String?=null) : NetworkResult<T>(data, message)

    class Loading<T>(data: T?=null,message: String?=null) : NetworkResult<T>(data,message)

}