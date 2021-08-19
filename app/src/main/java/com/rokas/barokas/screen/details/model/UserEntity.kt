package com.rokas.barokas.screen.details.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rokas.barokas.screen.details.model.UserEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class UserEntity(
    @PrimaryKey
    val id: Int,
    val name: String
) {
    fun toDomain() = UserDomain(
        id,
        name
    )

    companion object {
        const val TABLE_NAME = "users"
    }
}
