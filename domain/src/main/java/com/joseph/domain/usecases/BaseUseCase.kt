package com.joseph.domain.usecases


abstract class BaseUseCase<Result, in Params>() {

    abstract suspend operator fun invoke(params: Params): Result

}