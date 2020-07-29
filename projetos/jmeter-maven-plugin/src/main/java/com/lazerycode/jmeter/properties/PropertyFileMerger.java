package com.lazerycode.jmeter.properties;

import com.lazerycode.jmeter.JMeterMojo;

import java.util.Map;
import java.util.Properties;

/**
 * Handler that can merge Properties objects and Maps
 *
 * @author Arne Franken, Mark Collin
 */
public class PropertyFileMerger extends JMeterMojo {

    public PropertyFileMerger() {
    }

    /**
     * Merge two properties files together.
     * The additions will overwrite any existing properties in source if required.
     *
     * @param source
     * @param additions
     * @return
     */
    public Properties mergePropertiesFiles(Properties source, Properties additions) {
        if (source == null && additions == null) {
            return new Properties();
        } else if (source == null) {
            return stripReservedProperties(additions);
        } else if (additions == null) {
            return stripReservedProperties(source);
        }
        Properties merged = source;
        merged.putAll(additions);
        return stripReservedProperties(merged);
    }
    
    /**
     * Merge given Map into given Properties object
     *
     * @param customProperties Map to merge into the Properties object
     * @return merged Properties object
     */
    public Properties mergeProperties(Map<String, String> customProperties, Properties baseProperties) {
        if (customProperties != null && !customProperties.isEmpty()) {
            for (String key : customProperties.keySet()) {
                baseProperties.setProperty(key, customProperties.get(key));
                warnUserOfPossibleErrors(key, baseProperties);
            }
        }
        baseProperties = stripReservedProperties(baseProperties);
        return baseProperties;
    }

    //==================================================================================================================

    /**
     * This will strip all reserved properties from a Properties object.
     * (Used to ensure that restricted properties haven't been set in custom properties files)
     *
     * @param propertyFile
     * @return
     */
    private Properties stripReservedProperties(Properties propertyFile) {
        for (ReservedProperties reservedProperty : ReservedProperties.values()) {
            if (propertyFile.containsKey(reservedProperty.getPropertyKey())) {
                propertyFile.remove(reservedProperty.getPropertyKey());
                getLog().warn("Unable to set '" + reservedProperty.getPropertyKey() + "', it is a reserved property in the jmeter-maven-plugin");
            }
        }
        return propertyFile;
    }

    /**
     * Print a warning out to the user to highlight potential typos in the properties they have set.
     *
     * @param value
     */
    private void warnUserOfPossibleErrors(String value, Properties baseProperties) {
        for (String key : baseProperties.stringPropertyNames()) {
            if (!key.equals(value) && key.toLowerCase().equals(value.toLowerCase())) {
                getLog().warn("You have set a property called '" + value + "' which is very similar to '" + key + "'!");
            }
        }
    }
}