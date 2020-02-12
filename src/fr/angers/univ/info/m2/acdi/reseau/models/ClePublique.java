package fr.angers.univ.info.m2.acdi.reseau.models;

import java.math.BigInteger;
import java.util.Random;

import fr.angers.univ.info.m2.acdi.reseau.utils.Utils;

public class ClePublique {

	private BigInteger n;
	private BigInteger e;

	public ClePublique() {
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

		/*p = BigInteger.valueOf(53);
		q = BigInteger.valueOf(97);
		n = BigInteger.valueOf(5141);
		m = BigInteger.valueOf(4992);
		e = BigInteger.valueOf(7);*/

		this.n = n;
		this.e = e;

	}

	public BigInteger getN() {
		return n;
	}

	public void setN(BigInteger n) {
		this.n = n;
	}

	public BigInteger getE() {
		return e;
	}

	public void setE(BigInteger e) {
		this.e = e;
	}

}
