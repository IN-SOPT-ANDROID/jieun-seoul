package org.sopt.sample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.sample.data.dto.response.ResponseFollowerDTO
import org.sopt.sample.remote.FollowerServicePool
import org.sopt.sample.util.enqueueUtil
import timber.log.Timber

class HomeViewModel : ViewModel() {
    private val _homeResult = MutableLiveData<ResponseFollowerDTO?>()
    val homeResult: LiveData<ResponseFollowerDTO?>
        get() = _homeResult
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage
    private val _successGet = MutableLiveData<Boolean>()
    val successGet: LiveData<Boolean>
        get() = _successGet
    private val followerService = FollowerServicePool.FollowerService

    fun getData() {
        followerService.getData()
            .enqueueUtil({ result ->
                _homeResult.value = result
                Timber.i("데이터 get 성공: $result")
            }, { code ->
                Timber.d("데이터 get 실패: $code")
            })
    }
}

