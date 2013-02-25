/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.NASAccessApp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

/**
 *
 * @author Sylvain
 */
public class Record {

	// ####################################################################
	// ###                          CONSTANTES                          ###
	// ####################################################################
	
	private static final int    DATABASE_VERSION = 2;
	private static final String DATABASE_NAME = "NAS.db";
	private static final String DATABASE_TABLE_NAME = "access";

	// ####################################################################
	// ###                          VARIABLES                           ###
	// ####################################################################
	
	private int    number;		// ex: 849984
	private String type;		// ex: "Information"
	private String date;		// ex: "2013-02-23 18:18:16"
	private String users;		// ex: "sylvain"
	private String source;		// ex: "192.168.1.39"
	private String host;		// ex: "sylvain-pc"
	private String ressource;	// ex: "Multimedia/FILMS/[Magie] Harry Potter VII - Les Reliques de la Mort - Partie 1.avi"
	private String protocol;	// ex: "SAMBA"
	private String action;		// ex: "Read"
	
	private Context context;

	// ####################################################################
	// ###                         CONSTRUCTEUR                         ###
	// ####################################################################

	Record(){}
	
	Record(Context context)
	{
		this.context = context;
	}
	
	// ####################################################################
	// ###                          ACCESSEURS                          ###
	// ####################################################################
	
	
	public int getNumber() {
		return number;
	}

	public Record setNumber(int number) {
		this.number = number;
		return this;
	}

	public String getType() {
		return type;
	}

	public Record setType(String type) {
		this.type = type;
		return this;
	}

	public String getDate() {
		return date;
	}

	public Record setDate(String date) {
		this.date = date;
		return this;
	}

	public String getUsers() {
		return users;
	}

	public Record setUsers(String users) {
		this.users = users;
		return this;
	}

	public String getSource() {
		return source;
	}

	public Record setSource(String source) {
		this.source = source;
		return this;
	}

	public String getHost() {
		return host;
	}

	public Record setHost(String host) {
		this.host = host;
		return this;
	}

	public String getRessource() {
		return ressource;
	}

	public Record setRessource(String ressource) {
		this.ressource = ressource;
		return this;
	}

	public String getProtocol() {
		return protocol;
	}

	public Record setProtocol(String protocol) {
		this.protocol = protocol;
		return this;
	}

	public String getAction() {
		return action;
	}

	public Record setAction(String action) {
		this.action = action;
		return this;
	}
	
	public String toString()
	{
		return "[" + this.getDate() + "] " + this.getNumber();
	}
	
	// ####################################################################
	// ###                      METHODES D'INSTANCE                     ###
	// ####################################################################
	
	public void save()
	{
		StorageAccess BDD = new StorageAccess(context);
		BDD.open();
		BDD.getInstance().insert(DATABASE_TABLE_NAME, null, createContentValue());
		BDD.close();
	}
	
	public Record get(int number)
	{
		StorageAccess BDD = new StorageAccess(context);
		BDD.open();
		try {
			Cursor c = BDD.getInstance().query(
					DATABASE_TABLE_NAME,		// table
					BDD.getColumns(),			// colonnes
					StorageAccess.FIELD_NUMBER,	// clef
					null, null, null, null		// args, groupBy, having, orderBy
			);
			populate(c);
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		BDD.close();
		
		
		return this;
	}
	
	private ContentValues createContentValue()
	{
		ContentValues values = new ContentValues();
		
		values.put("INT", this.getNumber());
		values.put("STRING", this.getType());
		values.put("STRING", this.getDate().toString());
		values.put("STRING", this.getUsers());
		values.put("STRING", this.getSource());
		values.put("STRING", this.getHost());
		values.put("STRING", this.getRessource());
		values.put("STRING", this.getProtocol());
		values.put("STRING", this.getAction());
		
		return values;
	}
	
	private void populate(Cursor c)
	{
		c.moveToFirst();
		
		this.setNumber(c.getInt(StorageAccess.NUM_FIELD_NUMBER));
		this.setType(c.getString(StorageAccess.NUM_FIELD_TYPE));
		this.setDate(c.getString(StorageAccess.NUM_FIELD_DATE));
		this.setUsers(c.getString(StorageAccess.NUM_FIELD_USER));
		this.setSource(c.getString(StorageAccess.NUM_FIELD_SOURCE));
		this.setHost(c.getString(StorageAccess.NUM_FIELD_HOST));
		this.setRessource(c.getString(StorageAccess.NUM_FIELD_RESSOURCE));
		this.setProtocol(c.getString(StorageAccess.NUM_FIELD_PROTOCOL));
		this.setAction(c.getString(StorageAccess.NUM_FIELD_ACTION));
	}
}
