/*
 * Copyright (c) 2019 IBM Corporation and others
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bpb.jakarta.nosql.openlibertytest;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/country")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CountryService {


    @Inject
    CountryRepository countryRepository;


    @PostConstruct
    public void initPeople() {

    }

    @GET
    public Collection<Country> getAll() {

      return countryRepository.findAll();
    }

    @GET
    @Path("/{countryId}")
    public Country getCountry(@PathParam("countryId") String id) {
      return  countryRepository.getCountry(id);
       
    }

    @POST
    public String createCountry(@QueryParam("name") @NotEmpty @Size(min = 2, max = 50) String name,
            @QueryParam("id") String id) {             
     return   countryRepository.save(name,id);
  

    }

    @POST
    @Path("/{countryId}")
    public void updateCountry(@PathParam("countryId") String id, @Valid Country p) {
      countryRepository.updateCountry(id, p);
      
    }

    @DELETE
    @Path("/{countryId}")
    public void removeCountry(@PathParam("countryId") String id) {
        countryRepository.removeCountry(id);
      
    }



}
