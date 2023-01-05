package algo

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Exemple7Equilibrage {

    @ParameterizedTest
    @MethodSource("data")
    internal fun `should `(tableau: IntArray, expected: Pair<IntArray, IntArray>) {
        // When
        val result = Exemple7().apply(tableau)

        // Then
        assertThat(result.first).containsExactly(expected.first.toTypedArray())
        assertThat(result.second).containsExactly(expected.second.toTypedArray())
    }

    companion object {
        @JvmStatic
        fun data(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(intArrayOf(1, 2, 3), intArrayOf(1, 2) to intArrayOf(3)),
                Arguments.of(intArrayOf(1, 2, 3, 7, 8), intArrayOf(3, 7) to intArrayOf(1, 2, 8)),
                Arguments.of(
                    intArrayOf(1, 2, 3, 7, 8, 125),
                    intArrayOf(1, 2, 3, 7, 8) to intArrayOf(125)
                )
            )
        }
    }
}
