<<<<<<< HEAD
package com.news.service.Implementation;

import com.news.dto.InformationDto;
import com.news.entity.Information;
import com.news.repository.InformationRepo;
import com.news.service.InformationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class InformationServiceImp implements InformationService {

    private final InformationRepo informationRepo;
    private final ModelMapper modelMapper;

    public InformationServiceImp(InformationRepo informationRepo, ModelMapper modelMapper) {
        this.informationRepo = informationRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public void create(InformationDto information) {
        informationRepo.save(information.getInformation(modelMapper));
    }

    @Override
    public void delete(int id) {
        informationRepo.deleteById(id);
    }

    @Override
    public void update(InformationDto information) {
        informationRepo.save(information.getInformation(modelMapper));
    }

    @Override
    public List<InformationDto> findAll() {
        List<Information> informationListList = informationRepo.findAll();
        var result= new ArrayList<InformationDto>();

        informationListList.forEach(product -> {
            var informationDto=modelMapper.map(product, InformationDto.class);
            result.add(informationDto);
        });
        return result;
    }

    @Override
    public InformationDto findById(int id) {
        var information=informationRepo.findById(id).orElseThrow(()->new RuntimeException("information not found"));
        var result = modelMapper.map(information, InformationDto.class);
        return result;
    }
=======
package com.news.service.Implementation;public class InformationServieImp {
>>>>>>> origin/news
}
