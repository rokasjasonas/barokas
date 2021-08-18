package com.rokas.barokas.screen.details.model

data class UserResponse(
    val id: Int = 0,
    val name: String = ""
) {
    fun toDomain() = UserDomain(id, name)
}