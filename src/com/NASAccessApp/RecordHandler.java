/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.NASAccessApp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 *
 * @author Sylvain
 */
public class RecordHandler {

	// ####################################################################
	// ###                          CONSTANTES                          ###
	// ####################################################################
	
	public static final String TABLE_NAME = "access";
	
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
			
	// ####################################################################
	// ###                          VARIABLES                           ###
	// ####################################################################
			
	DatabaseConnexion connexion;

	// ####################################################################
	// ###                         CONSTRUCTEUR                         ###
	// ####################################################################

	RecordHandler(DatabaseConnexion connexion) {
		this.connexion = connexion;
	}
	
	public static String createTableQuery()
	{
		return "CREATE TABLE " + TABLE_NAME + " (" 
				+ FIELD_NUMBER    + " INTEGER PRIMARY KEY AUTOINCREMENT, " 	// ex: 849984
				+ FIELD_TYPE      + " TEXT, " 	// ex: "Information"
				+ FIELD_DATE      + " TEXT, "	// ex: "2013-02-23 18:18:16"
				+ FIELD_USER      + " TEXT, " 	// ex: "sylvain"
				+ FIELD_SOURCE    + " TEXT, " 	// ex: "192.168.1.39"
				+ FIELD_HOST      + " TEXT, " 	// ex: "sylvain-pc"
				+ FIELD_RESSOURCE + " TEXT, " 	// ex: "Multimedia/FILMS/[Magie] Harry Potter VII - Les Reliques de la Mort - Partie 1.avi"
				+ FIELD_PROTOCOL  + " TEXT, " 	// ex: "SAMBA"
				+ FIELD_ACTION    + " TEXT"		// ex: "Read"
				+ 
		");";
	}
	
	public static String dropTableQuery()
	{
		return "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
	}
	
	// ####################################################################
	// ###                      METHODES D'INSTANCE                     ###
	// ####################################################################
	
	private String[] getColumns()
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
	
	public Boolean save(Record record)
	{
		SQLiteDatabase bdd = connexion.open();
		long insert = bdd.insert(TABLE_NAME, null, record.formatValues());		
		connexion.close();
		
		return (insert != -1) ? true : false;
	}
	
	public Record get(int number)
	{
		SQLiteDatabase bdd = connexion.open();
		Cursor c = bdd.query(
				TABLE_NAME,				// table
				this.getColumns(),		// colonnes
				FIELD_NUMBER,			// clef
				null, null, null, null	// args, groupBy, having, orderBy
		);
		Record record = new Record(c);
		connexion.close();
		
		return record;
	}
}
