/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.obd2.exceptions;

import org.powermock.core.classloader.annotations.PrepareForTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.obd2.commands.SpeedCommand;
import com.obd2.exceptions.NonNumericResponseException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.powermock.api.easymock.PowerMock.*;

/**
 * Test results with echo on and off.
 */
@PrepareForTest(InputStream.class)
public class NonNumericResponseExceptionTest {

    private SpeedCommand command;
    private InputStream mockIn;

    /**
     * @throws Exception
     */
    @BeforeMethod
    public void setUp() throws Exception {
        command = new SpeedCommand();
    }

    /**
     * Test for valid InputStream read with echo
     *
     * @throws java.io.IOException, java.lang.InterruptedException
     */
    @Test(expectedExceptions = NonNumericResponseException.class)
    public void testValidSpeedMetricWithMessage() throws IOException, InterruptedException {
        // mock InputStream read
        mockIn = createMock(InputStream.class);
        mockIn.read();
        expectLastCall().andReturn((byte) 'O');
        expectLastCall().andReturn((byte) 'K');
        expectLastCall().andReturn((byte) '>');

        replayAll();

        // call the method to test
        command.run(mockIn, new ByteArrayOutputStream());

        verifyAll();
    }

}
