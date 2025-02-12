package com.github.lupaev.etran.mapper;

import com.github.lupaev.etran.dto.GetSKPP;
import com.github.lupaev.etran.dto.Row;
import com.github.lupaev.etran.dto.RowSet;
import com.github.lupaev.etran.dto.SetSKPP;
import com.github.lupaev.etran.entity.Skpp;
import com.github.lupaev.etran.entity.SkppStrs;
import com.github.lupaev.etran.util.AppConstants;
import org.mapstruct.*;

import java.util.List;


/**
 * Маппер для СКПП
 */
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface SkppMapper {

	@Mapping(target = "clOrgId", source = "clOrgId")
	@Mapping(target = "reportDate", expression = "java(skpp.getReportDate() != null ? "
		+ "skpp.getReportDate().atStartOfDay()"
		+ ".format(java.time.format.DateTimeFormatter.ofPattern(\"dd.MM.yyyy HH:mm:ss\")) : null)")
	GetSKPP toGetSkpp(Skpp skpp);

	@Mapping(target = "reportDate", expression = "java(skpp.getReportDate() != null ? "
		+ "skpp.getReportDate().atStartOfDay()"
		+ ".format(java.time.format.DateTimeFormatter.ofPattern(\"dd.MM.yyyy HH:mm:ss\")) : null)")
	@Mapping(target = "clOrgId", source = "clOrgId")
	@Mapping(target = "rowSets", source = "skppStrs", qualifiedByName = "toRowSetList")
	@Mapping(target = "autocalcFlag", constant = "0")
	@Named("toSetSkpp")
	SetSKPP toSetSkpp(Skpp skpp);

	@Mapping(target = "clpStringId", source = "clpStnggId")
	void updateSkppStrs(@MappingTarget SkppStrs skppStrs, Row row);

	@Mapping(target = "action", constant = "1")
	@Mapping(target = "clpType", source = "clpType", qualifiedByName = "getClType")
	@Named("toRowSet")
	RowSet toRowSet(SkppStrs skppStrs);

	@Named("getClType")
	default Integer getClType(Integer clpType){
		return switch (clpType) {
			case 11,12 -> 1;
			case 13,15 -> 2;
			default -> throw new IllegalArgumentException(AppConstants.UNKNOWN_SHIPPING_TYPE + clpType);
		};
	}

	@IterableMapping(qualifiedByName = "toRowSet")
	@Named("toRowSetList")
	List<RowSet> toRowSetList(List<SkppStrs> skppStrsList);
}
