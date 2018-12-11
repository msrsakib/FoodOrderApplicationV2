package blog.techanimate.sakib.foodorder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="userdetails.db";
    private static final String Table_NAME="user_details";
    private static final String ID="Id";
    private static final String NAME="Name";
    private static final String EMAIL="Email";
    private static final String USERNAME="Username";
    private static final String PASSWORD="Password";
    private static final String VERSION_NUMBER="1";

    private Context context;


    private static final String CREATE_TABLE ="CREATE Table "+Table_NAME+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+NAME+" VARCHAR(255) NOT NULL,"+EMAIL+" TEXT NOT NULL,"+USERNAME+" TEXT NOT NULL,"+PASSWORD+" TEXT NOT NULL)";
    private static final String DROP_TABLE="DROP TABLE IF EXISTS "+Table_NAME;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, Integer.parseInt(VERSION_NUMBER));
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase SQLiteDaabase) {

        try {
            SQLiteDaabase.execSQL(CREATE_TABLE);
            Toast.makeText(context,"OnCreate is Called",Toast.LENGTH_LONG).show();
        }catch (Exception e)
        {
            Toast.makeText(context,"Exception :"+e,Toast.LENGTH_LONG).show();
        }

    }


    @Override
    public void onUpgrade(SQLiteDatabase SQLiteDaabase, int oldVersion, int newVersion) {


        try {
            SQLiteDaabase.execSQL(DROP_TABLE);
            onCreate(SQLiteDaabase);
            Toast.makeText(context,"OnUpgrade is Called",Toast.LENGTH_LONG).show();
        }catch (Exception e)
        {
            Toast.makeText(context,"Exception :"+e,Toast.LENGTH_LONG).show();
        }
    }

    public long insertData(UserDetails userDetails)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(NAME,userDetails.getName());
        contentValues.put(EMAIL,userDetails.getEmail());
        contentValues.put(USERNAME,userDetails.getUsername());
        contentValues.put(PASSWORD,userDetails.getPassword());

        Long rowId=sqLiteDatabase.insert(Table_NAME,null,contentValues);
        return rowId;

    }


    public Boolean findPassword(String uname,String pass)
    {

        SQLiteDatabase sqLiteDaabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDaabase.rawQuery("SELECT * FROM " + Table_NAME, null);
        Boolean result = false;

        if (cursor.getCount() == 0)
        {
            Toast.makeText(context, "No data is found", Toast.LENGTH_LONG).show();
        }
        else {
            while (cursor.moveToNext())
            {
                String username = cursor.getString(3);
                String password = cursor.getString(4);

                if (username.equals(uname) && password.equals(pass)) {
                    result = true;
                    break;
                }
            }
        }

        return result;
    }
}
