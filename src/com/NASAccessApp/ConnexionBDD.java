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
public class ConnexionBDD extends SQLiteOpenHelper {

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
	
	ConnexionBDD(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	/**
	 * Crée la base de donnée lors de l'installation de l'application
	 * 
	 * @param SQLiteDatabase db 
	 * @author Sylvain{__/02/2013}
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(RecordHandler.createTableQuery());
	}

	/**
	 * Mise à jour de la structure de la base de données lors d'une mise à jour
	 * 
	 * @param SQLiteDatabase db
	 * @param int oldVersion
	 * @param int newVersion 
	 * @author Sylvain {__/02/2013}
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(RecordHandler.dropTableQuery());
		onCreate(db); 
	}

	// ####################################################################
	// ###                   ACCES A LA BASE DE DONNEES                 ###
	// ####################################################################
	
	/**
	 * Renvoie le gestionnaire de requêtes sur la table "access".
	 * 
	 * @return RecordHandler
	 * @author Sylvain {28/02/2013}
	 */
	public RecordHandler getTableRecord()
	{
		return new RecordHandler(this);
	}
	
	/**
	 * Ouvre la connexion à la base de données précédemment initialisée.
	 * 
	 * @return SQLiteDatabase
	 * @author Sylvain {28/02/2013}
	 */
	public SQLiteDatabase open()
	{
		return getWritableDatabase();
	}
}
