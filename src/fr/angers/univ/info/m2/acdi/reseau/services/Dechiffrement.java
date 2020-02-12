package fr.angers.univ.info.m2.acdi.reseau.services;

import java.math.BigInteger;
import java.util.ArrayList;

import fr.angers.univ.info.m2.acdi.reseau.models.ClePrivee;

public class Dechiffrement {

	private String chaineDechifree;

	public Dechiffrement(ClePrivee clePrivee, ArrayList<BigInteger> Si) {
		ArrayList<BigInteger> Sdechifre = new ArrayList<BigInteger>();
		for (BigInteger s : Si) {
			Sdechifre.add(s.modPow(clePrivee.getU(), clePrivee.getN()));
		}
		String chaineDechifree = "";
		for (BigInteger s : Sdechifre) {
			int charactere = Integer.parseInt(s.toString());
			chaineDechifree += (char) charactere;
		}

		System.out.println("Cle privee (N) : " + clePrivee.getN());
		System.out.println("Cle privee (U) : " + clePrivee.getU());
		System.out.println("-------------");
		System.out.println("Code Chifre " + Si);
		System.out.println("Code déchifrée : " + Sdechifre);
		System.out.println("Chaine dechifree " + chaineDechifree);
		this.setChaineDechifree(chaineDechifree);
	}

	public String getChaineDechifree() {
		return chaineDechifree;
	}

	public void setChaineDechifree(String chaineDechifree) {
		this.chaineDechifree = chaineDechifree;
	}

}
