package viettd.com.vnexpress.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import viettd.com.vnexpress.R;
import viettd.com.vnexpress.TypeNewsActivity;

public class TypeNewsAdapter extends ArrayAdapter<String> {

    private Context context;
    private int resId;
    private String[] strings;

    public TypeNewsAdapter(Context context, int resource, String[] objects) {
        super(context, resource, objects);
        this.context = context;
        this.resId = resource;
        this.strings = objects;
    }

    public class ViewHolder {
        TextView tvTypeNews;
        ImageView imvTypeAvatar;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null){
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(resId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvTypeNews = (TextView) convertView.findViewById(R.id.tvTypeNews);
            viewHolder.imvTypeAvatar = (ImageView) convertView.findViewById(R.id.imvTypeAvatar);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String newsType = strings[position];

        viewHolder.tvTypeNews.setText(newsType);
        Picasso.with(context).load(TypeNewsActivity.newsAvatar[position]).into(viewHolder.imvTypeAvatar);

        return convertView;
    }
}
