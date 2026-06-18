package com.kaipa.jetpackcompose.kotlintest.kotlinbasics

// kotlin has the following collections for grouping structured data

/*
1. Lists -> Ordered Collection of items
2. Sets  -> Unique UnOrdered Collection of items
3. Maps  -> Set of KeyValue pairs where keys are unique and can map to only a single value
Each collection can be either mutable or readable
*/

fun main(){
    compileReadOnlyList()
    compileMutableList()
    readListByIndex()
    readFirstAndLastElementInList()
    reversedList()
    checkTheListCount()
    checkIfElementIsPresentInList()
    addItem()
    removeItem()
    readOnlySet()
    mutableSet()
    readOnlyMap()
    mutableMap()
    readMap()
    removeMap()
    loopMap()
    containsCheck()
}

// read only list
val readOnlyList = listOf(1,2,3,4)
val mutableList : MutableList<Int> = mutableListOf(1,2,3,4,5,6)
fun compileReadOnlyList(){
    println("-------------------- compileReadOnlyList --------------------")
//  readOnlyList.add(5)  // we cannot add to read only list
    println("This is read only list $readOnlyList")
}

fun compileMutableList(){
    println("-------------------- compileMutableList --------------------")
    mutableList.add(9) // we can modify the list here
    println("This is mutable list $mutableList")
}

fun readListByIndex(){
    println("-------------------- readListByIndex --------------------")
    println("The list at index 2 is ${readOnlyList[2]}")
}
fun readFirstAndLastElementInList(){
    println("-------------------- readFirstAndLastElementInList --------------------")
    println("This is the first element in list ${readOnlyList.first()}")
    println("This is the last element in the list  ${readOnlyList.last()}")
}

fun reversedList(){
    println("-------------------- reversedList --------------------")
    println("This is the reversed list ${readOnlyList.reversed()}")
}

fun checkTheListCount(){
    println("-------------------- checkTheListCount --------------------")
    println("This is the count of list ${readOnlyList.count()}")
}

// 'in' operator -> used to check if an element is present in list or not
fun checkIfElementIsPresentInList(){
    println("-------------------- checkIfElementIsPresentInList --------------------")
    println(3 in readOnlyList) // prints true if readOnlyList has 3 as an element
}

fun addItem(){
    println("-------------------- addItem --------------------")
    mutableList.add(10)
    println("This is mutable list after adding item $mutableList")
}

fun removeItem(){
    println("-------------------- removeItem --------------------")
    mutableList.remove(2)
    println("This is mutable list after removing item $mutableList")
}

// sets will have similar list attributes like add element, count, remove element but
// sets are unordered collection of unique elements
fun readOnlySet(){
    println("-------------------- readOnlySet --------------------")
    val readOnlySet = setOf(1,2,2,2,2,3,4,5,6,10,11,23,23)
    println("This is read only set $readOnlySet")
}

fun mutableSet(){
    println("-------------------- mutableSet --------------------")
    val mutableSet = mutableSetOf(1,2,2,2,2,3,4,5,6,10,11,23,23)
    mutableSet.add(12)
    println("This is mutable set $mutableSet")
}

fun readOnlyMap(){
    println("-------------------- readOnlyMap --------------------")
    val readOnlyMap = mapOf("name" to "Hemanth","age" to 31,"mobile" to 7899619971)
    println("This is read only map $readOnlyMap")
}

fun mutableMap(){
    println("-------------------- mutableMap --------------------")
    val mutableMap : MutableMap<String,String> = mutableMapOf("name" to "Hemanth","age" to "31","mobile" to "7899619971")
    mutableMap["address"] = "bangalore"
    mutableMap["name"] = "HK"
    println("This is mutable map $mutableMap")
}

fun readMap(){
    println("-------------------- readMap --------------------")
    val readMap : MutableMap<String,String> = mutableMapOf("name" to "kaipa", "age" to "31")
    val key = readMap.get("name")
    println("This is the value of key $key")
}

fun removeMap(){
    println("-------------------- removeMap --------------------")
    val removeElement = mutableMapOf("name" to "hemanth", "age" to 31, "mobile" to "7899619971")
    println("original Map ${removeElement}")
    removeElement.remove("mobile")
    println("Map after removing element ${removeElement}")
}

fun loopMap(){
    println("-------------------- loopMap --------------------")
    val loopMap = mapOf("name" to "HK", "age" to 31, "address" to "bangalore")
    for(key in loopMap.keys){
        println("This is the key $key and value is ${loopMap[key]}")
    }
}

fun containsCheck(){
    println("-------------------- containsCheck --------------------")
    val containsMap =  mapOf("name" to "HK", "age" to 31, "address" to "bangalore")
    println("Checking valid key in map expecting value true = ${containsMap.containsKey("name")}")
    println("Checking invalid key in map expecting value false = ${containsMap.containsKey("hello")}")
}


