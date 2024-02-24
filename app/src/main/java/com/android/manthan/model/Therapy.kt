package com.android.manthan.model

import com.google.gson.annotations.SerializedName

data class Therapy(
        @SerializedName("data")
        var data: ArrayList<Data>? = null,
        @SerializedName("message")
        var message: String? = null,
        @SerializedName("status")
        var status: Int? = null,
        @SerializedName("success")
        var success: Boolean? = null,
        @SerializedName("token")
        var token: String? = null,
) {
    data class Data(
            @SerializedName("created_at")
            var createdAt: String? = null,
            @SerializedName("id")
            var id: Int? = null,
            @SerializedName("image_nm")
            var imageNm: String? = null,
            @SerializedName("image_path")
            var imagePath: String? = null,
            @SerializedName("name")
            var name: String? = null,
            @SerializedName("profile")
            var profile: String? = null,
            @SerializedName("status")
            var status: String? = null,
            @SerializedName("updated_at")
            var updatedAt: String? = null,
    )
}