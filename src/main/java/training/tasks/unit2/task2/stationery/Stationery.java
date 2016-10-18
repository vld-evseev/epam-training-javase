package training.tasks.unit2.task2.stationery;

import java.math.BigDecimal;
import java.util.Comparator;

public class Stationery implements Comparable<Stationery> {

    private String name;
    private BigDecimal price;

    public Stationery(String name, double price) {
        this.name = name;
        this.price = BigDecimal.valueOf(price);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = BigDecimal.valueOf(price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Stationery that = (Stationery) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return price != null ? price.equals(that.price) : that.price == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Stationery [name=" + name + ", price=" + price + "]";
    }


    @Override
    public int compareTo(Stationery that) {
        return this.name.compareTo(that.getName());
    }

    public static class PRICE_COMPARATOR implements Comparator<Stationery> {

        @Override
        public int compare(Stationery o1, Stationery o2) {
            return o1.price.compareTo(o2.getPrice());
        }
    }

    public static class NAME_AND_PRICE_COMPARATOR implements Comparator<Stationery> {

        @Override
        public int compare(Stationery o1, Stationery o2) {
            int byName = o1.name.compareTo(o2.getName());
            int byPrice = o1.getPrice().compareTo(o2.getPrice());

            if (byPrice < 0 && byName > 0) {
                return 1;
            } else if (byName == 0 && byPrice == 0) {
                return 0;
            } else {
                return -1;
            }
        }
    }

}
