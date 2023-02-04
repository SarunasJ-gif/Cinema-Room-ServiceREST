class Pumpkin {

    private boolean forHalloween;

    public Pumpkin(boolean forHalloween) {
        this.forHalloween = forHalloween;
    }

    // create method addCandle()
    void addCandle() {
        if (forHalloween) {
            Pumpkin pumpkin = new Pumpkin(forHalloween);
            Pumpkin.Candle candle = pumpkin.new Candle();
            candle.burning();
        } else {
            System.out.println("We don't need a candle.");
        }
    }

    class Candle {

        void burning() {
            System.out.println("The candle is burning! Boooooo!");
        }
    }
}