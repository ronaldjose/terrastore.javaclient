/**
 * Copyright 2009 Sergio Bossa (sergio.bossa@gmail.com)
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package terrastore.client;

import java.util.ArrayList;
import java.util.List;

import terrastore.client.connection.Connection;
import terrastore.client.connection.ConnectionFactory;
import terrastore.client.connection.resteasy.RESTEasyConnectionFactory;
import terrastore.client.mapping.JsonObjectDescriptor;

/**
 * Terrastore Client for communicating with a Terrastore server cluster.
 * 
 * You only need to know and connect a single Terrastore server, as the server
 * itself will manage requests and route them to other servers in the cluster,
 * if needed.
 * 
 * Terrastore Client instances are immutable.
 * 
 * @author Sven Johansson
 * @author Sergio Bossa
 * @date 24 apr 2010
 * @since 2.0
 */
public class TerrastoreClient {

    static ConnectionFactory connectionFactory = new RESTEasyConnectionFactory();

    /**
     * The connection abstraction that will be used for all operations derived
     * from the client instance.
     */
    private final Connection connection;

    /**
     * Connects to the Terrastore server identified by the provided
     * serverHost/url.
     * 
     * @param serverHost The Terrastore server address (i.e.
     *            http://localhost:8080).
     */
    public TerrastoreClient(String serverHost) throws TerrastoreClientException {
        this(serverHost, new ArrayList<JsonObjectDescriptor<?>>(0));
    }

    /**
     * Connects to the Terrastore server identified by the provided
     * serverHost/url, and uses the provided {@link JsonObjectDescriptor}s to
     * serialize/deserialize objects.
     * 
     * @param serverHost The Terrastore server address (i.e.
     *            http://localhost:8080).
     * @param descriptors A list of {@link JsonObjectDescriptor}s describing how
     *            to serialize and deserialize object values.
     */
    public TerrastoreClient(String serverHost,
            List<JsonObjectDescriptor<?>> descriptors)
            throws TerrastoreClientException {
        if (null == serverHost) {
            throw new IllegalArgumentException(
                    "Cannot establish connection to null server URL");
        }

        this.connection = connectionFactory.makeConnection(serverHost,
                descriptors);
    }

    /**
     * Sets up a {@link BucketOperation} instance for the specified bucket name.
     * 
     * @param bucketName The name of the bucket to access.
     * @return A {@link BucketOperation} instance for the specified bucket name.
     */
    public BucketOperation bucket(String bucketName) {
        return new BucketOperation(connection, bucketName);
    }

    /**
     * Sets up a {@link BucketsOperation}.
     * 
     * @return A {@link BucketsOperation}.
     */
    public BucketsOperation buckets() {
        return new BucketsOperation(connection);
    }

    /**
     * @return The server host that this client instance is set up to
     *         communicate with.
     */
    public String getServerHost() {
        return connection.getServerHost();
    }

}