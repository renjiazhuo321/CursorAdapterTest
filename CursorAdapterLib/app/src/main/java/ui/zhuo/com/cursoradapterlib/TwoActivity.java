package ui.zhuo.com.cursoradapterlib;

import android.app.Activity;
import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;

/**
 * Created by jiazhuo.ren on 2017/6/8.
 */

public class TwoActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.update_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("name", "测试2");
                getContentResolver().update(TestContentProvider.CONTENT_URI, contentValues,null,null);
                finish();
            }
        });

    }
}
