package com.codeTest.analyze_crypto.mappers;

import com.codeTest.analyze_crypto.dtos.history.HistoryGetDto;
import com.codeTest.analyze_crypto.dtos.history.HistoryPostDto;
import com.codeTest.analyze_crypto.dtos.history.HistoryPutDto;
import com.codeTest.analyze_crypto.entities.History;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HistoryMapper {
    History toEntity(HistoryPostDto historyPostDto);
    HistoryGetDto fromEntity(History history);
    List<HistoryGetDto> fromEntities(List<History> histories);
    void copy(HistoryPutDto historyPutDto, @MappingTarget History history);
}
