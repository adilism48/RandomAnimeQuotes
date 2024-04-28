package com.example.randomanimequotes.presentation.getQuoteFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.randomanimequotes.R
import com.example.randomanimequotes.data.retrofit.QuoteApiHelper
import com.example.randomanimequotes.data.retrofit.RetrofitBuilder
import com.example.randomanimequotes.data.room.QuoteDatabase
import com.example.randomanimequotes.databinding.FragmentGetQuoteBinding
import com.example.randomanimequotes.domain.Quote
import com.example.randomanimequotes.presentation.savedQuotesFragment.SavedQuotesFragment
import com.example.randomanimequotes.utils.Status

class GetQuoteFragment : Fragment() {
    private var _binding: FragmentGetQuoteBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: GetQuoteViewModel
    private lateinit var bGetNewQuote: Button
    private lateinit var bAdd: Button
    private lateinit var iBSaved: ImageButton

    private var _dataFromApi: Quote? = null
    private val dataFromApi get() = _dataFromApi!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGetQuoteBinding.inflate(inflater, container, false)

        val application = requireNotNull(this.activity).application
        val dao = QuoteDatabase.getInstance(application).quoteDao
        val newQuotesViewModelFactory =
            GetQuoteViewModelFactory(dao, QuoteApiHelper(RetrofitBuilder.quoteApiService))
        viewModel =
            ViewModelProvider(this, newQuotesViewModelFactory)[GetQuoteViewModel::class.java]

        bGetNewQuote = binding.bGetNewQuote
        bAdd = binding.bAdd
        iBSaved = binding.iBSaved

        setupObservers()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bGetNewQuote.setOnClickListener {
            setupObservers()
            showNewQuote(binding.tvQuote, binding.tvCharacter, binding.tvAnime)
            binding.tvQuote.isVisible = true
            binding.tvCharacter.isVisible = true
            binding.tvAnime.isVisible = true
            binding.tvDescription.isGone = true
            bAdd.isEnabled = true
        }

        bAdd.setOnClickListener {
            viewModel.newQuote = binding.tvQuote.text.toString()
            viewModel.newCharacter = binding.tvCharacter.text.toString()
            viewModel.newAnime = binding.tvAnime.text.toString()
            viewModel.addQuote()
            Toast.makeText(context, "Quote added", Toast.LENGTH_SHORT).show()
        }

        iBSaved.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_container, SavedQuotesFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupObservers() {
        viewModel.getQuoteFromApi().observe(viewLifecycleOwner) {
            it.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        _dataFromApi = resource.data!!
                    }

                    Status.ERROR -> {
                        Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun showNewQuote(tvQuote: TextView, tvCharacter: TextView, tvAnime: TextView) {
        tvQuote.text = dataFromApi.quote
        tvCharacter.text = dataFromApi.character
        tvAnime.text = dataFromApi.anime
    }
}