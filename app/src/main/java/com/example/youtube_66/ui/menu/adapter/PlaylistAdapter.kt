package com.example.youtube_66.ui.menu.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.youtube_66.data.model.PlaylistYtModel
import com.example.youtube_66.databinding.ItemPlaylistBinding

class PlaylistAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val oldItems = ArrayList<PlaylistYtModel.PlaylistItem>()

    class PlaylistHolder(itemView: ItemPlaylistBinding) :
        RecyclerView.ViewHolder(itemView.root){
        private val binding = itemView

        fun setData(data: PlaylistYtModel.PlaylistItem){
            binding.tvPlaylistName.text = data.snippetYt.title
            val videoCount = "${data.contentDetail.itemCount} videos"
            binding.tvCount.text = videoCount
            binding.imgPlaylist.load(data.snippetYt.thumbnails.high.url)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = ItemPlaylistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlaylistHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PlaylistHolder).setData(oldItems[position])
    }

    override fun getItemCount(): Int {
        return oldItems.size
    }

    fun setData(newList: List<PlaylistYtModel.PlaylistItem>, rv: RecyclerView){
        val playlistDiff = PlaylistDiffUtil(oldItems, newList)
        val diff = DiffUtil.calculateDiff(playlistDiff)
        oldItems.addAll(newList)
        diff.dispatchUpdatesTo(this)
        rv.scrollToPosition(oldItems.size - newList.size)
    }
}