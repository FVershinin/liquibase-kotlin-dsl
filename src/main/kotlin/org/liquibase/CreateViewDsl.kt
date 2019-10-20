package org.liquibase

import liquibase.change.core.CreateViewChange

class CreateViewDsl(name: String) {

    internal val definition = CreateViewChange().also {
         it.viewName = name
    }

    var description: String
        get() = TODO()
        set(value) {
            definition.remarks = value
        }

    var sql: String
        get() = TODO()
        set(value) {
            definition.selectQuery = value
        }

    fun replaceIfExists() {
        definition.replaceIfExists = true
    }
}