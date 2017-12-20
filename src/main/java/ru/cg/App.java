package ru.cg;

import ru.cg.datasource.DataSourceProvider;
import ru.cg.model.Project;

/**
 * @author Rinat Suleymanov
 */
public class App {

  public static void main(String[] args) {
    DataSourceProvider provider = new DataSourceProvider();
    FiasConverter fiasConverter = new FiasConverter(
        Project.ZAGS,
        provider.createProjectDataSource(),
        provider.createFiasDataSource());
    fiasConverter.convert();
  }
}
