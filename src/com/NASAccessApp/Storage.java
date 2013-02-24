/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.NASAccessApp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 *
 * @see http://developer.android.com/guide/topics/data/data-storage.html#db
 * @author Sylvain
 */
public class Storage extends SQLiteOpenHelper {
	
	private SQLiteDatabase db;
	
	private static final int    DATABASE_VERSION = 2;
	private static final String DATABASE_NAME = "NASAdmin";
	private static final String DICTIONARY_TABLE_NAME = "access";
	
	private static final String DICTIONARY_QUERY_CREATE_TABLE =
			"CREATE TABLE " + DICTIONARY_TABLE_NAME + " (" 
				+ "Number INT, " 	// ex: 849984
				+ "Type TEXT" 		// ex: "Information"
				+ "Date DATETIME" 	// ex: "2013-02-23 18:18:16"
				+ "Users TEXT" 		// ex: "sylvain"
				+ "Source TEXT" 	// ex: "192.168.1.39"
				+ "Host TEXT" 		// ex: "sylvain-pc"
				+ "Ressource TEXT" 	// ex: "Multimedia/FILMS/[Magie] Harry Potter VII - Les Reliques de la Mort - Partie 1.avi"
				+ "Protocol TEXT" 	// ex: "SAMBA"
				+ "Action TEXT"		// ex: "Read"
				+ 
			");";
	
	private static final String DICTIONARY_QUERY_INSERT_STATEMENT =
			"INSERT INTO " + DICTIONARY_TABLE_NAME + " VALUES("
				+ "%d"
				+ "%s"
				+ "%s"
				+ "%s"
				+ "%s"
				+ "%s"
				+ "%s"
				+ "%s"
				+ "%s"
				+
			");";
	
	Storage(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DICTIONARY_QUERY_CREATE_TABLE);
		this.db = db;
	}

	@Override
	public void onUpgrade(SQLiteDatabase sqld, int i, int i1) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
	public static boolean save(Record record)
	{
		String query = String.format(DICTIONARY_QUERY_INSERT_STATEMENT, 
				record.getNumber(),
				record.getType(),
				record.getDate(),
				record.getUsers(),
				record.getSource(),
				record.getHost(),
				record.getRessource(),
				record.getProtocol(),
				record.getAction()
		);
		
		db.execSQL(query);
		return true;
	}
}
