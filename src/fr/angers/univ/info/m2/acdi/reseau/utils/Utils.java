package fr.angers.univ.info.m2.acdi.reseau.utils;

import java.math.BigInteger;

public class Utils {
	public static boolean estImpair(BigInteger val) {
	    return !val.mod(new BigInteger("2")).equals(BigInteger.ZERO);
	}
}