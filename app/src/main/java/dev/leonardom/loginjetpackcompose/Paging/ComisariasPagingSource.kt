package dev.leonardom.loginjetpackcompose.Paging

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import dev.leonardom.loginjetpackcompose.Retrofit.Data.Data
import dev.leonardom.loginjetpackcompose.Retrofit.Data.ResponseAPI
import dev.leonardom.loginjetpackcompose.Retrofit.RetrofitInstance
import dev.leonardom.loginjetpackcompose.Retrofit.RetrofitServiceInterface
import dev.leonardom.loginjetpackcompose.viewModel.MainViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ComisariasPagingSource: PagingSource<Int, Data>() {

    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        return try {
            val prev = params.key?:0
            val retroInstance = RetrofitInstance.getRetrofitInstance()
            val retroService = retroInstance.create(RetrofitServiceInterface::class.java)
            val response = retroService.getComisariasList()
            if(response.isSuccessful){
                val body= response.body()?.data
                LoadResult.Page(
                    data = body!!,
                    prevKey = if(prev==0) null else prev-1,
                    nextKey = if(body.size<params.loadSize) null else prev+1
                )
            }
            else{
                LoadResult.Error(java.lang.Exception())
            }
        }
        catch (e:Exception){
            LoadResult.Error(e)
        }
    }

}

