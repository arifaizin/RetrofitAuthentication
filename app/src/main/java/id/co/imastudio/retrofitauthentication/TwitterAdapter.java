package id.co.imastudio.retrofitauthentication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import id.co.imastudio.retrofitauthentication.model.TwitterResponse;

/**
 * Created by idn on 1/5/2018.
 */

public class TwitterAdapter extends BaseAdapter {
//extends ke BaseAdapter

    public List<TwitterResponse> data=new ArrayList<TwitterResponse>();
    private Context context;

    //klik kanan > generate > constructor
    public TwitterAdapter(List<TwitterResponse> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View itemView= LayoutInflater.from(context).inflate(R.layout.twitter_list_item, viewGroup, false);

        TextView tweetText=(TextView) itemView.findViewById(R.id.tvTweet);
        TextView timeText=(TextView) itemView.findViewById(R.id.tvTime);
        TextView userName=(TextView) itemView.findViewById(R.id.tvUsername);

        tweetText.setText(data.get(i).getText());
        timeText.setText(data.get(i).getCreatedAt());
        userName.setText(data.get(i).getUser().getName());

        return itemView;
    }
}
