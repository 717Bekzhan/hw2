package com.company;

import Dto.DriverDto;
import Dto.DriverWithTruck;
import Dto.TruckDto;
import Dto.TruckWithoutDriver;
import Enums.State;
import Exceptions.MyException;
import Model.DaoDriver.DaoDriver;
import Model.DaoTruck.DaoTruck;
import Model.Driver;
import Model.Truck;
import Service.Service;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static final GsonBuilder builder = new GsonBuilder();
    public static final Gson gson = builder.setPrettyPrinting().create();
    public static final Path PATH = Paths.get("./src/truck.json");
    public static final Path PATH1 = Paths.get("./src/drivers.json");
    static Scanner scanner = new Scanner(System.in);
    static DaoTruck daoTruck = new DaoTruck();
    static DaoDriver daoDriver = new DaoDriver();



    public static void main(String[] args) {
        Truck truck1 = new Truck(1, "Daf", null, State.ON_THE_BASE);
        Truck truck2 = new Truck(2, "Volvo", null, State.ON_THE_BASE);
        Truck truck3 = new Truck(3, "Renault", null, State.ON_THE_BASE);
        Truck truck4 = new Truck(4, "Man", null, State.ON_THE_BASE);

        Truck[] trucks = {truck1, truck2, truck3, truck4};

        Driver drivers1 = new Driver(1, "Bekzhan", truck1);
        Driver drivers2 = new Driver(2, "Altynbek", truck2);
        Driver drivers3 = new Driver(3, "Ulanskiy", truck3);
        Driver drivers4 = new Driver(4, "Beksultan", truck4);
        Driver[] drivers = {drivers1, drivers2, drivers3, drivers4};
        DaoTruck daoTruck = new DaoTruck();
        DaoDriver daoDriver = new DaoDriver();
        String json = gson.toJson(trucks);
        String json1 = gson.toJson(drivers);
//        System.out.println(json);
        writeTrucks(json);
        drivers(json1);
        daoTruck.put(truck1);
        daoTruck.put(truck2);
        daoTruck.put(truck3);

        daoDriver.put(drivers1);
        daoDriver.put(drivers2);
        daoDriver.put(drivers3);
         Service service = new Service(daoDriver, daoTruck);
        daoTruck.show();


        while (true) {
            try {
                System.out.println();
                System.out.print("ENTER ID OF TRUCK TO SEE ALL INFORMATION ABOUT IT: ");
                int truck = scanner.nextInt();
                service.showIdTruck(truck);
                System.out.println("Press 1 to change Driver");
                System.out.println("Press 2 to send to the Route");
                System.out.println("Press 3 ot send to the Repairing");
                System.out.println("Press 3 ot send to the Base");
                int state = scanner.nextInt();
                switch (state) {
                    case 1 -> service.changeDriver(truck); // emne uchun biroosu service menen kalgany dao menen bolup kalgan
                    case 2 -> daoTruck.road(truck);
                    case 3 -> daoTruck.repairing(truck);
                    case 4 -> daoTruck.base(truck);
                }
            } catch (MyException e) { // my exceptiondu karmash uchun any yrgytysh (throw new MyException()) kerek birinchi
                System.err.println("WE COULD NOT FIND THIS NUMBER");
            }
            service.showService(); // methoddun atyn tushunuktuurok kylabyz
            System.out.println("=================================================");
            Map<Integer, TruckDto> mapTruck = new HashMap<>();
            Map<Integer, DriverDto> mapDriver = new HashMap<>();

            daoTruck.getDaoTruck().forEach((key, value) -> {
                mapTruck.put(key, new TruckDto(value.getID(),
                        value.getName(),
                        value.hasDriver() ? convert(value.getDriver()) : null,
                        value.getState()));
            });

            daoDriver.getDaoDriver().forEach((key, value) -> {
                mapDriver.put(key, new DriverDto(value.getID(),
                        value.getName(),
                        value.hasTruck() ? convert(value.getTruck()) : null));
            });
            String json4 = gson.toJson(mapTruck);
            writeTrucks(json);

            String json3 = gson.toJson(mapDriver);
            writeDrivers(json1);
        }
    }

    private static void writeDrivers(String json1) {
    }

    private static TruckWithoutDriver convert(Truck truck) {
        return new TruckWithoutDriver(
                truck.getID(),
                truck.getName(),
                truck.getState()
        );
    }

    private static DriverWithTruck.DriverWithoutTruck convert(Driver driver) {
        return new DriverWithTruck.DriverWithoutTruck(driver.getID(), driver.getName());
    }


    public static void writeTrucks(String obj) {
        Path write = Paths.get(String.valueOf(PATH));
        try {
            Files.writeString(write, obj, StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void drivers(String obj) {
        Path path = Paths.get(String.valueOf(PATH1));
        try {
            Files.writeString(path, obj, StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
