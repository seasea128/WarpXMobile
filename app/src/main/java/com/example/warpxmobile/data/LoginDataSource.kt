package com.example.warpxmobile.data

import com.example.warpxmobile.data.model.LoggedInUser
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    fun login(username: String, password: String): Result<LoggedInUser> {
        //if (username != "admin" && password != "admin") {
        //    return Result.Error(IOException("Error logging in"))
        //}
        return Result.Success(LoggedInUser(java.util.UUID.randomUUID().toString(), "test"))

        // try {
        //     // TODO: handle loggedInUser authentication
        //     val fakeUser = LoggedInUser(java.util.UUID.randomUUID().toString(), "Jane Doe")
        //     return Result.Success(fakeUser)
        // } catch (e: Throwable) {
        //     return Result.Error(IOException("Error logging in", e))
        // }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}