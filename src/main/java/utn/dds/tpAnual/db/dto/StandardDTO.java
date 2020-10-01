package utn.dds.tpAnual.db.dto;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public abstract class StandardDTO <T>{
    public abstract StandardDTO from(T object);
    public List<StandardDTO> fromList(List<T> objectList){
        return objectList.stream().map(o -> from(o)).collect(Collectors.toList());
    }
}
