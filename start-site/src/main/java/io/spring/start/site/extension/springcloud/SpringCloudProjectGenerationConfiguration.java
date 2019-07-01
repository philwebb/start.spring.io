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

import io.spring.initializr.generator.buildsystem.Build;
import io.spring.initializr.generator.buildsystem.gradle.GradleBuildSystem;
import io.spring.initializr.generator.buildsystem.maven.MavenBuildSystem;
import io.spring.initializr.generator.condition.ConditionalOnBuildSystem;
import io.spring.initializr.generator.condition.ConditionalOnPlatformVersion;
import io.spring.initializr.generator.io.template.TemplateRenderer;
import io.spring.initializr.generator.project.ProjectGenerationConfiguration;
import io.spring.initializr.generator.project.ResolvedProjectDescription;
import io.spring.initializr.metadata.InitializrMetadata;
import io.spring.initializr.versionresolver.DependencyManagementVersionResolver;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * Configuration for generation of projects that depend on Spring Cloud.
 *
 * @author Stephane Nicoll
 */
@ProjectGenerationConfiguration
public class SpringCloudProjectGenerationConfiguration {

	private final InitializrMetadata metadata;

	private final ResolvedProjectDescription description;

	public SpringCloudProjectGenerationConfiguration(InitializrMetadata metadata,
			ResolvedProjectDescription description) {
		this.metadata = metadata;
		this.description = description;
	}

	@Bean
	public SpringCloudFunctionBuildCustomizer springCloudFunctionBuildCustomizer() {
		return new SpringCloudFunctionBuildCustomizer(this.metadata, this.description);
	}

	@Bean
	public SpringCloudGcpBomBuildCustomizer springCloudGcpBomBuildCustomizer() {
		return new SpringCloudGcpBomBuildCustomizer(this.description);
	}

	@Bean
	public SpringCloudStreamBuildCustomizer springCloudStreamBuildCustomizer() {
		return new SpringCloudStreamBuildCustomizer();
	}

	@Bean
	@ConditionalOnPlatformVersion("2.1.0.RELEASE")
	public SpringCloudNetflixMaintenanceModeHelpDocumentCustomizer maintenanceModuleHelpDocumentCustomizer(Build build,
			TemplateRenderer templateRenderer) {
		return new SpringCloudNetflixMaintenanceModeHelpDocumentCustomizer(this.metadata, build, templateRenderer);
	}

	@Bean
	@ConditionalOnMissingBean(SpringCloudProjectsVersionResolver.class)
	SpringCloudProjectsVersionResolver springCloudProjectsVersionResolver(
			DependencyManagementVersionResolver versionResolver) {
		return new DefaultSpringCloudProjectsVersionResolver(this.metadata, versionResolver);
	}

	@Bean
	@ConditionalOnBuildSystem(MavenBuildSystem.ID)
	SpringCloudContractMavenBuildCustomizer springCloudContractMavenBuildCustomizer(
			SpringCloudProjectsVersionResolver versionResolver) {
		return new SpringCloudContractMavenBuildCustomizer(this.description, versionResolver);
	}

	@Bean
	@ConditionalOnBuildSystem(GradleBuildSystem.ID)
	SpringCloudContractGradleBuildCustomizer springCloudContractGradleBuildCustomizer(
			SpringCloudProjectsVersionResolver versionResolver) {
		return new SpringCloudContractGradleBuildCustomizer(this.description, versionResolver);
	}

}
