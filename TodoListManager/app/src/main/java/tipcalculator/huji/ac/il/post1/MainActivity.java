package tipcalculator.huji.ac.il.post1;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ChatActivity";
    private ChatArrayAdapter adp;
    private ListView list;
    private EditText chatText;
    private Button send;
    Intent intent;
    private boolean color = false;
    List<String> subject_list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        setContentView(R.layout.activity_main);
        send = (Button) findViewById(R.id.btn);
        list = (ListView) findViewById(R.id.listview);
        chatText = (EditText) findViewById(R.id.chat_text);
        adp = new ChatArrayAdapter(getApplicationContext(), R.layout.chat);
        list.setAdapter(adp);
        registerForContextMenu(list);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                String item = ((TextView)view).getText().toString();
                Toast.makeText(getApplicationContext(), "Clicked: "+item, Toast.LENGTH_LONG).show();
            }
        });



        // ---------------------------------------------------------------------------------
        // appeare the keyboard
        chatText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    return sendChatMessage();
                }
                return false;
            }
        });
        // ---------------------------------------------------------------------------------
        // add Message
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                sendChatMessage();
            }
        });

        // ---------------------------------------------------------------------------------
        // Scrolling
        list.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        list.setAdapter(adp);

        adp.registerDataSetObserver(new DataSetObserver() {
            public void OnChanged(){
                super.onChanged();
                list.setSelection(adp.getCount() -1);
            }
        });
        // ---------------------------------------------------------------------------------
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        menu.add(0, v.getId(), 0, "Delete");
    }

    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if(item.getTitle()=="Delete")adp.myRemove(info.position);
        return true;
    }

    // ---------------------------------------------------------------------------------
    private boolean sendChatMessage(){
        adp.add(new ChatMessage(color, chatText.getText().toString()));
        chatText.setText("");
        color = !color;
        return true;
    }
}