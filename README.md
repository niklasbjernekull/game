## Layout

- __`core`__
  - __`src/main/resource`:__ assets
  - __`src/main/java`:__ code, platform agnostic
- __`desktop`:__ contains launcher


## Building

Requires a recent version of maven. Tested with maven 3.1 and 3.2.


#### Create distributable

```
$ mvn clean package
```

Builds the project, runnable jar-with-dependencies in `./desktop/target/`


#### Build and run

```
$ mvn clean install
```

Automatically starts the game once built.
