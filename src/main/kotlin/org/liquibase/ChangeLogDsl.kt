package org.liquibase

import liquibase.changelog.DatabaseChangeLog

@LiquibaseDslMarker
class ChangeLogDsl {

    internal val changeLog = DatabaseChangeLog()

    fun preconditions(block: () -> Boolean) {
        TODO()
    }

    fun changeSet(id: String, author: String, block: ChangeSetDsl.() -> Unit) {
        changeLog.addChangeSet(ChangeSetDsl(id, author, changeLog).apply(block).changeSet)
    }
}