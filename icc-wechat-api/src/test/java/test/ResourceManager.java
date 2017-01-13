/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package test;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Manager class to get localized resources.
 */
public class ResourceManager {
    private ResourceBundle bundle;

    ResourceManager(String baseName, Locale locale) {
        this.bundle = ResourceBundle.getBundle(baseName, locale);
    }

    public static ResourceManager getInstance(String baseName) {
        return new ResourceManager(baseName, Locale.getDefault());
    }

    public static ResourceManager getInstance(String baseName, Locale locale) {
        return new ResourceManager(baseName, locale);
    }

    public String getString(String key) {
        return bundle.getString(key);
    }

    public String getFormattedString(String key, Object... args) {
        return MessageFormat.format(getString(key), args);
    }
    
    /**
     * 
     * @param key
     * @return
     */
    public static String getProperty(String key) {
		InputStream in = ResourceManager.class.getResourceAsStream("url.properties");
		Properties p = new Properties();
		try {
			p.load(in);
		} catch (IOException e) {
			new RuntimeException(e.getMessage());
		}
		return p.getProperty(key);
	}
    
    public static void testReadProperties(){
		Locale local=new Locale("zh", "CN");
		//classes下的路径
		ResourceBundle rb=ResourceBundle.getBundle("config/url",local);
		//包名.文件名
		ResourceBundle rb1=ResourceBundle.getBundle("test.url",local);
		System.err.println(rb.getString("test"));
		
	}
    public static void main(String[] args) {
		System.err.println(getProperty("test"));
	}
}
