package ru.cg;

import java.util.ArrayList;
import java.util.List;

import com.zaxxer.hikari.HikariDataSource;

import ru.cg.datasource.DataSourceProvider;
import ru.cg.model.Project;

/**
 * @author Rinat Suleymanov
 */
public class App {

  public static void main(String[] args) {
    DataSourceProvider provider = new DataSourceProvider();
    HikariDataSource projectDataSource = provider.createProjectDataSource();
    HikariDataSource fiasDataSource = provider.createFiasDataSource();

    List<FiasConverter> converters = new ArrayList<>();
    converters.add(new FiasConverter(Project.GAI_APPLICANT, projectDataSource, fiasDataSource));
    converters.add(new FiasConverter(Project.GAI_DIVISION_ADDRESS, projectDataSource, fiasDataSource));
    converters.add(new FiasConverter(Project.GAI_OBJECT_ADDRESS, projectDataSource, fiasDataSource));
    converters.add(new FiasConverter(Project.GAI_ORGANIZATION_VERSION_ADDRESS, projectDataSource, fiasDataSource));

    converters.forEach(FiasConverter::convert);
  }
}
