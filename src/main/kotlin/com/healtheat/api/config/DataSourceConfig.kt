package com.healtheat.api.config

import com.zaxxer.hikari.HikariDataSource
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource

@Slf4j
@Configuration
@EnableAutoConfiguration(exclude = [DataSourceAutoConfiguration::class])
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = ["com.healtheat.api"])
class DataSourceConfig() {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.master")
    fun masterDataSource(): DataSource {
        return DataSourceBuilder.create()
            .type(HikariDataSource::class.java)
            .build()
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    fun slaveDataSource(): DataSource {
        return DataSourceBuilder.create()
            .type(HikariDataSource::class.java)
            .build()
    }

    @Bean
    fun routingDataSource(
        @Qualifier("masterDataSource") masterDataSource: DataSource,
        @Qualifier("slaveDataSource") slaveDataSource: DataSource,
    ): DataSource? {

        val dataSourceMap: Map<Any, Any> = mutableMapOf(
            "master" to masterDataSource,
            "slave" to slaveDataSource
        )

        val routingDataSource: DynamicRoutingDatasource = DynamicRoutingDatasource()
        routingDataSource.setTargetDataSources(dataSourceMap)
        routingDataSource.setDefaultTargetDataSource(masterDataSource)

        return routingDataSource
    }

    @Primary
    @Bean
    fun dataSource(@Qualifier("routingDataSource") routingDataSource: DataSource): DataSource {
        return LazyConnectionDataSourceProxy(routingDataSource)
    }
}
