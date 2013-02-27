/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.NASAccessApp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 *
 * @see http://developer.android.com/guide/topics/data/data-storage.html#db
 * @see http://www.tutomobile.fr/comment-utiliser-sqlite-sous-android-tutoriel-android-n%C2%B019/19/10/2010/
 * @author Sylvain
 */
public class StorageAccess extends SQLiteOpenHelper {

	// ####################################################################
	// ###                          CONSTANTES                          ###
	// ####################################################################
	
	private static final int    DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "NAS";
	private static final String DATABASE_TABLE_NAME = "access";
	
	public static final String FIELD_NUMBER     = "Number";
	public static final int NUM_FIELD_NUMBER    = 0;
	public static final String FIELD_TYPE       = "Type";
	public static final int NUM_FIELD_TYPE      = 1;
	public static final String FIELD_DATE       = "Date";
	public static final int NUM_FIELD_DATE      = 2;
	public static final String FIELD_USER       = "Users";
	public static final int NUM_FIELD_USER      = 3;
	public static final String FIELD_SOURCE     = "Source";
	public static final int NUM_FIELD_SOURCE    = 4;
	public static final String FIELD_HOST       = "Host";
	public static final int NUM_FIELD_HOST      = 5;
	public static final String FIELD_RESSOURCE  = "Ressource";
	public static final int NUM_FIELD_RESSOURCE = 6;
	public static final String FIELD_PROTOCOL   = "Protocol";
	public static final int NUM_FIELD_PROTOCOL  = 7;
	public static final String FIELD_ACTION     = "Action";
	public static final int NUM_FIELD_ACTION    = 8;
	
	private static final String QUERY_CREATE_TABLE =
			"CREATE TABLE " + DATABASE_TABLE_NAME + " (" 
				+ FIELD_NUMBER    + " INTEGER PRIMARY KEY AUTOINCREMENT, " 	// ex: 849984
				+ FIELD_TYPE      + " TEXT, " 	// ex: "Information"
				+ FIELD_DATE      + " TEXT, "	// ex: "2013-02-23 18:18:16"
				+ FIELD_USER      + " TEXT, " 	// ex: "sylvain"
				+ FIELD_SOURCE    + " TEXT, " 	// ex: "192.168.1.39"
				+ FIELD_HOST      + " TEXT, " 	// ex: "sylvain-pc"
				+ FIELD_RESSOURCE + " TEXT, " 	// ex: "Multimedia/FILMS/[Magie] Harry Potter VII - Les Reliques de la Mort - Partie 1.avi"
				+ FIELD_PROTOCOL  + " TEXT, " 	// ex: "SAMBA"
				+ FIELD_ACTION    + " TEXT"	// ex: "Read"
				+ 
			");";
	
	private static final String QUERY_DROP_TABLE = 
			"DROP TABLE IF EXISTS " + DATABASE_TABLE_NAME + ";";

	// ####################################################################
	// ###                          VARIABLES                           ###
	// ####################################################################
	
	private SQLiteDatabase bdd;
	private String TAG;

	// ####################################################################
	// ###                         CONSTRUCTEUR                         ###
	// ####################################################################
	
	StorageAccess(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(QUERY_CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data"); 
		db.execSQL(QUERY_DROP_TABLE); 
		onCreate(db); 
	}

	// ####################################################################
	// ###                   ACCES A LA BASE DE DONNEES                 ###
	// ####################################################################
 
	/**
	 * Accesseur à l'instance de la base de données
	 * 
	 * @return SQLiteDatabase
	 * @author Sylvain {25/02/2013}
	 */
	public SQLiteDatabase getInstance(){
		return this.getWritableDatabase();
	}
	
	public  String[] getColumns()
	{
		return new String[] {
			FIELD_NUMBER,
			FIELD_TYPE,
			FIELD_DATE,
			FIELD_USER,
			FIELD_SOURCE,
			FIELD_HOST,
			FIELD_RESSOURCE,
			FIELD_PROTOCOL,
			FIELD_ACTION
		};
	}
}
