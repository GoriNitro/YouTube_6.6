package com.example.youtube_66.ui.menu.viewmodel

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.youtube_66.databinding.FragmentMenuBinding
import com.example.youtube_66.ui.menu.adapter.PlaylistAdapter

class MenuFragment : Fragment() {
    private val adapter = PlaylistAdapter()
    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: PlaylistViewModel by viewModels()
    private var isLoading = false
    private var isScroll = false
    private var currentItem = -1
    private var totalItem = -1
    private var scrollOutItem = -1
    private var isAllVideoLoaded = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val manager = LinearLayoutManager(requireContext())
        binding.rvPlaylists.adapter = adapter
        binding.rvPlaylists.layoutManager = manager

        binding.rvPlaylists.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    isScroll = true
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                currentItem = manager.childCount
                totalItem = manager.itemCount
                scrollOutItem = manager.findFirstVisibleItemPosition()
                if (isScroll && (currentItem + scrollOutItem == totalItem)){
                    isScroll = false
                    if (!isLoading){
                        if (!isAllVideoLoaded){
                            sharedViewModel.getPlaylist()
                        } else {
                            Toast.makeText(requireContext(), "All playlist loaded", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }

        })

        sharedViewModel.playlist.observe(viewLifecycleOwner) {
            adapter.setData(it?.items!!, binding.rvPlaylists)
            it.nextPageToken?.let { token ->
                Log.e("next page token", token)
            }
        }

        sharedViewModel.isAllPlaylistLoaded.observe(viewLifecycleOwner) {
            isAllVideoLoaded = it
            if (it) Toast.makeText(
                requireContext(),
                "All playlist has been loaded",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}