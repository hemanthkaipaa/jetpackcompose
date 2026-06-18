package com.kaipa.jetpackcompose.kotlintest.kotlinbasics

// 1 The problem — what is NullPointerException
// 2 Nullable types — ?
// 3 Safe call operator — ?.
// 4 Elvis operator — ?:
// 5 Not-null assertion — !!
// 6 Safe cast — as?
// 7 let with null safety
// 8 lateinit
// 9 lazy
fun main(){
    nullable()
    elvis()
}

//null safety in kotlin has several features to stop app crashing from null pointer exception
// kotlin prevents null by default

// 2. Nullable types
//nullable -> might or might not have value
var name : String? = "hemanth" // if we add ? after type makes the variable nullable - you can assign null to this var later
// any type can be nullable with `?`
/*
var name: String? = null
var age: Int? = null
var salary: Double? = null
var isActive: Boolean? = null
var person: Person? = null
var list: List<String>? = null
*/
fun nullable(){
    val name :String? = "Hemanth"
//    println(name.length) // gives compile time error as you cannot directly use it instead check null first
    //must check null first
    if(name!=null){
        println(name.length)
    }
}
// non nullable - > must have value and initialized
var name1 :String = "hemanth" // you cannot assign null value to this var

/**
 * String   →  never null, always has value
 * String?  →  might be null, must handle before use
 *
 * non-nullable  →  use when value is always required
 * nullable      →  use when value is optional
 */

// safe operator ?.
fun nullSafe(){
    val name : String? = "Hemanth"
    if(name!=null){
        println(name.length)
    }
    // using safe operator
    println(name?.length) // ? it will check if name is null or not and it will fetch the data from name
}
/**
 * obj?.method()
 *   → if obj is null  — returns null, skips method
 *   → if obj not null — calls method normally
 *
 * name?.length
 *   → if name is null  — returns null
 *   → if name not null — returns length
 */

// elvis operator
fun elvis(){
    println("Elvis")
    val name : String? = null
    println(name?.length?:0) // if name returns null then it will return 0
}
//val city = user?.address?.city ?: "City not available"  //elvis with chaining

//val validName = name ?: throw IllegalArgumentException("Name cannot be null")  // elvis with throw
//val validName = name ?: return    // elvis with return
//val result = name ?: getDefaultName() // elvis with function

/**
 * ?.   →  safe call    →  returns null if null
 * ?:   →  elvis        →  returns default if null
 *
 * name?.length ?: 0
 *   → get length safely
 *   → if null at any point — return 0
 */

//  !! — tells compiler "I KNOW this is not null, trust me"

fun notnull(){ //this scenario is solely for developer decision  -> note this might crash if dev is not careful for declaring the notnull operator
    var name : String? = "hemanth"
    println(name!!.length) // !! we know that name at this point will not be null, so we are convincing compiler
    name =null
    println(name?.length) // at this point we have declared it as null so name.lenght will be skipped
}

/**
 * !!  →  "I guarantee this is not null"
 *     →  if wrong — app crashes
 *     →  use as LAST resort only
 *     →  prefer ?. or ?: instead
 */

// safe cast as?

val obj : Any = "Hemanth" // type is string here
val _name : String = obj as String //normal cast
val _safeName : String? = obj as? String // safe cast

val obj2 : Any = 123
//val _age : Int = obj2 as String // will compile error
val _age : String? = obj2 as? String

/**
 * as   →  regular cast  →  crashes if wrong type
 * as?  →  safe cast     →  returns null if wrong type ✅
 *
 * always prefer as? over as
 * combine with ?: for default value
 */
// let with null safety -> let also is belongs to scope functions
fun let(){
    val name_1 : String? = "Hemanth"
    if(name_1!=null){                 // kind of boilerplate code
        println(name_1.length)
        println(name_1.uppercase())
    }

    name_1?.let{
        println(it.length)           // it is the instance var of name_1 string
        println(it.uppercase())
    }

    name_1?.let{ userName ->   // we can also define a custom var name if we want instead of it
        println(userName.length)
        println(userName.uppercase())
    }

    // let returns a value

    val goodName = name_1?.let {
        println(it.length)
        println(it.uppercase())
        it.length
    } ?: 0
    println(goodName)
}

//we can also use chaining let
data class Address(val city: String?)
data class User(val name: String?, val address: Address?)

fun chainingLet(){
    val user: User? = User("Hemanth", Address("Bangalore"))

    // chaining let
    user?.let { u ->
        println("User: ${u.name}")
        u.address?.let { addr ->
            println("City: ${addr.city}")
        }
    }
}

// lateinit
// problem — sometimes you can't initialize a variable immediately
// but you don't want it to be nullable either

// ❌ option 1 — nullable, but forces null checks everywhere
var name_2: String? = null

// ❌ option 2 — non-nullable, but must initialize immediately
var name_3: String = "..."   // what value to give?

// ✅ option 3 — lateinit, initialize later, no null checks needed
lateinit var name_4 : String   // promise to initialize before use

// ex

class UserProfile{
    lateinit var name : String
    lateinit var email : String

    fun config(name:String, email:String){
        this.name = name             // here we assigning the value
        this.email = email
    }
    // if we don't assign any value but used somewhere it will throw UninitializedPropertyAccessException

}

// Check before use — ::property.isInitialized:
class EmployeeNew{
    lateinit var name : String
    lateinit var email : String

    // no we have assigned any values to above vars,
    // to check nulls or initialization we use
    fun check(){
        if(::name.isInitialized){
            println(name)
        }
    }

}

/**
 * late init rules
 * // ✅ only var — not val
 * lateinit var name: String    // ✅
 * lateinit val name: String    // ❌ error — val can't be lateinit
 *
 * // ✅ only non-nullable types
 * lateinit var name: String    // ✅
 * lateinit var name: String?   // ❌ error — nullable can't be lateinit
 *
 * // ✅ only reference types — not primitives
 * lateinit var name: String    // ✅ reference type
 * lateinit var age: Int        // ❌ error — Int is primitive
 * lateinit var age: Integer    // ✅ boxed type works
 *
 * // ✅ only class properties or top level — not local variables
 * class MyClass {
 *     lateinit var name: String   // ✅ class property
 * }
 * fun myFun() {
 *     lateinit var name: String   // ❌ local variable not allowed
 * }
 *
 * lateinit var  →  initialize later, use without null checks
 *               →  only var, only non-nullable, no primitives
 *               →  crash if used before initialized
 *               →  use isInitialized to check safely
 *
 * when to use:
 * ✅ dependency injection
 * ✅ view binding in Android
 * ✅ setup/init methods
 * ✅ when value comes from framework
 */


// lazy

// when you have to create a DB instance which is heavy and launched during the startup
// so instead of launching immediately we use lazy keyword
class SQLdb{
    fun connectDB(){

    }
    fun disconnectDB(){

    }
}
class LazyUsage(){
    val heavyObject = SQLdb() // this has initialized without any need and and its heavy for DB
    // instead
    val heavyObj by lazy{
        SQLdb()
    }
    // here whenever i access heavyObj the SQL db will get initialized.
}
val name_9: String by lazy {
    println("Initializing name...")
    "Hemanth"               // last expression is the value
}

fun usage() {
    println("Before accessing name")
    println(name_9)           // initializes here — first access
    println(name_9)           // already initialized — reuses same value
    println(name_9)           // already initialized — reuses same value
}
//--------------------------------------------------------------------------------------------------