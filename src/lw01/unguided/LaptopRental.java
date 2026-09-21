package lw01.unguided;

public class LaptopRental extends Rental {
    public LaptopRental(String id, int days, int units) {
        super(id, days, units);
    }

    @Override
    public int calculateCharge() {
        int total = (getDays() * 40000) + 10000;
        return total * getUnits();
    }

    @Override
    public String label() {
        return "Laptop";
    }
}