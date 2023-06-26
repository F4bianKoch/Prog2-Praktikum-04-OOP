package canteen;

import java.security.cert.PKIXCertPathBuilderResult;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Arrays;

public class Sandwich extends CanteenProduct implements Ratable{
    private final SandwichIngredients[] ingredients;
    private int ratingSum;
    private int numberOfRatings;

    public Sandwich(String name, SandwichIngredients... ingredients) {
        super(name);

        // validate that bread is sandwich ingredient
        boolean hasBreadType = false;
        for (SandwichIngredients ingredient : ingredients) {
            if (ingredient == SandwichIngredients.BREAD)
                hasBreadType = true;
            if (ingredient == SandwichIngredients.WHOLE_GRAIN_BREAD)
                hasBreadType = true;
        }
        if (!hasBreadType)
            throw new SandwichHasNoBreadException("Sandwich must have at least one Bread Type!");
        
        if (ingredients.length < 2)
            throw new SandwichHasTooFewIngredientsException("Sandwich must have at least to ingredients!");

        this.ingredients = ingredients;
        this.ratingSum = 0;
        this.numberOfRatings = 0;
    }

    public SandwichIngredients[] getIngredients() {
        return this.ingredients;
    }

    public double getPrice() {
        LinkedList<SandwichIngredients> fourMostExpensive = getFourMostExpensive();
        double price = 0;

        for (SandwichIngredients ingredient : fourMostExpensive) {
            price = price + ingredient.getPrice();
        }

        return price;
    }

    public int getKcal() {
        int kcalSum = 0;

        for (SandwichIngredients ingredient : ingredients) {
            kcalSum = kcalSum + ingredient.getKcal();
        }

        return kcalSum;
    }

    @Override
    public boolean equals(Object other) {
       if (!super.equals(other))
           return false;
       
       // special comparisons for the CanteenProduct class
       Sandwich otherSandwich = (Sandwich) other;

       if (!this.getName().equals(otherSandwich.getName()))
           return false;
       if (!Objects.equals(this.getBaseProduct(), otherSandwich.getBaseProduct()))
           return false;
       if (!(this.getIngredients().length == otherSandwich.getIngredients().length))
           return false; 
       for (int i = 0; i < this.getIngredients().length; i++) {
           if (!(this.getIngredients()[i] == otherSandwich.getIngredients()[i]))
               return false;
       }
        
       return true;
    }

    @Override
    public int hashCode() {
       final int prime = 31;
       int result = super.hashCode();
       int ingredienthash = 0;
       for (SandwichIngredients ingredient : this.getIngredients()) {
           ingredienthash = ingredienthash + ingredient.hashCode();
       }
       result = prime * result + ingredienthash;
       return result;
    }

    @Override
    public String toString() {
       return this.getName() + "(" + this.getKcal() + "kcal)\t\t\t\t" + this.getPrice();
    }

    @Override
    public void rateProduct(int rating) {
        if (rating > 5 || rating < 1) 
            throw new IllegalArgumentException("rating must be between 1 and 5 included!");
        
        this.ratingSum = this.ratingSum + rating;
        numberOfRatings++;
    }

    @Override
    public double getAvgRating() {
        return this.ratingSum / getNumberOfRatings();
    }

    @Override
    public int getNumberOfRatings() {
        return numberOfRatings;
    }

    private LinkedList<SandwichIngredients> getFourMostExpensive() {
        LinkedList<SandwichIngredients> fourMostExpensive = new LinkedList<>();
        LinkedList<SandwichIngredients> tempIngredients = new LinkedList<>(Arrays.asList(this.ingredients));

        while (fourMostExpensive.size() < 4) {
            SandwichIngredients mostExpensive = null;
            for (SandwichIngredients ingredient : tempIngredients) {
                if (mostExpensive == null)
                    mostExpensive = ingredient;
                if (mostExpensive.getPrice() < ingredient.getPrice())
                    mostExpensive = ingredient;
            }

            fourMostExpensive.add(mostExpensive);

            tempIngredients.remove(mostExpensive);
        }

        return fourMostExpensive;
    }
}
