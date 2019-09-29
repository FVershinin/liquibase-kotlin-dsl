package org.liquibase

import liquibase.changelog.DatabaseChangeLog

fun changeLog(block: ChangeLogDsl.() -> Unit): DatabaseChangeLog {
    return ChangeLogDsl().apply(block).changeLog
}