package com.example.morefit.utils

sealed class Response<T>(val data: T? = null, val errorMessage: String? = null) {
	class Success<T>(data: T? = null) : Response<T>(data = data)
	class Error<T>(errorMessage: String) : Response<T>(errorMessage = errorMessage)
}
