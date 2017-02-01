/*
 * Copyright IBM Corp. 2017
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
package net.bluemix.connectors.cloudfoundry.creator;

import java.util.Map;

import net.bluemix.connectors.core.info.WatsonConversationServiceInfo;

import org.springframework.cloud.cloudfoundry.CloudFoundryServiceInfoCreator;
import org.springframework.cloud.cloudfoundry.Tags;

/**
 * Creates a new {@link WatsonConversationServiceInfo}.
 *
 * @author Ryan J. Baxter <rbaxter@apache.org>
 *
 */
public class WatsonConversationServiceInfoCreator
        extends CloudFoundryServiceInfoCreator<WatsonConversationServiceInfo> {

    /**
     * Constructor.
     */
    public WatsonConversationServiceInfoCreator() {
        super(new Tags(), WatsonConversationServiceInfo.SCHEME);
    }

    /**
     * {@inheritDoc}
     *
     * @param serviceData Incoming Service Data
     * @return True if Service Data acceptable.
     */
    @Override
    public boolean accept(final Map<String, Object> serviceData) {
        boolean result = false;
        // Don't really like using the label as the determining factor but that is the only
        // unique attribute to identify the service with.
        final Object obj = serviceData.get("label");
        if (obj instanceof String) {
            String label = (String) obj;
            result = "conversation".equals(label);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @param serviceData
     * @return
     */
    @Override
    public WatsonConversationServiceInfo createServiceInfo(final Map<String, Object> serviceData) {
        String id = null;
        String username = null;
        String password = null;
        String url = null;
        final Object idObj = serviceData.get("name");
        final Object credObject = serviceData.get("credentials");
        if (idObj instanceof String) {
            id = (String) idObj;
        }
        if (credObject instanceof Map<?, ?>) {
            final Map<String, Object> credentials = (Map<String, Object>) credObject;
            final Object usernameObj = credentials.get("username");
            final Object passwordObj = credentials.get("password");
            final Object urlObj = credentials.get("url");
            if (usernameObj instanceof String) {
                username = (String) usernameObj;
            }
            if (passwordObj instanceof String) {
                password = (String) passwordObj;
            }
            if (urlObj instanceof String) {
                url = (String) urlObj;
            }
        }
        return new WatsonConversationServiceInfo(id, username, password, url);
    }

}
