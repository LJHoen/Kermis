package kermis;

public abstract class Attractie {
	String naam;
	double prijs;
	int oppervlakte;
	double omzetPreTax;
	double omzetPostTax;
	int kaartjes;
	
	Attractie() {
		omzetPreTax = 0;
		omzetPostTax = 0;
		kaartjes = 0;
	}
	
	double draaien() throws Exception {
		System.out.println("De attractie " + naam + " draait \n");
		omzetPreTax += prijs;
		kaartjes++;
		return prijs;
	}
	
	String getNaam() {
		return naam;
	}
	
	int getKaartjes() {
		return kaartjes;
	}
	
	int getOppervlakte() {
		return oppervlakte;
	}
	
	double getPrijs() {
		return prijs;
	}
	
	double getOmzetPreTax() {
		return omzetPreTax;
	}
	
	double getOmzetPostTax() {
		return omzetPostTax;
	}
	
}

abstract class RisicoRijkeAttractie extends Attractie {
	int draaiLimiet;
	
	RisicoRijkeAttractie() {
		super();
	}
	
	double draaien() throws DraaiLimietException {
		if(kaartjes > 0 && kaartjes % 5 == 0) {
			throw new DraaiLimietException();
		} else {
			System.out.println("De attractie " + naam + " draait \n");
			omzetPreTax += prijs;
			kaartjes++;
			return prijs;
		}
	}
	
	void opstellingsKeuring() {
		System.out.println(naam + " heeft zijn draailimiet van " + draaiLimiet + " bereikt en krijgt een onderhoudsbeurt! \n");
	}
	
}

interface GokAttractie {
	
	abstract double kansSpelBelastingBetalen();
}

class Botsauto extends Attractie {
	
	Botsauto() {
		super();
		naam = "botsauto";
		prijs = 2.50;
	}
}

class Spin extends RisicoRijkeAttractie {
	
	Spin() {
		super();
		naam = "spin";
		prijs = 2.25;
		draaiLimiet = 5;
	}
}

class Hawaii extends RisicoRijkeAttractie {
	
	Hawaii() {
		super();
		naam = "hawaii";
		prijs = 2.90;
		draaiLimiet = 10;
	}
}

class Ladderklimmen extends Attractie implements GokAttractie {
	
	Ladderklimmen() {
		super();
		naam = "ladderklimmen";
		prijs = 5.00;
	}
	
	public double kansSpelBelastingBetalen() {
		double belasting = omzetPreTax * 0.3;
		omzetPostTax = omzetPreTax * 0.7;
		omzetPreTax = 0;
		return belasting;	
	}
}

class Spiegelpaleis extends Attractie {
	
	Spiegelpaleis() {
		super();
		naam = "spiegelpaleis";
		prijs = 2.75;
	}
	
}
	
class Spookhuis extends Attractie {
	
	Spookhuis() {
		super();
		naam = "spookhuis";
		prijs = 3.20;
	}
	
}

class DraaiLimietException extends Exception {
}