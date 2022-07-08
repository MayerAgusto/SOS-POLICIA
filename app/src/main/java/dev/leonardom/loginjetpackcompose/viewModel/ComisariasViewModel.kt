package dev.leonardom.loginjetpackcompose.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import dev.leonardom.loginjetpackcompose.Paging.ComisariasPagingSource

class ComisariasViewModel : ViewModel() {
    val pager = Pager(
        config= PagingConfig(pageSize = 2),
        pagingSourceFactory = { ComisariasPagingSource() }
    ).flow.cachedIn(viewModelScope)
}