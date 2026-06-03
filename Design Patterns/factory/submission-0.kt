interface Vehicle {
    fun getType(): String
}

class Car : Vehicle {
    override fun getType(): String {
        return "Car"
    }
}

class Bike : Vehicle {
    override fun getType(): String {
        return "Bike"
    }
}

class Truck : Vehicle {
    override fun getType(): String {
        return "Truck"
    }
}

abstract class VehicleFactory {
    abstract fun createVehicle(): Vehicle
}

class CarFactory : VehicleFactory() {
    // Write your code here
    override fun createVehicle(): Car {
        return Car()
    }
}

class BikeFactory : VehicleFactory() {
    // Write your code here
    override fun createVehicle(): Bike {
        return Bike()
    }
}

class TruckFactory : VehicleFactory() {
    // Write your code here
    override fun createVehicle(): Truck {
        return Truck()
    }
}
