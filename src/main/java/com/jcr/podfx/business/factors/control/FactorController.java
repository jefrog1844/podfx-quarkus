package com.jcr.podfx.business.factors.control;

import java.util.Optional;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import com.jcr.podfx.business.IdGenerator;
import com.jcr.podfx.business.dfmeas.entity.Dfmea;
import com.jcr.podfx.business.factors.entity.Factor;
import com.jcr.podfx.business.factors.entity.FactorDetail;
import com.jcr.podfx.business.interfaces.control.InterfaceController;

@ApplicationScoped
public class FactorController {

	@Inject
	InterfaceController ic;

	public void delete(String dfmeaId, String factorId) {
		Optional<Factor> optional = Factor.findByIdOptional(factorId);
		Factor factor = optional.orElseThrow(() -> new NotFoundException());
		ic.deleteInterface(factor);
		Dfmea dfmea = Dfmea.findById(dfmeaId);
		dfmea.removeFactor(factor);

		

	}

	public void save(String dfmeaId, FactorDetail detail) {
		Factor output = new Factor(IdGenerator.createId(), detail.getType(), detail.getName(), detail.getCategory());
		Dfmea dfmea = Dfmea.findById(dfmeaId);
		dfmea.addFactor(output);
		Set<Factor> factors = dfmea.getFactors();
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

	private void createInterface(Factor input, Factor output) {
		ic.save(input, output);
	}
}
