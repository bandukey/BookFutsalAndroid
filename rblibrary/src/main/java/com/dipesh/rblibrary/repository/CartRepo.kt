package com.dipesh.rblibrary.repository

import com.dipesh.rblibrary.api.CartAPI
import com.dipesh.rblibrary.api.MyApiRequest
import com.dipesh.rblibrary.api.ServiceBuilder
import com.dipesh.rblibrary.entity.Cart
import com.dipesh.rblibrary.response.*

class CartRepo: MyApiRequest() {

    private val cartAPI= ServiceBuilder.buildService(CartAPI::class.java)

    suspend fun addItemToCart(cart: Cart): AddCartResponse {
        return apiRequest {
            cartAPI.addItemToCart(
                    ServiceBuilder.token!!, cart
            )
        }
    }

    suspend fun getCartItems(): GetCartItemsResponse {
        return apiRequest {
            cartAPI.getCartItems(ServiceBuilder.token!!)
        }
    }

    suspend fun  deleteCartItem(id: String): DeleteCartResponse{
        return apiRequest {
            cartAPI.deleteCartItem(ServiceBuilder.token!!,id)
        }
    }
}