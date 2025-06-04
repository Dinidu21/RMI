import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) {
        try {
            // Get reference to RMI registry on localhost, port 1099
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);

            // Lookup the remote service by name
            Service service = (Service) registry.lookup("CalculatorService");
            System.out.println("Client: Connected to CalculatorService");

            // Call remote methods and print results
            System.out.println("Client: 5 + 3 = " + service.add(5, 3));
            System.out.println("Client: 5 - 3 = " + service.sub(5, 3));
            System.out.println("Client: 5 * 3 = " + service.mul(5, 3));
            System.out.println("Client: 25 / 5 = " + service.div(25, 5));

        } catch (NotBoundException e) {
            System.err.println("NotBoundException: " + e.getMessage());
        } catch (RemoteException e) {
            System.err.println("RemoteException: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
}
