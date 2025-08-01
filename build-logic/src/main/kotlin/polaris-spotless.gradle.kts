/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

plugins { id("com.diffplug.spotless") }

// skip spotless check for duplicated projects
if (!project.extra.has("duplicated-project-sources")) {
  spotless {
    java {
      target("src/*/java/**/*.java")
      googleJavaFormat()
      // licenseHeaderFile(rootProject.file("codestyle/copyright-header-java.txt"))
      endWithNewline()
      toggleOffOn()
    }
    kotlinGradle {
      ktfmt().googleStyle()
      // licenseHeaderFile(rootProject.file("codestyle/copyright-header-java.txt"), "$")
      target("*.gradle.kts")
    }
    format("xml") {
      target("src/**/*.xml", "src/**/*.xsd")
      targetExclude("codestyle/copyright-header.xml")
      eclipseWtp(com.diffplug.spotless.extra.wtp.EclipseWtpFormatterStep.XML)
        .configFile(rootProject.file("codestyle/org.eclipse.wst.xml.core.prefs"))
      // getting the license-header delimiter right is a bit tricky.
      // licenseHeaderFile(rootProject.file("codestyle/copyright-header.xml"), '<^[!?].*$')
    }
  }
}
