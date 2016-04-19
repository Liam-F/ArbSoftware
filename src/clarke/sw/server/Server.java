package clarke.sw.server;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;

import clarke.sw.calculateArbs.ArbCalculator;
import clarke.sw.calculateArbs.ArbData;
import clarke.sw.calculateArbs.CalculateArbs;

public class Server extends UnicastRemoteObject implements ArbCalculator {

	private static final long serialVersionUID = 1L;
	private CalculateArbs calculateArbs;
	private LinkedList<ArbData> arbData;
	private LinkedList<String> arbs = new LinkedList<String>();

	protected Server() throws RemoteException {
		calculateArbs = new CalculateArbs();
	}

	public LinkedList<String> calcArbs() throws RemoteException {
		arbData = calculateArbs.getArbs();

		for (ArbData ad : arbData) {
			if (ad.getArbPercentage() < 102)
				arbs.add(ad.getArbPercentage() + " --- " + ad.getPlayer1() + "   " + ad.getPlayer1BetAmount() + "   "
						+ ad.getPlayer1Website() + "   " + ad.getPlayer1Odds() + " --- " + ad.getPlayer2() + "   "
						+ ad.getPlayer2BetAmount() + "   " + ad.getPlayer2Website() + "   " + ad.getPlayer2Odds()
						+ "\n");
		}

		return arbs;
	}

	public static void main(String[] args) throws Exception {
		System.out.println("Starting remote service");

		LocateRegistry.createRegistry(1099);

		Naming.bind("arb-calculator", new Server());

		System.out.println("Service started...");
	}
}
