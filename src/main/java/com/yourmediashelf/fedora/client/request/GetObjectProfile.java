
package com.yourmediashelf.fedora.client.request;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.yourmediashelf.fedora.client.FedoraClient;
import com.yourmediashelf.fedora.client.FedoraClientException;
import com.yourmediashelf.fedora.client.response.GetObjectProfileResponse;

/**
 * Builder for the GetObjectProfile method.
 *
 * @author Edwin Shin
 */
public class GetObjectProfile
        extends FedoraRequest<GetObjectProfile> {

    private final String pid;

    /**
     * @param pid
     *        persistent identifier of the digital object
     */
    public GetObjectProfile(String pid) {
        this.pid = pid;
    }

    /**
     *
     * @param asOfDateTime yyyy-MM-dd or yyyy-MM-ddTHH:mm:ssZ
     * @return this builder
     */
    public GetObjectProfile asOfDateTime(String asOfDateTime) {
        addQueryParam("asOfDateTime", asOfDateTime);
        return this;
    }

    /**
     * <p>The format of the response. Defaults to "xml".</p>
     *
     * <p>The Fedora REST API default is "html", but
     * fedora-client will set "xml" as the default in order to parse the
     * response. If "html" is selected, the caller is responsible for parsing
     * the raw HTTP response as most of the FedoraResponse convenience methods
     * rely on an XML response.</p>
     *
     * @param format "html" or "xml". Defaults to "xml".
     * @return this builder
     */
    public GetObjectProfile format(String format) {
        addQueryParam("format", format);
        return this;
    }

    @Override
    public GetObjectProfileResponse execute(FedoraClient fedora) throws FedoraClientException {
        // default to xml for the format, so we can parse the results
        if (getFirstQueryParam("format") == null) {
            addQueryParam("format", "xml");
        }

        WebResource wr = fedora.resource();
        String path = String.format("objects/%s", pid);

        ClientResponse cr = wr.path(path).queryParams(getQueryParams()).get(ClientResponse.class);
        return new GetObjectProfileResponse(cr);
    }

}