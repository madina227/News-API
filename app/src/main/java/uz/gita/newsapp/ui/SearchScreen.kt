package uz.gita.newsapp.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.gita.newsapp.R
import uz.gita.newsapp.databinding.ScreenSearchBinding
import uz.gita.newsapp.presenter.SearchViewModel
import uz.gita.newsapp.presenter.impl.SearchViewModelImpl
import uz.gita.newsapp.ui.adapter.AllNewsAdapter

@AndroidEntryPoint
class SearchScreen : Fragment(R.layout.screen_search) {
    private val viewBinding: ScreenSearchBinding by viewBinding(ScreenSearchBinding::bind)
    private val viewModel: SearchViewModel by viewModels<SearchViewModelImpl>()
    private val adapter = AllNewsAdapter()

    private val handler: Handler by lazy { Handler(Looper.getMainLooper()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.rvSearch.adapter = adapter

        viewModel.searchListFlow.onEach {
            adapter.submitData(it)
            Log.d("SSS", "$it screen")
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewBinding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                handler.postDelayed({
                    if (query != null) {
                        viewModel.search(query)
                        Log.d("SSS", "$query on query submit")
                    }
                }, 200)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewLifecycleOwner.lifecycleScope.launch {
                    delay(500)
                    if (newText != null) {
                        viewModel.search(newText)
                        Log.d("SSS", "$newText")
                    }
                }


                return true
            }
        })

        adapter.setItemClickListener {
            findNavController().navigate(MainScreenDirections.actionMainScreenToArticleScreen2(it))
        }
    }
}