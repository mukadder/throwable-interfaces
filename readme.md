# Throwable interfaces

In an attempt to reduce `try/catch` blocks inside lambda statements, this project extends all the major functional 
interfaces found in `java.util.function.*` with new functional interfaces that allow checked exceptions.
 
## Basic usage

As an example for the new `FunctionWithThrowable` interface, consider we have a `Stream<File> fileStream`, which we wanted to map into its content. 

    Stream<String> content = fileStream.map(file -> {
       try {
          return IOUtils.readFile(file);
       } catch(IOException ioException) {
          throw new RuntimeException(ioException);
       }
    });
    
With the `FunctionWithThrowable` interface, this can be cleaned up like this:
    
    Stream<String> content = fileStream.map(FunctionWithThrowable.castFunctionWithThrowable(file -> {
          return IOUtils.readFile(file);
    }));
    
Using a static import and method reference, we can even make this more streamline.    
    
    Stream<String> content = fileStream.map(castFunctionWithThrowable(IOUtils::readFile));
    
## Interfaces



### &lt;E&gt; Generic

Each new interface extends the original interface with the same generics, plus an additional `<E extends Throwable>` 
that denotes the exception type that this will throw.



The an implementation of the Function interface: 

    Function<File, String> readingFunction = (file) -> {
       try {
          return IOUtils.readFile(file);
       } catch(IOException ioException) {
          throw new RuntimeException(ioException);
       }
    }

Can be expressed as:

    FunctionWithThrowable<File, String, IOException> readingFunction = (file) -> {
          return IOUtils.readFile(file);
    }
    
### Default method
    
Each new 'WithThrowable' interface has two methods.
    
    FunctionWithThrowable<File, String, IOException> readingFunction = (file) -> {
              return IOUtils.readFile(file);
    }

    // returns string, throws IOException.
    readingFunction.applyWithThrowable(file); 
    
    // returns string, internally calls .applyWithThrowable but does not 
    // throw an IOException, instead throws SuppressedException.
    readingFunction.apply(file); 

This means you can use the 'WithThrowable' interface interchangeably with the original 
interfaces without having the compiler complain about undeclared thrown exceptions. 

You can still catch/rethrow the exceptions with

    try {
      return fileStream.forEach(readingFunction);
    } catch(SuppressedException e) {
      throw (IOException) e.getCause();
    }
    
    
### Cast method

Each new interface has a static method that casts a lamba. This is useful if you want to use the original interface 
as a type, but with the internal of the new "WithThrowable" interface.


    Function<File, String> readingFunction = FunctionWithThrowable.castFunctionWithThrowable((file) -> {
       return IOUtils.readFile(file);
    });
    
### Convert Method
   
If you wanted to convert the original interface to the "WithThrowable" interface:
    
    Function<File, String> someFunction = ...;
    Function<File, String> readingFunction = FunctionWithThrowable.asFunctionWithThrowable(someFunction);
        
        
## Exception Handling

The interfaces come with some handy methods:

### withLogging(), withLogging(logger,  message), withLogging(logger)

Returns an interface that will log messages when a exception has occurred. Uses slf4j loggers.

    FunctionWithThrowable.castFunctionWithThrowable((file) -> {
       return IOUtils.readFile(file);
    }).withLogging();
    

### thatThrowsNothing()

Returns an interface that does nothing on exceptions. Only applicable on interfaces that specify no return type.

    // no exception will be thrown
    ConsumerWithThrowable.castConsumerWithThrowable((file) -> {
       if(!file.exists()) {
          throw new IOException("file does not exist");
       }
    }).thatThrowsNothing();
    
    
### thatIgnores(Class<? extends Throwable> ... throwableClasses)

Returns an interface that does nothing on exceptions. Only applicable on interfaces that specify no return type.

    // no exception will be thrown
    ConsumerWithThrowable.castConsumerWithThrowable((file) -> {
       if(!file.exists()) {
          throw new IOException("file does not exist");
       }
    }).thatIgnores(IOException.class); 

### thatReturnsOptional()


Returns an interface that will return Optional of the return type, which will be empty if there has been an error.
Only applicable on interfaces that return non-primitive types.

### thatReturnsOnCatch(defaultValue)

Returns an interface that will return some defaultValue if an exception occurs. Only applicable on interfaces that specify a return type.
    
    
## About the library

### Site

A site is generated at http://stefanliebenberg.github.io/throwable-interfaces/

### Javadocs

Javadocs at http://stefanliebenberg.github.io/throwable-interfaces/apidocs

### Maven ( and the rest )

This is published in central,
  
    <dependency>
      <groupId>org.slieb</groupId>
      <artifactId>throwable-interfaces</artifactId>
      <version>...</version>
    </dependency>
 
For latest version, see http://stefanliebenberg.github.io/throwable-interfaces/dependency-info.html


### How it was built:
  
  All the interfaces ( and their tests ) have actually been generated by reflecting the original interfaces. See src/tools/java.
  