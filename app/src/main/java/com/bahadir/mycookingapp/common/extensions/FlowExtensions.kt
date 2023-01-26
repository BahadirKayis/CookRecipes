package com.bahadir.mycookingapp.common.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.Flow

fun <T> Flow<T>.collectInResumed(viewLifecycleOwner: LifecycleOwner, response: (T) -> Unit) {
    viewLifecycleOwner.lifecycleScope.launchWhenResumed {
        collect {
            response(it)
        }
    }
}

fun <T> Flow<T>.collectInStarted(viewLifecycleOwner: LifecycleOwner, response: (T) -> Unit) {
    viewLifecycleOwner.lifecycleScope.launchWhenStarted {
        collect {
            response(it)
        }
    }
}

