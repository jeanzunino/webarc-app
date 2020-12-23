package com.undcon.app.mappers;

import org.apache.commons.httpclient.HttpState;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.undcon.app.dtos.PdvDto;
import com.undcon.app.model.PdvEntity;

@Component
public class PdvMapper {

	public PdvDto toDto(PdvEntity entity) {
		PdvDto pdv = new PdvDto();
		pdv.setId(entity.getId());
		pdv.setOpeningDate(entity.getOpeningDate());
		pdv.setClosingDate(entity.getClosingDate());
		pdv.setOpeningValue(entity.getOpeningValue());
		pdv.setClosingValue(entity.getClosingValue());
		return pdv;
	}
}
