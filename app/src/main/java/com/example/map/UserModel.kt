package com.example.map

data class UserModel(
    val name: String,
    val email: String,
    val uid: String,

){
    //    making empty constructor whenever we are making data class for firebase
    constructor() : this("","","")


}