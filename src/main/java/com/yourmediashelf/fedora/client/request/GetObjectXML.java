
package com.yourmediashelf.fedora.client.request;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.yourmediashelf.fedora.client.FedoraClient;
import com.yourmediashelf.fedora.client.FedoraClientException;
import com.yourmediashelf.fedora.client.response.FedoraResponse;
import com.yourmediashelf.fedora.client.response.FedoraResponseImpl;

/**
 * Builder for the GetObjectXML method.
 *
 * @author Edwin Shin
 * @since 0.0.3
 */
public class GetObjectXML
        extends FedoraRequest<GetObjectXML> {

    private final String pid;

    /**
     * @param pid
     *        persistent identifier of the digital object, e.g. "demo:1".
     */
    public GetObjectXML(String pid) {
        this.pid = pid;
    }

    @Override
    public FedoraResponse execute(FedoraClient fedora) throws FedoraClientException {
        WebResource wr = fedora.resource();
        String path = String.format("objects/%s/objectXML", pid);

        ClientResponse cr = wr.path(path).queryParams(getQueryParams()).get(ClientResponse.class);
        return new FedoraResponseImpl(cr);
    }
}