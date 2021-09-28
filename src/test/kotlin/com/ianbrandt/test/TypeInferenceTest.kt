package com.ianbrandt.test

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TypeInferenceTest {

	@Test
	fun `test Kotlin type inference`() {
		val cache = HashSet<String>()
		val testString = "testing"
		cache.add(testString)

		/* Compiles with AssertJ 3.20.2.
		 * With AssertJ 3.21.0: "Type mismatch: inferred type is String but Nothing! was expected"
		 * on the `contains(...)` call, unless the `describedAs(...)` call is removed, in which case
		 * it compiles again.
		 * Diff: https://github.com/assertj/assertj-core/compare/assertj-core-3.20.2...assertj-core-3.21.0
		 */
		assertThat(cache)
			.describedAs("cache")
			.contains(testString)
	}
}
