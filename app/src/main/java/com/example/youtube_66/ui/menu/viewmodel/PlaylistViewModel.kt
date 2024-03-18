package com.example.youtube_66.ui.menu.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.youtube_66.data.model.PlaylistYtModel
import com.example.youtube_66.data.repository.PlaylistRepository

class PlaylistViewModel : ViewModel() {
    private val repository = PlaylistRepository()
    private val _playlist = MutableLiveData<PlaylistYtModel?>()
    val playlist = _playlist
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading = _isLoading
    private val _isAllPlaylistLoaded = MutableLiveData<Boolean>()
    val isAllPlaylistLoaded = _isAllPlaylistLoaded
    var nextPageToken: String? = null

    init {
        getPlaylist()
    }

    fun getPlaylist() {
        _isLoading.value = true
        repository.getPlaylist(
            onResponse = { data ->
                _isLoading.value = false
                if (data.nextPageToken != null) {
                    nextPageToken = data.nextPageToken
                } else {
                    nextPageToken = null
                    _isAllPlaylistLoaded.value = true
                }
                if (data.items.isNotEmpty()) {
                    _playlist.value = data
                }
            },
            onFailure = { t ->
                _isLoading.value = false
                Log.e(TAG, "Failure: ", t)
            },
            nextPageToken = nextPageToken
        )
    }

    companion object {
        private val TAG = PlaylistViewModel::class.java.simpleName
    }
}