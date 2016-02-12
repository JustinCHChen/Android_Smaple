package example.com.dbprovider;

import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.os.RemoteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by justin on 1/12/16.
 */
public class DBManager {
    private static final String TAG = DBManager.class.getSimpleName();

    public static int insetDatas(ContentResolver contentResolver, List<Data> dataList) {
        Log.d(TAG, "insert dataList");

        if (contentResolver == null) {
            throw new IllegalArgumentException("Resolver should not  be null");
        }


        int count = 0;
        if (dataList != null && !dataList.isEmpty()) {
            int dataCount = dataList.size();
            ContentValues[] contentValues = new ContentValues[dataCount];
            for (int i = 0; i < dataCount; i++) {
                contentValues[i] = DataManager.toContentValues(dataList.get(i));
            }
            count = contentResolver.bulkInsert(DataTable.URI_DATA_TABLE, contentValues);
        }

        return count;
    }

    public static List<Data> queryAllData(ContentResolver contentResolver) {
        Log.d(TAG, "query all data");

        if (contentResolver == null) {
            throw new IllegalArgumentException("Resolver should not  be null");
        }

        List<Data> dataList = new ArrayList<>();
        String orderBy = DataTable.KEY_NAME;
        Cursor cursor = contentResolver.query(DataTable.URI_DATA_TABLE, DataTable.DATA_PROJECTION_A, null, null, orderBy);

        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Data data = DataManager.toData(cursor);
                dataList.add(data);
                cursor.moveToNext();
            }
        }

        return dataList;
    }

    public static int updateDatas(ContentResolver contentResolver, List<Data> dataList) {
        Log.d(TAG, "start update data");

        if (contentResolver == null) {
            throw new IllegalArgumentException("Resolver should not  be null");
        }

        int count = 0;
        if (dataList == null || dataList.isEmpty()) {
            return count;
        }

        ArrayList<ContentProviderOperation> ops = new ArrayList<>();
        String selection = DataTable.KEY_ID + " = ?";

        for (Data data : dataList) {
            ContentValues contentValues = DataManager.toContentValues(data);
            String[] selectionArgs = {data.getId()};

            ops.add(ContentProviderOperation.newUpdate(DataTable.URI_DATA_TABLE)
                .withValues(contentValues)
                .withSelection(selection, selectionArgs)
                .withYieldAllowed(ops.size() % 100 == 0)
                .build());
        }

        try {
            ContentProviderResult[] opsResults = contentResolver.applyBatch(DataProvider.AUTHORITY, ops);
            for (ContentProviderResult result : opsResults) {
                count += result.count;
            }
        } catch (RemoteException | OperationApplicationException e) {
            Log.e(TAG, e.toString());
        }

        Log.d(TAG, "end update data");
        return count;
    }

    public static int deleteDatas(ContentResolver contentResolver, List<Data> dataList) {
        Log.d(TAG, "start delete data");

        if (contentResolver == null) {
            throw new IllegalArgumentException("Resolver should not  be null");
        }

        int count = 0;
        if (dataList == null || dataList.isEmpty()) {
            return count;
        }

        ArrayList<ContentProviderOperation> ops = new ArrayList<>();
        String selection = DataTable.KEY_ID + " = ?";

        for (Data data : dataList) {
            ContentValues contentValues = DataManager.toContentValues(data);
            String[] selectionArgs = {data.getId()};

            ops.add(ContentProviderOperation.newDelete(DataTable.URI_DATA_TABLE)
                .withSelection(selection, selectionArgs)
                .withYieldAllowed(ops.size() % 100 == 0)
                .build());
        }

        try {
            ContentProviderResult[] opsResults = contentResolver.applyBatch(DataProvider.AUTHORITY, ops);
            for (ContentProviderResult result : opsResults) {
                count += result.count;
            }
        } catch (RemoteException | OperationApplicationException e) {
            Log.e(TAG, e.toString());
        }

        Log.d(TAG, "start delete data");
        return count;
    }
}
