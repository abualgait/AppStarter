package com.company.app.util

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Delegate for accessing [ViewModel] property through [SavedStateHandle]
 */
fun <T> SavedStateHandle.property(): ReadWriteProperty<Any, T?> = SavedStateProperty(this)

/**
 * Delegate for accessing [ViewModel] property through [SavedStateHandle] with a default initial value
 */
fun <T> SavedStateHandle.property(default: T): ReadWriteProperty<Any, T> =
    SavedStatePropertyWithDefault(this, default)

private class SavedStateProperty<T>(
    private val handle: SavedStateHandle
) : ReadWriteProperty<Any, T?> {

    override fun getValue(thisRef: Any, property: KProperty<*>): T? {
        return handle[property.name]
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T?) {
        handle[property.name] = value
    }
}

private class SavedStatePropertyWithDefault<T>(
    private val handle: SavedStateHandle,
    private val default: T
) : ReadWriteProperty<Any, T> {

    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        return handle[property.name] ?: default
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        handle[property.name] = value
    }
}
