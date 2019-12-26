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
    //    private lateinit var viewModel: DetailViewModel
//    private lateinit var dummyShow: MovieValue
//
//    @Before
//    fun setUp() {
//        viewModel = DetailViewModel()
//        dummyShow = MovieValue(
//            "424694",
//            "Bohemian Rhapsody",
//            "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock \\'n\\' roll band Queen in 1970. Hit songs become instant classics. When Mercury\\'s increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
//            "Drama, Music",
//            "80",
//            "October 24, 2018",
//            R.drawable.poster_bohemian
//        )
//    }
//
//    @Test
//    fun getMovies() {
//        val show = viewModel.getSelectedShow("movie", "424694")
//        assertNotNull(show)
//        assertEquals(dummyShow.id, show?.id)
//        assertEquals(dummyShow.title, show?.title)
//        assertEquals(dummyShow.desc, show?.desc)
//        assertEquals(dummyShow.genres, show?.genres)
//        assertEquals(dummyShow.rating, show?.rating)
//        assertEquals(dummyShow.image, show?.image)
//    }
    private val movieRepository = mock(MovieRepository::class.java)
    private lateinit var viewModel: DetailViewModel
    private val dummyMovie = DataDummy.generateMovieDataDummy()[0]
    private val dummyTvShow = DataDummy.generateTvShowDataDummy()[0]

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = DetailViewModel(dummyMovie.id, movieRepository)
        viewModel = DetailViewModel(dummyTvShow.id, movieRepository)
    }

    @After
    fun tearDown() {

    }

    @Test
    fun getMovieDetail() {
        val resource: Resource<MovieEntity> = Resource.success(dummyMovie)
        val movieData = MutableLiveData<Resource<MovieEntity>>()
        movieData.value = Resource.success(dummyMovie)

        `when`(movieRepository.getMovieDetail(dummyMovie.id)).thenReturn(movieData)

        val observer = mock(Observer::class.java) as Observer<in Resource<MovieEntity>>
        viewModel.getMovie().observeForever(observer)

        verify(observer).onChanged(resource)
    }

    @Test
    fun getTvShowDetail() {
        val resource: Resource<TvShowEntity> = Resource.success(dummyTvShow)
        val movieData = MutableLiveData<Resource<TvShowEntity>>()
        movieData.value = Resource.success(dummyTvShow)

        `when`(movieRepository.getTvShowDetail(dummyTvShow.id)).thenReturn(movieData)

        val observer = mock(Observer::class.java) as Observer<in Resource<TvShowEntity>>
        viewModel.getTvShow().observeForever(observer)

        verify(observer).onChanged(resource)
    }
}