package atdd.station.service;

import atdd.station.domain.SubwayLine;
import atdd.station.domain.SubwayLineRepository;
import atdd.station.dto.subwayLine.SubwayLineCreateRequestDto;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SoftAssertionsExtension.class)
public class SubwayLineServiceTest {

    @Mock
    SubwayLineRepository subwayLineRepository;

    @InjectMocks
    SubwayLineService subwayLineService;

    @Test
    public void 지하철노선_생성시_성공하는지(SoftAssertions softly) {
        //given
        SubwayLine subwayLine = SubwayLine.builder()
                .name("2호선")
                .build();

        //when
        when(subwayLineRepository.save(any())).thenReturn(subwayLine);

        SubwayLine createSubwayLine = subwayLineService.create(SubwayLineCreateRequestDto.toDtoEntity(subwayLine));

        //then
        softly.assertThat(createSubwayLine.getId()).isEqualTo(0L);
        softly.assertThat(createSubwayLine.getName()).isEqualTo("2호선");
    }

    @Test
    public void 지하철노선_list_조회가_성공하는지(SoftAssertions softly) {
        //given
        List<SubwayLine> subwayLines = new ArrayList<>();

        SubwayLine secondSubwayLine = SubwayLine.builder()
                .name("2호선")
                .build();

        SubwayLine firstSubwayLine = SubwayLine.builder()
                .name("1호선")
                .build();

        subwayLines.add(secondSubwayLine);
        subwayLines.add(firstSubwayLine);

        //when
        when(subwayLineRepository.findAll()).thenReturn(subwayLines);

        List<SubwayLine> listedSubwayLines = subwayLineService.list();

        //then
        softly.assertThat(listedSubwayLines).isNotNull();
        softly.assertThat(listedSubwayLines.size()).isGreaterThan(1);
        softly.assertThat(listedSubwayLines.toString()).contains("2호선");
        softly.assertThat(listedSubwayLines.toString()).contains("1호선");
    }

}
