package com.example.book.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.book.MainViewModel
import com.example.book.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        val adapter = BookAdapter(requireContext())
        binding.searchRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            this.adapter = adapter
        }
        viewModel.search("系统")
        viewModel.books.observe(
            viewLifecycleOwner
        ) { t -> adapter.setBooks(t) }
        binding.searchBtn.setOnClickListener {
            val name = binding.searchEdit.text.toString()
            if (name.isNotEmpty()) {
                viewModel.search(name)
            }
        }
        return binding.root
    }
}