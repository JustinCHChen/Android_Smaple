package example.com.dbprovider;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Created by justin on 1/14/16.
 */
public class DataManager {

    public static ContentValues toContentValues(Data data) {
        if (data == null) throw new IllegalArgumentException("data is null");

        ContentValues contentValues = new ContentValues();
        contentValues.put(DataTable.KEY_ID, data.getId());
        contentValues.put(DataTable.KEY_NAME, data.getName());
        contentValues.put(DataTable.KEY_PHONE, data.getPhone());
        contentValues.put(DataTable.KEY_SEX, data.getSex());

        return contentValues;
    }

    public static Data toData(Cursor cursor) {
        if (cursor == null) throw new IllegalArgumentException("cursor is null");

        Data data = new Data();
        int index;

        index = cursor.getColumnIndex(DataTable.KEY_ID);
        if (index != -1) {
            data.setId(cursor.getString(index));
        }

        index = cursor.getColumnIndex(DataTable.KEY_NAME);
        if (index != -1) {
            data.setName(cursor.getString(index));
        }

        index = cursor.getColumnIndex(DataTable.KEY_PHONE);
        if (index != -1) {
            data.setPhone(cursor.getString(index));
        }

        index = cursor.getColumnIndex(DataTable.KEY_SEX);
        if (index != -1) {
            data.setSex(cursor.getInt(index));
        }

        return data;
    }
}
