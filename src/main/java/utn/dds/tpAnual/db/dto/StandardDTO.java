package utn.dds.tpAnual.db.dto;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public abstract class StandardDTO <T>{

    public abstract StandardDTO from(T object);

    public abstract T toEntity();

    public List<StandardDTO> fromList(List<T> objectList){
        return objectList.stream().map(o -> from(o)).collect(Collectors.toList());
    }
    
    public List<T> toList(List<StandardDTO<T>> dtoList) {
        return dtoList.stream().map(dto -> dto.toEntity()).collect(Collectors.toList());
    }
}
