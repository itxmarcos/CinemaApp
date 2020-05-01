package com.example.cinemaapp.utils.api

import com.example.cinemaapp.utils.data_model.Movie
import com.google.gson.Gson
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

const val REMOTE = "https://movies-api-v2.000webhostapp.com"

object ApiClient {

    val movies : MutableList<Movie> by lazy {
        loadMovies()
    }

    fun loadMovies() : MutableList<Movie> {

        var urlMovie = URL("$REMOTE/mobile/user/getMovies.php?user=test2&pass=1234")
        var urlConnectionMovie = urlMovie.openConnection() as HttpURLConnection
        val inputStreamMovie = BufferedInputStream(urlConnectionMovie.inputStream)

        val gson = Gson()
        val jsonStringMovies = getAPI.readStream(inputStreamMovie)
        val array = gson.fromJson(jsonStringMovies, Array<Movie>::class.java)
        urlConnectionMovie.disconnect()
        val mutableList = mutableListOf<Movie>()
        mutableList.addAll(array)
        return mutableList
    }

    fun readStream(inputStream : InputStream) : String {
        val br = BufferedReader(InputStreamReader(inputStream))
        val total = StringBuilder()
        while (true) {
            val line = br.readLine() ?: break
            total.append(line).append('\n')
        }
        return total.toString()
    }
}