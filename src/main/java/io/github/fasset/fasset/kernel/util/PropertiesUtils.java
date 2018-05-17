/**
 * fassets - Project for light-weight tracking of fixed assets
 * Copyright © 2018 Edwin Njeru (mailnjeru@gmail.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.fasset.fasset.kernel.util;

import org.hibernate.validator.internal.metadata.location.FieldConstraintLocation;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import static org.slf4j.LoggerFactory.getLogger;

public class PropertiesUtils {

    private static final org.slf4j.Logger log = getLogger(PropertiesUtils.class);

    /**
     * Will return Properties from any file on the classpath, if is defined with the relative
     * path from the resources folder.
     * For instance the "application.properties" in the resources folder will look like this: "application".
     * Yes, leave out the ".properties" bit
     * @param fileName Filename containing property definitions
     * @return Properties read from the properties file
     */
    public static Properties fetchProperties(String fileName){

        log.info("Reading properties from the path : {}", fileName);

        Properties properties = new Properties();

        try {
            File file = ResourceUtils.getFile(String.format("classpath:%s.properties",fileName));
            InputStream in  = new FileInputStream(file);
            properties.load(in);
        } catch (java.io.IOException e) {
            log.error(e.getMessage());
        }

        return properties;
    }
}
