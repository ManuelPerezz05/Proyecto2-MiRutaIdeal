package com.example.mirutaideal

import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity

class PrivacyPolicyActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_privacy_policy)

        val webView: WebView = findViewById(R.id.privacyWebView)
        webView.settings.javaScriptEnabled = true
        webView.loadUrl("file:///android_asset/politica-de-privacidad.html")

    }
}