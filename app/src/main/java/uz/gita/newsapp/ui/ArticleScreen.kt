package uz.gita.newsapp.ui

import android.os.Build
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import uz.gita.newsapp.R
import uz.gita.newsapp.data.local.room.NewsEntity
import uz.gita.newsapp.data.remote.model.Article
import uz.gita.newsapp.databinding.ScreenArticleBinding
import uz.gita.newsapp.presenter.ArticleViewModel
import uz.gita.newsapp.presenter.impl.ArticleViewModelImpl
import uz.gita.newsapp.utils.hideProgress
import uz.gita.newsapp.utils.showProgress

@AndroidEntryPoint
class ArticleScreen : Fragment(R.layout.screen_article) {
    private val viewBinding: ScreenArticleBinding by viewBinding(ScreenArticleBinding::bind)
    private var isFavourite: Boolean = false

    private val viewModel: ArticleViewModel by viewModels<ArticleViewModelImpl>()

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var isBookmarked = false
        val args: Article? = requireArguments().getParcelable("article")

        viewBinding.webView.loadUrl(args!!.url!!)
        showProgress()
        viewBinding.webView.apply {
            webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    hideProgress()
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            isBookmarked = viewModel.check(title = args.title!!)
            if (isBookmarked) {
                viewBinding.imgSave.setImageResource(R.drawable.favorite)
            } else {
                viewBinding.imgSave.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            }
        }

        viewBinding.imgBack.setOnClickListener {
            findNavController().popBackStack()
        }

        viewBinding.imgSave.setOnClickListener {
            isFavourite = !isFavourite
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.bookmarkArticle(NewsEntity(article = args))
                isBookmarked = viewModel.check(title = args.title!!)
                if (!isBookmarked) {
                    viewBinding.imgSave.setImageResource(R.drawable.favorite)
                } else {
                    viewBinding.imgSave.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                }
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewBinding.webView.destroy()
    }

}