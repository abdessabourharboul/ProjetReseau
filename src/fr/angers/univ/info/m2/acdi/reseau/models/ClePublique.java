package fr.angers.univ.info.m2.acdi.reseau.models;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Random;

import fr.angers.univ.info.m2.acdi.reseau.utils.Utils;

public class ClePublique implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 8648794995456351556L;
    private BigInteger n;
    private BigInteger e;
    private BigInteger p;
    private BigInteger q;
    private static final int BIT_LENGTH = 6;

    public ClePublique() {
        Random rand = new Random();
        BigInteger p = BigInteger.probablePrime((int) Math.pow(2, BIT_LENGTH), rand);
        BigInteger q = BigInteger.probablePrime((int) Math.pow(2, BIT_LENGTH), rand);
        while (p.compareTo(q) == 0) {
            p = BigInteger.probablePrime((int) Math.pow(2, BIT_LENGTH), rand);
        }
        BigInteger m = (p.subtract(BigInteger.ONE).multiply((q.subtract(BigInteger.ONE))));
        BigInteger n = p.multiply(q);
        BigInteger e = BigInteger.probablePrime((int) Math.pow(2, BIT_LENGTH / 2), rand);
        while (m.gcd(e).compareTo(new BigInteger("1")) != 0) {
            e = BigInteger.probablePrime((int) Math.pow(2, BIT_LENGTH / 2), rand);
        }
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
