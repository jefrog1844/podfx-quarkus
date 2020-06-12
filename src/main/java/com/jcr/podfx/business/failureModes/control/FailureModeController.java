/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcr.podfx.business.failureModes.control;

import com.jcr.podfx.business.dfmeas.entity.Dfmea;
import com.jcr.podfx.business.failureModes.entity.FailureMode;
import java.util.List;
import java.util.stream.Stream;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.jcr.podfx.business.funktions.entity.Funktion;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import static java.util.stream.Collectors.groupingBy;
import javax.ws.rs.NotFoundException;

@ApplicationScoped
public class FailureModeController {

    @Inject
    EntityManager em;

    
}
