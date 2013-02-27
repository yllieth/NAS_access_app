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
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		
		
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
    }
	
	public void insert()
	{
		try{
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
		} catch (Exception e){
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
		}
	}
	
	public void select()
	{
		try{
			Record check = new Record(this);
			check.get(760000);

			if(check instanceof Record){
				Toast.makeText(this, check.toString(), Toast.LENGTH_LONG).show();
			}
		} catch (Exception e) {
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
		}
	}
}