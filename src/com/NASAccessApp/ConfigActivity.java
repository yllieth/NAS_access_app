package com.NASAccessApp;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.EditTextPreference;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;

/**
 * Gère la configuration de l'application.
 *
 * La configuration de l'application utilise <code>SharedPreference</code>.
 * 
 * <h1>Edition</h1>
 * <h1>Accès</h1>
 * <h1>Eléments configurables</h1>
 * - Connexion
 * 		- <code>pref_host_address_key</code> : EditTextPreference
 * 
 * @author Sylvain 08/07/2013
 */
public class ConfigActivity extends PreferenceActivity
							implements OnSharedPreferenceChangeListener
{

	// ##################################################################
	// ###                    VARIABLES D'INSTANCE                    ###
	// ##################################################################
	
	/**
	 * Identifiant du champ permettant de définir l'adresse du NAS à observer.
	 * Cette chaine est définie dans <code>res/strings</code>.
	 */
	private String pref_host_address_key;
	
	/** 
	 * Elément du layout définit dans <code>res/layout/configuration.xml</code> 
	 * permettant de définir l'adresse du NAS à observer.
	 */
	private EditTextPreference pref_host_address;

	// ##################################################################
	// ###                    FONCTIONS PUBLIQUES                     ###
	// ##################################################################
	
	/** 
	 * Fonction exécutée lors d'une modification de la configuration.
	 *
	 * - Modification de l'adresse du NAS à observer : 
	 *		on modifie l'attribut <code>summary</code> du EditTextPreference
	 *		pour y indiquer l'adresse que l'on vient de définir.
	 * 
	 * @important Cette fonction nécessite que la classe implémente OnSharedPreferenceChangeListener
	 * @author Sylvain 08/07/2013
	 */
	public void onSharedPreferenceChanged(SharedPreferences configuration, 
										  String key)
	{
		if (key.equals(pref_host_address_key)) {
			pref_host_address.setSummary(
				getString(R.string.pref_host_address_summary) + " (" +
				configuration.getString(pref_host_address_key, "") + ")"
			);
		}
	}
	
	/**
	 * Fonction exécutée à la création de l'Activity.
	 *
	 * Elle :
	 * - fait le lien entre le code métier et la vue (<code>res/layout/configuration.xml</code>)
	 * - initialise les variables d'instance de manière à pouvoir y accéder dans les autres fonctions
	 *
	 * @author Sylvain 08/07/2013
	 */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.layout.configuration);
		
		// initialisation
		pref_host_address_key = getString(R.string.pref_host_address_key);
		pref_host_address = (EditTextPreference) ConfigActivity.this.findPreference("conf_host_address");
    }
}

