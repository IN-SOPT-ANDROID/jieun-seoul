package org.sopt.sample.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.sample.data.dto.response.ResponseFollowerDto
import org.sopt.sample.remote.FollowerServicePool
import org.sopt.sample.util.enqueueUtil

class HomeViewModel : ViewModel() {
    private var _userList: MutableLiveData<ResponseFollowerDto> = MutableLiveData()
    val userList: LiveData<ResponseFollowerDto>
        get() = _userList

    private val getFollowerService=FollowerServicePool.FollowerService

    fun getFollower() {
        getFollowerService.getFollowers().enqueueUtil({ result ->
            _userList.value = result
            Log.d("fff","팔로워 데이터 접근 성공")
        }, { code ->
            Log.d("fff","팔로워 데이터 접근 실패")
        })
    }
}

