package fr.angers.univ.info.m2.acdi.reseau.models;

import java.math.BigInteger;
import java.util.Random;

import fr.angers.univ.info.m2.acdi.reseau.utils.Utils;

public class ClePrivee {

	private BigInteger n;
	private BigInteger u;

	public ClePrivee() {
		Random rand = new Random();
		BigInteger p = new BigInteger(4, rand);
		BigInteger q;
		do {
			q = new BigInteger(4, rand);
		} while (p == q);
		BigInteger m = (p.subtract(BigInteger.ONE).multiply((q.subtract(BigInteger.ONE))));
		BigInteger n = p.multiply(q);

		BigInteger e;
		do {
			e = new BigInteger(2, rand);
		} while (e == m || !Utils.estImpair(e) && !e.gcd(m).equals(BigInteger.ONE));

		/*p = BigInteger.valueOf(53);
		q = BigInteger.valueOf(97);
		n = BigInteger.valueOf(5141);
		m = BigInteger.valueOf(4992);
		e = BigInteger.valueOf(7);*/

		// Appliquer l'algorithme d'euclid
		BigInteger r0 = e;
		BigInteger r1 = m;
		BigInteger u0 = BigInteger.ONE;
		BigInteger u1 = BigInteger.ZERO;
		BigInteger v0 = BigInteger.ZERO;
		BigInteger v1 = BigInteger.ONE;

		BigInteger ri = new BigInteger("-1");
		BigInteger ui = new BigInteger("-1");
		BigInteger vi = new BigInteger("-1");

		while (ri.compareTo(BigInteger.ZERO) != 0) {
			ri = r0.subtract(r0.divide(r1).multiply(r1));

			// System.out.println(ri);

			ui = u0.subtract(r0.divide(r1).multiply(u1));
			u0 = u1;
			u1 = ui;
			// System.out.println(ui);

			vi = v0.subtract(r0.divide(r1).multiply(v1));
			v0 = v1;
			v1 = vi;
			// System.out.println(vi);
			r0 = r1;
			r1 = ri;
		}

		BigInteger k = BigInteger.ZERO;

		while (u0.compareTo(new BigInteger("2")) != 1 || u0.compareTo(m) != -1) {
			k = k.subtract(BigInteger.ONE);
			u0 = u0.subtract(k.multiply(m));
		}

		/*
		 * System.out.println(r0); System.out.println(u0); System.out.println(v0);
		 */

		this.n = n;
		this.u = u0;

	}

	public BigInteger getN() {
		return n;
	}

	public void setN(BigInteger n) {
		this.n = n;
	}

	public BigInteger getU() {
		return u;
	}

	public void setU(BigInteger u) {
		this.u = u;
	}

}