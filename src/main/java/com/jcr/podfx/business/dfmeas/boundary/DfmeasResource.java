package com.jcr.podfx.business.dfmeas.boundary;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import com.jcr.podfx.business.dfmeas.entity.Dfmea;
import com.jcr.podfx.business.dfmeas.entity.DfmeaDetail;

import io.quarkus.panache.common.Sort;

@ApplicationScoped
@Path("/dfmeas")
public class DfmeasResource {

    @GET
    @RolesAllowed("read")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Dfmea> find() {
        return Dfmea.listAll(Sort.by("title"));
    }

    @POST
    @Transactional
    @RolesAllowed("create")
    @Consumes(MediaType.APPLICATION_JSON)
    public void save(DfmeaDetail input) {
        Dfmea dfmea = new Dfmea(input);
        dfmea.persist();
    }

    @Path("{dfmeaId}")
    @GET
    @RolesAllowed("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Dfmea get(@PathParam("dfmeaId") Long id) {
        Optional<Dfmea> optional = Dfmea.findByIdOptional(id);
        Dfmea dfmea = optional.orElseThrow(() -> new NotFoundException());
        return dfmea;
    }

    @Path("{dfmeaId}")
    @DELETE
    @Transactional
    @RolesAllowed("delete")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("dfmeaId") Long id) {
        Dfmea dfmea = get(id);
        if (dfmea.isPersistent()) {
            dfmea.delete();
        }
    }

    @Path("{dfmeaId}")
    @PUT
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(@PathParam("dfmeaId") Long dfmeaId,
            DfmeaDetail input) {
        Dfmea dfmea = get(dfmeaId);
        dfmea.update(input);
    }

    @GET
    @Path("search")
    @RolesAllowed("read")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Dfmea> search(@Context UriInfo info) {
        String title = info.getQueryParameters().getFirst("title");
        Stream<Dfmea> dfmeas = Dfmea.streamAll();
        return dfmeas.
                filter(d -> d.title.toLowerCase().contains(title.toLowerCase())).
                collect(Collectors.toList());
    }

}
