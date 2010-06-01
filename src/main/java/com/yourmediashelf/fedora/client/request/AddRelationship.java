
package com.yourmediashelf.fedora.client.request;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.yourmediashelf.fedora.client.FedoraClient;
import com.yourmediashelf.fedora.client.FedoraClientException;
import com.yourmediashelf.fedora.client.response.FedoraResponse;
import com.yourmediashelf.fedora.client.response.FedoraResponseImpl;

/**
 * Builder for the AddRelationship method.
 *
 * @author Edwin Shin
 */
public class AddRelationship
        extends FedoraRequest<AddRelationship> {

    private final String pid;

    public AddRelationship(String pid) {
        this.pid = pid;
    }

    public AddRelationship subject(String subject) {
        addQueryParam("subject", subject);
        return this;
    }

    public AddRelationship predicate(String predicate) {
        addQueryParam("predicate", predicate);
        return this;
    }

    public AddRelationship object(String object) {
        addQueryParam("object", object);
        return this;
    }

    public AddRelationship isLiteral(boolean isLiteral) {
        addQueryParam("isLiteral", Boolean.toString(isLiteral));
        return this;
    }

    public AddRelationship datatype(String datatype) {
        addQueryParam("datatype", datatype);
        return this;
    }

    @Override
    public FedoraResponse execute(FedoraClient fedora) throws FedoraClientException {
        WebResource wr = fedora.resource();
        String path = String.format("objects/%s/relationships/new", pid);

        return new FedoraResponseImpl(wr.path(path)
                .queryParams(getQueryParams()).post(ClientResponse.class));
    }

}