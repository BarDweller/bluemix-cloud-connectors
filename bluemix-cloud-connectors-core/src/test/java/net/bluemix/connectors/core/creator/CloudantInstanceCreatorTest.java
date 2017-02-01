/*
 * Copyright IBM Corp. 2015
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.bluemix.connectors.core.creator;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import net.bluemix.connectors.core.info.CloudantServiceInfo;

import org.ektorp.CouchDbInstance;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.cloud.service.ServiceConnectorConfig;

public class CloudantInstanceCreatorTest {

    private CloudantInstanceCreator creator;

    @Before
    public void setUp() throws Exception {
        creator = new CloudantInstanceCreator();
    }

    @After
    public void tearDown() throws Exception {
        creator = null;
    }

    @Test
    public void testCreate() {
        final CloudantServiceInfo badUrlServiceInfo = new CloudantServiceInfo(
                "id",
                "username",
                "password",
                "hostname",
                443,
                "url"
        );
        assertNull(creator.create(badUrlServiceInfo, new ServiceConnectorConfig() {
        }));

        final CloudantServiceInfo serviceInfo = new CloudantServiceInfo(
                "testId",
                "username",
                "password",
                "username.cloudant.com",
                443,
                "https://username:password@username.cloudant.com"
        );
        assertTrue(creator.create(serviceInfo, new ServiceConnectorConfig() {
        }) instanceof CouchDbInstance);
    }

}
