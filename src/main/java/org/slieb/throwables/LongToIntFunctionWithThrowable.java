package org.slieb.throwables;

/**
 * Generated from java.util.function.LongToIntFunction
 *
 * @param <E> The extension
 */
@FunctionalInterface
public interface LongToIntFunctionWithThrowable<E extends Throwable> extends java.util.function.LongToIntFunction {
    /**
     * @param longtointfunctionwiththrowable object
     * @param <E> The extension
     * @return the cast interface
     */
    static <E extends Throwable> LongToIntFunctionWithThrowable<E> castLongToIntFunctionWithThrowable(LongToIntFunctionWithThrowable<E> longtointfunctionwiththrowable) {
        return longtointfunctionwiththrowable;
    }

    /** 
     * Overridden method of LongToIntFunctionWithThrowable that will call applyAsIntWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @return the value
     */
    @Override
    default int applyAsInt(long v1) {
        try {
            return applyAsIntWithThrowable(v1);
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
    int applyAsIntWithThrowable(long v1) throws E;
}