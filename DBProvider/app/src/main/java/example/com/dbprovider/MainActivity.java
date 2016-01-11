package example.com.dbprovider;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DataTable.KEY_ID, "1");
        contentValues.put(DataTable.KEY_NAME, "justin");
        contentValues.put(DataTable.KEY_PHONE, "0936992652");
        contentValues.put(DataTable.KEY_SEX, 0);

        getContentResolver().insert(DataTable.DATA_TABLE_URI, contentValues);
        Cursor cursor = getContentResolver().query(DataTable.DATA_TABLE_URI, DataTable.DATA_PROJECTION_A, null, null, null);
        cursor.moveToFirst();
        String name = cursor.getString(cursor.getColumnIndex(DataTable.KEY_NAME));
        Log.d("justin++", "name = "+name);
    }
}
