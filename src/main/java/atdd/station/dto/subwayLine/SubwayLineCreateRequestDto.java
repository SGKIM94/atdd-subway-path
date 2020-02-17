package atdd.station.dto.subwayLine;

import atdd.station.domain.Edges;
import atdd.station.domain.Subway;
import atdd.station.domain.SubwayLine;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SubwayLineCreateRequestDto {
    private String name;
    private String startTime;
    private String endTime;
    private String intervalTime;
    private List<Subway> subways;
    private Edges edges;

    @Builder
    public SubwayLineCreateRequestDto(String name, String startTime, String endTime, String intervalTime, List<Subway> subways, Edges edges) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.intervalTime = intervalTime;
        this.subways = subways;
        this.edges = edges;
    }

    public SubwayLine toEntity() {
        return SubwayLine.builder()
                .name(this.name)
                .startTime(this.startTime)
                .endTime(this.endTime)
                .intervalTime(this.intervalTime)
                .subways(this.subways)
                .edges(this.edges)
                .build();
    }

    public static SubwayLineCreateRequestDto toDtoEntity(SubwayLine subwayLine, List<Subway> subways) {
        return SubwayLineCreateRequestDto.builder()
                .name(subwayLine.getName())
                .startTime(subwayLine.getStartTime())
                .endTime(subwayLine.getEndTime())
                .intervalTime(subwayLine.getIntervalTime())
                .subways(subways)
                .edges(subwayLine.getEdges())
                .build();
    }
}
