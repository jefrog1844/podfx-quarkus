package com.jcr.podfx.business.factors.control;

import com.jcr.podfx.business.dfmeas.entity.Dfmea;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import com.jcr.podfx.business.factors.entity.Factor;
import com.jcr.podfx.business.factors.entity.FactorDetail;
import com.jcr.podfx.business.interfaces.control.InterfaceController;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.transaction.Transactional;

@ApplicationScoped
public class FactorController {

    @Inject
    InterfaceController ic;

    public List<FactorDetail> listAll(Long dfmeaId) {
        Stream<Factor> factors = Factor.find("DFMEA_ID", dfmeaId).stream();
        return factors.map(f -> new FactorDetail(f.id, f.name, f.category, f.getDfmea().id))
                .collect(Collectors.toList());
    }

    public Factor findById(Long factorId) {
        Optional<Factor> optional = Factor.findByIdOptional(factorId);
        return optional.orElseThrow(() -> new NotFoundException());
    }

    @Transactional
    public void save(Long dfmeaId, FactorDetail detail) {
        Dfmea dfmea = Dfmea.findById(dfmeaId);
        Factor output = new Factor(detail.getName(), detail.getCategory());
        output.setDfmea(dfmea);

        List<Factor> factors = Factor.find("DFMEA_ID", dfmeaId).list();
        output.persistAndFlush();
        for (Factor input : factors) {
            if (input.isExternal() && output.isExternal()) {
                continue;
            } else if (input.equals(output)) {
                continue;
            } else if (input.isInternal()) {
                createInterface(input, output);
            } else {
                createInterface(output, input);
            }
        }
        
    }
    
    @Transactional
    public void update(FactorDetail input) {
        Factor.update("name =?1,category=?2 where id=?3", input.getName(), input.getCategory(),
                input.getId());
    }
    

    @Transactional
    public void delete(Long factorId) {
        // first delete interfaces which have FK to this factor
        ic.deleteInterface(factorId);
        Factor.delete("id", factorId);

    }

    private void createInterface(Factor input, Factor output) {
        ic.save(input, output);
    }
}
