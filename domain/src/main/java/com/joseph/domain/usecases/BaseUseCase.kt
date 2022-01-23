package com.joseph.domain.usecases

import com.joseph.domain.util.TaskResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map


abstract class BaseUseCase<Result, in Params>() {

    abstract suspend operator fun invoke(params: Params): Result

    protected fun <T> Flow<T>.toResult() = map {
        TaskResult.Success(it) as TaskResult<T>
    }.catch { cause: Throwable ->
        emit(TaskResult.Failed(cause.message.toString()))
    }

}