package org.slieb.throwables;

/**
 * Generated from java.util.function.IntPredicate
 *
 * @param <E> The extension
 */
@FunctionalInterface
public interface IntPredicateWithThrowable<E extends Throwable> extends java.util.function.IntPredicate {
    /**
     * @param intpredicatewiththrowable object
     * @param <E> The extension
     * @return the cast interface
     */
    static <E extends Throwable> IntPredicateWithThrowable<E> castIntPredicateWithThrowable(IntPredicateWithThrowable<E> intpredicatewiththrowable) {
        return intpredicatewiththrowable;
    }

    /** 
     * Overridden method of IntPredicateWithThrowable that will call testWithThrowable, but catching any exceptions.
     *
     * @param v1 parameter to overridden method
     * @return the value
     */
    @Override
    default boolean test(int v1) {
        try {
            return testWithThrowable(v1);
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
    boolean testWithThrowable(int v1) throws E;
}
