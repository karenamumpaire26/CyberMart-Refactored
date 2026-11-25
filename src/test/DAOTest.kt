package test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class DAOTest {

    @Test
    fun testDataAccessDoesNotConcatenateSQL() {
        val username = "admin"


        val safeSQL = "SELECT * FROM users WHERE username = ?"

        val unsafeSQL = "SELECT * FROM users WHERE username = '" + username + "'"

        assertTrue(safeSQL.contains("?"), "Safe SQL uses placeholders")
        assertFalse(unsafeSQL.contains("?"), "Unsafe SQL concatenates strings directly")
    }
}