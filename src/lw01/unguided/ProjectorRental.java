package lw01.unguided;

public class ProjectorRental extends Rental {
    public ProjectorRental(String id, int days, int units) {
        super(id, days, units);
    }

    @Override
    public int calculateCharge() {
        int days = getDays();
        int total;
        if (days <= 3) {
            total = days * 60000;
        } else {
            total = (3 * 60000) + ((days - 3) * 45000);
        }
        total += 20000;
        return total * getUnits();
    }

    @Override
    public String label() {
        return "Projector";
    }
}