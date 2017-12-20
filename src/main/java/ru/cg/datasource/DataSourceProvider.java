package ru.cg.datasource;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Tim Urmancheev
 */
public final class DataSourceProvider {

  private static final Logger logger = LoggerFactory.getLogger(DataSourceProvider.class);

  private Properties prop;

  public DataSourceProvider() {
    prop = new Properties();
    try {
      prop.load(new FileInputStream(System.getProperty("props")));
    }
    catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public HikariDataSource createFiasDataSource() {
    HikariConfig datasourceConfig = new HikariConfig();

    datasourceConfig.setUsername(prop.getProperty("fias.username"));
    datasourceConfig.setPassword(prop.getProperty("fias.password"));
    datasourceConfig.setDriverClassName(prop.getProperty("fias.driver"));
    datasourceConfig.setJdbcUrl(prop.getProperty("fias.url"));
    datasourceConfig.setMaximumPoolSize(100);

    logger.info(
        "Creating project db data source, user: [{}], url:[{}], maxPoolSize: [{}]",
        datasourceConfig.getUsername(), datasourceConfig.getJdbcUrl(), datasourceConfig.getMaximumPoolSize()
    );

    return new com.zaxxer.hikari.HikariDataSource(datasourceConfig);
  }

  public HikariDataSource createProjectDataSource() {
    HikariConfig datasourceConfig = new HikariConfig();

    datasourceConfig.setUsername(prop.getProperty("project.username"));
    datasourceConfig.setPassword(prop.getProperty("project.password"));
    datasourceConfig.setDriverClassName(prop.getProperty("project.driver"));
    datasourceConfig.setJdbcUrl(prop.getProperty("project.url"));
    datasourceConfig.setMaximumPoolSize(100);

    logger.info(
        "Creating project db data source, user: [{}], url:[{}], maxPoolSize: [{}]",
        datasourceConfig.getUsername(), datasourceConfig.getJdbcUrl(), datasourceConfig.getMaximumPoolSize()
    );

    return new com.zaxxer.hikari.HikariDataSource(datasourceConfig);
  }
}
