package cis3334.java_heartrate_start;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import java.sql.Date;

@Entity
public class Heartrate {
    public Integer pulse;       // actual rate in beats per minute
    public Integer age;            // age when heart rate measurement was taken
    public Double maxHeartRate;    // calculated maximum rate based on age
    public Double percent;         // percent of maximum rate and a precent
    //public Date date;
    public Integer range;          // which range this heart rate is in, used as index into arrays below

    @PrimaryKey(autoGenerate = true)
    public Integer id;

    // ---- The following values don't need to be stored in the database, so the SQL to ignore them
    // TODO: These should really be stored somewhere else, like an XML or JSON file such as /res/values/strings.xml
    @Ignore
    final String rangeNames[] = {"Resting", "Moderate", "Endurance", "Aerobic","Anaerobic","Red zone"};
    @Ignore
    final String rangeDescriptions[] = {"In active or resting", "Weight maintenance and warm up", "Fitness and fat burning", "Cardio training and endurance","Hardcore interval training","Maximum Effort"};
    @Ignore
    final Double rangeBounds[] = {.50, .60, .70, .80, .90, 1.00};  // These must be in ascending order


    public Heartrate(Integer pulse, Integer age) {
        this.pulse = pulse;
        this.age = age;

        // Ensure they're not null, provide defaults if needed
        if (this.pulse == null) this.pulse = 0;
        if (this.age == null) this.age = 0;

        calcHeartRange();
    }

    /**
     *  Calculate the maximum heartrate and precent of max using the CDC guidelines
     *  Uses the age and pulse to do this calculation
     *  @return range -- the range index (usually 0 - 5) used for the index into the arrays above
     */
    public Integer calcHeartRange() {
        // Add null checks
        if (age == null || pulse == null) {
            range = 0; // Default to first range
            return range;
        }

        maxHeartRate = 220.0 - age;
        percent = pulse / maxHeartRate;

        for (int i = 0; i < rangeNames.length; i++) {
            if (percent < rangeBounds[i]) {
                range = i;
                return range;
            }
        }
        return rangeNames.length - 1;
    }

    /**
     * @return the name for this range such as Aerobic
     */
    public String getRangeName() {
        if (range == null) {
            calcHeartRange();
        }
        return rangeNames[range != null ? range : 0];
    }

    /**
     * @return the longer description for this range such as "Fitness and fat burning"
     */
    public String getRangeDescrtiption() {
        if (range == null) {
            calcHeartRange();
        }
        return rangeDescriptions[range != null ? range : 0];
    }

    /**
     * @return the heartrate as a descriptive string that can be displayed
     */
    public String toString() {
        return "Pulse of " + pulse + " - Cardio level: " + getRangeName();
    }


}