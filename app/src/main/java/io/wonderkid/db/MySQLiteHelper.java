package io.wonderkid.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.wonderkid.model.Message;
import io.wonderkid.model.MessageWrapper;


public class MySQLiteHelper extends SQLiteOpenHelper {

	//Stores List of Stores user Connected to
	public static final String TABLE_CHAT_LIST = "chats";
	public static final String COLUMN_CHAT_ID = "chat_id";
	public static final String COLUMN_ISMINE = "ismine";
	public static final String COLUMN_SUCCESS = "success";
	public static final String COLUMN_ERRORMESSAGE = "errormessage";
	public static final String COLUMN_CHATBOTNAME = "chatbotname";
	public static final String COLUMN_CHATBOTID = "chatbotid";
	public static final String COLUMN_MESSAGE = "message";
	public static final String COLUMN_EMOTION = "emotion";

	private static final String DATABASE_NAME = "users.db";
	private static final int DATABASE_VERSION = 1;

	SQLiteDatabase db;
	Context context;
	// Database creation sql statement

	private static final String DATABASE_CREATE_CHAT_LIST = "create table "
			+ TABLE_CHAT_LIST + "(" +
            COLUMN_CHAT_ID + " integer primary key autoincrement, " +
            COLUMN_ISMINE + " integer, " +
            COLUMN_SUCCESS + " text," +
            COLUMN_ERRORMESSAGE + " text," +
            COLUMN_CHATBOTNAME + " text," +
            COLUMN_CHATBOTID + " integer," +
            COLUMN_MESSAGE + " text," +
            COLUMN_EMOTION + " text);";

	public MySQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE_CHAT_LIST);
	}

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //do nothing on upgrade
    }

    public long addMessage(MessageWrapper messageWrapper) {
		db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();

        if(messageWrapper.getIsMine())
		    cv.put(COLUMN_ISMINE,1);
        else
            cv.put(COLUMN_ISMINE,0);
		cv.put(COLUMN_SUCCESS, messageWrapper.getSuccess());
		cv.put(COLUMN_ERRORMESSAGE,messageWrapper.getErrorMessage());
		cv.put(COLUMN_CHATBOTNAME,messageWrapper.getMessage().getChatBotName());
		cv.put(COLUMN_CHATBOTID,messageWrapper.getMessage().getChatBotID());
		cv.put(COLUMN_MESSAGE,messageWrapper.getMessage().getMessage());
		cv.put(COLUMN_EMOTION,messageWrapper.getMessage().getEmotion());

		long r = db.insert(TABLE_CHAT_LIST, null, cv);
		db.close();
		return r;
	}

	public List<MessageWrapper> getChatHistory() {

		List<MessageWrapper> history = new ArrayList<MessageWrapper>();
		db = this.getWritableDatabase();
		String sql = "SELECT * FROM " + TABLE_CHAT_LIST + ";";
		Cursor c = db.rawQuery(sql, null);
		c.moveToFirst();
		for (int i = 0; i < c.getCount(); i++) {
            MessageWrapper messageWrapper = new MessageWrapper();
            Message message = new Message();

            if(c.getInt(1)==1)
                messageWrapper.setIsMine(true);
            else
                messageWrapper.setIsMine(false);

            messageWrapper.setSuccess(c.getString(2));
            messageWrapper.setErrorMessage(c.getString(3));

            message.setChatBotName(c.getString(4));
            message.setChatBotID(c.getInt(5));
            message.setMessage(c.getString(6));
            message.setEmotion(c.getString(7));

            messageWrapper.setMessage(message);

            history.add(messageWrapper);
			c.moveToNext();
		}
		db.close();
		return history;
	}

}