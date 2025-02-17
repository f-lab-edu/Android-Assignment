package com.yongjincompany.android_assignment.core.util

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

fun Fragment.repeatOnLifecycleState(
    state: Lifecycle.State = Lifecycle.State.STARTED,
    block: suspend CoroutineScope.() -> Unit
) =
    viewLifecycleOwner.launchRepeatOnLifecycleState(state, block)

fun AppCompatActivity.repeatOnLifecycleState(
    state: Lifecycle.State = Lifecycle.State.STARTED,
    block: suspend CoroutineScope.() -> Unit
): Job =
    launchRepeatOnLifecycleState(state, block)

private fun LifecycleOwner.launchRepeatOnLifecycleState(
    state: Lifecycle.State = Lifecycle.State.STARTED,
    block: suspend CoroutineScope.() -> Unit
) =
    lifecycleScope.launch {
        lifecycle.repeatOnLifecycle(state, block)
    }