package Dto;

import com.google.gson.annotations.SerializedName;

public class DriverDto {
    public DriverDto(int id, String name, TruckWithoutDriver truckWithoutDriver) {
    }

    public record driverDto(int id, String name,
                            @SerializedName(value = "Truck") TruckWithoutDriver truckWithoutDriver) {
    }
}
