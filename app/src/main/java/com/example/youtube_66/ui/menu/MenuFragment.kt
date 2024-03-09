package com.example.youtube_66.ui.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.youtube_66.R
import com.example.youtube_66.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {
    private val binding by lazy{
        FragmentMenuBinding.inflate(layoutInflater)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }
}