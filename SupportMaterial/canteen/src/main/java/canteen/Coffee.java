package canteen;

import java.util.Objects;

public class Coffee extends CanteenProduct {

   private CoffeeIngredients[] ingredients; 

   public Coffee(String name, Coffee baseProduct, CoffeeIngredients... ingredients) {
       super(name, baseProduct);

       if (ingredients.length == 0)
           throw new IllegalArgumentException("There must be at least one ingredient!");

       this.ingredients = ingredients;
   }

   public Coffee(String name, CoffeeIngredients... ingredients) {
       this(name, null, ingredients);
   }

   public CoffeeIngredients[] getIngredients() {
       return this.ingredients; 
   }

   @Override
   public double getPrice() {
       double price = this.getBaseProduct() == null ? 0.0 : this.getBaseProduct().getPrice();
       for (CoffeeIngredients ingredient : this.ingredients) {
           price = price + ingredient.getPrice();
       }
       return price;
   }

   @Override
   public boolean equals(Object other) {
       if (!super.equals(other))
           return false;
       
       // special comparisons for the CanteenProduct class
       Coffee otherCoffee = (Coffee) other;

       if (!this.getName().equals(otherCoffee.getName()))
           return false;
       if (!Objects.equals(this.getBaseProduct(), otherCoffee.getBaseProduct()))
           return false;
       if (!(this.getIngredients().length == otherCoffee.getIngredients().length))
           return false; 
       for (int i = 0; i < this.getIngredients().length; i++) {
           if (!(this.getIngredients()[i] == otherCoffee.getIngredients()[i]))
               return false;
       }
        
       return true;
   }

   @Override
   public int hashCode() {
       final int prime = 31;
       int result = super.hashCode();
       int ingredienthash = 0;
       for (CoffeeIngredients ingredient : ingredients) {
           ingredienthash = ingredienthash + ingredient.hashCode();
       }
       result = prime * result + ingredienthash;
       return result;
   }

   @Override
   public String toString() {
       return this.getName() + "\t\t\t\t" + this.getPrice();
   }
}