package mancala;

public record MancalaSettings(Integer numHouses, Integer numSeeds) {
    public static MancalaSettings DEFAULT_SETTINGS = new MancalaSettings(6, 4);
}
