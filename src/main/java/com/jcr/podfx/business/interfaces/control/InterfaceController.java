package com.jcr.podfx.business.interfaces.control;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.jcr.podfx.business.factors.entity.Factor;
import com.jcr.podfx.business.interfaces.entity.Interface;
import com.jcr.podfx.business.interfaces.entity.InterfaceDetail;
import com.jcr.podfx.business.interfaces.entity.Matrix;
import java.util.Map;
import java.util.Optional;
import static java.util.stream.Collectors.groupingBy;
import java.util.stream.Stream;
import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.NotFoundException;

@ApplicationScoped
public class InterfaceController {

    @Inject
    EntityManager em;

    public Interface getInterface(Long interfaceId) {
        Optional<Interface> optional = Interface.findByIdOptional(interfaceId);
        return optional.orElseThrow(() -> new NotFoundException());
    }

    
    public Map<Factor,List<Interface>> getMatrix(Long dfmeaId) {
        //Stream<Interface> interfaces = Interface.find("dfmea_id", dfmeaId).stream();
        Stream<Interface> interfaces = em.createQuery("select i from Interface i "
                + "JOIN Factor fInput on fInput.id = i.inputFactor.id "
                + "JOIN Factor fOutput on fOutput.id = i.outputFactor.id "
                + "where fInput.dfmea.id = :dfmeaId", Interface.class).
                setParameter("dfmeaId", dfmeaId).
                getResultList().stream();
        
        Map<Factor,List<Interface>> matrix = interfaces
                .collect(groupingBy(Interface::getInputFactor));
        return matrix;
    }
    
    
    public List<Matrix> getInterfaceMatrix(Long dfmeaId) {
        Stream<Factor> factors = Factor.find("dfmea_id", dfmeaId).stream();
        
       
                return factors
                .filter(Factor::isInternal)
                .map(f -> new Matrix(f)).collect(Collectors.toList());
    }

    public void deleteInterface(Long factorId) {
        Interface.delete("OUTPUT_FACTOR_ID", factorId);
        Interface.delete("INPUT_FACTOR_ID", factorId);
    }

    public void update(InterfaceDetail input) {
        Interface.update(
                "enabled=?1,physicalConnection=?2,energyTransfer=?3,materialExchange=?4,dataExchange=?5 where id=?6",
                input.isEnabled(), input.getPhysicalConnection(), input.getEnergyTransfer(),
                input.getMaterialExchange(), input.getDataExchange(), input.getId());
    }

    public void save(Factor input, Factor output) {
        Interface i = new Interface(input, output, false);
        i.persist();
    }
    
    public List<Interface> getOldInterfaceMatrix(Long dfmeaId) {
        List<Interface> results = em.createQuery("select i from Interface i "
                + "JOIN Factor fInput on fInput.id = i.inputFactor.id "
                + "JOIN Factor fOutput on fOutput.id = i.outputFactor.id "
                + "where fInput.dfmea.id = :dfmeaId", Interface.class).
                setParameter("dfmeaId", dfmeaId).
                getResultList();
        return results;
    }

}
