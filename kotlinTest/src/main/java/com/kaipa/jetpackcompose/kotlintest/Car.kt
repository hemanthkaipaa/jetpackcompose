package com.kaipa.jetpackcompose.kotlintest


fun main(){
    val car = Car("Blue", "Xcent", 11)
    car.drive()
}



class Car : Vehicle{

    constructor(color: String, model: String, age: Int) :super(color, model){
        super.vehicleAge = age
    }

    override fun drive() {
        println("Driving a $color $model which has age $vehicleAge")
    }
}