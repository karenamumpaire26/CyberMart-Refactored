package test.kotlin

import database.DatabaseConnection
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class DatabaseConnectionTest {

    @Test
    fun testSingletonReturnsSameInstance() {

        val instance1 = DatabaseConnection.getInstance()
        val instance2 = DatabaseConnection.getInstance()


        assertNotNull(instance1, "The database connection instance should not be null")
        assertSame(instance1, instance2, "getInstance() must return the exact same object every time")
    }
}