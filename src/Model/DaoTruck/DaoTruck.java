package Model.DaoTruck;

import Enums.State;
import Model.Driver;
import Model.Truck;

import java.util.HashMap;
import java.util.Map;

public class DaoTruck {
    Map<Integer, Truck> daoTruck = new HashMap<>();

    public Map<Integer, Truck> getDaoTruck() {
        return daoTruck;
    }

    public void setDaoTruck(Map<Integer, Truck> daoTruck) {
        this.daoTruck = daoTruck;
    }

    public void put(Truck truck) {
        daoTruck.put(truck.getID(), truck);
    }

    public void putDriver(int ID, Driver driver) {
        if (driver.isFree()) {
            for (Map.Entry<Integer, Truck> d : daoTruck.entrySet()) {
                if (d.getKey() == ID) {
                    d.getValue().setDriver(driver);
                }
            }
        } else System.out.println("Driver is not free! ");
    }

    public Truck findWithID(int ID) {
        return daoTruck.get(ID);
    }

    public void road(int ID) {
        for (Map.Entry<Integer, Truck> k : daoTruck.entrySet()) { // forgo salbai ele jon ele .get() degen methoddu chakyrsanyz bolmok
            if (k.getKey() == ID) {
                if (k.getValue().getState().equals(State.ON_THE_ROAD)) {
                    System.err.println("These truck on the way ");
                } else {
                    if (k.getValue().getDriver() == null) { // esli driver null bolso emne uchun driver bosh emes dep chygarat
                        System.out.println("Driver is not free!");
                    } else {
                        k.getValue().setState(State.ON_THE_ROAD);
                        System.out.println("- - - - - - - - - - - - - - - - -");
                        System.out.println("Driver successfully on the road ");
                    }
                }
            }
        } // no clean code
    } public void repairing(int id) { // repairden repairge kylsak kandai bolot
        daoTruck.entrySet().stream().filter(x -> x.getKey() == id).forEach(x -> x.getValue().setState(State.ON_THE_REPAIR));
        System.out.println("=================================================");
        System.out.println("you have successfully sent for repair");
    }

    public void base(int id) { // basedan basega jonotsok bolobu?
        daoTruck.entrySet().stream().filter(x -> x.getKey() == id).forEach(x -> x.getValue().setState(State.ON_THE_BASE));
        System.out.println("=================================================");
        System.out.println("you have successfully sent for base");
    }

    public void showId(int id) { // jon ele .get() looptun orduna koldonsonuz jakshy bolmok
        for (Map.Entry<Integer, Truck> t : daoTruck.entrySet()) {
            if (id == t.getKey()) {
                try {
                    System.out.println(" Id          : " + t.getValue().getID());
                    System.out.println(" Truck       : " + t.getValue().getName());
                    System.out.println(" Driver      : " + t.getValue().getDriver().getName());
                    System.out.println(" Truck State : " + t.getValue().getState());
                } catch (NullPointerException e) {
                    System.out.println(" Driver      : " + " ");
                    System.out.println(" Truck State : " + t.getValue().getState() + "\n");
                }
            }
        }
    }

    public void show() {
        System.out.println("""
                 #     | Bus            | Driver   | State
                -------+----------------+----------+--------------""");
        for (Map.Entry<Integer, Truck> entry : daoTruck.entrySet()) {
            Integer key = entry.getKey();
            Truck value = entry.getValue();
            try {
                System.out.printf("""
                                -%d-    |  %s       | %s  | %s            """, key, value.getName(),
                        value.getDriver().getName(), value.getState() + "\n");
            } catch (NullPointerException e) {
                System.out.printf("""
                                -%d-    |  %s       | %s        | %s            """, key, value.getName(),
                        " ", value.getState() + "\n");
            }
        }
    }
}
