package canteen;

import java.util.Objects;

/**
 * CanteenProduct
 */
public abstract class CanteenProduct {
    private final String name;
    private CanteenProduct baseProduct;

    public CanteenProduct(String name, CanteenProduct baseProduct) {
        // validate name
        if (name != null && name.trim().length() >= 3)
            this.name = name.trim();
        else
            throw new IllegalArgumentException("name needs to be at least 3 characters long!");

        // set baseProduct
        this.baseProduct = baseProduct;
    }

    public CanteenProduct(String name) {
        this(name, null);
    }

    /**
     * Getter for objects name.
     * 
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter for objects baseProduct.
     * @return baseProduct
     */
    public CanteenProduct getBaseProduct() {
        return this.baseProduct;
    }

    public abstract double getPrice();

    /**
     * Method to compare this CanteenProduct deeply with a given {other} CanteenProduct.
     * 
     * <pre>
     * True if CanteenProducts are equal. False if CanteenProducts are not equal.
     * </pre>
     * 
     * @return boolean
     */
    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (other == null)
            return false;
        if (getClass() != other.getClass())
            return false;
        
        // special comparisons for the CanteenProduct class
        CanteenProduct otherProduct = (CanteenProduct) other;

        if (!this.getName().equals(otherProduct.getName()))
            return false;
        if (!Objects.equals(this.baseProduct, otherProduct.getBaseProduct()))
            return false;
        
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.name == null ? 0 : this.name.hashCode())) + ((this.baseProduct == null ? 0 : this.baseProduct.hashCode()));
        return result;
    }
}