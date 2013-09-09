package com.NASAccessApp;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.preference.PreferenceCategory;
import android.preference.PreferenceActivity;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.util.Log;
import java.util.Map;
import java.util.HashMap;

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
	 * Afin de pouvoir mettre à jour dynamiquement le summary, on enregistre
	 * la phrase initiale afin de pouvoir composer le summary comme suit :
	 * - <phrase initiale> (<valeur actuelle>)
	 *
	 * > modifiée dans registerPreference()
	 * > lue dans updateSummary
	 */
	private Map<String, String> summaries = new HashMap <String, String>();

	// ##################################################################
	// ###                    FONCTIONS PUBLIQUES                     ###
	// ##################################################################
	
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
		
		registerPreference("pref_host_address", getString(R.string.pref_host_address_summary));
    }
	
	@Override
	public void onResume() {
		super.onResume();
		
		// set up a listener whenever a key changes
		getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		
		// unregister the listener whenever a key changes
		getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
	}
	
	/**
	 * Appelée lors de la création de l'activité pour initialiser le summary.
	 * 
	 * En principe, le summary est une phrase prédéfinie. Car, en principe, 
	 * on n'affiche pas la valeur de la préférence dans le summary. Or c'est 
	 * le cas ici !
	 * 
	 * @author Sylvain{09/09/2013}
	 */
	private void initSummary(Preference p) {
		if (p instanceof PreferenceCategory) {
			PreferenceCategory pCat = (PreferenceCategory) p;
			for (int i = 0 ; i < pCat.getPreferenceCount() ; i++) {
				initSummary(pCat.getPreference(i));
			}
		} else {
			updateSummary(p);
		}
	}
	
	/**
	 * Enregistre la préférence et permet surtout de gérer les summary 
	 * qui vont devoir contenir la valeur de la préférence.
	 * 
	 * @example registerPreference("pref_host_address", getString(R.string.pref_host_address_summary))
	 * Le premier paramètre est l'identifiant de la préférence
	 * Le second parametre est la phrase initiale du summary
	 * 
	 * @author Sylvain{09/09/2013}
	 */
	private void registerPreference(String key, String baseSummary) {
		Preference p = findPreference(key);
		p.setSummary(baseSummary);
		summaries.put(p.getKey(), baseSummary);
		
		initSummary(p);
	}
	
	/**
	 * Met à jour le summary d'une préférence pour indiquer la valeur
	 * définie par l'utilisateur. Pour des raisons pratiques, cette
	 * mise à jour n'est faite que lorsque la valeur de l'utilisateur
	 * est une chaine de caractères qui a au moins 1 caractère.
	 * 
	 * @author Sylvain{09/09/2013}
	 */
	private void updateSummary(Preference p) {
		String oldSummary = summaries.get(p.getKey());
		String newSummary = "";
		//Log.i("updateSummary", "AVANT : " + oldSummary);
		
		if (p instanceof ListPreference) {
			ListPreference listPreference = (ListPreference) p;
			newSummary = (String) listPreference.getEntry();
		}
		
		if (p instanceof EditTextPreference) {
			EditTextPreference editTextPreference = (EditTextPreference) p;
			newSummary = editTextPreference.getText();
		}

		if (newSummary.length() > 0) {
			newSummary = oldSummary + " (" + newSummary + ")";
			//Log.i("updateSummary", "APRES : " + newSummary);
			p.setSummary(newSummary);
		}
	}
	
	/** 
	 * Fonction exécutée lors d'une modification de la configuration.
	 *
	 * - Modification de l'adresse du NAS à observer : 
	 *		on modifie l'attribut <code>summary</code> du EditTextPreference
	 *		pour y indiquer l'adresse que l'on vient de définir.
	 * 
	 * @important Cette fonction nécessite que la classe implémente OnSharedPreferenceChangeListener
	 * @author Sylvain{08/07/2013}
	 */
	public void onSharedPreferenceChanged(SharedPreferences configuration, String key) {
		initSummary(findPreference(key));
	}
}

