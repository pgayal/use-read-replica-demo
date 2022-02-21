# Use Read Replica Demo for Select Queries

This is a spring boot application to demo the use of readonly-db/read-replica for select queries.

## How does it work?

We have to configure 2 data sources where default data source(primary data source) connects to writer and other one
connects to the read-replica. Separate set of connection parameters are defined in the application.properties

#### Primary Data Source

Configuration: [PrimaryDataSourceConfiguration.java](/src/main/java/com/example/usereadreplicademo/config/PrimaryDataSourceConfiguration.java)
This is same data source config as we currently have. We just need to make we add excludeFilters in
@EnableJpaRepositories annotation to exclude the ready-only repositories.

```
@EnableJpaRepositories(
        basePackages = "com.example.usereadreplicademo",
        excludeFilters = @ComponentScan.Filter(ReadOnlyRepository.class),
        entityManagerFactoryRef = "entityManagerFactory"
)
```

#### Read-Only Data Source

Configuration: [ReadOnlyDataSourceConfiguration.java](/src/main/java/com/example/usereadreplicademo/config/ReadOnlyDataSourceConfiguration.java)
This is new data source configured to connect to read-replica and register the JPA repo
