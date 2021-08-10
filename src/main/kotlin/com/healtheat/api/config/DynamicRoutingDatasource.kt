package com.healtheat.api.config

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource
import org.springframework.transaction.support.TransactionSynchronizationManager

class DynamicRoutingDatasource : AbstractRoutingDataSource() {

    @Override
    override fun determineCurrentLookupKey(): Any? {
        val isReadOnly: Boolean = TransactionSynchronizationManager.isCurrentTransactionReadOnly();

        return if (isReadOnly) "slave" else "master"
    }
}
