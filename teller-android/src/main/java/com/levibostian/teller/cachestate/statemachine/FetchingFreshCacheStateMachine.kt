package com.levibostian.teller.cachestate.statemachine

import java.util.*


/**
 * Immutable state machine for the phase of response's lifecycle when cache response exists. This state machine is responsible for containing information on if a repository is fetching fresh cache response to update the existing cache response.
 */
internal class FetchingFreshCacheStateMachine private constructor(val state: State, val errorDuringFetch: Throwable?, val lastTimeFetched: Date) {

    var isFetching: Boolean = false
        get() = state == State.IS_FETCHING
        private set

    companion object {
        fun notFetching(lastTimeFetched: Date): FetchingFreshCacheStateMachine = FetchingFreshCacheStateMachine(State.NOT_FETCHING, null, lastTimeFetched)
    }

    fun fetching(): FetchingFreshCacheStateMachine = FetchingFreshCacheStateMachine(State.IS_FETCHING, null, lastTimeFetched)

    fun failedFetching(error: Throwable): FetchingFreshCacheStateMachine = FetchingFreshCacheStateMachine(State.NOT_FETCHING, error, lastTimeFetched)

    fun successfulFetch(timeFetched: Date): FetchingFreshCacheStateMachine = FetchingFreshCacheStateMachine(State.NOT_FETCHING, null, timeFetched)

    internal enum class State {
        NOT_FETCHING,
        IS_FETCHING
    }

    override fun toString(): String {
        return when (state) {
            State.IS_FETCHING -> "Fetching fresh cache response, last time fetched: $lastTimeFetched."
            State.NOT_FETCHING -> {
                return if (errorDuringFetch != null) "Done fetching fresh response, error occurred: $errorDuringFetch, last time fetched: $lastTimeFetched."
                else "Not fetching fresh cache response, last time fetched: $lastTimeFetched."
            }
        }
    }

}