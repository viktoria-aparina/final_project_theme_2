package org.pm.ui.pages;

import org.pm.ui.utils.PropertiesLoader;

import java.util.Properties;

public abstract class BasePage {

  private static final String CONFIG_PROPERTIES = "config.properties";
  protected static final Properties properties = PropertiesLoader.loadProperties(CONFIG_PROPERTIES);

  public abstract boolean isPageOpened();
}
