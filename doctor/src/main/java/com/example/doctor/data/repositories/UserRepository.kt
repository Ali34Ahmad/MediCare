package com.example.doctor.data.repositories

import com.example.doctor.data.model.user.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    /** Add a new user to the database with the same ID as the current user*/
    suspend fun addNewUser(user: User)
    /** Get a user from the database by its ID*/
    suspend fun getUser() : User?
    /** Update a user in the database*/
    suspend fun updateUser(user: User)
    /** This function just for the doctor: get the user by its id*/
    suspend fun getUserById(id:String) : User?
    /**Get the current user*/
    val user: Flow<User?>
}