package com.greativy.leo14;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.text.DateFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by leokh on 4/4/2016.
 */
public class GameListAdapter extends ArrayAdapter<GameListItem> {
    private int resource;
    private List<GameListItem> items;
    public GameListAdapter(Context context, int resource, List<GameListItem> objects) {
        super(context, resource, objects);
        this.resource = resource;
        this.items = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout itemView;
        final GameListItem item = getItem(position);

        if(convertView == null) {
            itemView = new LinearLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater li = (LayoutInflater)
                    getContext().getSystemService(inflater);
            li.inflate(resource, itemView, true);
        }
        else {
            itemView = (LinearLayout) convertView;
        }

        TextView titleView = (TextView) itemView.findViewById(R.id.tv_GameTitle);
        TextView Player1View = (TextView) itemView.findViewById(R.id.tv_Player1);
        TextView Player2View = (TextView) itemView.findViewById(R.id.tv_Player2);
        TextView Player3View = (TextView) itemView.findViewById(R.id.tv_Player3);
        TextView Player4View = (TextView) itemView.findViewById(R.id.tv_Player4);
        TextView DateView = (TextView) itemView.findViewById(R.id.tv_Date);
        titleView.setText(item.getGameTitle());
        Player1View.setText(item.getPlayer1());
        Player2View.setText(item.getPlayer2());
        Player3View.setText(item.getPlayer3());
        Player4View.setText(item.getPlayer4());
        DateFormat df;
        df = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, Locale.US);
        String datetime = df.format(item.getCreateTime());
        DateView.setText(datetime);

        return itemView;
    }

    public void set(int index, GameListItem item) {
        if(index >= 0 && index <items.size()){
            items.set(index, item);
            notifyDataSetChanged();
        }
    }

    public GameListItem get(int index) {
        return items.get(index);
    }



}
