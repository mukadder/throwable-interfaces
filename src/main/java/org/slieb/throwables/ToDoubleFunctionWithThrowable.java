package org.slieb.throwables;

import java.lang.Throwable;
import java.util.function.Consumer;
import java.util.function.ToDoubleFunction;
import org.slf4j.Logger;
/**
 * Generated from ToDoubleFunction
 * Extends java.util.function.ToDoubleFunction to allow for a checked exception.
 *
 * @param <T> some generic flag
 * @param <E> The extension
 */
@FunctionalInterface
public interface ToDoubleFunctionWithThrowable<T, E extends Throwable> extends ToDoubleFunction<T> {


    /**
     * Utility method to mark lambdas of type ToDoubleFunctionWithThrowable
     *
     * @param todoublefunctionwiththrowable The interface instance
     * @param <T> Generic that corresponds to the same generic on ToDoubleFunction  
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <T, E extends Throwable> ToDoubleFunctionWithThrowable<T, E> castToDoubleFunctionWithThrowable(final ToDoubleFunctionWithThrowable<T, E> todoublefunctionwiththrowable) {
        return todoublefunctionwiththrowable;
    }
    /**
     * Utility method to convert ToDoubleFunctionWithThrowable
     * @param todoublefunction The interface instance
     * @param <T> Generic that corresponds to the same generic on ToDoubleFunction  
     * @param <E> The type this interface is allowed to throw
     * @return the cast interface
     */
    static <T, E extends Throwable> ToDoubleFunctionWithThrowable<T, E> asToDoubleFunctionWithThrowable(final ToDoubleFunction<T> todoublefunction) {
        return todoublefunction::applyAsDouble;
    }

    /** 
     * Overridden method of ToDoubleFunctionWithThrowable that will call applyAsDoubleWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @return the value
     */
    @Override
    default double applyAsDouble(final T v1) {
        try {
            return applyAsDoubleWithThrowable(v1);
        } catch (final RuntimeException | Error exception) {
            throw exception;
        } catch (final Throwable throwable) {
            throw new SuppressedException(throwable);
        }
    }

    /** 
     * Functional method that will throw exceptions.
     *
     * @param v1 parameter to overridden method
     * @return the value
     * @throws E some exception
     */
    double applyAsDoubleWithThrowable(final T v1) throws E;


    /**
     * @param logger The logger to log exceptions on
     * @param message A message to use for logging exceptions
     * @return An interface that will log all exceptions to given logger
     */
    default ToDoubleFunctionWithThrowable<T, E> withLogging(Logger logger, String message) {
        return (final T v1) -> {
            try {
                return applyAsDoubleWithThrowable(v1);
            } catch (final Throwable throwable) {
                logger.error(message, throwable);
                throw throwable;
            }
        };
    }


    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     * @param logger The logger instance to log exceptions on
     * @return An interface that will log exceptions on given logger
     */
    default ToDoubleFunctionWithThrowable<T, E> withLogging(org.slf4j.Logger logger) {
        return withLogging(logger, "Exception in ToDoubleFunctionWithThrowable with arguments {}");
    }


    /**
     * Will log WARNING level exceptions on logger if they occur within the interface
     * @return An interface that will log exceptions on global logger
     */
    default ToDoubleFunctionWithThrowable<T, E> withLogging() {
        return withLogging(org.slf4j.LoggerFactory.getLogger(getClass()));
    }



    /**
     * @param consumer An exception consumer.
     * @return An interface that will log all exceptions to given logger
     */
    @SuppressWarnings("Duplicates")
    default ToDoubleFunctionWithThrowable<T, E> onException(Consumer<Throwable> consumer) {
        return (final T v1) -> {
            try {
                return applyAsDoubleWithThrowable(v1);
            } catch (final Throwable throwable) {
                consumer.accept(throwable);
                throw throwable;
            }
        };
    }
}
