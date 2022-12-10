package uz.gita.newsapp.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.newsapp.R
import uz.gita.newsapp.databinding.ScreenNewsBinding
import uz.gita.newsapp.presenter.NewsViewModel
import uz.gita.newsapp.presenter.impl.NewsViewModelImpl
import uz.gita.newsapp.ui.adapter.AllNewsAdapter
import uz.gita.newsapp.ui.adapter.TopNewsAdapter

@AndroidEntryPoint
class NewsScreen : Fragment(R.layout.screen_news) {
    private val viewBinding: ScreenNewsBinding by viewBinding(ScreenNewsBinding::bind)
    private val viewModel: NewsViewModel by viewModels<NewsViewModelImpl>()
    private val topAdapter = TopNewsAdapter()
    private val allAdapter = AllNewsAdapter()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.rvTopNews.adapter = topAdapter
        viewBinding.rvRecentNews.adapter = allAdapter

        viewModel.latestNewsList.onEach {
            allAdapter.submitData(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.topHeadlinesList.onEach {
            topAdapter.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.topHeadlines("business")

        viewBinding.priorityChipGroup.setOnCheckedStateChangeListener { chipGroup, i ->
            changePriority(chipGroup, i)
        }

        allAdapter.setItemClickListener {
            Log.d("NNNNN", "${it.title}")
            findNavController().navigate(MainScreenDirections.actionMainScreenToArticleScreen2(it))
        }

        topAdapter.setItemClickListener {
            findNavController().navigate(MainScreenDirections.actionMainScreenToArticleScreen2(it))
        }

        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(viewBinding.rvTopNews)

        val snapHelper2:SnapHelper=LinearSnapHelper()
        snapHelper2.attachToRecyclerView(viewBinding.rvRecentNews)
    }

    private fun changePriority(chipGroup: ChipGroup, i: List<Int>) {
        val id = i[0]
        val chip = chipGroup.findViewById(id) as Chip
        when (chip.text) {
            "technology" -> viewModel.topHeadlines("technology")
            "entertainment" -> viewModel.topHeadlines("entertainment")
            "health" -> viewModel.topHeadlines("health")
            "science" -> viewModel.topHeadlines("science")
            "sport" -> viewModel.topHeadlines("sport")
            else -> viewModel.topHeadlines("business")
        }
    }
}