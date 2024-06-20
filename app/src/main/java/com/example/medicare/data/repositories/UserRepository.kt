package com.example.medicare.data.repositories

import com.example.medicare.data.model.user.User

interface UserRepository {
    /** Add a new user to the database with the same ID as the current user*/
    suspend fun addNewUser(user: User)
    /** Get the current user from the database by its ID*/
    suspend fun getUser() : User?
    /** Update a user in the database*/
    suspend fun updateUser(user: User)
    /** This function just for the doctor: get the user by its id*/
    suspend fun getUserById(id:String) : User?
    /** This function just for the doctor: get the visit number of a user*/
    suspend fun getVisitNumber(id:String) : Int
}