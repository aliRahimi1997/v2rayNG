package com.v2ray.ang.ui.main

import org.junit.Assert.assertEquals
import org.junit.Test

class SubscriptionImportNameTest {

    @Test
    fun returnsUnmodifiedNameWhenNotPresent() {
        assertEquals("Sub", uniqueSubscriptionName("Sub", setOf("Other", "Sub 2")))
    }

    @Test
    fun appendsIncrementingSuffixWhenNameExists() {
        assertEquals("Sub 2", uniqueSubscriptionName("Sub", setOf("Sub")))
        assertEquals("Sub 3", uniqueSubscriptionName("Sub", setOf("Sub", "Sub 2")))
        assertEquals("Sub 4", uniqueSubscriptionName("Sub", setOf("Sub", "Sub 2", "Sub 3")))
    }

    @Test
    fun reusesFirstAvailableGap() {
        assertEquals("Sub 2", uniqueSubscriptionName("Sub", setOf("Sub", "Sub 3")))
    }
}
