### Run Jetty embedded via Java code:

```
mvn compile exec:java -Dexec.mainClass=com.ck.webapp1.Main -Dexec.classpathScope=compile
```

### Launch with Jetty plugin:
```
mvn package jetty:deploy-war
```

### Run without packaging all the time
`webapp1-runner` does not have the webapp as dependency, so packaging it will not package webapp1 (saves time, and allows you to change thw war before launching Jetty.
```
mvn package
mvn -am -pl webapp1-runner package jetty:deploy-war
```

### Classpath sorting
System property `myclassloader.sort` with values `asc` or `desc` triggers classpath sorting.

Sort the jars in ascending alphabetic order:
```
mvn -Dmyclassloader.sort=asc -am -pl webapp1-runner package jetty:deploy-war
```
