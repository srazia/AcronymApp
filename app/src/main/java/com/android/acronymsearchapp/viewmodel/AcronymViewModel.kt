package com.android.acronymsearchapp.viewmodel

import android.content.Context
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.acronymsearchapp.domain.network.NetworkStatus
import com.android.acronymsearchapp.domain.network.responses.AcronymSearchResponse
import com.android.acronymsearchapp.domain.repository.AcronymRepository
import com.android.acronymsearchapp.util.NetworkHelper
import com.android.acronymsearchapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

@HiltViewModel
class AcronymViewModel @Inject constructor(
    private val acronymRepository: AcronymRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _acronymListInfo = MutableLiveData<Resource<AcronymSearchResponse>>()
    val acronymListInfo: LiveData<Resource<AcronymSearchResponse>>
        get() = _acronymListInfo

    fun loadAcronymSearchData(context: Context, searchItem: String){
        viewModelScope.launch {
            getAcronymSearchData(context, searchItem)
        }
    }

    private suspend fun getAcronymSearchData(context: Context, searchItem: String) {

        viewModelScope.launch {
            if (networkHelper.isNetworkConnected()) {
                withContext(Dispatchers.Main) {
                  //  context.showDialog()
                }

                acronymRepository.getAcronymBySearch(searchItem).let { response ->

                    when (response) {
                        is NetworkStatus.Success -> {
                            _acronymListInfo.postValue(Resource.success(response.data))
                        }
                        is NetworkStatus.Error -> {
                            if (response.errorMessage.isNullOrEmpty().not()) {
                                _acronymListInfo.postValue(Resource.error(response.errorMessage?:"", null))
                            } else {
                                _acronymListInfo.postValue(Resource.error("Unknown Error", null))
                            }
                        }

                        else -> {}
                    }
                    withContext(Dispatchers.Main) {
                       // context.hideDialog()
                    }
                }
            } else {
              //  context.showInternetDialog()
            }
        }
    }
}