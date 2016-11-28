# Java Configuration

1. You have been given the ApplicationConfiguration class with a bean definition for a DataSource
to an in memory database. Add other bean definitions to the configuration class to have a NumberReaderImpl and
JdbcNumberStorer.

   Hint: ApplicationConfiguration may need an annotation and you will a JdbcTemplate bean too...
   
2. Create a new `@Configuraton` class called `DataAccessConfiguration`. Move the bean definition for the JdbcNumberStorer here
and add a second bean definition for a FileNumberStorer. Add `@Import(DataAccessConfiguration.class)` onto ApplicationConfiguration
to reference this new `@Configuration` class. Additionally in ApplicationConfiguration, add a field of type NumberStorer and annotate
this field with `@Autowired`. Now alter the bean definition of NumberReaderImpl to reference the new field in its constructor. See below...

   ```java
@Configuration
@Import(DataAccessConfiguration.class)
public class ApplicationConfiguration {

    @Autowired
    private NumberStorer numberStorer;
...

    @Bean
    public NumberReader numberReader() {
        return new NumberReaderImpl(numberStorer);
    }
}
```

   Run the application. You should see Spring complain that there are more than one candidate bean of type
   NumberStorer.

   Use the `@Profile` annotation on the bean definition for FileNumberStorer in DataAccessConfiguration.
   
   Run the application and you will and it will start up and wire in a JdbcNumberStorer
    
3. Activate the profile with `-Dspring.profiles.active` in your run configuration. You must set the value of this to match the profile value you gave to
   the bean definition (`-Dspring.profiles.active=file`, `@Profile("file")`).
   
   Run the application and we will once again see that the application will fail to start as Spring once again
   cannot find a unique bean definition for NumberStorer.
   
   Add the `@Primary` annotation and run again.