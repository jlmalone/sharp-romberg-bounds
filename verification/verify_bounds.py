#!/usr/bin/env python3
"""Transcription checks for the sharp mesh-dilation bounds.

The manuscript proofs are authoritative. This program checks representative
exponentials and finite positive mixtures after the resource-constrained
preparation pass.
"""

from __future__ import annotations

import math
from collections.abc import Sequence


def integral_exponential(rate: float, length: float = 1.0) -> float:
    if rate == 0.0:
        return length
    return -math.expm1(-rate * length) / rate


def trapezoid_exponential(
    panels: int,
    rate: float,
    length: float = 1.0,
) -> float:
    if panels <= 0:
        raise ValueError("panels must be positive")
    if rate == 0.0:
        return length

    h = length / panels
    ratio = math.exp(-rate * h)
    geometric_interior = ratio * (1.0 - ratio ** (panels - 1)) / (1.0 - ratio)
    return h * (
        0.5 * (1.0 + math.exp(-rate * length)) + geometric_interior
    )


def lagrange_at_zero(nodes: Sequence[float], values: Sequence[float]) -> float:
    if len(nodes) != len(values) or not nodes:
        raise ValueError("nodes and values must have the same nonzero size")
    if len(set(nodes)) != len(nodes):
        raise ValueError("nodes must be distinct")

    total = 0.0
    for j, (node_j, value_j) in enumerate(zip(nodes, values, strict=True)):
        weight = 1.0
        for k, node_k in enumerate(nodes):
            if j != k:
                weight *= -node_k / (node_j - node_k)
        total += weight * value_j
    return total


def romberg_exponential(
    panel_counts: Sequence[int],
    rate: float,
    length: float = 1.0,
) -> float:
    nodes = [panels ** -2 for panels in panel_counts]
    values = [
        trapezoid_exponential(panels, rate, length) for panels in panel_counts
    ]
    return lagrange_at_zero(nodes, values)


def mixture_integral(
    weights: Sequence[float],
    rates: Sequence[float],
    length: float = 1.0,
) -> float:
    return sum(
        weight * integral_exponential(rate, length)
        for weight, rate in zip(weights, rates, strict=True)
    )


def mixture_romberg(
    panel_counts: Sequence[int],
    weights: Sequence[float],
    rates: Sequence[float],
    length: float = 1.0,
) -> float:
    return sum(
        weight * romberg_exponential(panel_counts, rate, length)
        for weight, rate in zip(weights, rates, strict=True)
    )


def assert_ratio_and_enclosure(
    panel_counts: Sequence[int],
    q: int,
    exact: float,
    coarse: float,
    refined: float,
) -> None:
    m = len(panel_counts)
    coarse_error = coarse - exact
    refined_error = refined - exact
    if not (coarse_error > 0.0 and refined_error > 0.0):
        raise AssertionError(
            f"expected positive errors, got {coarse_error=} and {refined_error=}"
        )

    ratio = refined_error / coarse_error
    lower_ratio = q ** (-2 * m)
    upper_ratio = q ** -1
    if not lower_ratio < ratio < upper_ratio:
        raise AssertionError(
            f"ratio {ratio} outside ({lower_ratio}, {upper_ratio})"
        )

    lower = (q * refined - coarse) / (q - 1)
    upper = (q ** (2 * m) * refined - coarse) / (q ** (2 * m) - 1)
    if not lower < exact < upper:
        raise AssertionError(
            f"integral {exact} outside enclosure ({lower}, {upper})"
        )


def check_exponential_identity() -> None:
    for panels in (1, 2, 5, 11):
        for rate in (0.2, 1.0, 4.0, 20.0):
            x = rate / (2.0 * panels)
            expected = integral_exponential(rate) * x / math.tanh(x)
            actual = trapezoid_exponential(panels, rate)
            if not math.isclose(actual, expected, rel_tol=2e-13, abs_tol=2e-13):
                raise AssertionError(
                    f"trapezoidal identity failed: {panels=}, {rate=}"
                )


def check_exponentials() -> None:
    for panel_counts in ((2,), (2, 3), (2, 5, 7), (3, 4, 7, 9)):
        for q in (2, 3):
            refined_counts = tuple(q * n for n in panel_counts)
            for rate in (0.2, 0.7, 2.0, 8.0, 30.0):
                exact = integral_exponential(rate)
                coarse = romberg_exponential(panel_counts, rate)
                refined = romberg_exponential(refined_counts, rate)
                assert_ratio_and_enclosure(
                    panel_counts,
                    q,
                    exact,
                    coarse,
                    refined,
                )


def check_mixtures() -> None:
    cases = (
        ((1.0, 0.5), (0.4, 5.0)),
        ((0.2, 1.3, 0.7), (0.1, 1.0, 25.0)),
        ((4.0, 0.1, 2.0, 0.8), (0.3, 2.0, 7.0, 40.0)),
    )
    panel_counts = (2, 5, 8)
    for weights, rates in cases:
        exact = mixture_integral(weights, rates)
        for q in (2, 3):
            coarse = mixture_romberg(panel_counts, weights, rates)
            refined = mixture_romberg(
                tuple(q * n for n in panel_counts),
                weights,
                rates,
            )
            assert_ratio_and_enclosure(
                panel_counts,
                q,
                exact,
                coarse,
                refined,
            )


def check_midpoint_endpoint() -> None:
    panels = 7
    rate = 3.0
    h = 1.0 / panels
    midpoint = h * sum(
        math.exp(-rate * (j + 0.5) * h) for j in range(panels)
    )
    identity = (
        2.0 * trapezoid_exponential(2 * panels, rate)
        - trapezoid_exponential(panels, rate)
    )
    if not math.isclose(midpoint, identity, rel_tol=2e-13, abs_tol=2e-13):
        raise AssertionError("midpoint refinement identity failed")


def main() -> None:
    check_exponential_identity()
    check_exponentials()
    check_mixtures()
    check_midpoint_endpoint()
    print("All sharp Romberg transcription checks passed.")


if __name__ == "__main__":
    main()
