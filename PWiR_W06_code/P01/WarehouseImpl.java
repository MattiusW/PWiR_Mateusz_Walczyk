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

  // Add a function that accepts an amount as an argument and returns the name of
  // the most expensive
  public String findMaxPricedItemWithinBudget(double budget) throws RemoteException {
    String maxPricedItem = null;
    double maxPrice = 0;

    for (Map.Entry<String, Double> entry : prices.entrySet()) {
      double price = entry.getValue();
      if (price <= budget && price > maxPrice) {
        maxPricedItem = entry.getKey();
        maxPrice = price;
      }
    }

    return maxPricedItem;
  }

}