package example.com.dbprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by justin on 1/10/16.
 */
public class DataProvider extends ContentProvider {
    public static final String TAG = DataProvider.class.getSimpleName();
    public static final String AUTHORITY = "com.example.dbprovider";

    private static final int DATA_TABLE = 1;
    private static UriMatcher mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private DBSQLiteHelper mHelper;

    static {
        mUriMatcher.addURI(AUTHORITY, DataTable.TABLE_NAME, DATA_TABLE);
    }

    private String getTable(Uri uri) {
        String result;
        switch (mUriMatcher.match(uri)) {
            case DATA_TABLE:
                result = DataTable.TABLE_NAME;
                break;
            default:
                throw new IllegalArgumentException("illegal rui:" + uri);
        }
        return result;
    }

    @Override
    public boolean onCreate() {
        Log.d(TAG, "DataProvider create");
        mHelper = new DBSQLiteHelper(getContext());
        mHelper.getReadableDatabase();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        String tableName = getTable(uri);
        Cursor cursor = mHelper.query(tableName, projection, selection, selectionArgs, sortOrder);
        Log.d("justin++", "cursor = " + cursor);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        String tableName = getTable(uri);
        long id = mHelper.insert(tableName, values);
        Log.d("justin++", "id = " + id);
        return id != -1 ? Uri.withAppendedPath(uri, Long.toString(id)) : null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        String tableName = getTable(uri);
        int count = mHelper.delete(tableName, selection, selectionArgs);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        String tableName = getTable(uri);
        int count = mHelper.update(tableName, values, selection, selectionArgs);
        return count;
    }
}
