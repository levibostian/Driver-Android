package com.levibostian.teller.testing.extensions

import com.levibostian.teller.cachestate.OnlineCacheState
import com.levibostian.teller.repository.OnlineRepository
import com.levibostian.teller.testing.cachestate.OnlineCacheStateTesting
import java.util.*

fun <CACHE: Any> OnlineCacheState.Testing.none(): OnlineCacheState<CACHE> = OnlineCacheStateTesting.none()

fun <CACHE: Any> OnlineCacheState.Testing.noCache(requirements: OnlineRepository.GetCacheRequirements,
                                                  more: (OnlineCacheStateTesting.NoCacheExists.() -> Unit)? = null): OnlineCacheState<CACHE> = OnlineCacheStateTesting.noCache(requirements, more)

fun <CACHE: Any> OnlineCacheState.Testing.cache(requirements: OnlineRepository.GetCacheRequirements,
                                                lastTimeFetched: Date,
                                                more: (OnlineCacheStateTesting.CacheExists<CACHE>.() -> Unit)? = null): OnlineCacheState<CACHE> = OnlineCacheStateTesting.cache(requirements, lastTimeFetched, more)