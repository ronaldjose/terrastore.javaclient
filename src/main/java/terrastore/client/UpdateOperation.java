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

import java.util.Map;

import terrastore.client.connection.Connection;

/**
 * @author Sven Johansson
 * @date 25 apr 2010
 * @since 2.0
 */
public class UpdateOperation extends AbstractKeyOperation {
	
	private UpdateFunction function = UpdateFunction.REPLACE;
	private Map<String, Object> parameters;
	private long timeOut;

	public UpdateOperation(KeyOperation key, Connection connection) {
		super(key, connection);
	}

	/**
	 * Specifies The max number of milliseconds for the update operation to
	 * complete successfully.
	 * 
	 * @param timeOut The timeout for this operation, in milliseconds
	 */
	public UpdateOperation timeOut(long timeOut) {
		this.timeOut = timeOut;
		return this;
	}

	public UpdateOperation parameters(Map<String, Object> parameters) {
		this.parameters = parameters;
		return this;
	}
	
	/**
	 * Executes this update operation.
	 * 
	 * The server side function must not last more that the given timeout (expressed in milliseconds): otherwise,
     * an exception is thrown and update is aborted.<br>
     * That's because the server side update operation locks the updated value for its duration (in order to provide per-record ACID properties): use of timeouts
     * minimizes livelocks and starvations.
	 * 
	 * @throws TerrastoreClientException
	 */
	public void execute() throws TerrastoreClientException {
		connection.executeUpdate(this);
	}

	public String key() {
		return key.key();
	}
	
	public String bucketName() {
		return key.bucketName();
	}

	public String functionId() {
		return function.functionId();
	}

	public long timeOut() {
		return timeOut;
	}

	public Map<String, Object> parameters() {
		return parameters;
	}
	
	
}