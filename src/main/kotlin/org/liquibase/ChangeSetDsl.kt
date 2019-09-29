package org.liquibase

import liquibase.changelog.ChangeSet
import liquibase.changelog.DatabaseChangeLog

@LiquibaseDslMarker
class ChangeSetDsl(id: String, author: String, changeLog: DatabaseChangeLog) {

    internal val changeSet = ChangeSet(id, author, false, false, "", "", "", changeLog)


    fun createIndex(name: String, block: () -> Unit) {
        changeSet.changeLog
        TODO()
    }

    fun createTable(name: String, block: TableBlock) {
        changeSet.addChange(CreateTableDsl(name).apply(block).createTableChange)
    }

    fun createSequence(name: String, block: () -> Unit) {
        TODO()
    }

    fun createProcedure(name: String, block: () -> Unit) {
        TODO()
    }

    fun createView(name: String, block: () -> Unit) {
        TODO()
    }
}