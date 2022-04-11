package Model.DaoDriver;

import Model.Driver;
import Model.Truck;

import java.util.HashMap;
import java.util.Map;

public class DaoDriver {
    Map<Integer, Driver> daoDriver = new HashMap<>();

    public Map<Integer, Driver> getDaoDriver() {
        return daoDriver;
    }

    public void put(Driver driver) {
        daoDriver.put(driver.getID(), driver);
    }

    public void setDaoDriver(Map<Integer, Driver> daoDriver) {
        this.daoDriver = daoDriver;


    }

    public void putTruck(int ID, Truck truck) {
        daoDriver.entrySet().stream().filter(x -> x.getKey() == ID).forEach(x -> x.getValue().setTruck(truck));
    }

    public Driver findWithID(int ID) {
        return daoDriver.get(ID);
    }

    public void show() {
        System.out.println("""
                 #     | Driver         | Bus      
                -------+----------------+--------------""");
        for (Map.Entry<Integer, Driver> entry : daoDriver.entrySet()) {
            Integer key = entry.getKey();
            Driver value = entry.getValue();
            try {
                System.out.printf("""
                                -%d-    | %s        | %s           """,
                        key, value.getName(), value.getTruck() + "\n");
            } catch (NullPointerException e) {
                System.out.printf("""
                                -%d-    | %s        | %s           """,
                        key, value.getName(), " " + "\n");
            }
        }
    }
}

