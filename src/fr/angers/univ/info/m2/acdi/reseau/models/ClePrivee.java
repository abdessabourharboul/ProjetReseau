package fr.angers.univ.info.m2.acdi.reseau.models;

import java.math.BigInteger;

public class ClePrivee {

	private BigInteger n;
	private BigInteger u;

	public ClePrivee(ClePublique clePublique) {
		// Appliquer l'algorithme d'euclid
		BigInteger m = (clePublique.getP().subtract(BigInteger.ONE).multiply((clePublique.getQ().subtract(BigInteger.ONE))));
		BigInteger r0 = clePublique.getE();
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
			ui = u0.subtract(r0.divide(r1).multiply(u1));
			u0 = u1;
			u1 = ui;
			vi = v0.subtract(r0.divide(r1).multiply(v1));
			v0 = v1;
			v1 = vi;
			r0 = r1;
			r1 = ri;
		}
		BigInteger k = BigInteger.ZERO;
		while (u0.compareTo(new BigInteger("2")) != 1 || u0.compareTo(m) != -1) {
			k = k.subtract(BigInteger.ONE);
			u0 = u0.subtract(k.multiply(m));
		}
		this.n = clePublique.getN();
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