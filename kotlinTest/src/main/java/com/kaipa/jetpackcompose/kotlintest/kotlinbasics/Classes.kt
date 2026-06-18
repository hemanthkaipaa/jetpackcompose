package com.kaipa.jetpackcompose.kotlintest.kotlinbasics
//#Topic
// 1 Basic class — declaration, properties, methods
// 2 Primary constructor
// 3 Secondary constructor
// 4 init block
// 5 Data class
// 6 Sealed class
// 7 Object & Companion object
// 8 Abstract class
// 9 Interface
// 10 Inheritance
// 11 Visibility modifiers
fun main(){
    val person = Person()
    person.name = "Hemanth"
    person.age = 31
    person.greet()

    // primary constructor
    val employee = Employee("Hemanth")
    employee.greetEmployee()

    // secondary constructor
    val student = Student("1234","Hemanth","10th")
    student.studentInfo()
    val student2 = Student("1234","Hemanth")
    student2.studentInfo()
    val studentDefaultCons = Student()
    studentDefaultCons.usingDefaultConstructor()

    // secondary constructor with primary constructor delegating/satisfying primary constructor
    val office = Office("TechM","Hemanth")
    office.officeInfo()
    val officeEmployee = Office("bangalore","7899619971","TechM","Hemanth")
    officeEmployee.employeeInfo()
    CollegeBlock("ECE","HEMANTH")
    println("primary constructor called")
    CollegeBlock("ECE","HEMANTH","ASHOK")
    //data class------------------------------------------------------------------------------------
    val computer1 = Computer("hpnotebook",2012,"HP")
    val computer3 = Computer("MSI Ryzen 5",2021,"MSI")
    println("Printing from computer1 ${computer1.company}")
    println("Printing from computer 3 ${computer3.company}")
    val computer4 = computer1.copy(mfYear = 2013)
    println("Printing from computer 4 ${computer4.mfYear} - ${computer4.model}")
    // componetN / destructing the object
    val (model,mfYear) = computer1
    println("de structuring the object $computer1")
    println(model)
    println(mfYear)
    //-------------------sealed class--------------------------------------
    fun handleAPIResult(result: APIResult){
        when(result){
            APIResult.loading -> println("Loading please wait")
            is APIResult.Success -> println("Success code = ${result.code}")
            is APIResult.Error -> println("Failed code = ${result.errorCode}")
        }
    }
    //---------------------object and companionObject-----------------------------
    DBManager.connect()
    DBManager.connect()
    DBManager.disconnect()

    val db1 = DBManager
    val db2 = DBManager
    println(db1 == db2) // same instance hence true

    //companion object
    println("Laptop owner ${Laptop.owner}")
    println("no of accounts ${Laptop.noOfAccounts}")
    Laptop.starting("Loki")
    Laptop.shuttingDown("Loki")

    // abstract class
    val dog = Dog()
    dog.makeSound()
    dog.breathe()

    val cat = Cat()
    cat.makeSound()
    cat.breathe()

    // abstract class with constructor
    val car = Car("Xcent","Hyundai")
    car.vehicleStarted()
    car.vehicleRunning()

    val bike = Bike("FZs","Yamaha")
    bike.vehicleStarted()
    bike.vehicleRunning()
    // --------interface --------------
    val button = Button()
    button.onButtonClick()
    //------------inheritance ----------
    val mobile = Mobile("motoralla edge neo 40","moto")
    mobile.mobileConfig()
}

// basic class declaration
class Person{
    var name : String = ""
    var age : Int = -1
    fun greet(){
        println("Hi I'm $name and I'm $age old")
    }
}

// primary constructor

class Employee(val name:String){
    fun greetEmployee(){
        println("Hi $name how are you, your profile is shortlisted")
    }
}
// Secondary constructor
class Student{
    var roll : String = ""
    var name : String = ""
    var standard : String = ""
    constructor(roll:String,name:String,standard:String){
        this.roll = roll
        this.name = name
        this.standard = standard
    }
    constructor(){
        println(" This is default constructor")
    }
    constructor(roll:String,name:String){
        this.roll = roll
        this.name = name
    }

    fun studentInfo(){
        println("Hi I'm $name bearing roll : $roll and $standard " )
    }

    fun usingDefaultConstructor(){
        println("Using Default constructor")
    }
}

// secondary constructor with primary constructor satisfying the primary.!
class Office(val empId:String,var empName:String){
    var empAddress : String = ""
    var empMobile  : String = ""

    constructor(empAddress:String,empMobile:String,empId:String,empName:String):this(empId,empName){
        this.empMobile = empMobile
        this.empAddress =empAddress
    }
    fun officeInfo(){
        println("Hello I'm $empName and my Id is $empId")
    }
    fun employeeInfo(){
        println("Hello I'm $empName and my Id is $empId , Address is $empAddress, mobile is $empMobile")
    }
}

