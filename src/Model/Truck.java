package Model;

import Enums.State;

import java.util.Objects;

public class Truck {
    private int ID;
    private String name;
    private Driver driver;
    private State state;

    public Truck() {
    }

    public Truck(int ID, String name, Driver driver, State state) {
        this.ID = ID;
        this.name = name;
        this.driver = driver;
        this.state = state;
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

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public boolean hasDriver() {
        return this.driver != null;
    }


    @Override
    public String toString() {
        return "Truck{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", driver=" + driver +
                ", state=" + state +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Truck truck = (Truck) o;
        return ID == truck.ID && Objects.equals(name, truck.name) && Objects.equals(driver, truck.driver) && state == truck.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, name, driver, state);
    }
}
