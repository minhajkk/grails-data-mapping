package org.grails.orm.hibernate.connections

import groovy.transform.CompileStatic
import org.grails.datastore.mapping.config.ConfigurationBuilder
import org.grails.datastore.mapping.core.connections.ConnectionSourceSettings
import org.springframework.core.env.PropertyResolver

/**
 * Builds the GORM for Hibernate configuration
 *
 * @author Graeme Rocher
 * @since 6.0
 */
@CompileStatic
class HibernateConnectionSourceSettingsBuilder extends ConfigurationBuilder<HibernateConnectionSourceSettings, HibernateConnectionSourceSettings> {
    HibernateConnectionSourceSettings fallBackHibernateSettings

    HibernateConnectionSourceSettingsBuilder(PropertyResolver propertyResolver, String configurationPrefix = "", ConnectionSourceSettings fallBackConfiguration = null) {
        super(propertyResolver, configurationPrefix, fallBackConfiguration)

        if(fallBackConfiguration instanceof HibernateConnectionSourceSettings) {
            fallBackHibernateSettings = (HibernateConnectionSourceSettings)fallBackConfiguration
        }
    }

    @Override
    protected HibernateConnectionSourceSettings createBuilder() {
        def settings = new HibernateConnectionSourceSettings()
        if(fallBackHibernateSettings != null) {
            settings.getHibernate().putAll( fallBackHibernateSettings.getHibernate() )
        }
        return settings
    }

    @Override
    protected HibernateConnectionSourceSettings toConfiguration(HibernateConnectionSourceSettings builder) {
        return builder
    }
}
