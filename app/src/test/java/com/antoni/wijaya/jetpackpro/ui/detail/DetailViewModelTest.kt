package com.antoni.wijaya.jetpackpro.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.antoni.wijaya.jetpackpro.data.source.local.entity.MovieEntity
import com.antoni.wijaya.jetpackpro.data.source.local.entity.TvShowEntity
import com.antoni.wijaya.jetpackpro.data.source.remote.MovieRepository
import com.antoni.wijaya.jetpackpro.utils.DataDummy
import com.antoni.wijaya.jetpackpro.vo.Resource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

class DetailViewModelTest {
    private val movieRepository = mock(MovieRepository::class.java)
    private lateinit var movieViewModel: DetailViewModel
    private lateinit var tvViewModel: DetailViewModel
    private val dummyMovie = DataDummy.generateMovieDataDummy()[0]
    private val dummyTvShow = DataDummy.generateTvShowDataDummy()[0]

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        movieViewModel = DetailViewModel(dummyMovie.id, movieRepository)
        tvViewModel = DetailViewModel(dummyTvShow.id, movieRepository)
    }

    @After
    fun tearDown() {

    }

    @Test
    fun getMovieDetail() {
        val resource: Resource<MovieEntity> = Resource.success(dummyMovie)
        val movieData = MutableLiveData<Resource<MovieEntity>>().also {
            it.value = resource
        }

        `when`(movieRepository.getMovieDetail(dummyMovie.id)).thenReturn(movieData)

        val observer = mock(Observer::class.java) as Observer<Resource<MovieEntity>>
        movieViewModel.setUsername("Antoni")
        movieViewModel.getMovie().observeForever(observer)

        verify(observer).onChanged(resource)
    }

    @Test
    fun getTvShowDetail() {
        val resource: Resource<TvShowEntity> = Resource.success(dummyTvShow)
        val movieData = MutableLiveData<Resource<TvShowEntity>>()
        movieData.value = Resource.success(dummyTvShow)

        `when`(movieRepository.getTvShowDetail(dummyTvShow.id)).thenReturn(movieData)

        val observer = mock(Observer::class.java) as Observer<Resource<TvShowEntity>>
        tvViewModel.setUsername("Wijaya")
        tvViewModel.getTvShow().observeForever(observer)

        verify(observer).onChanged(resource)
    }
}