package clarke.sw.calculateArbs;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.LinkedList;

public interface ArbCalculator extends Remote {
	public LinkedList<String> calcArbs() throws RemoteException;
}