// init block
class CollegeBlock(val branch:String, var hod:String){
    // init block runs after primary constructor
    init{
        println("This is init block $branch -> $hod")
    }
    // secondary constructor
    constructor(branch:String, hod:String, cr:String):this(branch,hod){
        println("secondary constructor called, Here are the three params passed $branch, $hod, $cr")
    }
}

//-------------------------  data class   ----------------------------------------------------------
// a data class will store the data
data class Computer(var model:String, var mfYear:Int, val company:String)


//------------------------ sealed class   ----------------------------------------------------------
/*
sealed class:
✅ cannot be extended outside the file
✅ all subclasses must be in same file
✅ mainly used for response/state handling
✅ each subclass carries its own data
✅ compiler knows all cases — no else needed in when
*/
sealed class APIResult{
    object loading : APIResult()
    data class Success(val code:Int, val successMessage:String)
    data class Error(val errorCode :Int, val errorMessage:String)
}

//---------------------------------------object and companion object -------------------------------
// object is a singleton instance and only one instance exist over complete app.
object DBManager{
    val DBName : String = "APPDB"
    var connectionCount = 0

    fun connect(){
        connectionCount++
        println(" DB $DBName connected successfully with connection count $connectionCount")
    }
    fun disconnect(){
        connectionCount --
        println(" DB $DBName disconnected successfully with connection count $connectionCount")
    }
}

// companion object must be tied to a class, and it can't be outside the class
class Laptop(val laptopModel:String,val mfYear:Int){
    companion object{
        val owner :String = "Hemanth"
        val noOfAccounts :Int = 5

        fun starting(guest: String){
            println("Logging In with user $guest")
        }

        fun shuttingDown(guest:String){
            println("Logging off with user $guest")
        }
    }
}

//---------------------abstract class ---------------------------------------------
// abstract class cannot be integrated directly
abstract class Animal{
    // subclass must provide value
    abstract val sound :String
    fun breathe(){
        println("Animal beathing is normal")
    }
    // subclass must implement
    abstract fun makeSound()
}

class Dog : Animal(){
    override val sound: String = "woof"
    override fun makeSound() {
       println("Dog makes sound $sound")
    }
}
class Cat : Animal(){
    override val sound: String = "meow"
    override fun makeSound() {
        println("Cat makes sound $sound")
    }
}

// abstract class with constructor

abstract class Vehicle(val model:String, val company:String){
    abstract val countryOrigin : String // subclass must provide value
    abstract val vehicleType : String // same above
    abstract fun vehicleRunning()
    fun vehicleStarted(){
        println("$vehicleType from $countryOrigin started and Idle, about to move")
    }
}

class Car (model:String, company:String) : Vehicle(model,company){
    override val countryOrigin: String = "South Korea"
    override val vehicleType: String
        get() = "car"

    override fun vehicleRunning() {
        println("$model vehicle from $company running and about to reach destination")
    }
}

class Bike(model:String, company:String): Vehicle(model,company){
    override val vehicleType: String
        get() = "Bike"
    override val countryOrigin: String
        get() = "India"

    override fun vehicleRunning() {
        println("Bike model $model and company $company running for service lately")
    }
}
/*
abstract class  →  base template with shared state/logic
                →  subclasses share common behavior
                →  BaseActivity, BaseViewModel, BaseAdapter

interface       →  defines a contract/capability
                →  what a class CAN DO
                →  Clickable, Flyable, Repository
*/
//----------------------------interface------------------------------------------------------------
interface Clickable{
    fun onButtonClick() // abstract by default
    fun onSuccess(){  // concrete method
        println("button clicked and click event registered")
    }
}

class Button: Clickable{
    override fun onButtonClick() {
        println("Button Clicked in UI")
        onSuccess()
    }
}

//-------------------- inheritance ------------------------------------

// in kotlin classes are final by default and cannot inherit as like java.
// so we use 'open' keyword to allow subclasses can inherit the parent class

//Base class
open class SmartDevice(val model:String,val brand:String){
    var ram :String = ""
    var battery : String = ""
    var storage : String = ""
    var mobileType : String = ""
    open fun mobileConfig(){
        println("$mobileType is a device from $brand and modelName -> $model having ram : $ram, battery : $battery, Storage : $storage")
    }
}

class Mobile(model:String,brand:String): SmartDevice(model,brand){
    init{
        super.mobileType = "Motoralla Hand set"
        super.storage = "256 GB"
        super.battery = "6000 mah"
        super.ram = "12 GB"
    }

    override fun mobileConfig() {
        super.mobileConfig()
        println("method inherited successfully")
    }

}
//--------------------------visibility modifiers------------------------------------
// 4 visibility modifiers in Kotlin
/**
 * public    // visible everywhere — default
 * private   // visible only in same file/class
 * protected // visible in same class + subclasses
 * internal  // visible only within same module
 */
