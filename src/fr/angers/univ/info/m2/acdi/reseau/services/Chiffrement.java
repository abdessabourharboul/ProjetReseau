package fr.angers.univ.info.m2.acdi.reseau.services;

import java.math.BigInteger;
import java.util.ArrayList;

import fr.angers.univ.info.m2.acdi.reseau.models.ClePublique;
import java.io.Serializable;

public class Chiffrement implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -3103667553045584586L;

    private ArrayList<BigInteger> Si;

    public Chiffrement(ClePublique clePublique, String chaineAChifre) {
        ArrayList<BigInteger> S = new ArrayList();
        for (int i = 0; i < chaineAChifre.length(); i++) {
            char c = chaineAChifre.charAt(i);
            int ascii = (int) c;
            S.add(new BigInteger(String.valueOf(ascii)));
        }
        Si = new ArrayList();
        for (BigInteger s : S) {
            Si.add(s.modPow(clePublique.getE(), clePublique.getN()));
        }

        // déplacer ces messages car ils s'affichent à chaque fois que l'on fait cette construction
        /*System.out.println("Cle publique (N) : " + clePublique.getN());
        System.out.println("Cle publique (E) : " + clePublique.getE());
        System.out.println("-------------");*/
        System.out.println("Code ASCII de la chaine " + S);
        System.out.println("Code Chiffre depuis code ASCII" + Si);
    }

    public ArrayList<BigInteger> getSi() {
        return Si;
    }

    public void setSi(ArrayList<BigInteger> si) {
        Si = si;
    }

}
