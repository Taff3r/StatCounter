package taffer.statcounter.Model;

import android.hardware.Sensor;
import android.hardware.SensorEvent;

public class OrientationDetector implements Detector {
    public final static float[] UPSIDEDOWN = {0, 0, -10};
    public final static float[] RIGHTSIDEUP = {0, 0, 10};
    public final static float[] RIGHT90DEG = {-10, 0, 0};
    public final static float[] LEFT90DEG = {10, 0, 0};
    public final static float[] VERTICAL = {0, 10, 0};
    public final static float[] VERTICALUPSIDEDOWN = {0, -10, 0};


    private float[] successOrientation;
    private double accuracy;

    /**
     * Creates a new OrientationDetector.
     * @param successOrientation, either a custom float[] or one predefined.
     * @param accuracy, the accuracy of detection.
     */

    public OrientationDetector(float[] successOrientation, double accuracy){
        this.successOrientation = successOrientation;
        this.accuracy =  accuracy;
    }

    @Override
    public int detectEvent(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            if(isAccurate(event.values[0],successOrientation[0]) && isAccurate(event.values[1], successOrientation[1]) && isAccurate(event.values[2], successOrientation[2])){
                return SUCCESS;
            }
        }
        return FAIL;
    }

    private boolean isAccurate(float c, float r){
        if(c >= r - this.accuracy && c <= r + this.accuracy){
            return true;
        }
        return false;
    }

}
