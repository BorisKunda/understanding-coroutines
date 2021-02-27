package net.simplifiedcoding.understandingcoroutines.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
                android.util.Log.i(TAG, "Start $i")
                //async
                // val quotes1: Deferred<List<Quote>?> = async { getQuotes("1") }
                // val quotes2: Deferred<List<Quote>?> = async { getQuotes("2") }
                // val quotes3: Deferred<List<Quote>?> = async { getQuotes("3") }

                // val movies1: Deferred<List<Movie>?> = async { getMovies("1") }
                // val movies2: Deferred<List<Movie>?> = async { getMovies("2") }
                // val movies3: Deferred<List<Movie>?> = async { getMovies("3") }
                //--regular--
                val quotes1: List<Quote>? = getQuotes("1")
                val quotes2: List<Quote>? = getQuotes("2")
                val quotes3: List<Quote>? = getQuotes("3")

                val movies1: List<Movie>? = getMovies("1")
                val movies2: List<Movie>? = getMovies("2")
                val movies3: List<Movie>? = getMovies("3")

                android.util.Log.i(TAG, "Finish $i")
            }
        }
    }

    private suspend fun getQuotes(index: String): List<Quote>? {
        return withContext(Dispatchers.IO) {
            android.util.Log.i(TAG, "Getting Quotes---$index")
            MyApi().getQuotes().body()?.quotes
        }
    }

    private suspend fun getMovies(index: String): List<Movie>? {
        return withContext(Dispatchers.IO) {
            android.util.Log.i(TAG, "Getting Movies---$index")
            MyApi().getMovies().body()
        }
    }
}