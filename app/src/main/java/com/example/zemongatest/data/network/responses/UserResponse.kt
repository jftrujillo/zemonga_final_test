package com.example.zemongatest.data.network.responses

import com.google.gson.annotations.SerializedName

class UserResponse (
    @SerializedName("id") val userId: Int,
    val name: String,
    val email: String,
    val phone: String,
    val website: String
)