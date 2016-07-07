package viettd.com.vnexpress;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import viettd.com.vnexpress.adapter.TypeNewsAdapter;

public class TypeNewsActivity extends AppCompatActivity {

    private static String[] typeNews;
    private static String[] typeNewsLink;
    private GridView gvTypeNews;
    private ArrayAdapter<String> adapter;

    public static int[] newsAvatar = new int[]{R.drawable.home, R.drawable.thoisu, R.drawable.thegioi, R.drawable.kinhdoanh,
            R.drawable.giaitri, R.drawable.sports, R.drawable.phapluat, R.drawable.giaoduc, R.drawable.suckhoe,
            R.drawable.giadinh, R.drawable.dulich, R.drawable.khoahoc, R.drawable.sohoa, R.drawable.xe, R.drawable.congdong,
            R.drawable.tamsu, R.drawable.cuoi};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_news);

        gvTypeNews = (GridView) findViewById(R.id.gvTypeNews);

        typeNews = getResources().getStringArray(R.array.type_news);
        typeNewsLink = getResources().getStringArray(R.array.type_news_link);

        adapter = new TypeNewsAdapter(this, R.layout.item_type_news, typeNews);
        gvTypeNews.setAdapter(adapter);
        gvTypeNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TypeNewsActivity.this, NewsCategoryActivity.class);
                intent.putExtra("link", typeNewsLink[position]);
                startActivity(intent);
            }
        });

    }
}
