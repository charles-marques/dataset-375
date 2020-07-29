package com.softcocoa.db;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.google.android.maps.GeoPoint;
import com.softcocoa.goodmap.C;
import com.softcocoa.location.HLocation;

public class DatabaseHelper extends SQLiteOpenHelper {

	// The Android's default system path of your application database.
	private static String DB_PATH = "/data/data/com.softcocoa.goodmap/databases/";

	private static String DB_NAME = "goodmap.sqlite";

	private SQLiteDatabase myDatabase;

	private final Context myContext;

	/**
	 * Constructor Takes and keeps a reference of the passed context in order to
	 * access to the application assets and resources.
	 * 
	 * @param context
	 */
	/*
	 * public DataBaseHelper(Context context) { super(context, DB_NAME, null,
	 * 1); this.myContext = context; }
	 */

	public DatabaseHelper(Context context, String packageName, String dbName) {
		super(context, dbName, null, 1);
		this.myContext = context;
		this.DB_PATH = "/data/data/" + packageName + "/databases/";
		this.DB_NAME = dbName;
	}
	
	public DatabaseHelper(Context context) {
		super(context, DB_NAME, null, 1);
		this.myContext = context;
	}

	/**
	 * Creates a empty database on the system and rewrites it with your own
	 * database.
	 * */
	public void createDataBase() throws IOException {

		boolean dbExist = checkDataBase();

		if (dbExist) {
			// do nothing - database already exist
		} else {

			// By calling this method and empty database will be created into
			// the default system path
			// of your application so we are gonna be able to overwrite that
			// database with our database.
			this.getReadableDatabase();

			try {

				copyDataBase();

			} catch (IOException e) {

				throw new Error("Error copying database");

			}
		}

	}

	/**
	 * Check if the database already exist to avoid re-copying the file each
	 * time you open the application.
	 * 
	 * @return true if it exists, false if it doesn't
	 */
	private boolean checkDataBase() {

		SQLiteDatabase checkDB = null;

		try {
			String myPath = DB_PATH + DB_NAME;
			checkDB = SQLiteDatabase.openDatabase(myPath, null,
					SQLiteDatabase.OPEN_READONLY | SQLiteDatabase.NO_LOCALIZED_COLLATORS);

		} catch (SQLiteException e) {

			// database does't exist yet.

		}

		if (checkDB != null) {

			checkDB.close();

		}

		return checkDB != null ? true : false;
	}

	/**
	 * Copies your database from your local assets-folder to the just created
	 * empty database in the system folder, from where it can be accessed and
	 * handled. This is done by transfering bytestream.
	 * */
	private void copyDataBase() throws IOException {

		// Open your local db as the input stream
		InputStream myInput = myContext.getAssets().open(DB_NAME);

		// Path to the just created empty db
		String outFileName = DB_PATH + DB_NAME;

		// Open the empty db as the output stream
		OutputStream myOutput = new FileOutputStream(outFileName);

		// transfer bytes from the inputfile to the outputfile
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer)) > 0) {
			myOutput.write(buffer, 0, length);
		}

		// Close the streams
		myOutput.flush();
		myOutput.close();
		myInput.close();

	}

	public void openDataBase() throws SQLException {

		// Open the database
		String myPath = DB_PATH + DB_NAME;
		myDatabase = SQLiteDatabase.openDatabase(myPath, null,
				SQLiteDatabase.OPEN_READONLY | SQLiteDatabase.NO_LOCALIZED_COLLATORS);
	}

	@Override
	public synchronized void close() {

		if (myDatabase != null)
			myDatabase.close();

		super.close();

	}

	@Override
	public void onCreate(SQLiteDatabase db) {

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	// Add your public helper methods to access and get content from the
	// database.
	// You could return cursors by doing "return myDataBase.query(....)" so it'd
	// be easy
	// to you to create adapters for your views.

	public Cursor getAllPositions(String filter) {
		Cursor cursor = null;
		if (filter == null) {
			cursor = myDatabase.query("position", null, null, null, null, null, "id");
		}
		else {
			filter = filter.replace(" ", "%");
			cursor = myDatabase.query("position", null, "detail LIKE ?", new String[]{"%"+filter+"%"}, null, null, "id");
		}
		
		if(cursor.moveToFirst())
			return cursor;
		else {
			cursor.close();
			return null;
		}
	}
	
	public GeoPoint getLocationPoint(String locationId) {
		Cursor cursor = myDatabase.query("position", null, "id = ?", new String[]{locationId}, null, null, "id");
		Log.i(C.TAG, "cursor size:"+cursor.getCount());
		if (cursor.moveToFirst()) {
			double latitude = cursor.getDouble(cursor.getColumnIndex("latitude"));
			double longitude = cursor.getDouble(cursor.getColumnIndex("longitude"));
			Log.i(C.TAG, "latitude:"+latitude+", longitude:"+longitude);
			cursor.close();
			return HLocation.createGeoPoint(latitude, longitude);
		}
		else {
			cursor.close();
			return null;
		}
	}
	
	public String getLocationName(String locationId) {
		Cursor cursor = myDatabase.query("position", null, "id = ?", new String[]{locationId}, null, null, "id");
		Log.i(C.TAG, "cursor size:"+cursor.getCount());
		if (cursor.moveToFirst()) {
			String name = cursor.getString(cursor.getColumnIndex("detail"));
			cursor.close();
			return name;
		}
		else {
			cursor.close();
			return null;
		}
	}
	
}
