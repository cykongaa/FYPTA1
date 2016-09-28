package hkust.fypta1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by kongchingyiii on 7/9/16.
 */
public class CustomListAdapter extends BaseAdapter {
    private ArrayList<Follower> listData;
    private LayoutInflater layoutInflater;

    public CustomListAdapter(Context aContext, ArrayList<Follower> listData) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
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

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.follower_row, null);
            holder = new ViewHolder();
            holder.iconView = (ImageView) convertView.findViewById(R.id.icon);

            holder.userNameView = (TextView) convertView.findViewById(R.id.followName);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        holder.iconView.setImageResource(getId(listData.get(position).getUserIcon(),R.drawable.class));
        holder.userNameView.setText( listData.get(position).getFollowerName());

        return convertView;
    }

    public static int getId(String resourceName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(resourceName);
            return idField.getInt(idField);
        } catch (Exception e) {
            throw new RuntimeException("No resource ID found for: "
                    + resourceName + " / " + c, e);
        }
    }

    static class ViewHolder {
        ImageView iconView;
        TextView userNameView;

    }
}