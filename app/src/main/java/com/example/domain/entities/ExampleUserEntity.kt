package com.example.domain.entities

import androidx.annotation.Nullable

/**
 * This entity is shared between Presentation and Data - they both can access it.
 */
class ExampleUserEntity(@Nullable userID: String?, @Nullable userName: String?) {

    var mUserID: String? = userID
    var mUserName: String? = userName

}
