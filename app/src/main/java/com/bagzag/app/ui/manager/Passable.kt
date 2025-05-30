package com.bagzag.app.ui.manager

interface Passable<in T> {
    fun passData(t: T)
}
