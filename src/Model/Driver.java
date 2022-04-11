package Model;

import java.util.Objects;

public class Driver {
    private int ID;
    private String name;
    private Truck truck;

    public Driver() {
    }

    public Driver(int ID, String name, Truck truck) {
        this.ID = ID;
        this.name = name;
        this.truck = truck;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", truck='" + truck + '\'' +
                '}';
    }

    public boolean hasTruck() {
        return this.truck != null;
    }

    public boolean isFree() {
        return this.truck == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return ID == driver.ID && Objects.equals(name, driver.name) && Objects.equals(truck, driver.truck);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, name, truck);
    }
}
