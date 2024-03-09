package com.example.youtube_66.ui.playlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtube_66.databinding.FragmentPlaylistBinding

class PlaylistFragment : Fragment() {
    private  val binding by lazy{
        FragmentPlaylistBinding.inflate(layoutInflater)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        with(binding){
            rvVideos.layoutManager = LinearLayoutManager(requireContext())
            rvVideos.adapter = VideoAdapter()
        }
    }
}