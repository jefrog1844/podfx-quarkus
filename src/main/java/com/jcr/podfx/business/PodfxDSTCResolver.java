/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcr.podfx.business;

import java.sql.SQLException;

import javax.enterprise.context.ApplicationScoped;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;

import io.agroal.api.AgroalDataSource;
import static io.agroal.api.configuration.AgroalConnectionPoolConfiguration.ConnectionValidator.defaultValidator;
import io.agroal.api.configuration.AgroalDataSourceConfiguration;
import io.agroal.api.configuration.supplier.AgroalDataSourceConfigurationSupplier;
import io.agroal.api.security.NamePrincipal;
import io.agroal.api.security.SimplePassword;
import io.quarkus.arc.Unremovable;
import io.quarkus.hibernate.orm.runtime.JPAConfig;
import io.quarkus.hibernate.orm.runtime.customized.QuarkusConnectionProvider;
import io.quarkus.hibernate.orm.runtime.tenant.TenantConnectionResolver;
import java.sql.Connection;
import java.sql.Statement;
import static java.time.Duration.ofSeconds;
import javax.inject.Inject;
import org.hibernate.MultiTenancyStrategy;

@ApplicationScoped
@Unremovable
public class PodfxDSTCResolver implements TenantConnectionResolver {

    @Inject
    JPAConfig jpaConfig;

    @Override
    public ConnectionProvider resolve(String tenant) {
        MultiTenancyStrategy strategy = jpaConfig.getMultiTenancyStrategy();
        QuarkusConnectionProvider quarkusConnectionProvider;
        try {
            AgroalDataSource agroalDataSource = AgroalDataSource.from(createDataSourceConfiguration(tenant));
            quarkusConnectionProvider = new TenantConnectionProvider(tenant, agroalDataSource);

        } catch (SQLException ex) {
            throw new IllegalStateException("Failed to create a new data source based on the tenantId: " + tenant, ex);
        }

        return quarkusConnectionProvider;
    }

    private static AgroalDataSourceConfiguration createDataSourceConfiguration(
            String tenant) {
        return new AgroalDataSourceConfigurationSupplier()
                .dataSourceImplementation(AgroalDataSourceConfiguration.DataSourceImplementation.AGROAL)
                .metricsEnabled(false)
                .connectionPoolConfiguration(cp -> cp
                .minSize(0)
                .maxSize(5)
                .initialSize(0)
                .connectionValidator(defaultValidator())
                .acquisitionTimeout(ofSeconds(5))
                .leakTimeout(ofSeconds(5))
                .validationTimeout(ofSeconds(50))
                .reapTimeout(ofSeconds(500))
                .connectionFactoryConfiguration(cf -> cf
                .jdbcUrl("jdbc:postgresql://Macbook-air.local:5432/podfx")
                .connectionProviderClassName("org.postgresql.Driver")
                .principal(new NamePrincipal("podfx"))
                .credential(new SimplePassword(""))
                )
                ).get();
    }

    private static class TenantConnectionProvider extends QuarkusConnectionProvider {

        private final String tenantId;

        public TenantConnectionProvider(String tenantId,
                AgroalDataSource dataSource) {
            super(dataSource);
            this.tenantId = tenantId;
        }

        @Override
        public Connection getConnection() throws SQLException {
            Connection conn = super.getConnection();
            //conn.setSchema(tenantId);
            /**
             * The following policy must exist on each table that has a tenant
             * column
             *
             * CREATE POLICY tenant_isolation_policy ON tenant USING (tenant_id
             * = current_setting('app.current_tenant')::String);
             *
             * https://www.postgresql.org/docs/9.5/ddl-rowsecurity.html
             * https://aws.amazon.com/blogs/database/multi-tenant-data-isolation-with-postgresql-row-level-security/
             */

            Statement sql = conn.createStatement();
            sql.execute("SET app.current_tenant = '" + tenantId + "'");

            return conn;
        }

    }

}
