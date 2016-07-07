package viettd.com.vnexpress;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import viettd.com.vnexpress.adapter.AppAdapter;
import viettd.com.vnexpress.rss.RSSItem;
import viettd.com.vnexpress.rss.RssParser;

public class NewsCategoryActivity extends FragmentActivity {

    List<RSSItem> items;
    RssParser rssParser = new RssParser();
    ListView listView;
    private String rootUrl = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getIntent() != null){
            rootUrl = getIntent().getStringExtra("link");
        }

        items = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listview);


        new GetListItem(this).execute();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(NewsCategoryActivity.this, NewActivity.class);
                intent.putExtra("link", items.get(i).get_link());
                Log.e("Tryto", items.get(i).get_link());
                startActivity(intent);
            }
        });


    }

    public class GetListItem extends AsyncTask<Void, Void, Void> {

        Context context;
        ProgressDialog pd;

        GetListItem(Context context) {
            this.context = context;
        }

        protected void onPreExecute() {

        }

        @Override
        protected Void doInBackground(Void... params) {
            items = rssParser.getRSSFeedItems(rootUrl);
            Log.d("rss", items.size() + "");
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            AppAdapter adapter = new AppAdapter(context, items);
            listView.setAdapter(adapter);
        }

    }
}