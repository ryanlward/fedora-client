
package com.yourmediashelf.fedora.client.request;

import static com.yourmediashelf.fedora.client.FedoraClient.getNextPID;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.yourmediashelf.fedora.client.response.GetNextPIDResponse;

public class GetNextPIDTest extends FedoraMethodBaseTest {
    @Test
    public void testGetOnePid() throws Exception {
        GetNextPIDResponse response = getNextPID().execute(fedora());
        assertEquals(200, response.getStatus());
        assertNotNull(response.getPid());
    }

    @Test
    public void testGetOneNamespacedPid() throws Exception {
        String namespace = "foo";
        GetNextPIDResponse response = getNextPID().namespace(namespace).execute(fedora());
        assertEquals(200, response.getStatus());
        assertTrue(response.getPid().startsWith(namespace));
    }

    @Test
    public void testGetTwoPids() throws Exception {
        GetNextPIDResponse response = null;
        response = getNextPID().numPIDs(2).execute(fedora()) ;
        assertEquals(200, response.getStatus());
        assertEquals(2, response.getPids().size());
    }
}
