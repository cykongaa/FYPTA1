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
 * Created by kongchingyiii on 5/10/16.
 */

public class SharingListAdapter extends BaseAdapter {
    private ArrayList<Sharing> listData;
    private LayoutInflater layoutInflater;

    public SharingListAdapter(Context aContext, ArrayList<Sharing> listData) {
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
            convertView = layoutInflater.inflate(R.layout.sharing_row, null);
            holder = new ViewHolder();
            holder.pictureView = (ImageView) convertView.findViewById(R.id.picture);

            holder.contentView = (TextView) convertView.findViewById(R.id.content);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        holder.pictureView.setImageResource(getId(listData.get(position).getPhoto(),R.drawable.class));
        holder.contentView.setText( listData.get(position).getContent());

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
        ImageView pictureView;
        TextView contentView;

    }
}
