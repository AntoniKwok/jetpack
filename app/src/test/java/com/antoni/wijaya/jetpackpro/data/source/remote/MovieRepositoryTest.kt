package com.antoni.wijaya.jetpackpro.data.source.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.antoni.wijaya.jetpackpro.data.FakeMovieRepository
import com.antoni.wijaya.jetpackpro.data.source.local.LocalRepository
import com.antoni.wijaya.jetpackpro.data.source.local.entity.MovieEntity
import com.antoni.wijaya.jetpackpro.data.source.local.entity.TvShowEntity
import com.antoni.wijaya.jetpackpro.data.source.remote.repository.RemoteRepository
import com.antoni.wijaya.jetpackpro.utils.DataDummy
import com.antoni.wijaya.jetpackpro.utils.InstantAppExecutors
import com.antoni.wijaya.jetpackpro.utils.LiveDataTestUtil
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

class MovieRepositoryTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var local = mock(LocalRepository::class.java)
    private var remote = mock(RemoteRepository::class.java)
    private var instantAppExecutors = mock(InstantAppExecutors::class.java)
    private val fakeMovieRepository =
        FakeMovieRepository(remote, local, instantAppExecutors)

    private val movies = DataDummy.generateMovieDataDummy()
    private val tvshows = DataDummy.generateTvShowDataDummy()

    private val movieId = movies[0].id
    private val tvId = tvshows[0].id

    @Test
    fun allMovieData() {
        val dummy = MutableLiveData<List<MovieEntity>>()
        dummy.value = movies

        `when`(local.getAllMovie()).thenReturn(dummy)

        val result = LiveDataTestUtil.getValue(fakeMovieRepository.getMovieData())

        verify(local).getAllMovie()
        assertNotNull(result.data)
        assertEquals(movies.size, result.data?.size)
    }

    @Test
    fun getAllTvShow() {
        val dummy = MutableLiveData<List<TvShowEntity>>()
        dummy.value = tvshows

        `when`(local.getAllTvShow()).thenReturn(dummy)

        val result = LiveDataTestUtil.getValue(fakeMovieRepository.getTvShowData())

        verify(local).getAllTvShow()
        assertNotNull(result.data)
        assertEquals(tvshows.size, result.data?.size)

    }

    @Test
    fun detailMovie() {
        val dummy = MutableLiveData<MovieEntity>()
        dummy.value = movies[0]

        `when`(local.getDetailMovie(movieId)).thenReturn(dummy)
        val result = LiveDataTestUtil.getValue(fakeMovieRepository.getMovieDetail(movieId))

        verify(local).getDetailMovie(movieId)
        assertNotNull(result.data)
        assertEquals(movies[0].title, result.data?.title)
    }

    @Test
    fun detailTvShow() {
        val dummy = MutableLiveData<TvShowEntity>()
        dummy.value = tvshows[0]

        `when`(local.getDetailTvShow(tvId)).thenReturn(dummy)
        val result = LiveDataTestUtil.getValue(fakeMovieRepository.getTvShowDetail(tvId))

        verify(local).getDetailTvShow(tvId)
        assertNotNull(result.data)
        assertEquals(tvshows[0].title, result.data?.title)
    }


}