package example.com.dbprovider;

import android.net.Uri;

/**
 * Created by justin on 1/10/16.
 */
public class DataTable {
    public static final String TABLE_NAME = "data";

    public static final Uri DATA_TABLE_URI = Uri.parse("content://" + DataProvider.AUTHORITY + "/" + TABLE_NAME);

    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_SEX = "sex";

    public static final String CREATE_DATA_TABLE =
        "CREATE TABLE " + TABLE_NAME +
            "(" +
            KEY_ID + " TEXT PRIMARY KEY," +
            KEY_NAME + " TEXT, " +
            KEY_PHONE + " TEXT," +
            KEY_SEX + " INTEGER" +
            ")";

    public static final String[] DATA_PROJECTION_A = {
        KEY_ID,
        KEY_NAME
    };
}
