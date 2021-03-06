package com.rokas.barokas.screen.details.model

data class UserDomain(
    val id: Int,
    val name: String
) {
    fun toEntity(): UserEntity = UserEntity(
        id, name
    )
}