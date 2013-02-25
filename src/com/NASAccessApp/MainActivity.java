package com.NASAccessApp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		
		// ecriture en base
		Record test = new Record(this);
		test.setNumber(760000)
			.setType("Information")
			.setDate("2012-11-13 23:02:22")
			.setUsers("Sylvain")
			.setSource("192.168.1.39")
			.setHost("sylvain-pc")
			.setRessource("Multimedia/MUSIQUES/Lynda LEMAY/Lynda Lemay - Blessée/16-Lynda Lemay _ Farce d'oreille.mp3")
			.setProtocol("SAMBA")
			.setAction("Read")
			.save();
		
		// lecture
		Record check = new Record(this);
		check.get(760000);
		
		// affichage
		if(check.getClass().toString().equals("Record")){
			Toast.makeText(this, check.toString(), Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(this, "Ce livre n'existe pas dans la BDD", Toast.LENGTH_LONG).show();
		}
    }
}