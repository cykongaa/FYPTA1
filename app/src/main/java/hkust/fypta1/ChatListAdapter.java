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

public class ChatListAdapter extends BaseAdapter {
    private ArrayList<Chat> listData;
    private LayoutInflater layoutInflater;

    public ChatListAdapter(Context aContext, ArrayList<Chat> listData) {
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
            convertView = layoutInflater.inflate(R.layout.chat_row, null);
            holder = new ViewHolder();
            holder.pictureView = (ImageView) convertView.findViewById(R.id.chatIcon);

            holder.chatNameView = (TextView) convertView.findViewById(R.id.chatName);
            holder.msgView = (TextView) convertView.findViewById(R.id.msgReview);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        holder.pictureView.setImageResource(getId(listData.get(position).getChatIcon(),R.drawable.class));
        holder.msgView.setText( listData.get(position).getChatName());
        holder.chatNameView.setText(listData.get(position).getMsgList().get(0).getMessageText());

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
        TextView chatNameView;
        TextView msgView;

    }
}
