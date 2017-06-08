package ui.zhuo.com.cursoradapterlib;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private ListView riverListView;
    private static final int LOADER_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.update_test);
        button.setText("One");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TwoActivity.class);
                startActivity(intent);
            }
        });
        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(LOADER_ID, null, this);
        setRiverListViewAdapter();
    }

    ListCursorAdapter listCursorAdapter;

    private void setRiverListViewAdapter() {
        riverListView = (ListView) this.findViewById(R.id.riverList);
        //去掉查询数据库，由于查询数据库可能耗时，所以换loaderManager方式查询数据库，赋值
//      Cursor cursor = getContentResolver().query(TestContentProvider.CONTENT_URI, null,
//                null, null, null);
        listCursorAdapter = new ListCursorAdapter(this, null);
        riverListView.setAdapter(listCursorAdapter);
    }

    //新增loaderManager，使用loaderManager查询数据，加载数据集
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Loader<Cursor> loader = null;
        switch (id) {
            case LOADER_ID:
                loader = new CursorLoader(MainActivity.this, TestContentProvider.CONTENT_URI,
                        null, null, null, null);
                break;
        }
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        switch (loader.getId()) {
            case LOADER_ID:
                listCursorAdapter.swapCursor(cursor);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        listCursorAdapter.swapCursor(null);
    }
}
