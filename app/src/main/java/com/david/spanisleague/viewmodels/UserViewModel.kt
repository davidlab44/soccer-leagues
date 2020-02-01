package com.david.spanisleague.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.david.spanisleague.data.User
import com.david.spanisleague.data.UserRepository

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private var repository: UserRepository = UserRepository(application)
    private var allUsers: LiveData<List<User>> = repository.getAllUsers()
    private var filteredUsers: MutableLiveData<List<User>> = MutableLiveData()

    fun insert(user: User) {
        repository.insert(user)
    }

    fun update(user: User) {
        repository.update(user)
    }

    fun delete(user: User) {
        repository.delete(user)
    }

    fun deleteAllUsers() {
        repository.deleteAllUsers()
    }

    fun getAllUsers(): LiveData<List<User>> {
        return allUsers
    }

    fun filterByName(userName: String) {
        filteredUsers.value = repository.getFilteredUsers(userName)
    }

    fun getFilteredUsers(): LiveData<List<User>> {
        return filteredUsers
    }
}