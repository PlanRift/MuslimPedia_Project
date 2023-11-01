package com.example.muslimpedia.ui.detail

import android.graphics.Bitmap
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.muslimpedia.data.model.ArticlesItem
import com.example.muslimpedia.databinding.ActivityDetailNewsBinding
import com.squareup.picasso.Picasso

class DetailNewsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailNewsBinding

    companion object {
        const val DATA_NEWS = "data_news"
        const val DATE_NEWS = "date_news"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarDetail)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = "Detail Berita"
        }


        val dataNews = intent.getParcelableExtra<ArticlesItem>(DATA_NEWS)
        val date = intent.getStringExtra(DATE_NEWS)


        setupMyXml(dataNews, date)
        setupWebView(dataNews)
    }

    private fun setupWebView(dataNews: ArticlesItem?) {
        val webSetting = binding.wvDetail.settings
        webSetting.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW

        binding.wvDetail.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                binding.loadingView.root.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                binding.loadingView.root.visibility = View.GONE
            }
        }
        dataNews?.url?.let { binding.wvDetail.loadUrl(it) }
    }

    private fun setupMyXml(dataNews: ArticlesItem?, date: String?) {
        binding.apply {
            detailTitle.text = dataNews?.title
            detailAuthor.text = dataNews?.author
            detailPublishedAt.text = date

            Picasso.get()
                .load(dataNews?.urlToImage)
                .fit()
                .centerInside()
                .into(detailImage)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()

    }

}
