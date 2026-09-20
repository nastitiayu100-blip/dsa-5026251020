package lw01.prelab;

public class ColourPrint extends PrintJob {

    private static final int TIER1_LIMIT = 10;
    private static final int TIER1_RATE = 1500;
    private static final int TIER2_RATE = 1000;
    private static final int SETUP_FEE = 2000;

    public ColourPrint(String id, int pages) {
        super(id, pages);
    }

    @Override
    public int calculateCharge() {
        int pages = getPages();
        int charge;
        if (pages <= TIER1_LIMIT) {
            charge = pages * TIER1_RATE;
        } else {
            charge = (TIER1_LIMIT * TIER1_RATE) + ((pages - TIER1_LIMIT) * TIER2_RATE);
        }
        charge += SETUP_FEE;
        return charge;
    }

    @Override
    public String label() {
        return "Colour";
    }
}