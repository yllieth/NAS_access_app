/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.NASAccessApp;

import java.net.Inet4Address;
import java.util.Date;

/**
 *
 * @author Sylvain
 */
public class Record {

	// ####################################################################
	// ###                          VARIABLES                           ###
	// ####################################################################
	
	int    Number;		// ex: 849984
	String Type;		// ex: "Information"
	Date   Date;		// ex: "2013-02-23 18:18:16"
	String Users;		// ex: "sylvain"
	String Source;		// ex: "192.168.1.39"
	String Host;		// ex: "sylvain-pc"
	String Ressource;	// ex: "Multimedia/FILMS/[Magie] Harry Potter VII - Les Reliques de la Mort - Partie 1.avi"
	String Protocol;	// ex: "SAMBA"
	String Action;		// ex: "Read"


	// ####################################################################
	// ###                          ACCESSEURS                          ###
	// ####################################################################
	
	
	public int getNumber() {
		return Number;
	}

	public Record setNumber(int Number) {
		this.Number = Number;
		return this;
	}

	public String getType() {
		return Type;
	}

	public Record setType(String Type) {
		this.Type = Type;
		return this;
	}

	public Date getDate() {
		return Date;
	}

	public Record setDate(Date Date) {
		this.Date = Date;
		return this;
	}

	public String getUsers() {
		return Users;
	}

	public Record setUsers(String Users) {
		this.Users = Users;
		return this;
	}

	public String getSource() {
		return Source;
	}

	public Record setSource(String Source) {
		this.Source = Source;
		return this;
	}

	public String getHost() {
		return Host;
	}

	public Record setHost(String Host) {
		this.Host = Host;
		return this;
	}

	public String getRessource() {
		return Ressource;
	}

	public Record setRessource(String Ressource) {
		this.Ressource = Ressource;
		return this;
	}

	public String getProtocol() {
		return Protocol;
	}

	public Record setProtocol(String Protocol) {
		this.Protocol = Protocol;
		return this;
	}

	public String getAction() {
		return Action;
	}

	public Record setAction(String Action) {
		this.Action = Action;
		return this;
	}

	// ####################################################################
	// ###                      METHODES D'INSTANCE                     ###
	// ####################################################################
	
	public boolean save()
	{
		return Storage.save(this);
	}
}
