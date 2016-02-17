package clarke.sw.client;

import java.rmi.Naming;
import java.util.LinkedList;

import clarke.sw.calculateArbs.ArbCalculator;

public class Client {

	public static void main(String[] args) throws Exception {
		ArbCalculator ac = (ArbCalculator) Naming.lookup("///arb-calculator");
		
		LinkedList<String> arbs = ac.calcArbs();
		
		for(String s : arbs){
			System.out.println(s + "\n");
		}
	}

}
