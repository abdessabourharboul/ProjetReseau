package fr.angers.univ.info.m2.acdi.reseau.models;

import java.math.BigInteger;
import java.util.Random;

import fr.angers.univ.info.m2.acdi.reseau.utils.Utils;

public class ClePrivee {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6531224431955554856L;
	private BigInteger n;
	private Integer e;

	public ClePrivee() {
		Random rand = new Random();
		BigInteger p = new BigInteger(128, rand);
		BigInteger q;
		do {
			q = new BigInteger(128, rand);
		} while (p == q);
		BigInteger m = (p.subtract(BigInteger.ONE).multiply((q.subtract(BigInteger.ONE))));
		BigInteger n = p.multiply(q);

		BigInteger e;
		do {
			e = new BigInteger(32, rand);
		} while (e == m || !Utils.estImpair(e) && !e.gcd(m).equals(BigInteger.ONE));

		System.out.println(p);
		System.out.println(q);
		System.out.println(m);
		System.out.println(n);
		System.out.println(e);

	}

}
