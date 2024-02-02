// autor: Cay Horstmann

import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

public class WarehouseImpl extends UnicastRemoteObject implements Warehouse {
  public WarehouseImpl() throws RemoteException {
    prices = new HashMap<String, Double>();
    prices.put("Blackwell Toaster", 24.95);
    prices.put("Toaster", 14.25);
    prices.put("ZapXpress microwave Oven", 49.95);
  }

  public double getPrice(String description) throws RemoteException {
    Double price = prices.get(description);
    return price == null ? 0 : price;
  }

  private Map<String, Double> prices;

}