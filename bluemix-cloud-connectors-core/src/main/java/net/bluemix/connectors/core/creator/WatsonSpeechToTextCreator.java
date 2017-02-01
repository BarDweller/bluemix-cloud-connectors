/*
 * Copyright IBM Corp. 2017
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.bluemix.connectors.core.creator;

import com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText;
import net.bluemix.connectors.core.info.WatsonSpeechToTextServiceInfo;
import org.springframework.cloud.service.AbstractServiceConnectorCreator;
import org.springframework.cloud.service.ServiceConnectorConfig;

/**
 * Class which creates a
 * {@link com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText} object
 * using the service credentials for the Watson Speech To Text service.
 *
 * @author Hans W. Uhlig <hans.uhlig@ibm.com>
 */
public class WatsonSpeechToTextCreator
        extends AbstractServiceConnectorCreator<SpeechToText, WatsonSpeechToTextServiceInfo> {

    @Override
    public SpeechToText create(
            final WatsonSpeechToTextServiceInfo serviceInfo,
            final ServiceConnectorConfig serviceConnectorConfig) {
        final SpeechToText service = new SpeechToText();
        service.setEndPoint(serviceInfo.getUrl());
        service.setUsernameAndPassword(serviceInfo.getUsername(), serviceInfo.getPassword());
        return service;
    }

}
