package net.simplifiedcoding.understandingcoroutines.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import net.simplifiedcoding.understandingcoroutines.data.models.Movie
import net.simplifiedcoding.understandingcoroutines.data.models.Quote
import net.simplifiedcoding.understandingcoroutines.data.network.MyApi

class QuoteViewModel : ViewModel() {

    private val TAG = "QuoteViewModel"

    val quotes: LiveData<List<Quote>?> = MutableLiveData()

    init {
        viewModelScope.launch {
            quotes as MutableLiveData
            for (i in 1..5) {
                val quotes1: Deferred<List<Quote>?> = async { getQuotes() }
                val quotes2: Deferred<List<Quote>?> = async { getQuotes() }
                val quotes3: Deferred<List<Quote>?> = async { getQuotes() }

                val movies1: Deferred<List<Movie>?> = async { getMovies() }
                val movies2: Deferred<List<Movie>?> = async { getMovies() }
                val movies3: Deferred<List<Movie>?> = async { getMovies() }
            }
        }
    }

    private suspend fun getQuotes(): List<Quote>? {
        return withContext(Dispatchers.IO) {
            android.util.Log.i(TAG, "Getting Quotes")
            MyApi().getQuotes().body()?.quotes
        }
    }

    private suspend fun getMovies(): List<Movie>? {
        return withContext(Dispatchers.IO) {
            android.util.Log.e(TAG, "Getting Movies")
            MyApi().getMovies().body()
        }
    }
}