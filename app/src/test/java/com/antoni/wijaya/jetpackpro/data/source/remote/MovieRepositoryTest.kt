package com.antoni.wijaya.jetpackpro.data.source.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.antoni.wijaya.jetpackpro.data.FakeMovieRepository
import com.antoni.wijaya.jetpackpro.data.source.local.LocalRepository
import com.antoni.wijaya.jetpackpro.data.source.remote.repository.RemoteRepository
import com.antoni.wijaya.jetpackpro.utils.DataDummy
import com.antoni.wijaya.jetpackpro.utils.InstantAppExecutors
import com.antoni.wijaya.jetpackpro.utils.LiveDataTestUtil
import com.antoni.wijaya.jetpackpro.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.invocation.InvocationOnMock

class MovieRepositoryTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var local = mock(LocalRepository::class.java)
    private var remote = mock(RemoteRepository::class.java)
    private var instantAppExecutors = mock(InstantAppExecutors::class.java)
    private val fakeMovieRepository =
        FakeMovieRepository.getInstance(remote, local, instantAppExecutors)

    private val movies = DataDummy.generateMovieDataDummy()
    private val tvshows = DataDummy.generateTvShowDataDummy()

    val movieId = movies[0].id
    val tvId = tvshows[0].id

    @Test
    fun allMovieData() {
        doAnswer { invocation: InvocationOnMock ->
            (invocation.arguments[0] as RemoteRepository.LoadMovieCallback)
                .onAllMovieReceived(movies)
            null
        }.`when`(remote)
            .getMovieData()

        val result =
            Resource.success(LiveDataTestUtil.getValue(fakeMovieRepository?.getMovieData()))
        verify(remote, times(1)).getMovieData()

        assertNotNull(result)
        assertEquals(movies.size, result.data?.data?.size)
    }


}