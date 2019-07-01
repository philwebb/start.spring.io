/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.spring.start.site.extension.springcloud;

import io.spring.initializr.generator.spring.test.build.GradleBuildAssert;
import io.spring.initializr.web.project.ProjectRequest;
import io.spring.start.site.extension.AbstractExtensionTests;
import org.junit.jupiter.api.Test;

/**
 * Tests for {@link SpringCloudContractGradleBuildCustomizer}.
 *
 * @author Olga Maciaszek-Sharma
 */
class SpringCloudContractGradleBuildCustomizerTests extends AbstractExtensionTests {

	@Test
	void springCloudContractVerifierPluginAddedWhenSCCDependencyPresent() {
		ProjectRequest projectRequest = createProjectRequest(
				SpringCloudContractDependencyVerifier.CLOUD_CONTRACT_VERIFIER_DEPENDENCY_ID);
		projectRequest.setType("gradle-project");

		GradleBuildAssert build = generateGradleBuild(projectRequest);
		build.containsSubsequence("buildscript {", "dependencies {",
				"classpath 'org.springframework.cloud:spring-cloud-contract-gradle-plugin:");
		build.contains("apply plugin: 'spring-cloud-contract'");
	}

}
