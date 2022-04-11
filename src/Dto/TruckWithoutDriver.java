package Dto;

import Enums.State;

public class TruckWithoutDriver {
    public TruckWithoutDriver(int id, String name, State state) {
    }

    public record truckWithoutDriver(int id, String name, State state) {
    }

}
