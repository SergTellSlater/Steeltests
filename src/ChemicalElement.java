// Углерод
abstract class ChemicalElement {
    protected double maxDigitOfFrame;
    protected double minDigitOfFrame;
    protected double difference;
    protected String name;

    public double getMaxDigitOfFrame() {
        return maxDigitOfFrame;
    }

    public void setMaxDigitOfFrame(double maxDigitOfFrame) {
        this.maxDigitOfFrame = maxDigitOfFrame <= 0 ? 0 : maxDigitOfFrame;
    }

    public double getMinDigitOfFrame() {
        return minDigitOfFrame;
    }

    public void setMinDigitOfFrame(double minDigitOfFrame) {
        this.minDigitOfFrame = minDigitOfFrame <= 0 ? 0 : minDigitOfFrame;
    }

    public double getDifference() {
        return difference;
    }

    public void setDifference(double difference) {
        this.difference = difference <= 0 ? 0 : difference;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}







