package com.antoni.wijaya.jetpackpro.ui.home.fragment.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.antoni.wijaya.jetpackpro.data.source.local.entity.TvShowEntity
import com.antoni.wijaya.jetpackpro.data.source.remote.MovieRepository
import com.antoni.wijaya.jetpackpro.utils.DataDummy
import com.antoni.wijaya.jetpackpro.vo.Resource
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

class TvShowViewModelTest{

    private lateinit var viewModel : TvShowViewModel
    private val movieRepository = mock(MovieRepository::class.java)

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp(){
        viewModel = TvShowViewModel(movieRepository)
    }

    @Test
    fun getMovies(){
        val movies : ArrayList<TvShowEntity> = DataDummy.generateTvShowDataDummy()

        val movieData = MutableLiveData<Resource<List<TvShowEntity>>>()
        movieData.value = Resource.success(movies)

        `when`(movieRepository.getTvShowData()).thenReturn(movieData)

        val observer = mock(Observer::class.java)as Observer<in Resource<List<TvShowEntity>>>
        viewModel.getTvShows()?.observeForever(observer)

        verify(observer).onChanged(Resource.success(movies))

        assertNotNull(movies)
        assertEquals(11, movies.size)
    }
}