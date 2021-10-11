package com.example.posttest

import com.google.gson.annotations.SerializedName

class UsersList{
    @SerializedName("name")
    var name: String? = null
    @SerializedName("location")
    var location: String? = null
}