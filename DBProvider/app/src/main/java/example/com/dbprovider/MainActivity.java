package example.com.dbprovider;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Handler.Callback, View.OnClickListener {

    public static final int MSG_OFFSET = 1000;

    public static final int MSG_INSERT = 1;
    public static final int MSG_UPDATE = 2;
    public static final int MSG_DELETE = 3;
    public static final int MSG_QUERY = 4;

    public static final int UI_MSG_INSERT = MSG_OFFSET + 1;
    public static final int UI_MSG_UPDATE = MSG_OFFSET + 2;
    public static final int UI_MSG_DELETE = MSG_OFFSET + 3;
    public static final int UI_MSG_QUERY = MSG_OFFSET + 4;

    private HandlerThread mWorkingThread;
    private Handler mUIHandler;
    private WorkingHandler mWorkingHandler;

    private EditText mIdField;
    private EditText mNameField;
    private EditText mPhoneField;
    private EditText mSexField;
    private Button mBtnInsert;
    private Button mBtnDelete;
    private Button mBtnUpdate;
    private Button mBtnQuery;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initHandler();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initView() {
        mIdField = (EditText) findViewById(R.id.user_id);
        mNameField = (EditText) findViewById(R.id.user_name);
        mPhoneField = (EditText) findViewById(R.id.user_phone);
        mSexField = (EditText) findViewById(R.id.user_sex);

        mBtnInsert = (Button) findViewById(R.id.btn_insert);
        mBtnDelete = (Button) findViewById(R.id.btn_delete);
        mBtnUpdate = (Button) findViewById(R.id.btn_update);
        mBtnQuery = (Button) findViewById(R.id.btn_query);

        mBtnInsert.setOnClickListener(this);
        mBtnDelete.setOnClickListener(this);
        mBtnUpdate.setOnClickListener(this);
        mBtnQuery.setOnClickListener(this);
    }

    private void initHandler() {
        mWorkingThread = new HandlerThread("WorkingThread");
        mWorkingThread.start();

        mUIHandler = new Handler(this);
        mWorkingHandler = new WorkingHandler(mWorkingThread.getLooper(), this);
        mWorkingHandler.setUIHandler(mUIHandler);
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case UI_MSG_INSERT:
                String resultCount;
                if ((Integer) msg.obj > 0) {
                    resultCount = "insert successfully";
                } else {
                    resultCount = "insert fail";
                }

                Toast.makeText(this, resultCount, Toast.LENGTH_SHORT).show();
                break;
            case UI_MSG_UPDATE:
                if ((Integer) msg.obj > 0) {
                    resultCount = "update successfully";
                } else {
                    resultCount = "update fail";
                }

                Toast.makeText(this, resultCount, Toast.LENGTH_SHORT).show();
                break;
            case UI_MSG_DELETE:
                if ((Integer) msg.obj > 0) {
                    resultCount = "delete successfully";
                } else {
                    resultCount = "delete fail";
                }

                Toast.makeText(this, resultCount, Toast.LENGTH_SHORT).show();
                break;
            case UI_MSG_QUERY:
                List<Data> dataList = (List<Data>) msg.obj;
                String queryResult = "";
                for (Data data : dataList) {
                    queryResult = queryResult + data.getId() + " " + data.getName() + " " + data.getPhone() + " " + data.getSex() + "\n";
                }
                Toast.makeText(this, queryResult, Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_insert:
                Data data = getInputData();
                if (data != null) {
                    List<Data> dataList = new ArrayList<>();
                    dataList.add(data);
                    Message msg = mWorkingHandler.obtainMessage(MSG_INSERT, dataList);
                    mWorkingHandler.sendMessage(msg);
                } else {
                    Toast.makeText(this, "Please insert all field", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_update:
                data = getInputData();
                if (data != null) {
                    List<Data> dataList = new ArrayList<>();
                    dataList.add(data);
                    Message msg = mWorkingHandler.obtainMessage(MSG_UPDATE, dataList);
                    mWorkingHandler.sendMessage(msg);
                } else {
                    Toast.makeText(this, "Please insert all field", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btn_delete:
                data = getInputData();
                if (data != null) {
                    List<Data> dataList = new ArrayList<>();
                    dataList.add(data);
                    Message msg = mWorkingHandler.obtainMessage(MSG_DELETE, dataList);
                    mWorkingHandler.sendMessage(msg);
                } else {
                    Toast.makeText(this, "Please insert all field", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btn_query:
                Message msg = mWorkingHandler.obtainMessage(MSG_QUERY);
                mWorkingHandler.sendMessage(msg);
                break;

        }
    }

    private Data getInputData() {
        Data data = new Data();
        String id = !TextUtils.isEmpty(mIdField.getText()) ? mIdField.getText().toString() : null;
        String name = !TextUtils.isEmpty(mNameField.getText()) ? mNameField.getText().toString() : null;
        String phone = !TextUtils.isEmpty(mPhoneField.getText()) ? mPhoneField.getText().toString() : null;
        int sex = !TextUtils.isEmpty(mSexField.getText()) ? Integer.parseInt(mSexField.getText().toString()) : 0;

        if (!TextUtils.isEmpty(id)/* && !TextUtils.isEmpty(name) && !TextUtils.isEmpty(phone)*/) {
            data.setId(id);
            data.setName(name);
            data.setPhone(phone);
            data.setSex(sex);

            mIdField.setText("");
            mNameField.setText("");
            mPhoneField.setText("");
            mSexField.setText("");
        } else {
            data = null;
        }


        return data;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWorkingThread != null && mWorkingThread.isAlive()) {
            mWorkingThread.getLooper().quit();
            mWorkingThread = null;
        }
    }

    class WorkingHandler extends Handler {
        Context mContext;
        Handler mUIHandler;

        public WorkingHandler(Looper looper, Context context) {
            super(looper);
            mContext = context;
        }

        public void setUIHandler(Handler handler) {
            mUIHandler = handler;
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_INSERT:
                    int count = DBManager.insetDatas(mContext.getContentResolver(), (List<Data>) msg.obj);
                    if (mUIHandler != null) {
                        Message message = mUIHandler.obtainMessage(UI_MSG_INSERT, count);
                        mUIHandler.sendMessage(message);
                    }
                    break;
                case MSG_UPDATE:
                    count = DBManager.updateDatas(mContext.getContentResolver(), (List<Data>) msg.obj);
                    if (mUIHandler != null) {
                        Message message = mUIHandler.obtainMessage(UI_MSG_UPDATE, count);
                        mUIHandler.sendMessage(message);
                    }
                    break;
                case MSG_DELETE:
                    count = DBManager.deleteDatas(mContext.getContentResolver(), (List<Data>) msg.obj);
                    if (mUIHandler != null) {
                        Message message = mUIHandler.obtainMessage(UI_MSG_DELETE, count);
                        mUIHandler.sendMessage(message);
                    }
                    break;
                case MSG_QUERY:
                    List<Data> dataList = DBManager.queryAllData(mContext.getContentResolver());
                    if (mUIHandler != null) {
                        Message message = mUIHandler.obtainMessage(UI_MSG_QUERY, dataList);
                        mUIHandler.sendMessage(message);
                    }
                    break;
            }
        }
    }
}
