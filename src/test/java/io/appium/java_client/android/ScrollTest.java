/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.appium.java_client.android;

import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

import static org.junit.Assert.assertNotNull;

public class ScrollTest {

    private static AppiumDriverLocalService service;
    private static AndroidDriver<MobileElement> driver;

    @BeforeClass public static void beforeClass() throws Exception {
        service = AppiumDriverLocalService.buildDefaultService();
        service.start();

        if (service == null || !service.isRunning())
            throw new RuntimeException("An appium server node is not started!");

        File appDir = new File("src/test/java/io/appium/java_client");
        File app = new File(appDir, "ApiDemos-debug.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        driver = new AndroidDriver<>(service.getUrl(), capabilities);
    }

    @AfterClass public static void afterClass() {
        if (driver != null) {
            driver.quit();
        }
        if (service != null)
            service.stop();
    }

    @Before public void setup() throws Exception {
        driver.resetApp();
    }

    @Test public void scrollToTest() {
        driver.findElementByAccessibilityId("Views").click();
        driver.scrollTo("Radio G");
        MobileElement radioGroup = driver.findElementByAccessibilityId("Radio Group");
        assertNotNull(radioGroup.getLocation());
    }

    @Test public void scrollToExactTest() {
        driver.findElementByAccessibilityId("Views").click();
        driver.scrollToExact("Radio Group");
        MobileElement radioGroup = driver.findElementByAccessibilityId("Radio Group");
        assertNotNull(radioGroup.getLocation());
    }
}
