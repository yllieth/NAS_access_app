/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.NASAccessApp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

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
			
	ConnexionBDD connexion;

	// ####################################################################
	// ###                         CONSTRUCTEUR                         ###
	// ####################################################################

	RecordHandler(ConnexionBDD connexion) {
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
	
	/**
	 * Renvoie la liste des colonnes de la table "access".
	 * 
	 * @return String[]
	 * @author Sylvain {28/02/2013}
	 */
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
	
	/**
	 * Transforme un record en ContentValues pour l'insertion en base de données
	 * 
	 * @param Record record
	 * @return ContentValues
	 * @author Sylvain {28/02/2013}
	 */
	private ContentValues format(Record record)
	{
		ContentValues values = new ContentValues();
		
		values.put(FIELD_NUMBER, record.getNumber());
		values.put(FIELD_TYPE, record.getType());
		values.put(FIELD_DATE, record.getDate());
		values.put(FIELD_USER, record.getUsers());
		values.put(FIELD_SOURCE, record.getSource());
		values.put(FIELD_HOST, record.getHost());
		values.put(FIELD_RESSOURCE, record.getRessource());
		values.put(FIELD_PROTOCOL, record.getProtocol());
		values.put(FIELD_ACTION, record.getAction());
		
		return values;
	}
	
	/**
	 * Enregistre le Record en base de données.
	 * 
	 * @param record
	 * @return Boolean
	 * @author Sylvain {28/02/2013}
	 */
	public Boolean save(Record record)
	{
		SQLiteDatabase bdd = connexion.open();
		long insert = bdd.insert(TABLE_NAME, null, format(record));		
		connexion.close();
		
		return (insert != -1) ? true : false;
	}
	
	/**
	 * Renvoie un Record précis à partir de son identifiant.
	 * 
	 * @param int number
	 * @return Record
	 * @author Sylvain {28/02/2013}
	 */
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
	
	/**
	 * Renvoie l'ensemble des Record contnus dans la table "access"
	 * 
	 * @return List
	 * @author Sylvain {28/02/2013}
	 */
	public List<Record> getAll()
	{
		List<Record> collection = new ArrayList<Record>();
		
		SQLiteDatabase bdd = connexion.open();
		Cursor c = bdd.rawQuery("SELECT * FROM " + TABLE_NAME, null);
		if (c.moveToFirst()){
			do {
				collection.add(new Record(c));
			} while (c.moveToNext());
		}
		
		return collection;
	}
	
	/**
	 * Renvoie le nombre de Record de la table "access"
	 * 
	 * @return int
	 * @author Sylvain {28/02/2013}
	 */
	public int count()
	{
		return getAll().size();
	}
}
