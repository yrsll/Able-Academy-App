package com.example.ablemovieapi.data

import com.google.gson.annotations.SerializedName

data class Poster(
    @SerializedName("file_path")val filePath: String
): java.io.Serializable
