package com.jcr.podfx.business.interfaces.control;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.jcr.podfx.business.factors.entity.Factor;
import com.jcr.podfx.business.interfaces.entity.Interface;
import com.jcr.podfx.business.interfaces.entity.InterfaceDetail;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import static java.util.stream.Collectors.groupingBy;
import java.util.stream.Stream;
import javax.ws.rs.NotFoundException;

@ApplicationScoped
public class InterfaceController {

    @Inject
    EntityManager em;

    public Interface getInterface(Long interfaceId) {
        Optional<Interface> optional = Interface.findByIdOptional(interfaceId);
        return optional.orElseThrow(() -> new NotFoundException());
    }

    public Map<Factor, List<Interface>> getMatrix(Long dfmeaId) {

        Stream<Interface> interfaces = em.createQuery("select i from Interface i "
                + "JOIN Factor fInput on fInput.id = i.inputFactor.id "
                + "JOIN Factor fOutput on fOutput.id = i.outputFactor.id "
                + "where fInput.dfmea.id = :dfmeaId", Interface.class).
                setParameter("dfmeaId", dfmeaId).
                getResultList().stream().sorted(new Comparator<Interface>() {
                    @Override
                    public int compare(Interface i1, Interface i2) {
                        if (i1.getInputFactor().name.equals(i2.getInputFactor().name)) {
                            return i1.getOutputFactor().name.compareTo(i2.getOutputFactor().name);
                        } else {
                            return i1.getInputFactor().name.compareTo(i2.getInputFactor().name);
                        }
                    }
                });

        Map<Factor, List<Interface>> matrix = interfaces
                .collect(groupingBy(Interface::getInputFactor));
        return matrix;
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

}
