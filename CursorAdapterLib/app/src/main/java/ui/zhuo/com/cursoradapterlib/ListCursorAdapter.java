package ui.zhuo.com.cursoradapterlib;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by jiazhuo.ren on 2017/6/7.
 */

public class ListCursorAdapter extends CursorAdapter{
    public ListCursorAdapter(Context context, Cursor c) {
        super(context, c);
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        final LayoutInflater layoutInflater = LayoutInflater.from(context);
        ViewHolder viewHolder=new ViewHolder();
        View view=   layoutInflater.inflate(R.layout.item_test, viewGroup, false);
        viewHolder.textView= (TextView) view.findViewById(R.id.riverName);
        view.setTag(viewHolder);
        return view;
    }
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder viewHolder= (ViewHolder) view.getTag();
        viewHolder.textView.setText( cursor.getString(cursor.getColumnIndex("name")));

    }
     class ViewHolder {
         TextView textView;
    }


}
