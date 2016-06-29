// BPData.java - BPData properties
// Copyright 2015, Anderson Software Group, Inc.
// Gail and Paul Anderson

package asgteach.bpmonitor;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class BPData {
    public final static String SYSTOLIC_PROP_NAME = "systolic";
    public final static String DIASTOLIC_PROP_NAME = "diastolic";
    public final static String PULSE_PROP_NAME = "pulse";
    public final static String DATE_PROP_NAME = "date";
    
    private final ObjectProperty<LocalDateTime> date;
    private final IntegerProperty systolic;
    private final IntegerProperty diastolic;
    private final IntegerProperty pulse;
    
    private final static DateTimeFormatter formatter = 
            DateTimeFormatter.ofPattern("MMM dd/hh:mma");
    
    public BPData() {
        date = new SimpleObjectProperty<>(this, DATE_PROP_NAME);
        systolic = new SimpleIntegerProperty(this, SYSTOLIC_PROP_NAME);
        diastolic = new SimpleIntegerProperty(this, DIASTOLIC_PROP_NAME);
        pulse = new SimpleIntegerProperty(this, PULSE_PROP_NAME);
    }

    //  Getters and setters
    public final LocalDateTime getDate() {
        return date.get();
    }

    public final void setDate(LocalDateTime date) {
        this.date.set(date);
    }

    public final Integer getSystolic() {
        return systolic.get();
    }

    public final void setSystolic(Integer sistolic) {
        this.systolic.set(sistolic);
    }

    public final Integer getDiastolic() {
        return diastolic.get();
    }

    public final void setDiastolic(Integer diastolic) {
        this.diastolic.set(diastolic);
    }

    public final Integer getPulse() {
        return pulse.get();
    }

    public final void setPulse(Integer pulse) {
        this.pulse.set(pulse);
    }
    
    // Property getters
    public ObjectProperty<LocalDateTime> dateProperty() {
        return date;
    }
    
    public IntegerProperty systolicProperty() {
        return systolic;
    }
    
    public IntegerProperty diastolicProperty() {
        return diastolic;
    }
    
    public IntegerProperty pulseProperty() {
        return pulse;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getDate().format(formatter)).append("-");
        sb.append(getSystolic().toString()).append("/");
        sb.append(getDiastolic().toString()).append("/");
        sb.append(getPulse().toString());
        return sb.toString();
    } 
    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.date);
        hash = 53 * hash + Objects.hashCode(this.systolic);
        hash = 53 * hash + Objects.hashCode(this.diastolic);
        hash = 53 * hash + Objects.hashCode(this.pulse);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BPData other = (BPData)obj;
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.systolic, other.systolic)) {
            return false;
        }
        if (!Objects.equals(this.diastolic, other.diastolic)) {
            return false;
        }
        return Objects.equals(this.pulse, other.pulse);
    }
}
