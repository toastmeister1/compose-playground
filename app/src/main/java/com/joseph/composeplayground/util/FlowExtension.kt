package com.joseph.composeplayground.util

import com.joseph.domain.util.TaskResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect


suspend fun <T> Flow<TaskResult<T>>.collectWithCallback(onSuccess: suspend (T) -> Unit, onFailed: suspend (String) -> Unit) {
    collect { result ->
        when(result) {
            is TaskResult.Success -> {
                onSuccess.invoke(result.data)
            }

            is TaskResult.Failed -> {
                onFailed.invoke(result.message)
            }
        }
    }
}