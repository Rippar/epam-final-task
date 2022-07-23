package com.epam.jwd.carrentproject.entity;

import java.io.Serial;
import java.math.BigDecimal;

/**
 * The {@code Car} class represents a car entity
 */
public class Car extends AbstractEntity {

    @Serial
    private static final long serialVersionUID = 1L;
    private int carId;
    private String carBrand;
    private String carModel;
    private String carClass;
    private String carBody;
    private boolean isAutoTransmission;
    private boolean airConditioning;
    private int numOfDoors;
    private int numOfSeats;
    private BigDecimal rentalPrice;
    private boolean isActive;

    public static class CarBuilder {
        private Car newCar;

        public CarBuilder() {
            newCar = new Car();
        }

        public CarBuilder withCarId(int id) {
            newCar.carId = id;
            return this;
        }

        public CarBuilder withCarBrand(String carBrand) {
            newCar.carBrand = carBrand;
            return this;
        }

        public CarBuilder withCarModel(String carModel) {
            newCar.carModel = carModel;
            return this;
        }

        public CarBuilder withCarClass(String carClass) {
            newCar.carClass = carClass;
            return this;
        }

        public CarBuilder withCarBody(String carBody) {
            newCar.carBody = carBody;
            return this;
        }

        public CarBuilder withIsAutoTransmission(boolean isAutoTransmission) {
            newCar.isAutoTransmission = isAutoTransmission;
            return this;
        }

        public CarBuilder withAirConditioning(boolean airConditioning) {
            newCar.airConditioning = airConditioning;
            return this;
        }

        public CarBuilder withNumOfDoors(int numOfDoors) {
            newCar.numOfDoors = numOfDoors;
            return this;
        }

        public CarBuilder withNumOfSeats(int numOfSeats) {
            newCar.numOfSeats = numOfSeats;
            return this;
        }

        public CarBuilder withRentalPrice(BigDecimal rentalPrice) {
            newCar.rentalPrice = rentalPrice;
            return this;
        }

        public CarBuilder withIsActive(boolean isActive) {
            newCar.isActive = isActive;
            return this;
        }

        public Car build() {
            return newCar;
        }

    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarClass() {
        return carClass;
    }

    public void setCarClass(String carClass) {
        this.carClass = carClass;
    }

    public String getCarBody() {
        return carBody;
    }

    public void setCarBody(String carBody) {
        this.carBody = carBody;
    }

    public boolean isAutoTransmission() {
        return isAutoTransmission;
    }

    public void setAutoTransmission(boolean autoTransmission) {
        isAutoTransmission = autoTransmission;
    }

    public boolean isAirConditioning() {
        return airConditioning;
    }

    public void setAirConditioning(boolean airConditioning) {
        this.airConditioning = airConditioning;
    }

    public int getNumOfDoors() {
        return numOfDoors;
    }

    public void setNumOfDoors(int numOfDoors) {
        this.numOfDoors = numOfDoors;
    }

    public int getNumOfSeats() {
        return numOfSeats;
    }

    public void setNumOfSeats(int numOfSeats) {
        this.numOfSeats = numOfSeats;
    }

    public BigDecimal getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(BigDecimal rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (carId != car.carId) return false;
        if (isAutoTransmission != car.isAutoTransmission) return false;
        if (airConditioning != car.airConditioning) return false;
        if (numOfDoors != car.numOfDoors) return false;
        if (numOfSeats != car.numOfSeats) return false;
        if (isActive != car.isActive) return false;
        if (carBrand != null ? !carBrand.equals(car.carBrand) : car.carBrand != null) return false;
        if (carModel != null ? !carModel.equals(car.carModel) : car.carModel != null) return false;
        if (carClass != null ? !carClass.equals(car.carClass) : car.carClass != null) return false;
        if (carBody != null ? !carBody.equals(car.carBody) : car.carBody != null) return false;
        return rentalPrice != null ? rentalPrice.equals(car.rentalPrice) : car.rentalPrice == null;
    }

    @Override
    public int hashCode() {
        int result = carId;
        result = 31 * result + (carBrand != null ? carBrand.hashCode() : 0);
        result = 31 * result + (carModel != null ? carModel.hashCode() : 0);
        result = 31 * result + (carClass != null ? carClass.hashCode() : 0);
        result = 31 * result + (carBody != null ? carBody.hashCode() : 0);
        result = 31 * result + (isAutoTransmission ? 1 : 0);
        result = 31 * result + (airConditioning ? 1 : 0);
        result = 31 * result + numOfDoors;
        result = 31 * result + numOfSeats;
        result = 31 * result + (rentalPrice != null ? rentalPrice.hashCode() : 0);
        result = 31 * result + (isActive ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
                "carId=" + carId +
                ", carBrand='" + carBrand + '\'' +
                ", carModel='" + carModel + '\'' +
                ", carClass='" + carClass + '\'' +
                ", carBody='" + carBody + '\'' +
                ", isAutoTransmission=" + isAutoTransmission +
                ", airConditioning=" + airConditioning +
                ", numOfDoors=" + numOfDoors +
                ", numOfSeats=" + numOfSeats +
                ", rentalPrice=" + rentalPrice +
                ", isActive=" + isActive +
                '}';
    }
}
