package com.kaipa.jetpackcompose.kotlintest

// Primary constructor
open class Vehicle(var color: String, var model: String) {
    // 1. Declare the property for age
    var vehicleAge: Int = 0

    constructor() : this(model = "",color = "") {

    }
    // Secondary constructor delegates to the primary constructor using `this`
    constructor(color: String) :this(color,model=""){
        // You can add more specific logic for the secondary constructor here
        println("Secondary constructor called with color: $color and model: $model")
    }

    // 2. The parameter is just 'age', not 'var age'
    constructor(age :Int) : this(color = "", model = ""){
       // 3. Assign the local parameter to the class property
       this.vehicleAge = age
    }

   open fun drive(){
        // 4. Use the class property here
        println("Driving a $color $model which has age $vehicleAge")
    }
}