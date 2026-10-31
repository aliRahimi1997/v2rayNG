package com.v2ray.ang.ui.main

import java.util.UUID

/** Main-thread request ownership; a late reply must not complete a newer test. */
internal class MainTestRequests {
    data class Bulk(val id: String, val groupId: String)

    private var current: String? = null
    var lastCompletedCurrent: String? = null
        private set
    var bulk: Bulk? = null
        private set
    val isTesting: Boolean get() = current != null || bulk != null

    fun beginCurrent(): String = UUID.randomUUID().toString().also {
        current = it
        lastCompletedCurrent = null
    }

    fun completeCurrent(id: String): Boolean {
        if (id != current) return false
        current = null
        lastCompletedCurrent = id
        return true
    }

    fun isCurrentOrLastCompleted(id: String): Boolean = current == id || lastCompletedCurrent == id

    fun invalidateCurrent() {
        current = null
        lastCompletedCurrent = null
    }

    fun beginBulk(groupId: String): Bulk = Bulk(UUID.randomUUID().toString(), groupId).also { bulk = it }

    fun completeBulk(id: String): Bulk? {
        val request = bulk?.takeIf { it.id == id } ?: return null
        bulk = null
        return request
    }

    fun cancelBulk() {
        bulk = null
    }
}
