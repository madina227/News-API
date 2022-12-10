package uz.gita.newsapp.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.gita.newsapp.ui.BookmarksScreen
import uz.gita.newsapp.ui.NewsScreen
import uz.gita.newsapp.ui.SearchScreen

class MainPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> NewsScreen()
        1 -> SearchScreen()
        else -> BookmarksScreen()
    }
}