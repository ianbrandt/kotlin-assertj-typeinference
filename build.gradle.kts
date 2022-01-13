import org.gradle.api.JavaVersion.VERSION_11
import org.gradle.api.tasks.testing.logging.TestLogEvent.*
import org.gradle.api.tasks.wrapper.Wrapper.DistributionType.ALL
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("jvm") version "1.5.31"
}

group = "com.ianbrandt"
version = "1.0-SNAPSHOT"

repositories {
	mavenCentral()
}

dependencies {
	implementation(kotlin("stdlib"))

	testImplementation(platform("org.junit:junit-bom:5.8.1"))

	// Compiles
	// testImplementation("org.assertj:assertj-core:3.20.2")

	// e: src\test\kotlin\com\ianbrandt\test\TypeInferenceTest.kt:
	// Type mismatch: inferred type is String but Nothing! was expected
	testImplementation("org.assertj:assertj-core:3.21.0")

	testImplementation("org.junit.jupiter:junit-jupiter-api")

	testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

java {
	sourceCompatibility = VERSION_11
	targetCompatibility = VERSION_11
}

tasks {
	withType<KotlinCompile> {
		with(kotlinOptions) {
			languageVersion = "1.5"
			apiVersion = "1.5"
			jvmTarget = VERSION_11.toString()
		}
	}

	withType<Test> {
		useJUnitPlatform()
		testLogging {
			showStandardStreams = true
			events(PASSED, SKIPPED, FAILED, STANDARD_OUT, STANDARD_ERROR)
		}
	}

	named<Wrapper>("wrapper") {
		gradleVersion = "7.3.3"
		distributionType = ALL
	}
}
