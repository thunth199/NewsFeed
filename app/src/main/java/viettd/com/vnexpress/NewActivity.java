package viettd.com.vnexpress;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
public class NewActivity extends Activity {

    WebView webview;
    String link, title, description, date;
    String detail = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.new_layout);

        webview = (WebView) findViewById(R.id.desc);
        Bundle bundle = getIntent().getExtras();
        link = bundle.getString("link");
        Log.e("Tryto",link);
        WebSettings webSettings = webview.getSettings();
        webSettings.setSupportZoom(true);

//        webview.loadUrl(link);

        GetData getData = new GetData();
        getData.execute(link);

    }

    public class GetData extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {

            try {
                Document doc = Jsoup.connect(link)
                        .get();

                Elements title = doc.select("div.title_news h1");
                Log.e("Tryto",title.toString());

                Elements date = doc.select("div.block_timer");
                Log.e("Tryto",date.toString());

                Elements description = doc.select("div.short_intro");
                Log.e("Tryto",description.toString());

                doc.select("table").remove();

                Elements main = doc.select("div.fck_detail");
                Log.e("Tryto",main.toString());

                detail += "<h2 style = \" color: red \">" + title.text()
                        + "</h2>";
                detail += "<font size=\" 1.2em \" style = \" color: #005500 \"><em>"
                        + date.text() + "</em></font>";
                detail += "<p style = \" color: #999999 \"><b>" + "<font size=\" 4em \" >"
                        + description.text() + "</font></b></p>";
                detail += "<font size=\" 4em \" >"+  main.toString() + "</font>";

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            webview.loadDataWithBaseURL(
                    "",
                    "<style>img{display: inline;height: auto;max-width: 100%;}"
                            + " p {font-family:\"Tangerine\", \"Sans-serif\",  \"Serif\" font-size: 48px} </style>"
                            + detail, "text/html", "UTF-8", "");

        }

    }
}
