package ma.xproce.gestionevenements.mappers;

import ma.xproce.gestionevenements.dao.entities.Demande;
import ma.xproce.gestionevenements.dto.DemandeDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DemandeMapper {

    private final ModelMapper modelMapper;

    public DemandeMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Demande fromDtoToEntity(DemandeDto dto) {
        return modelMapper.map(dto, Demande.class);
    }

    public DemandeDto fromEntityToDto(Demande entity) {
        return modelMapper.map(entity, DemandeDto.class);
    }
}
