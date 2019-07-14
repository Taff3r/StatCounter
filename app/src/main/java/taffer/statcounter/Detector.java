package taffer.statcounter;

import android.hardware.SensorEvent;

public interface Detector {
    int SUCCESS = 1;
    int FAIL = -1;
    int detectEvent(SensorEvent e);
}
