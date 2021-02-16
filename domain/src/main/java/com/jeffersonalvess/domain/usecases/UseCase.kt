package com.jeffersonalvess.domain.usecases

interface UseCase<in T, out V> {
    fun run(param: T): V
}