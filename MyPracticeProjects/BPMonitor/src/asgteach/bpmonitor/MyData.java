// MyData.java - BPData generator
// Copyright 2015, Anderson Software Group, Inc.
// Gail and Paul Anderson

package asgteach.bpmonitor;
import java.time.LocalDateTime;
import javafx.collections.ObservableList;

public class MyData {
    // Helper method that creates a new BPData object from provided values.
    // This is also called when a new BP Reading is added to the Line Chart
    // and backingList
    public static BPData buildData(LocalDateTime date,
            Integer sis, Integer dia, Integer pulse) {
        BPData data = new BPData();
        data.setDate(date);
        data.setSystolic(sis);
        data.setDiastolic(dia);
        data.setPulse(pulse);
        return data;
    }
    
    // This is the initial data we use for the chart.
    // (You could read this data from an XML file.)
    // Note: Because we wrap backingList in a sortedBackingList,
    // the order here doesn't matter--the data will get sorted
    // for us by the SortedList behavior
    public static void fillBPData(ObservableList<BPData> backingList) {
        backingList.add(buildData(LocalDateTime.of(
                2015, 9, 17, 7, 2), 122, 84, 61));
        backingList.add(buildData(LocalDateTime.of(
                2015, 9, 13, 18, 34), 143, 92, 65));
        backingList.add(buildData(LocalDateTime.of(
                2015, 9, 13, 23, 42), 148, 84, 65));
        backingList.add(buildData(LocalDateTime.of(
                2015, 9, 14, 7, 26), 125, 83, 61));
        backingList.add(buildData(LocalDateTime.of(
                2015, 9, 14, 23, 7), 138, 89, 58));
        backingList.add(buildData(LocalDateTime.of(
                2015, 9, 15, 7, 8), 135, 87, 65));
        backingList.add(buildData(LocalDateTime.of(
                2015, 9, 15, 23, 26), 128, 88, 60));
        backingList.add(buildData(LocalDateTime.of(
                2015, 9, 16, 6, 51), 131, 83, 57));
        backingList.add(buildData(LocalDateTime.of(
                2015, 9, 16, 22, 18), 121, 83, 60));

        backingList.add(buildData(LocalDateTime.of(
                2015, 9, 17, 22, 54), 135, 85, 69));
        backingList.add(buildData(LocalDateTime.of(
                2015, 9, 18, 7, 43), 112, 81, 68));
        backingList.add(buildData(LocalDateTime.of(
                2015, 9, 19, 0, 26), 123, 76, 66));
        backingList.add(buildData(LocalDateTime.of(
                2015, 9, 19, 7, 9), 119, 79, 60));
        backingList.add(buildData(LocalDateTime.of(
                2015, 9, 19, 22, 48), 131, 86, 64));
        backingList.add(buildData(LocalDateTime.of(
                2015, 9, 20, 7, 12), 119, 85, 70));
        backingList.add(buildData(LocalDateTime.of(
                2015, 9, 21, 0, 11), 119, 78, 60));
        backingList.add(buildData(LocalDateTime.of(
                2015, 9, 21, 7, 59), 120, 82, 69));
        backingList.add(buildData(LocalDateTime.of(
                2015, 9, 21, 23, 56), 136, 88, 58));
        backingList.add(buildData(LocalDateTime.of(
                2015, 9, 22, 7, 14), 121, 79, 61));

        backingList.add(buildData(LocalDateTime.of(
                2015, 9, 22, 22, 53), 129, 83, 61));
        backingList.add(buildData(LocalDateTime.of(
                2015, 9, 23, 7, 04), 120, 85, 71));

        backingList.add(buildData(LocalDateTime.of(
                2015, 9, 23, 23, 18), 133, 81, 56));
        backingList.add(buildData(LocalDateTime.of(
                2015, 9, 24, 7, 50), 132, 78, 65));
        backingList.add(buildData(LocalDateTime.of(
                2015, 9, 24, 23, 12), 115, 79, 64));
        backingList.add(buildData(LocalDateTime.of(
                2015, 9, 25, 6, 52), 118, 79, 59));
        backingList.add(buildData(LocalDateTime.of(
                2015, 9, 25, 22, 19), 128, 85, 69));
        backingList.add(buildData(LocalDateTime.of(
                2015, 9, 26, 7, 50), 113, 78, 65));
        backingList.add(buildData(LocalDateTime.of(
                2015, 9, 26, 22, 50), 119, 80, 60));
        backingList.add(buildData(LocalDateTime.of(
                2015, 9, 27, 6, 57), 112, 81, 59));
        backingList.add(buildData(LocalDateTime.of(
                2015, 9, 27, 23, 34), 139, 91, 57));
        backingList.add(buildData(LocalDateTime.of(
                2015, 9, 28, 7, 27), 116, 79, 63));
        backingList.add(buildData(LocalDateTime.of(
                2015, 9, 28, 23, 12), 137, 86, 60));
        backingList.add(buildData(LocalDateTime.of(
                2015, 9, 29, 6, 53), 118, 81, 59));
        backingList.add(buildData(LocalDateTime.of(
                2015, 9, 29, 22, 47), 125, 82, 58));
        backingList.add(buildData(LocalDateTime.of(
                2015, 9, 30, 7, 24), 104, 72, 61));

        backingList.add(buildData(LocalDateTime.of(
                2015, 9, 30, 23, 15), 106, 74, 65));
        backingList.add(buildData(LocalDateTime.of(
                2015, 10, 5, 7, 29), 119, 80, 58));
        backingList.add(buildData(LocalDateTime.of(
                2015, 10, 5, 23, 26), 121, 77, 58));
        backingList.add(buildData(LocalDateTime.of(
                2015, 10, 1, 7, 32), 112, 76, 58));

        backingList.add(buildData(LocalDateTime.of(
                2015, 10, 1, 23, 04), 134, 80, 58));
        backingList.add(buildData(LocalDateTime.of(
                2015, 10, 2, 6, 41), 111, 75, 54));
        backingList.add(buildData(LocalDateTime.of(
                2015, 10, 3, 7, 17), 117, 76, 61));
        backingList.add(buildData(LocalDateTime.of(
                2015, 10, 3, 22, 8), 127, 78, 58));
        backingList.add(buildData(LocalDateTime.of(
                2015, 10, 4, 7, 18), 115, 75, 57));

        //System.out.println(backingList);
    }
}
