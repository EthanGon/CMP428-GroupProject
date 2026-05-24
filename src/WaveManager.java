public class WaveManager {
    private int wave = 1;
    private int waveTimer = 0;
    private final int WAVE_DURATION = 60 * 30;
    private static WaveManager instance;

    public WaveManager() {
        instance = this;
        System.out.println("Wave 1 started!");
    }

    public static WaveManager getInstance() { return instance; }

    public void update() {
        waveTimer++;
        if (waveTimer >= WAVE_DURATION) {
            waveTimer = 0;
            wave++;
            System.out.println("Wave " + wave + " started!");
        }
    }

    public int getWave() { return wave; }

    public int getSpawnCount() {
        return 1 + (wave / 3);
    }

    public int getSpawnTime() {
        return Math.max(1, 4 - (wave - 1));
    }

    public int getMonsterType() {
        if (wave >= 10 && Math.random() < 0.05) return 3;
        if (wave >= 5 && Math.random() < 0.3) return 2;
        if (wave >= 3 && Math.random() < 0.4) return 1;
        return 0;
    }
}