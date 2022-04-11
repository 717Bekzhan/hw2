package Dto;

import Enums.State;
import com.google.gson.annotations.SerializedName;

public class TruckDto {
    public TruckDto(int id, String name, DriverWithTruck.DriverWithoutTruck driverWithoutTruck, State state) {
    }

    public record truckDto(int id,
                           String name,
                           @SerializedName(value = "driver") DriverWithTruck.DriverWithoutTruck driverWithoutTruck,
                           State state) {
    }

}
