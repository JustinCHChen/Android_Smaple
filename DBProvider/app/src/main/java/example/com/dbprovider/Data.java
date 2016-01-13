package example.com.dbprovider;

/**
 * Created by justin on 1/14/16.
 */
public class Data {
    private String mId = "";
    private String mName = "";
    private String mPhone = "";
    private int mSex = 0;

    public void setId(String id) {
        mId = id;
    }

    public String getId() {
        return mId;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public void setPhone(String phone) {
        mPhone = phone;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setSex(int sex) {
        mSex = sex;
    }

    public int getSex() {
        return mSex;
    }
}
