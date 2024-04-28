package com.example.randomanimequotes.presentation.savedQuotesFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.randomanimequotes.R
import com.example.randomanimequotes.data.room.QuoteDatabase
import com.example.randomanimequotes.databinding.FragmentSavedQuotesBinding

class SavedQuotesFragment : Fragment() {
    private var _binding: FragmentSavedQuotesBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: SavedQuotesViewModel
    private lateinit var savedQuotesAdapter: SavedQuotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSavedQuotesBinding.inflate(inflater, container, false)

        val application = requireNotNull(this.activity).application
        val dao = QuoteDatabase.getInstance(application).quoteDao
        val savedQuotesViewModelFactory = SavedQuotesViewModelFactory(dao)
        viewModel =
            ViewModelProvider(this, savedQuotesViewModelFactory)[SavedQuotesViewModel::class.java]

        setupRecyclerView()

        viewModel.quotesFromDb.observe(viewLifecycleOwner) {
            it?.let {
                savedQuotesAdapter.submitList(it)
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView() {
        with(binding.rcView) {
            savedQuotesAdapter = SavedQuotesAdapter()
            adapter = savedQuotesAdapter
        }
        setupSwipeListener(binding.rcView)
    }

    private fun setupSwipeListener(rvView: RecyclerView) {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = savedQuotesAdapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteQuote(item)
                Toast.makeText(context, "Quote Deleted", Toast.LENGTH_SHORT).show()
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvView)
    }
}