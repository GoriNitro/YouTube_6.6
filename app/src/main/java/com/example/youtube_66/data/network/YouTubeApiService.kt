package com.example.youtube_66.data.network


import com.example.youtube_66.data.model.PlaylistYtModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface YouTubeApiService {
    @GET("playlists")
    fun getPlaylist(
        @Query("part") part: String,
        @Query("channelId") channelId: String,
        @Query("maxResults") maxResults: String,
        @Query("pageToken") pageToken: String?
    ) : Call<PlaylistYtModel>
}