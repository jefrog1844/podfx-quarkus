/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcr.podfx.business.dfmeas.control;

import com.jcr.podfx.business.dfmeas.entity.Dfmea;
import com.jcr.podfx.business.dfmeas.entity.DfmeaDetail;
import io.quarkus.panache.common.Sort;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;

@ApplicationScoped
public class DfmeaController {

    public List<Dfmea> listAll() {
        return Dfmea.listAll(Sort.by("title"));
    }

    @Transactional
    public Dfmea create(DfmeaDetail input) {
        Dfmea dfmea = new Dfmea(input);
        dfmea.originated = LocalDate.now();
        dfmea.persistAndFlush();
        return dfmea;
    }

    public Dfmea findById(Long id) {
        Optional<Dfmea> optional = Dfmea.findByIdOptional(id);
        Dfmea dfmea = optional.orElseThrow(() -> new NotFoundException());
        return dfmea;
    }

    @Transactional
    public void delete(Long id) {
        Dfmea.delete("id", id);
    }

    @Transactional
    public void update(Long id, DfmeaDetail input) {
        Dfmea dfmea = findById(id);
        dfmea.update(input);
    }

    public List<Dfmea> search(String title) {
        Stream<Dfmea> dfmeas = Dfmea.streamAll();
        return dfmeas.
                filter(d -> d.title.toLowerCase().contains(title.toLowerCase())).
                collect(Collectors.toList());
    }

}
