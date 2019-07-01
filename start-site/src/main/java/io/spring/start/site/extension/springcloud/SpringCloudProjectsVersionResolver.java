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

import java.util.Optional;

import io.spring.initializr.generator.version.Version;

/**
 * Resolves metadata related to generating projects with Spring Cloud dependencies.
 *
 * @author Olga Maciaszek-Sharma
 */
public interface SpringCloudProjectsVersionResolver {

	/**
	 * Resolve the version of a specified artifact that matches the provided Spring Boot
	 * version.
	 * @param bootVersion the Spring Boot version to check the Spring Cloud Release train
	 * version against.
	 * @param artifactId id of the specified Spring Cloud artifact.
	 * @return an {@link Optional} of the appropriate project version.
	 */
	Optional<String> resolveVersion(Version bootVersion, String artifactId);

}
