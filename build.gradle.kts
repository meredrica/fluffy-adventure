plugins {
	id("org.jetbrains.kotlin.jvm") version "1.5.0"
	id("org.jetbrains.kotlin.kapt") version "1.5.0"
	id("org.jetbrains.kotlin.plugin.allopen") version "1.5.0"
	id("com.github.johnrengelman.shadow") version "7.0.0"
	id("io.micronaut.application") version "1.5.0"
	id("com.diffplug.spotless") version "5.12.4"
}

version = "0.1"
group = "dispatch"
val imageVersion = System.getenv("CI_COMMIT_SHORT_SHA") ?: "latest"

val kotlinVersion=project.properties.get("kotlinVersion")
repositories {
	mavenCentral()
}

micronaut {
	runtime("netty")
	testRuntime("junit5")
	processing {
		incremental(true)
		annotations("dispatch.*")
	}
}

spotless {
	kotlin {
		ktfmt("0.24")
	}
}

dependencies {
	implementation("io.micronaut:micronaut-http-client")
	implementation("io.micronaut:micronaut-management")
	implementation("io.micronaut:micronaut-runtime")
	implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")
	implementation("io.micronaut.rabbitmq:micronaut-rabbitmq")
	implementation("org.jetbrains.kotlin:kotlin-reflect:${kotlinVersion}")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${kotlinVersion}")
	implementation("org.jetbrains.kotlin:kotlin-stdlib:${kotlinVersion}")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-common:${kotlinVersion}")
	testImplementation("org.testcontainers:junit-jupiter")
	testImplementation("org.testcontainers:testcontainers")
	compileOnly("org.graalvm.nativeimage:svm")
	implementation("io.micronaut:micronaut-validation")
	runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")
	runtimeOnly("org.apache.logging.log4j:log4j-api:2.14.1")
}


application {
	mainClass.set("dispatch.ApplicationKt")
}
java {
	sourceCompatibility = JavaVersion.toVersion("1.8")
}

tasks {
	assemble {
		dependsOn(spotlessApply)
	}
	compileKotlin {
		kotlinOptions {
			jvmTarget = "1.8"
		}
	}
	compileTestKotlin {
		kotlinOptions {
			jvmTarget = "1.8"
		}
	}
	dockerBuild {
		images.set(listOf("dispatch:${imageVersion}"))
	}
	dockerBuildNative {
		images.set(listOf("dispatch:${imageVersion}"))
	}
}

