package frc.robot;

public class Map {
    private Map() {}
    /**
     * Maps a value from one range to another
     * @param value The value to map
     * @param istart The start of the input range
     * @param istop The end of the input range
     * @param ostart The start of the output range
     * @param ostop The end of the output range
     * @return The mapped value
     */
    public static double map(double value, double istart, double istop, double ostart, double ostop) {
        return ostart + (ostop - ostart) * ((value - istart) / (istop - istart));
    }
}
