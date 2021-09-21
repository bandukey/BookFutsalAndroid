package com.dipesh.finalassignment

import com.dipesh.rblibrary.entity.Item
import com.dipesh.rblibrary.entity.User
import com.dipesh.rblibrary.repository.ItemRepo
import com.dipesh.rblibrary.repository.UserRepo
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class FutsalTest {

    @Test
    fun checkLogin() = runBlocking{
        val userRepo = UserRepo()
        val response = userRepo.loginUser("saugat", "123456")
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun registerUser()= runBlocking {
        val user =
                User(firstName = "fname", lastName = "lname", password = "password",
                        address = "address",phone = "phone",email = "mail2")
        val userRepo = UserRepo()
        val response = userRepo.registerUser(user)
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun addFutsal() = runBlocking {
        val food = Item(itemName= "name",itemType= "type", itemPrice= 200, itemRating= "rating")

        val itemRepository = ItemRepo()
        val response = itemRepository.addFutsal(food)
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult, actualResult)
    }
}