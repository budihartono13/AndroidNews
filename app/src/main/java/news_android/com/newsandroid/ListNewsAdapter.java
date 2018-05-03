package news_android.com.newsandroid;

import android.content.Context;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Budi Hartono on 14/04/2018.
 */

public class ListNewsAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<HashMap<String,String>> listData;
    private LayoutInflater layoutInflater;

    public ListNewsAdapter(Context context, ArrayList<HashMap<String, String>> listData){
        this.context = context;
        this.listData = listData;
        layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rootView = layoutInflater.inflate(R.layout.detail_news,parent,false);

        TextView txtauthor     = (TextView) rootView.findViewById(R.id.txt_author);
        TextView txttitle      = (TextView) rootView.findViewById(R.id.txt_title);
        TextView txtdescription= (TextView) rootView.findViewById(R.id.txt_deskripsi);
        TextView txturl        = (TextView) rootView.findViewById(R.id.txt_url);
        TextView txturlToImage = (TextView) rootView.findViewById(R.id.txt_urltoimage);
        TextView txtpublishedAt= (TextView) rootView.findViewById(R.id.txt_publishedat);

        txturl.setMovementMethod(LinkMovementMethod.getInstance());
        txturlToImage.setMovementMethod(LinkMovementMethod.getInstance());

        txtauthor.setText(listData.get(position).get("author"));
        txttitle.setText(listData.get(position).get("title"));
        txtdescription.setText(listData.get(position).get("description"));
        txturl.setText(listData.get(position).get("url"));
        txturlToImage.setText(listData.get(position).get("urlToImage"));
        txtpublishedAt.setText(listData.get(position).get("publishedAt"));

        return rootView;
    }
}
