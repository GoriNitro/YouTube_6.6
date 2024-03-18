package com.example.youtube_66.data.repository

import com.example.youtube_66.data.model.PlaylistYtModel
import com.example.youtube_66.di.getService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlaylistRepository {
    private val service = getService()

    fun getPlaylist(
        onResponse: (PlaylistYtModel) -> Unit,
        onFailure: (Throwable) -> Unit,
        nextPageToken: String?
    ) {
        service.getPlaylist(
            "snippet,contentDetails",
            "UCkXmLjEr95LVtGuIm3l2dPg",
            "10",
            nextPageToken
        ).enqueue(object : Callback<PlaylistYtModel> {
            override fun onResponse(call: Call<PlaylistYtModel>, response: Response<PlaylistYtModel>) {
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data != null) {
                        onResponse.invoke(data)
                    }
                } else {
                    onFailure.invoke(Throwable("Unsuccessful response"))
                }
            }

            override fun onFailure(call: Call<PlaylistYtModel>, t: Throwable) {
                onFailure.invoke(t)
            }
        })
    }
}
