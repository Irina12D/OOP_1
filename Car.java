/**
 * Car class
 */
public class Car {

    String model;
    String color;
    int year;
    int price;      // euro
    int power;      // horse power
    int maxSpeed;   // km/h
    boolean isEngine;   
    private String VIN;
    

    /**
     * @param car model
     * @param year of release
     * @param the cost of the car
     * @param car color
     * @param engine capacity
     * @param maximum speed
     */
    public Car(String carModel, int yearRelease, int cost, String carColor, int engineCapacity, int carMaxSpeed) {
        this.model = carModel;
        this.year = yearRelease;
        this.price = cost;
        this.color = carColor;
        this.power = engineCapacity;
        this.maxSpeed = carMaxSpeed;
        this.isEngine = false;
    }

    /**
     * @param VehicleIdentificationNumber
     */
    public void setVIN(String serialNo)
    {
        VIN = serialNo;
    }

    public String getVIN()
    {
        return VIN;
    }

    public void getInfo(){
        System.out.println("model: " + model + ", year of release: " + year + ", cost: " + price + " euro, color: " + color + ", engine capacity: " + power + " horse power");
    }

    public void start()
    {
        this.isEngine = true;
    }

    public void stop()
    {
        this.isEngine = false;
    }

    /**
     * @param destination
     */
    public void road(String place)
    {
        if(this.isEngine)
            System.out.println(this.model + " goes to " + place);
        else
            System.out.println(this.model + " is not working so we can't go anywhere");
    }
    
    @Override
    public boolean equals(Object obj) 
    {
        if(this == obj)
            return true;
        if(!(obj instanceof Car))
            return false;  
        Car car = (Car) obj;
        return VIN.equals(car.VIN);
    }

    @Override
    public String toString() {
        return model;
    }
}
