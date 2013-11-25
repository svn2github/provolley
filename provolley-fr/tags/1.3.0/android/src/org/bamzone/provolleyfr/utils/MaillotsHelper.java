package org.bamzone.provolleyfr.utils;

import org.bamzone.provolleyfr.ProVolley;
import org.bamzone.provolleyfr.provider.ResourcesProvider;

import android.graphics.drawable.Drawable;

public class MaillotsHelper {

	public static Drawable[] getProbableMaillots(ResourcesProvider provider, String codeDomicile, String codeExterieur) {
		Drawable[] drawableArray = new Drawable[2];
		
		// Par défaut, ce sont les maillots officiels
		String dom=ProVolley.LIBELLE_MAILLOT_DOMICILE;
		String ext=ProVolley.LIBELLE_MAILLOT_EXTERIEUR;
		
		// En cas de couleurs trop proches, l'équipe se déplaçant prend le deuxième jeu
		if (provider.getMaillotCouleur(codeDomicile, dom).equals(provider.getMaillotCouleur(codeExterieur, ext))) {
			ext=ProVolley.LIBELLE_MAILLOT_DOMICILE;
		}
		
		// Si ca marche toujours pas, c'est l'équipe recevant qui prend son deuxième jeu
		if (provider.getMaillotCouleur(codeDomicile, dom).equals(provider.getMaillotCouleur(codeExterieur, ext))) {
			dom=ProVolley.LIBELLE_MAILLOT_EXTERIEUR;
		}
		
		drawableArray[0]=provider.getMaillot(codeDomicile, dom);
		drawableArray[1]=provider.getMaillot(codeExterieur, ext);
		return drawableArray; 
	}
}
