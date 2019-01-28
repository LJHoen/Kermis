package kermis;

import java.util.*;
import java.text.DecimalFormat;

public class Kassa {
	private float omzet;
	private int kaartjes;
	
	Kassa() {
	}
	
	void opvragen(ArrayList<Attractie> attracties) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Kies 'o' om de omzet te tonen of 'k' om het aantal verkochte kaartjes te tonen");
		String input = scanner.next();
		if(input.equals("o")) {
			System.out.println("\nTotale omzet: " + omzet);
			for(Attractie a : attracties) {
				System.out.println(a.getNaam() + ": " + (a.getOmzetPreTax() + a.getOmzetPostTax()) + " euro");
			}
		} else if( input.equals("k")) {
			System.out.println("\nTotaal aantal kaartjes verkocht: " + kaartjes);
			for(Attractie a : attracties) {
				System.out.println(a.getNaam() + ": " + a.getKaartjes() + " kaartjes");
			}
		} else {
			System.out.println("Input " + input + " wordt niet ondersteund");
		}
	}
	
	void keuzeMaken(ArrayList<Attractie> attracties) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Tot welke attractie wenst u toegang?");
		for(int i = 0; i < attracties.size(); i++) {
			System.out.println("Toets " + (i+1)  +  " voor " + attracties.get(i).getNaam());
		}
		int input = scanner.nextInt();
		if(input > 0 && input <= attracties.size()) {
			try {
				omzet += attracties.get(input-1).draaien();
				kaartjes++;
			} catch (DraaiLimietException dle){
				RisicoRijkeAttractie tempLeash = (RisicoRijkeAttractie)attracties.get(input-1);
				tempLeash.opstellingsKeuring();
			} catch (Exception e) {
				e.getMessage();
			}
		} else {
			System.out.println("Die attractie hebben wij niet! \n");
		}
	}
	
	
	static ArrayList<Attractie> maakAttracties() {
		ArrayList<Attractie> attracties = new ArrayList();
		attracties.add(new Botsauto());
		attracties.add(new Spin());
		attracties.add(new Spiegelpaleis());
		attracties.add(new Spookhuis());
		attracties.add(new Hawaii());
		attracties.add(new Ladderklimmen());
		return attracties;
	}
	
	public static void main(String[] args) {
		Kassa kassa = new Kassa();
		ArrayList<Attractie> attracties = maakAttracties();
		for(int i = 0; i < 16; i++) {
			kassa.keuzeMaken(attracties);
		}
		kassa.opvragen(attracties);
		kassa.opvragen(attracties);
	}
}