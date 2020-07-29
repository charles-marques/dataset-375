/*
 * Copyright (C) 2009  AndroidNerds.org
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.androidnerds.app.aksunai.util;

import java.util.HashMap;

/**
 * LowerHashMap extends on HashMap and store/retrieves the keys in lowercase.
 */
public class LowerHashMap<V> extends HashMap<String, V> {
    @Override
    public V get(Object key) {
        return super.get(((String) key).toLowerCase());
    }

    @Override
    public V put(String key, V value) {
        return super.put(key.toLowerCase(), value);
    }
}

