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
package com.bpbonline.jakarta.nosql.openlibertytest.test;

import com.bpb.jakarta.nosql.openlibertytest.Country;
import com.bpb.jakarta.nosql.openlibertytest.CountryService;
import java.util.Collection;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.microshed.testing.jaxrs.RESTClient;
import org.microshed.testing.jupiter.MicroShedTest;
import org.microshed.testing.testcontainers.ApplicationContainer;
import org.testcontainers.junit.jupiter.Container;

@MicroShedTest
public class JaxrsJsonIT {

    @Container
    public static ApplicationContainer app = new ApplicationContainer()
            .withAppContextRoot("/openlibertytest")
            .withReadinessPath("/health/ready");

    @RESTClient
    public static CountryService countryService;

    @Test
    public void testCreateCountry() {
        String id = countryService.createCountry("Brasil", "br");
        assertNotNull(id);

    }

    @Test
    public void testGetCountry() {
        String id = countryService.createCountry("Colombia", "co");
        Country country = countryService.getCountry(id);
        assertEquals("Colombia", country.getName());
        assertNotNull(country.getId());
    }
//

    @Test
    public void testGetAll() {
        String country1Id = countryService.createCountry("Panama", "pa");
        String country2Id = countryService.createCountry("Colombia", "co");
        String country3Id = countryService.createCountry("Brasil", "br");

        Collection<Country> allCountry = countryService.getAll();
        assertTrue(allCountry.size() >= 2);
    }

    @Test
    public void testUpdate() {
        String countryId = countryService.createCountry("Argentina", "ar");

        Country originalCountry = countryService.getCountry(countryId);
        assertEquals("Argentina", originalCountry.getName());

        countryService.updateCountry(countryId, new Country("Spain", originalCountry.getId()));
        Country updatedCountry = countryService.getCountry(countryId);
        assertEquals("Spain", updatedCountry.getName());

    }

}
