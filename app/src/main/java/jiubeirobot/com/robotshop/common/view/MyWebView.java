package jiubeirobot.com.robotshop.common.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * ==========================
 * 创建人：TheJoker丶豪 on
 * 创建时间：2017/8/25.
 * 创建内容：自定义的webview
 * ==========================
 */

public class MyWebView extends WebView {
    public MyWebView(Context context) {
        this(context, null);
    }

    public MyWebView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // 默认缓存模式
        WebSettings settings = getSettings();

        settings.setCacheMode(WebSettings.LOAD_DEFAULT);

        settings.setJavaScriptEnabled(true); // 设置支持javaScript
        settings.setSaveFormData(true);
        settings.setSavePassword(false); // 不保存密码

        settings.setLoadsImagesAutomatically(true);
        settings.setSupportMultipleWindows(true);
        settings.setLightTouchEnabled(true);

        settings.setBuiltInZoomControls(false); // 不支持页面放大

        setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY); // 滚动条
        settings.setDefaultTextEncodingName("UTF-8"); // 非常关键，否则设置了WebChromeClient后会出现乱码

        settings.setUseWideViewPort(false);
        settings.setLoadWithOverviewMode(false);

        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        settings.setJavaScriptCanOpenWindowsAutomatically(true);
    }

    public String getHtmlData(String bodyHTML) {

        String head = "<head>" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> " +
                "<style>img{max-width: 100%; width:auto; height:auto;}</style>" +
                "</head>";
        return "<html>" + head + "<body>" + bodyHTML + "</body></html>";

    }


    public void setHtml(final String htmlText) {

        WebSettings settings = getSettings();
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        loadDataWithBaseURL(null, getHtmlData(htmlText), "text/html", "utf-8", null);
        setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        setWebChromeClient(new WebChromeClient());
    }

}
