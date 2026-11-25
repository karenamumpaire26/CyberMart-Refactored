package test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ObserverPatternTest {

    @Test
    fun testObserverUpdatesMessageOnEvent() {
        var messageLabel = "Waiting..."


        val eventAction = { messageLabel = "Welcome to Cyber Mart" }
        eventAction()

        assertEquals("Welcome to Cyber Mart", messageLabel)
    }
}