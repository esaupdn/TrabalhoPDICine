package br.edu.ifsuldeminas.mch.trabalhocinemapdi;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class SuggestionActivity extends AppCompatActivity {

    private WebView video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion);

        video = findViewById(R.id.VideoYoutube);
        WebSettings webSettings = video.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE); // Desabilita o cache


        String videoID = "dEbe0rS4Z2A";
        String html = "<html><body><iframe width=\"100%\" height=\"120%\" " +
                "src=\"https://www.youtube.com/embed/" + videoID +
                "\" frameborder=\"0\" allowfullscreen> </iframe></body></html>";

        video.loadData(html, "text/html", "utf-8");
    }
}