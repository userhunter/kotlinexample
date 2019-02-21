package com.example.webview.test;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.JsPromptResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.IOException;

import example.pacewear.com.appname.R;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * autour: bobbylu
 * date: 2019/2/14 on 10:37
 */
public class WebViewTestActivity extends Activity {

    private static final String TAG = "WebViewTestActivity";

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_test);

//        initWebView();
        initOkHttp();
    }

    private void initOkHttp(){

        OkHttpClient okHttpClient = new OkHttpClient();

         Request request = new Request.Builder()
                    .url("http://publicobject.com/helloworld.txt")
                    .build();

            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                    Headers responseHeaders = response.headers();
                    for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                        Log.d(TAG,responseHeaders.name(i) + ": " + responseHeaders.value(i));
                    }

                    Log.d(TAG,response.body().string());
                }
            });
    }

    private void initWebView(){
        mWebView = (WebView)findViewById(R.id.webview);

        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return super.shouldOverrideUrlLoading(view, url);
            }
        });

        mWebView.setWebChromeClient(new WebChromeClient(){

            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
                return super.onJsPrompt(view, url, message, defaultValue, result);
            }

            @Override
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                return super.onConsoleMessage(consoleMessage);
            }

        });

        mWebView.loadUrl("http://hao123.com");
    }


}
