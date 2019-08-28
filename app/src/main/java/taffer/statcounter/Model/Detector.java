package taffer.statcounter.Model;

import android.hardware.SensorEvent;

/**
 * Interface for Detectors.
 */
public interface Detector {
    int SUCCESS = 1;
    int FAIL = -1;
    int detectEvent(SensorEvent e);
}
