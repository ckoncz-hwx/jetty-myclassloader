Run embedded Jetty:

```
mvn compile exec:java -Dexec.mainClass=com.ck.webapp1.Main -Dexec.classpathScope=compile
```

Run with Jetty plugin:
```
mvn package jetty:deploy-war
```
 