package com.example.cinemaapp

import android.os.Bundle
import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_movie_list.*
import com.example.cinemaapp.utils.CustomAdapterMovies
import com.example.cinemaapp.utils.api.ApiClient
import com.example.cinemaapp.utils.data_model.Movie
import kotlinx.coroutines.runBlocking

const val ID = "ID"
const val EDIT_CODE = 27

class MovieList : AppCompatActivity() {
    lateinit var adapter: CustomAdapterMovies

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        val corroutine by lazy {
            runBlocking{
                init()
            }
        }
    }

    suspend fun init(): MutableList<Movie> {
        val value = ApiClient.movies
        runOnUiThread {
            adapter = CustomAdapterMovies(context = this@MovieList, resourceId = R.layout.row_element_movie, items = value)
            movie_list.adapter = this@MovieList.adapter
            movie_list.setOnItemClickListener { _, _, _, _ ->
                Toast.makeText(this@MovieList, "Bye", Toast.LENGTH_LONG).show()
            }
            movie_list.setOnItemLongClickListener { parent, view, position, id ->
                Toast.makeText(this@MovieList, "Hi", Toast.LENGTH_LONG).show()
                return@setOnItemLongClickListener true
            }
        }
        return value
    }

    fun updateUI() {
        val adapter = movie_list.adapter as CustomAdapterMovies
        adapter.notifyDataSetChanged()
        updateUI()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == EDIT_CODE)
            adapter.notifyDataSetChanged()
    }
}