package atdd.station.domain;

import lombok.Builder;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class SubwayLine {
    private static final String DEFAULT_START_TIME = "05:00";
    private static final String DEFAULT_END_TIME = "23:50";
    private static final String DEFAULT_INTERVAL = "10";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min = 2, max = 20)
    private String name;

    @Size(min = 4, max = 10)
    private String startTime;

    @Size(min = 4, max = 10)
    private String endTime;

    @Size(min = 2, max = 20)
    private String intervalTime;

    @OneToMany(mappedBy = "station", fetch = FetchType.EAGER)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private List<Subway> stations;

    private boolean deleted = false;

    public SubwayLine() {
    }

    @Builder
    public SubwayLine(String name, String startTime, String endTime, String intervalTime) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.intervalTime = intervalTime;
        this.startTime = startTime;
    }

    public SubwayLine(String name) {
        this.name = name;
        this.startTime = DEFAULT_START_TIME;
        this.endTime = DEFAULT_END_TIME;
        this.intervalTime = DEFAULT_INTERVAL;
    }

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getIntervalTime() {
        return intervalTime;
    }


    public void deleteSubwayLine() {
        this.deleted = true;
    }

    public boolean isDeleted() {
        return this.deleted;
    }

    public List<Subway> getStations() {
        return stations;
    }

    @Override
    public String toString() {
        return "SubwayLine{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", interval='" + intervalTime + '\'' +
                ", station=" + stations +
                ", deleted=" + deleted +
                '}';
    }

}


