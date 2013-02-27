/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.NASAccessApp;

import android.database.Cursor;

/**
 *
 * @author Sylvain
 */
public class Record {

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

	// ####################################################################
	// ###                         CONSTRUCTEUR                         ###
	// ####################################################################

	Record(){}

	/**
	 * Crée une instance de Record à partir de données récupérés en base de données
	 * 
	 * @param Cursor c
	 * @author Sylvain {28/02/2013}
	 */
	Record(Cursor c) {
		c.moveToFirst();
		
		number    = c.getInt(RecordHandler.NUM_FIELD_NUMBER);
		type      = c.getString(RecordHandler.NUM_FIELD_TYPE);
		date      = c.getString(RecordHandler.NUM_FIELD_DATE);
		users     = c.getString(RecordHandler.NUM_FIELD_USER);
		source    = c.getString(RecordHandler.NUM_FIELD_SOURCE);
		host      = c.getString(RecordHandler.NUM_FIELD_HOST);
		ressource = c.getString(RecordHandler.NUM_FIELD_RESSOURCE);
		protocol  = c.getString(RecordHandler.NUM_FIELD_PROTOCOL);
		action    = c.getString(RecordHandler.NUM_FIELD_ACTION);
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
	
	// ####################################################################
	// ###                      METHODES D'INSTANCE                     ###
	// ####################################################################
	
	@Override
	public String toString()
	{
		return "[" + this.getDate() + "] " + this.getNumber();
	}
}
