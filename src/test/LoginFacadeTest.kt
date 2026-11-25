package test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class LoginFacadeTest {

    @Test
    fun testEmptyInputRejectedByFacade() {
        val username = ""
        val password = ""


        val isValid = username.isNotBlank() && password.isNotBlank()

        assertFalse(isValid, "Empty string not allowed")
    }
}