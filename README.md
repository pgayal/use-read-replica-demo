# Use Read Replica Demo for Select Queries

This is a spring boot application to demo the use of readonly-db/read-replica for select queries.

## How does it work?

We have to configure 2 data sources where default data source(primary data source) connects to writer and other one
connects to the read-replica. Separate set of connection parameters are defined in the `application.properties`

### Primary Data Source

Configuration: [PrimaryDataSourceConfiguration.java](/src/main/java/com/example/usereadreplicademo/config/PrimaryDataSourceConfiguration.java)
This is same data source config as we currently have. We just need to make we add excludeFilters in
`@EnableJpaRepositories` annotation to exclude the ready-only repositories.

```
@EnableJpaRepositories(
        basePackages = "com.example.usereadreplicademo",
        excludeFilters = @ComponentScan.Filter(ReadOnlyRepository.class),
        entityManagerFactoryRef = "entityManagerFactory"
)
```

**Everything else stays same no updates to repositories or entities**
<br/><br/>


### Read-Only Data Source
Configuration: [ReadOnlyDataSourceConfiguration.java](/src/main/java/com/example/usereadreplicademo/config/ReadOnlyDataSourceConfiguration.java)
This is new data source configured to connect to read-replica and register the JPA repositories annotated with `@ReadOnlyRepository`
All the queries done using the read-only repositories will go against the read replica.
```
@EnableJpaRepositories(
        basePackages = "com.example.usereadreplicademo",
        includeFilters = @ComponentScan.Filter(ReadOnlyRepository.class),
        entityManagerFactoryRef = "readOnlyEntityManagerFactory"
)
```
<b>Properties</b>: `spring.readonly.datasource` in `application.properties`

<br/>

### Implementation Steps to Move Heavy Load Select Queries to Read-Replica

1. New Repository
   - Create new read-only repository for related entity - refer [EngagementReadOnlyRepository.java](/src/main/java/com/example/usereadreplicademo/repositories/EngagementReadOnlyRepository.java)
   - Extends `ReadOnlyBaseRepository`
   - Annotate `@ReadOnlyRepository` - VERY IMPORTANT
2. Add required method or query to newly created class
3. Inject this new repository in the required service class and call method
4. DONE

<br/>

### Classes to Refer
1. [application.properties](/src/main/resources/application.properties)
2. [PrimaryDataSourceConfiguration.java](/src/main/java/com/example/usereadreplicademo/config/PrimaryDataSourceConfiguration.java)
3. [ReadOnlyDataSourceConfiguration.java](/src/main/java/com/example/usereadreplicademo/config/ReadOnlyDataSourceConfiguration.java)
4. [ReadOnlyRepository.java](/src/main/java/com/example/usereadreplicademo/annotation/ReadOnlyRepository.java) - annotation to identify read-only repositories
5. [EngagementReadOnlyRepository.java](/src/main/java/com/example/usereadreplicademo/repositories/ReadOnlyBaseRepository.java) - base repository to create entity specific read-only repository and to restrict save/update/delete operations 
6. [EngagementReadOnlyRepository.java](/src/main/java/com/example/usereadreplicademo/repositories/EngagementReadOnlyRepository.java) - example of read-only repository for Engagement entity


