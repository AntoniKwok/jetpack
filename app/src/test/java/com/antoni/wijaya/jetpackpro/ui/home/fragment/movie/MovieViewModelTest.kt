package com.antoni.wijaya.jetpackpro.ui.home.fragment.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.antoni.wijaya.jetpackpro.data.source.local.entity.MovieEntity
import com.antoni.wijaya.jetpackpro.data.source.remote.MovieRepository
import com.antoni.wijaya.jetpackpro.utils.DataDummy
import com.antoni.wijaya.jetpackpro.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*


class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel
    private val movieRepository = mock(MovieRepository::class.java)

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = MovieViewModel(movieRepository)
    }

    @Test
    fun getMovies() {
        val movies: ArrayList<MovieEntity> = DataDummy.generateMovieDataDummy()

        val movieData = MutableLiveData<Resource<List<MovieEntity>>>()
        movieData.value = Resource.success(movies)

        `when`(movieRepository.getMovieData()).thenReturn(movieData)

        val observer = mock(Observer::class.java) as Observer<in Resource<List<MovieEntity>>>
        viewModel.getMovies()?.observeForever(observer)

        verify(observer).onChanged(Resource.success(movies))

        assertNotNull(movies)
        assertEquals(11, movies.size)
    }

}