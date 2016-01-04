package org.slieb.throwables;

/**
 * Generated from java.util.function.ToDoubleFunction
 *
 * @param <T> some generic flag
 * @param <E> The extension
 */
@FunctionalInterface
public interface ToDoubleFunctionWithThrowable<T, E extends Throwable> extends java.util.function.ToDoubleFunction<T> {
    /**
     * @param todoublefunctionwiththrowable object
     * @param <T> some generic flag
     * @param <E> The extension
     * @return the cast interface
     */
    static <T, E extends Throwable> ToDoubleFunctionWithThrowable<T, E> castToDoubleFunctionWithThrowable(ToDoubleFunctionWithThrowable<T, E> todoublefunctionwiththrowable) {
        return todoublefunctionwiththrowable;
    }

    /** 
     * Overridden method of ToDoubleFunctionWithThrowable that will call applyAsDoubleWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @return the value
     */
    @Override
    default double applyAsDouble(T v1) {
        try {
            return applyAsDoubleWithThrowable(v1);
        } catch (final RuntimeException | Error exception) {
            throw exception;
        } catch (final Throwable throwable) {
            throw new org.slieb.throwables.SuppressedException(throwable);
        }
    }

    /** 
     * Functional method that will throw exceptions.
     *
     * @param v1 parameter to overridden method
     * @return the value
     * @throws E some exception
     */
    double applyAsDoubleWithThrowable(T v1) throws E;
}
