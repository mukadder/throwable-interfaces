package org.slieb.throwables;

/**
 * Generated from java.util.function.Function
 *
 * @param <T> some generic flag
 * @param <R> some generic flag
 * @param <E> The extension
 */
@FunctionalInterface
public interface FunctionWithThrowable<T, R, E extends Throwable> extends java.util.function.Function<T, R> {
    /**
     * @param functionwiththrowable object
     * @param <T> some generic flag
     * @param <R> some generic flag
     * @param <E> The extension
     * @return the cast interface
     */
    static <T, R, E extends Throwable> FunctionWithThrowable<T, R, E> castFunctionWithThrowable(FunctionWithThrowable<T, R, E> functionwiththrowable) {
        return functionwiththrowable;
    }

    /** 
     * Overridden method of FunctionWithThrowable that will call applyWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @return the value
     */
    @Override
    default R apply(T v1) {
        try {
            return applyWithThrowable(v1);
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
    R applyWithThrowable(T v1) throws E;
}
