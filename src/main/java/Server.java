import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;

import java.rmi.registry.LocateRegistry;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.Registry;


public class Server extends UnicastRemoteObject implements Service{

    public Server() throws RemoteException {
        super();
    }

    @Override
    public int add(int a, int b) throws RemoteException {
        System.out.println("Server: add called with " + a + " and " + b);
        return a + b;
    }

    @Override
    public int sub(int a, int b) throws RemoteException {
        System.out.println("Server: sub called with " + a + " and " + b);
        return a - b;
    }

    @Override
    public int mul(int a, int b) throws RemoteException {
        System.out.println("Server: mul called with " + a + " and " + b);
        return a * b;
    }

    @Override
    public int div(int a, int b) throws RemoteException {
        System.out.println("Server: div called with " + a + " and " + b);
        if (b == 0) {
            throw new RemoteException("Division by zero is not allowed.");
        }
        return a / b;
    }

    public static void main(String[] args) {
        try {
            // Create an instance of the server
            Server server = new Server();
            System.out.println("Server instance created.");

            // Create a registry on port 1099
            Registry registry = LocateRegistry.createRegistry(1099);

            // Bind the server instance to the name "CalculatorService"
            registry.bind("CalculatorService", server);
            System.out.println("Server bound to 'CalculatorService'.");

        } catch (RemoteException e) {
            System.err.println("RemoteException: " + e.getMessage());
        } catch (AlreadyBoundException e) {
            System.err.println("AlreadyBoundException: " + e.getMessage());
        }
    }

}
