import fr.angers.univ.info.m2.acdi.reseau.models.ClePrivee;
import fr.angers.univ.info.m2.acdi.reseau.models.ClePublique;
import fr.angers.univ.info.m2.acdi.reseau.services.Chiffrement;
import fr.angers.univ.info.m2.acdi.reseau.services.Dechiffrement;

public class Main {

	public static void main(String[] args) {
		String testChiffrement = "Bonjour!";
		ClePublique clePublique = new ClePublique();
		ClePrivee clePrivee = new ClePrivee();

		Chiffrement ch = new Chiffrement(clePublique, testChiffrement);
		Dechiffrement dch = new Dechiffrement(clePrivee, ch.getSi());
		System.out.println(testChiffrement);
		System.out.println("Apres dechifrement " + dch.getChaineDechifree());

	}

}
