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

/**
 * Checked exception thrown when Terrastore server returns a failure response,
 * providing the HTTP response status code and (optional) response body entity. <br/>
 * <br/>
 * This exception is checked because users of the API should catch it and get
 * more information about the failure by inspecting the status code and response
 * entity.
 * 
 * @author Sergio Bossa
 */
public class TerrastoreRequestException extends TerrastoreClientException {

    private static final long serialVersionUID = 5783498742315816361L;

    private final int status;
    private final String entity;

    public TerrastoreRequestException(int status, String entity) {
        super();
        this.status = status;
        this.entity = entity;
    }

    public int getStatus() {
        return status;
    }

    public String getEntity() {
        return entity;
    }
}
