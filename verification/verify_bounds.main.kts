#!/usr/bin/env kotlin

import kotlin.math.abs
import kotlin.math.exp
import kotlin.math.expm1
import kotlin.math.max
import kotlin.math.pow
import kotlin.math.tanh

fun integralExponential(rate: Double, length: Double = 1.0): Double =
    if (rate == 0.0) length else -expm1(-rate * length) / rate

fun trapezoidExponential(
    panels: Int,
    rate: Double,
    length: Double = 1.0,
): Double {
    require(panels > 0) { "panels must be positive" }
    if (rate == 0.0) {
        return length
    }

    val h = length / panels
    val ratio = exp(-rate * h)
    val interior = ratio * (1.0 - ratio.pow(panels - 1)) / (1.0 - ratio)
    return h * (0.5 * (1.0 + exp(-rate * length)) + interior)
}

fun lagrangeAtZero(nodes: List<Double>, values: List<Double>): Double {
    require(nodes.isNotEmpty() && nodes.size == values.size)
    require(nodes.toSet().size == nodes.size) { "nodes must be distinct" }

    return nodes.indices.sumOf { j ->
        var weight = 1.0
        for (k in nodes.indices) {
            if (j != k) {
                weight *= -nodes[k] / (nodes[j] - nodes[k])
            }
        }
        weight * values[j]
    }
}

fun rombergExponential(
    panelCounts: List<Int>,
    rate: Double,
    length: Double = 1.0,
): Double {
    val nodes = panelCounts.map { panels -> panels.toDouble().pow(-2) }
    val values = panelCounts.map { panels ->
        trapezoidExponential(panels, rate, length)
    }
    return lagrangeAtZero(nodes, values)
}

fun mixtureIntegral(
    weights: List<Double>,
    rates: List<Double>,
    length: Double = 1.0,
): Double {
    require(weights.size == rates.size)
    return weights.indices.sumOf { index ->
        weights[index] * integralExponential(rates[index], length)
    }
}

fun mixtureRomberg(
    panelCounts: List<Int>,
    weights: List<Double>,
    rates: List<Double>,
    length: Double = 1.0,
): Double {
    require(weights.size == rates.size)
    return weights.indices.sumOf { index ->
        weights[index] *
            rombergExponential(panelCounts, rates[index], length)
    }
}

fun isClose(
    left: Double,
    right: Double,
    relativeTolerance: Double = 2e-12,
    absoluteTolerance: Double = 2e-12,
): Boolean =
    abs(left - right) <=
        max(absoluteTolerance, relativeTolerance * max(abs(left), abs(right)))

fun assertRatioAndEnclosure(
    panelCounts: List<Int>,
    q: Int,
    exact: Double,
    coarse: Double,
    refined: Double,
) {
    val m = panelCounts.size
    val coarseError = coarse - exact
    val refinedError = refined - exact
    check(coarseError > 0.0 && refinedError > 0.0) {
        "expected positive errors: coarse=$coarseError refined=$refinedError"
    }

    val ratio = refinedError / coarseError
    val lowerRatio = q.toDouble().pow(-2 * m)
    val upperRatio = q.toDouble().pow(-1)
    check(ratio > lowerRatio && ratio < upperRatio) {
        "ratio $ratio outside ($lowerRatio, $upperRatio)"
    }

    val q2m = q.toDouble().pow(2 * m)
    val lower = (q * refined - coarse) / (q - 1)
    val upper = (q2m * refined - coarse) / (q2m - 1.0)
    check(exact > lower && exact < upper) {
        "integral $exact outside ($lower, $upper)"
    }
}

fun checkExponentialIdentity() {
    for (panels in listOf(1, 2, 5, 11)) {
        for (rate in listOf(0.2, 1.0, 4.0, 20.0)) {
            val x = rate / (2.0 * panels)
            val expected = integralExponential(rate) * x / tanh(x)
            val actual = trapezoidExponential(panels, rate)
            check(isClose(actual, expected)) {
                "trapezoidal identity failed: panels=$panels rate=$rate"
            }
        }
    }
}

fun checkExponentials() {
    val panelSets =
        listOf(
            listOf(2),
            listOf(2, 3),
            listOf(2, 5, 7),
            listOf(3, 4, 7, 9),
        )
    for (panelCounts in panelSets) {
        for (q in listOf(2, 3)) {
            val refinedCounts = panelCounts.map { q * it }
            for (rate in listOf(0.2, 0.7, 2.0, 8.0, 30.0)) {
                val exact = integralExponential(rate)
                val coarse = rombergExponential(panelCounts, rate)
                val refined = rombergExponential(refinedCounts, rate)
                assertRatioAndEnclosure(
                    panelCounts,
                    q,
                    exact,
                    coarse,
                    refined,
                )
            }
        }
    }
}

fun checkMixtures() {
    val cases =
        listOf(
            listOf(1.0, 0.5) to listOf(0.4, 5.0),
            listOf(0.2, 1.3, 0.7) to listOf(0.1, 1.0, 25.0),
            listOf(4.0, 0.1, 2.0, 0.8) to listOf(0.3, 2.0, 7.0, 40.0),
        )
    val panelCounts = listOf(2, 5, 8)
    for ((weights, rates) in cases) {
        val exact = mixtureIntegral(weights, rates)
        for (q in listOf(2, 3)) {
            val coarse = mixtureRomberg(panelCounts, weights, rates)
            val refined =
                mixtureRomberg(
                    panelCounts.map { q * it },
                    weights,
                    rates,
                )
            assertRatioAndEnclosure(
                panelCounts,
                q,
                exact,
                coarse,
                refined,
            )
        }
    }
}

fun checkMidpointEndpoint() {
    val panels = 7
    val rate = 3.0
    val h = 1.0 / panels
    val midpoint =
        h * (0 until panels).sumOf { j ->
            exp(-rate * (j + 0.5) * h)
        }
    val identity =
        2.0 * trapezoidExponential(2 * panels, rate) -
            trapezoidExponential(panels, rate)
    check(isClose(midpoint, identity)) {
        "midpoint refinement identity failed"
    }
}

checkExponentialIdentity()
checkExponentials()
checkMixtures()
checkMidpointEndpoint()
println("All sharp Romberg transcription checks passed.")
