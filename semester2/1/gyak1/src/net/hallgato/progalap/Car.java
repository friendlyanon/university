package net.hallgato.progalap;

public class Car {
    String type;
    int yearOfManufacture;
    String color;
    int numberOfPersons;
    int numberOfSeats;

    public Car(String type, int yearOfManufacture, String color, int numberOfPersons, int numberOfSeats) {
        this.type = type;
        this.yearOfManufacture = yearOfManufacture;
        this.color = color;
        this.numberOfPersons = numberOfPersons;
        this.numberOfSeats = numberOfSeats;
    }
}
