package fr.angers.univ.info.m2.acdi.reseau.models;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Random;

public class ClePublique implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 8648794995456351556L;
	private BigInteger n;
	private BigInteger e;
	private BigInteger p;
	private BigInteger q;

	public ClePublique() {
		Random rand = new Random();
		BigInteger p = BigInteger.probablePrime((int) Math.pow(2, 11), rand);
		BigInteger q = BigInteger.probablePrime((int) Math.pow(2, 11), rand);
		while (p.compareTo(q) == 0) {
			p = BigInteger.probablePrime((int) Math.pow(2, 11), rand);
		}
		// BigInteger p = new BigInteger(16, rand);
		// BigInteger q;
		/*
		 * do { q = new BigInteger(16, rand); } while (p == q);
		 */
		BigInteger m = (p.subtract(BigInteger.ONE).multiply((q.subtract(BigInteger.ONE))));
		BigInteger n = p.multiply(q);
		BigInteger e = BigInteger.probablePrime((int) Math.pow(2, 11 / 2), rand);
		/*
		 * do { e = new BigInteger(8, rand); } while (e == m || !Utils.estImpair(e) &&
		 * !e.gcd(m).equals(BigInteger.ONE));
		 */

		while (m.gcd(e).compareTo(new BigInteger("1")) != 0) {
			e = BigInteger.probablePrime((int) Math.pow(2, 11 / 2), rand);
		}
		/*
		 * p = BigInteger.valueOf(53); q = BigInteger.valueOf(97); n =
		 * BigInteger.valueOf(5141); m = BigInteger.valueOf(4992); e =
		 * BigInteger.valueOf(7);
		 */
		this.n = n;
		this.e = e;
		this.p = p;
		this.q = q;
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

	public BigInteger getP() {
		return p;
	}

	public void setP(BigInteger n) {
		this.p = p;
	}

	public BigInteger getQ() {
		return q;
	}

	public void setQ(BigInteger Q) {
		this.q = q;
	}

	@Override
	public String toString() {
		return "ClePublique{" + "n=" + n + ", e=" + e + '}';
	}

}
