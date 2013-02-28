package com.NASAccessApp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity
{

	Button bt_insert;
	Button bt_select;
	ConnexionBDD database;
	String url = "http://192.168.1.46:8080/cgi-bin/sys/sysRequest.cgi?subfunc=sys_logs&conncsv=1&count=0.808290574233979&sid=7duduham";
	
    /** Called when the activity is first created. */
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