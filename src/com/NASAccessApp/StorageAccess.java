/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.NASAccessApp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

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
	
	private static final int    DATABASE_VERSION = 2;
	private static final String DATABASE_NAME = "NAS.db";
	private static final String DATABASE_TABLE_NAME = "access";
	
	public static final String FIELD_NUMBER     = "Number";
	public static final int NUM_FIELD_NUMBER    = 0;
	public static final String FIELD_TYPE       = "Type";
	public static final int NUM_FIELD_TYPE      = 1;
	public static final String FIELD_DATE       = "Datetime";
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
				+ FIELD_NUMBER    + " INT, " 	// ex: 849984
				+ FIELD_TYPE      + " TEXT" 	// ex: "Information"
				+ FIELD_DATE      + " TEXT"		// ex: "2013-02-23 18:18:16"
				+ FIELD_USER      + " TEXT" 	// ex: "sylvain"
				+ FIELD_SOURCE    + " TEXT" 	// ex: "192.168.1.39"
				+ FIELD_HOST      + " TEXT" 	// ex: "sylvain-pc"
				+ FIELD_RESSOURCE + " TEXT" 	// ex: "Multimedia/FILMS/[Magie] Harry Potter VII - Les Reliques de la Mort - Partie 1.avi"
				+ FIELD_PROTOCOL  + " TEXT" 	// ex: "SAMBA"
				+ FIELD_ACTION    + " TEXT"		// ex: "Read"
				+ 
			");";

	// ####################################################################
	// ###                          VARIABLES                           ###
	// ####################################################################
	
	private SQLiteDatabase bdd;

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
	public void onUpgrade(SQLiteDatabase sqld, int i, int i1) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	// ####################################################################
	// ###                   ACCES A LA BASE DE DONNEES                 ###
	// ####################################################################
	
	/**
	 * Ouverture de l'accès à la base de données 
	 * 
	 * @return void
	 * @author Sylvain {25/02/2013}
	 */
	public void open(){
		bdd = this.getWritableDatabase();
	}
 
	/**
	 * Accesseur à l'instance de la base de données
	 * 
	 * @return SQLiteDatabase
	 * @author Sylvain {25/02/2013}
	 */
	public SQLiteDatabase getInstance(){
		return bdd;
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
