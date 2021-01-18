package com.codeTest.analyze_crypto.mappers;

import com.codeTest.analyze_crypto.dtos.history.HistoryGetDto;
import com.codeTest.analyze_crypto.dtos.history.HistoryPostDto;
import com.codeTest.analyze_crypto.dtos.history.HistoryPutDto;
import com.codeTest.analyze_crypto.entities.History;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-01-16T14:56:07+1100",
    comments = "version: 1.4.0.Beta1, compiler: IncrementalProcessingEnvironment from gradle-language-java-6.7.1.jar, environment: Java 14.0.2 (Oracle Corporation)"
)
@Component
public class HistoryMapperImpl implements HistoryMapper {

    @Override
    public History toEntity(HistoryPostDto historyPostDto) {
        if ( historyPostDto == null ) {
            return null;
        }

        History history = new History();

        history.setCurrency( historyPostDto.getCurrency() );
        history.setDate( historyPostDto.getDate() );
        history.setOpen( historyPostDto.getOpen() );
        history.setHigh( historyPostDto.getHigh() );
        history.setLow( historyPostDto.getLow() );
        history.setClose( historyPostDto.getClose() );
        history.setVolume( historyPostDto.getVolume() );
        history.setMarket_cap( historyPostDto.getMarket_cap() );

        return history;
    }

    @Override
    public HistoryGetDto fromEntity(History history) {
        if ( history == null ) {
            return null;
        }

        HistoryGetDto historyGetDto = new HistoryGetDto();

        historyGetDto.setId( history.getId() );
        historyGetDto.setCurrency( history.getCurrency() );
        historyGetDto.setDate( history.getDate() );
        historyGetDto.setOpen( history.getOpen() );
        historyGetDto.setHigh( history.getHigh() );
        historyGetDto.setLow( history.getLow() );
        historyGetDto.setClose( history.getClose() );
        historyGetDto.setVolume( history.getVolume() );
        historyGetDto.setMarket_cap( history.getMarket_cap() );

        return historyGetDto;
    }

    @Override
    public List<HistoryGetDto> fromEntities(List<History> histories) {
        if ( histories == null ) {
            return null;
        }

        List<HistoryGetDto> list = new ArrayList<HistoryGetDto>( histories.size() );
        for ( History history : histories ) {
            list.add( fromEntity( history ) );
        }

        return list;
    }

    @Override
    public void copy(HistoryPutDto historyPutDto, History history) {
        if ( historyPutDto == null ) {
            return;
        }

        history.setCurrency( historyPutDto.getCurrency() );
        history.setDate( historyPutDto.getDate() );
        history.setOpen( historyPutDto.getOpen() );
        history.setHigh( historyPutDto.getHigh() );
        history.setLow( historyPutDto.getLow() );
        history.setClose( historyPutDto.getClose() );
        history.setVolume( historyPutDto.getVolume() );
        history.setMarket_cap( historyPutDto.getMarket_cap() );
    }
}
