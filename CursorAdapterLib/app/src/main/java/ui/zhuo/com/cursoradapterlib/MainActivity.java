package ui.zhuo.com.cursoradapterlib;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView riverListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.update_test);
        button.setText("One");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent intent=new Intent(MainActivity.this,TwoActivity.class);
                startActivity(intent);
            }
        });
        setRiverListViewAdapter();
    }

    ListCursorAdapter listCursorAdapter;

    private void setRiverListViewAdapter() {
        riverListView = (ListView) this.findViewById(R.id.riverList);
        Cursor cursor =  getContentResolver().query(TestContentProvider.CONTENT_URI, null,
                null, null, null);
        listCursorAdapter = new ListCursorAdapter(this, cursor);
        riverListView.setAdapter(listCursorAdapter);
    }
}
