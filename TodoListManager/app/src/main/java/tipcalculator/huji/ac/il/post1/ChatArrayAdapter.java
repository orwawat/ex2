package tipcalculator.huji.ac.il.post1;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.data;

/**
 * Created by orwa on 3/13/2017.
 */

public class ChatArrayAdapter extends ArrayAdapter<ChatMessage> {

    private TextView chatText;
    private List<ChatMessage> MessageList = new ArrayList<ChatMessage>();
    private LinearLayout layout;

    public ChatArrayAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public void add(ChatMessage object) {
        // TODO Auto-generated method stub

        MessageList.add(object);
        super.add(object);
    }

    public void myRemove(int position){
        MessageList.remove(position);
        super.notifyDataSetChanged();
    }


    public int getCount() {
        return this.MessageList.size();
    }

    public ChatMessage getItem(int index) {
        return this.MessageList.get(index);
    }

    public View getView(int position, View ConvertView, ViewGroup parent) {

        View v = ConvertView;
        if (v == null) {

            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.chat, parent, false);

        }

        layout = (LinearLayout) v.findViewById(R.id.Message1);
        ChatMessage messageobj = getItem(position);
        chatText = (TextView) v.findViewById(R.id.SingleMessage);
        chatText.setText(messageobj.message);

        layout.setBackgroundColor(messageobj.color ? 0xff0000ff : 0xffff0000);

        return v;
    }

}