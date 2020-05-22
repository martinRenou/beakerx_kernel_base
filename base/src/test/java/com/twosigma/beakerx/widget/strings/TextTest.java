/*
 *  Copyright 2017 TWO SIGMA OPEN SOURCE, LLC
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.twosigma.beakerx.widget.strings;

import com.twosigma.beakerx.KernelTest;
import com.twosigma.beakerx.KernelTestFactory;
import com.twosigma.beakerx.kernel.KernelManager;
import com.twosigma.beakerx.widget.Text;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;

import static com.twosigma.beakerx.widget.TestWidgetUtils.verifyMsgForProperty;
import static com.twosigma.beakerx.widget.strings.TextFieldChecker.verifyTextField;

public class TextTest {

  private KernelTest groovyKernel;

  @Before
  public void setUp() throws Exception {
    groovyKernel = KernelTestFactory.getKernel();
    KernelManager.register(groovyKernel);
  }

  @After
  public void tearDown() throws Exception {
    KernelManager.register(null);
  }

  @Test
  public void shouldSendCommOpenWhenCreate() throws Exception {
    //given
    //when
    new Text();
    //then
    verifyTextField(
            groovyKernel.getPublishedMessages(),
            Text.MODEL_NAME_VALUE,
            Text.MODEL_MODULE_VALUE,
            Text.VIEW_NAME_VALUE,
            Text.VIEW_MODULE_VALUE
    );
  }

  @Test
  public void shouldSendCommMsgWhenValueChange() throws Exception {
    //given
    Text widget = text();
    //when
    widget.setValue("1");
    //then
    verifyMsgForProperty(groovyKernel, Text.VALUE, "1");
  }

  private Text text() throws NoSuchAlgorithmException {
    Text widget = new Text();
    groovyKernel.clearPublishedMessages();
    return widget;
  }
}