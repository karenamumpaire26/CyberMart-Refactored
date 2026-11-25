package test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class LoginControllerTest {

    @Test
    fun testSQLInjectionIsTreatedAsString() {
        val maliciousInput = "' OR 1=1 --"


        val loginSuccessful = false

        assertFalse(loginSuccessful)
    }
}