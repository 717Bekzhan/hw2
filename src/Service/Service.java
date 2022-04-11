package Service;

import Enums.State;
import Model.DaoDriver.DaoDriver;
import Model.DaoTruck.DaoTruck;
import Model.Driver;
import Model.Truck;

import java.util.Map;
import java.util.Scanner;

public class Service {
    private DaoDriver daoDriver;
    private DaoTruck daoTruck;

    Scanner scanner = new Scanner(System.in);

    public Service(DaoDriver daoDriver, DaoTruck daoTruck) {
        this.daoDriver = daoDriver;
        this.daoTruck = daoTruck;
    }

    public void changeDriver(int truck) {
        for (Map.Entry<Integer, Truck> e : daoTruck.getDaoTruck().entrySet()) {
            if (e.getValue().getState().equals(State.ON_THE_BASE)) {
                daoDriver.show();
                System.out.print("TO CHANGE DRIVER SELECT DRIVER: ");
                int driver = scanner.nextInt();
                changeDriver1(truck, driver);
                break;
            } else if (e.getValue().getState().equals(State.ON_THE_ROAD)) {
                System.err.printf("YOU CAN'T CHANGE DRIVER, BECAUSE THE TRUCK[%S] ON THE ROAD\n",
                        e.getValue().getName());
                break;
            } else {
                System.err.printf("YOU CAN'T CHANGE DRIVER, BECAUSE THE TRUCK[%S] ON THE REPAIR\n",
                        e.getValue().getName());
                break;
            }
        }
    }

    public void changeDriver1(int idTruck, int idDriver) {
        Truck truck = daoTruck.findWithID(idTruck);
        Driver driver = daoDriver.findWithID(idDriver);
        try {
            if (driver.isFree()) {
                truck.getDriver().setTruck(null);
                truck.setDriver(driver);
                driver.setTruck(truck);
            }else {
                System.err.println("Driver is not free!!");
            }
        }catch (NullPointerException ex) {
            truck.setDriver(driver);
            driver.setTruck(truck);
        }
    }

    public void showService() {
        System.out.println();
        daoTruck.show();
        System.out.println();
        daoDriver.show();
        System.out.println();
    }

    public void showIdTruck(int ID) {
        daoTruck.showId(ID);
    }
}
