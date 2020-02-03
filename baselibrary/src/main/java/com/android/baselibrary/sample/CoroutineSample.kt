package com.android.baselibrary.sample

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

class CoroutineSample : ViewModel() {
    suspend fun fetchDocs() {                             // Dispatchers.Main
        val result = get("https://developer.android.com") // Dispatchers.IO for `get`
        show(result)                                      // Dispatchers.Main
    }

    fun show(result: Unit) {

    }

    suspend fun get(url: String) = withContext(Dispatchers.IO) { /* ... */ }

    fun onDocsNeeded() {
        viewModelScope.launch {    // Dispatchers.Main
            fetchDocs()            // Dispatchers.Main (suspend function call)
        }
    }

    suspend fun fetchTwoDocs() =
        coroutineScope {
            val deferredOne = async { fetchDocs() }
            val deferredTwo = async { fetchDocs() }
            deferredOne.await()
            deferredTwo.await()
        }

    fun launchDataLoad() {
        viewModelScope.launch {
            sortList()
            // Modify UI
        }
    }

    /**
     * Heavy operation that cannot be done in the Main Thread
     */
    suspend fun sortList() = withContext(Dispatchers.Default) {
        // Heavy work
    }

    class FragmentSample : Fragment() {
        // Get a reference to the ViewModel scoped to this Fragment
        val viewModel1 by viewModels<CoroutineSample>()

        // Get a reference to the ViewModel scoped to its Activity
        val viewModel2 by activityViewModels<CoroutineSample>()
    }

    class ActivitySample : AppCompatActivity() {
        // Get a reference to the ViewModel scoped to this Fragment
        val viewModel1 by viewModels<CoroutineSample>()

        // Get a reference to the ViewModel scoped to its Activity
    }
}