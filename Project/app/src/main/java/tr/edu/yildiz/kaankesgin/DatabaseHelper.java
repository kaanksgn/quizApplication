package tr.edu.yildiz.kaankesgin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.lang.reflect.Array;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static SQLiteDatabase users;
    public static SQLiteDatabase questions;

    private static final String DATABASE_NAME = "databases.db";

    private static final String TABLE_USER = "Users";
    private static final String col0 = "Photo";
    private static final String col1 = "Name";
    private static final String col2 = "Surname";
    private static final String col3 = "Email";
    private static final String col4 = "Phone";
    private static final String col5 = "Birthdate";
    private static final String col6 = "Password";


    private final Context context;
    private String TABLE_QUEST = "Questions";
    private static final String col21 = "Question";
    private static final String col22 = "Opt1";
    private static final String col23 = "Opt2";
    private static final String col24 = "Opt3";
    private static final String col25 = "Opt4";
    private static final String col26 = "Opt5";
    private static final String col27 = "rightAnswer";

    public static DatabaseHelper DBHelper = null;
    public static SQLiteDatabase DB = null;

    public static DatabaseHelper getInstance(Context context) {
        if (DBHelper == null) {

            DBHelper = new DatabaseHelper(context);

        }

        return DBHelper;
    }

    public DatabaseHelper(@Nullable Context context){
        super(context,DATABASE_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    String createTable = "CREATE TABLE "+ TABLE_USER +"(ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                                                        +col0+" TEXT,"
                                                        +col1+" TEXT,"
                                                        +col2+" TEXT,"
                                                        +col3+" TEXT,"
                                                        +col4+" TEXT,"
                                                        +col5+" TEXT,"
                                                        +col6+" TEXT);";

    String createTableQuest = "CREATE TABLE "+ TABLE_QUEST +"(ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                +col21+" TEXT,"
                +col22+" TEXT,"
                +col23+" TEXT,"
                +col24+" TEXT,"
                +col25+" TEXT,"
                +col26+" TEXT,"
                +col27+" TEXT);";



    db.execSQL(createTable);
    db.execSQL(createTableQuest);


    }

    private void openDatabase() {
        if (DB == null) {
            DB = getWritableDatabase();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS "+TABLE_USER);
    db.execSQL("DROP TABLE IF EXISTS "+TABLE_QUEST);
    onCreate(db);
    }


    boolean addUser(int photo,String name,String surname,String email,String phone,String birthday,String password){
        openDatabase();

        ContentValues cv= new ContentValues();
        cv.put(col0,photo);
        cv.put(col1,name);
        cv.put(col2,surname);
        cv.put(col3,email);
        cv.put(col4,phone);
        cv.put(col5,birthday);
        cv.put(col6,password);

        long addResult = DB.insert(TABLE_USER,null,cv);
        if (addResult== -1){
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();
            return false;
        }else{
            Toast.makeText(context,"Added Successfully.",Toast.LENGTH_SHORT).show();
            return true;
        }
    }


    public boolean checkUser(String user, String passw){
        openDatabase();
        Cursor c;
        String[] col = new String[]{"ID"};
        String[] args = new String[]{user,passw};



        c = DB.query(TABLE_USER, col,"Email =? AND Password =?",args,null,null,null);
        int length = c.getCount();


       if (length==1)
           return true;
       else
           return false;



    }

    public boolean checkNewUser (String mail ){
        openDatabase();
        Cursor c;
        String[] col  = new String[] {"ID"};
        String[] args = new String[] {mail};
        c = DB.query(TABLE_USER,col,"Email =?",args,null,null,null);
        int lenght = c.getCount();

        if(lenght==0){
            return true;
        }else
            return false;
    }

    String getUserName(String email){
        openDatabase();
        String query = "SELECT Name FROM Users WHERE Email = '"+email+"'";
        Cursor c;
        c = DB.rawQuery(query,null);
        String name=null;

        c.moveToNext();
        name = c.getString(0);


        return name;

    }

    String getUserPhoto(String email){
        openDatabase();
        String query = "SELECT Photo FROM Users WHERE Email = '"+email+"'";
        Cursor c;
        c = DB.rawQuery(query,null);
        String photo=null;
        c.moveToNext();
        photo = c.getString(0);
        return photo;
    }

    boolean deleteQuest(String id){
        openDatabase();

        int deleteResult=  DB.delete(TABLE_QUEST,"ID = "+id,null);
        if (deleteResult== -1){
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();
            return false;
        }else{
            Toast.makeText(context,"Deleted Successfully.",Toast.LENGTH_SHORT).show();
            return true;
        }
    }
    boolean editQuest(String id,String quest, String o1, String o2,@Nullable String o3,@Nullable String o4, @Nullable String o5,String right){
        openDatabase();
        ContentValues cv = new ContentValues();

        cv.put(col21,quest);
        cv.put(col22,o1);
        cv.put(col23,o2);
        cv.put(col24,o3);
        cv.put(col25,o4);
        cv.put(col26,o5);
        cv.put(col27,right);

        int editResult=  DB.update(TABLE_QUEST,cv,"ID = "+id,null);
        if (editResult== -1){
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();
            return false;
        }else{
            Toast.makeText(context,"Updated Successfully.",Toast.LENGTH_SHORT).show();
            return true;
        }

    }
    boolean addQuest(String quest, String o1, String o2,@Nullable String o3,@Nullable String o4, @Nullable String o5,String right){
        openDatabase();

        ContentValues cv = new ContentValues();

        cv.put(col21,quest);
        cv.put(col22,o1);
        cv.put(col23,o2);
        cv.put(col24,o3);
        cv.put(col25,o4);
        cv.put(col26,o5);
        cv.put(col27,right);

       long addResult=  DB.insert(TABLE_QUEST,null,cv);
        if (addResult== -1){
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();
            return false;
        }else{
            Toast.makeText(context,"Added Successfully.",Toast.LENGTH_SHORT).show();
            return true;
        }
    }



    Cursor readAllData(){
        String query = "SELECT * FROM "+TABLE_QUEST;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = null;
        if(db!=null){
            cursor = db.rawQuery(query,null);

        }
        return cursor;
    }


}
