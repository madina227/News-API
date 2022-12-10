package uz.gita.newsapp.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.newsapp.R
import uz.gita.newsapp.databinding.ScreenBookmarksBinding
import uz.gita.newsapp.presenter.BookmarksViewModel
import uz.gita.newsapp.presenter.impl.BookmarksViewModelImpl
import uz.gita.newsapp.ui.adapter.BookmarkAdapter

@AndroidEntryPoint
class BookmarksScreen : Fragment(R.layout.screen_bookmarks) {

    private val viewBinding: ScreenBookmarksBinding by viewBinding(ScreenBookmarksBinding::bind)
    private val viewModel: BookmarksViewModel by viewModels<BookmarksViewModelImpl>()
    private val adapter: BookmarkAdapter by lazy { BookmarkAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.rvBookmark.adapter = adapter

        viewModel.bookmarksList.onEach {
            adapter.submitList(it)
            Log.d("LOGGI", "screen ${it} ")
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        adapter.setItemClickListener {
            val article = it.article
            findNavController().navigate(
                MainScreenDirections.actionMainScreenToArticleScreen2(
                    article!!
                )
            )
        }


    }
}