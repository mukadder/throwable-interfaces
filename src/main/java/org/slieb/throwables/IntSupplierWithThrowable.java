package org.slieb.throwables;

/**
 * Generated from java.util.function.IntSupplier
 *
 * @param <E> The extension
 */
@FunctionalInterface
public interface IntSupplierWithThrowable<E extends Throwable> extends java.util.function.IntSupplier {
    /**
     * @param intsupplierwiththrowable object
     * @param <E> The extension
     * @return the cast interface
     */
    static <E extends Throwable> IntSupplierWithThrowable<E> castIntSupplierWithThrowable(IntSupplierWithThrowable<E> intsupplierwiththrowable) {
        return intsupplierwiththrowable;
    }

    /** 
     * Overridden method of IntSupplierWithThrowable that will call getAsIntWithThrowable, but catching any exceptions.
     *
     * @return the value
     */
    @Override
    default int getAsInt() {
        try {
            return getAsIntWithThrowable();
        } catch (final RuntimeException | Error exception) {
            throw exception;
        } catch (final Throwable throwable) {
            throw new org.slieb.throwables.SuppressedException(throwable);
        }
    }

    /** 
     * Functional method that will throw exceptions.
     *
     * @return the value
     * @throws E some exception
     */
    int getAsIntWithThrowable() throws E;
}
