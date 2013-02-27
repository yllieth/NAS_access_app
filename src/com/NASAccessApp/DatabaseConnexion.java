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
 * @see http://www.tutomobile.fr/comment-utiliser-sqlite-sous-android-tutoriel-android-n%C2%B019/19/10/2010/
 * @author Sylvain
 */
public class DatabaseConnexion extends SQLiteOpenHelper {

	// ####################################################################
	// ###                          CONSTANTES                          ###
	// ####################################################################
	
	private static final int      DATABASE_VERSION = 1;
	private static final String   DATABASE_NAME = "NAS";

	// ####################################################################
	// ###                          VARIABLES                           ###
	// ####################################################################
	
	

	// ####################################################################
	// ###                         CONSTRUCTEUR                         ###
	// ####################################################################
	
	DatabaseConnexion(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(RecordHandler.createTableQuery());
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(RecordHandler.dropTableQuery());
		onCreate(db); 
	}

	// ####################################################################
	// ###                   ACCES A LA BASE DE DONNEES                 ###
	// ####################################################################
	
	public RecordHandler getTable(String tableName)
	{
		return new RecordHandler(this);
	}
	
	public SQLiteDatabase open()
	{
		return getWritableDatabase();
	}
}
