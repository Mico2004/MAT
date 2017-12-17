package com.qualitest.utils.java;

import org.apache.log4j.Logger;
import org.junit.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

    public class PropertiesGetter {

        Logger logger=Logger.getLogger(PropertiesGetter.class);

        public File file;

        FileInputStream fileInput = null;

        Properties prop=null;

        public PropertiesGetter() throws IOException {

            System.setProperty("Environment","qa");

            if(System.getProperty("Environment").equals("dev"))
                file = new File ("src/main/resources/dev.properties");

            else if(System.getProperty("Environment").equals("qa"))
                file = new File ("src/main/resources/qa.properties");

            else if(System.getProperty("Environment").equals("perf"))
                file = new File ("src/main/resources/perf.properties");

            else if(System.getProperty("Environment").equals("prod"))
                file = new File ("src/main/resources/prod.properties");

            fileInput = new FileInputStream(file);

            prop = new Properties();

            prop.load(fileInput);

        }

        public String getProp(String key){

            try{

                return prop.getProperty(key);

            }catch (Exception e){

                Assert.assertTrue("Key: "+key+", doesn't exist in the property file",false);

                return null;
            }

        }

    }