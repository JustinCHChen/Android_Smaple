package example.com.dbprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by justin on 1/10/16.
 */
public class DBSQLiteHelper extends BaseSQLiteOpenHelper {
    private static final String TAG = DBSQLiteHelper.class.getSimpleName();
    public static final String DB_NAME = "exampleDB";

    public DBSQLiteHelper(Context context) {
        super(context, DB_NAME, null, UPDATE_PATCHES.length);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        CREATE_DB.apply(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "Upgrade DB table");
        for (int i = oldVersion; i < newVersion; i++) {
            Log.d(TAG, "Upgrade DB table patch = " + i);
            UPDATE_PATCHES[i].apply(db);
        }

    }

    public static abstract class Patch {

        abstract void apply(SQLiteDatabase db);
    }

    //For first create
    public static final Patch CREATE_DB = new Patch() {
        @Override
        void apply(SQLiteDatabase db) {
            Log.d(TAG, "Create table :" + DataTable.TABLE_NAME);
            db.execSQL(DataTable.CREATE_DATA_TABLE);
        }
    };


    //For each update
    public static final Patch UPDAT_PATCH_EMPTY = new Patch() {

        @Override
        void apply(SQLiteDatabase db) {
            //Do nothing
        }
    };

    public static final Patch[] UPDATE_PATCHES = new Patch[]{
        UPDAT_PATCH_EMPTY
    };
}
