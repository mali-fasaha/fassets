/*
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
package io.github.fasset.fasset.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * This is a configuration for storage location for uploaded files
 *
 * @author edwin.njeru
 * @version $Id: $Id
 */
@ConfigurationProperties("storage")
public class StorageProperties {

    private String location = "temp-uploads";

    /**
     * <p>Getter for the field <code>location</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getLocation() {
        return location;
    }

    /**
     * <p>Setter for the field <code>location</code>.</p>
     *
     * @param location a {@link java.lang.String} object.
     * @return a {@link io.github.fasset.fasset.config.StorageProperties} object.
     */
    public StorageProperties setLocation(String location) {
        this.location = location;
        return this;
    }
}
