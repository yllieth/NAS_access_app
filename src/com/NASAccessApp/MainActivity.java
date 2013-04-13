package com.NASAccessApp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity
{

	Button bt_insert;
	Button bt_select;
	ConnexionBDD database;
	String queryUrl = "/cgi-bin/sys/sysRequest.cgi?subfunc=sys_logs&conncsv=1&count=0.808290574233979&sid=7duduham";
	
    /**
	 * Cette méthode est appelée à la création de l'activité (l'ouverture de l'application)
	 * 
	 * @param Bundle savedInstanceState
	 * @return void
	 * @author Sylvain 
	 */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		
		database = new ConnexionBDD(this);
		
		bt_insert = (Button) findViewById(R.id.bt_insert);
        bt_select = (Button) findViewById(R.id.bt_select);
		
		bt_insert.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
				insert();
        	}
        });
		bt_select.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
				select();
        	}
        });
		
		database.close();
    }
	
	/**
	 * Cette méthode est appelée lors de l'appuie sur le bouton "menu" du périphérique.
	 * 
	 * @see    http://www.tutomobile.fr/faire-des-menus-et-sous-menus-tutoriel-android-n%C2%B012/27/07/2010/
	 * @param  Menu menu
	 * @return Boolean 
	 * @author Sylvain {01/03/2013}
	 */
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();		//Création d'un MenuInflater qui va permettre d'instancier un Menu XML en un objet Menu
        inflater.inflate(R.layout.menu, menu);			//Instanciation du menu XML spécifier en un objet Menu
 
        // Il n'est pas possible de modifier l'icône d'entête du sous-menu 
		// via le fichier XML on le fait donc en JAVA
    	menu.getItem(0).getSubMenu().setHeaderIcon(R.drawable.options);
 
        return true;
     }
	
	/**
	 * Cette méthode est appelée lors du clic sur un élément du menu.
	 * 
	 * @param item
	 * @return Boolean
	 * @author Sylvain {01/03/2013}
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		//On regarde quel item a été cliqué grâce à son id et on déclenche une action
		switch (item.getItemId()) {
			case R.id.option:
				Toast.makeText(this, "Option", Toast.LENGTH_SHORT).show();
				return true;
			case R.id.insert:
				Toast.makeText(this, "Insertion ...", Toast.LENGTH_SHORT).show();
				insert();
				return true;
			case R.id.get:
				Toast.makeText(this, "Récupération ...", Toast.LENGTH_SHORT).show();
				select();
				return true;
			case R.id.getAll:
				Toast.makeText(this, "Récupération ...", Toast.LENGTH_SHORT).show();
				return true;
			case R.id.count:
				Toast.makeText(this, "Comptage ...", Toast.LENGTH_SHORT).show();
				return true;
			case R.id.quitter:
				finish();
				return true;
		}
		return false;}
	
	public void insert()
	{
		try{
			Record test = new Record();
			test.setNumber(760000)
				.setType("Information")
				.setDate("2012-11-13 23:02:22")
				.setUsers("Sylvain")
				.setSource("192.168.1.39")
				.setHost("sylvain-pc")
				.setRessource("Multimedia/MUSIQUES/Lynda LEMAY/Lynda Lemay - Blessée/16-Lynda Lemay _ Farce d'oreille.mp3")
				.setProtocol("SAMBA")
				.setAction("Read");
			database.getTableRecord().save(test);
		} catch (Exception e){
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
		}
	}
	
	public void select()
	{
		try{
			Record check = database.getTableRecord().get(760000);

			if(check instanceof Record){
				Toast.makeText(this, check.toString(), Toast.LENGTH_LONG).show();
			}
		} catch (Exception e) {
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
		}
	}
}