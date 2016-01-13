package example.com.dbprovider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.util.Log;

import java.util.List;

/**
 * Created by justin on 1/12/16.
 */
public class DBManager {
    private static final String TAG = DBManager.class.getSimpleName();

    public static int insetDatas(ContentResolver contentResolver, List<Data> datas) {
        Log.d(TAG, "insert datas");

        if (contentResolver == null)
            throw new IllegalArgumentException("Resolver should not  be null");

        int count = 0;
        if (datas != null && !datas.isEmpty()) {
            int datasCount = datas.size();
            ContentValues[] contentValues = new ContentValues[datasCount];
            for (int i = 0; i < datasCount; i++) {
                contentValues[i] = DataManager.toContentValues(datas.get(i));
            }
            count = contentResolver.bulkInsert(DataTable.DATA_TABLE_URI, contentValues);
        }

        return count;
    }

    public static void queryAllData() {

    }

    public static void updateData() {

    }

    public static void deleteData() {

    }
}
