# arXiv metadata

## Proposed classification

- Primary category: math.NA (Numerical Analysis)
- Proposed cross-list: math.CA (Classical Analysis and ODEs)
- Journal reference: leave blank until accepted
- DOI: leave blank until assigned

## Title

Sharp Mesh-Dilation Bounds for Arbitrary Romberg Extrapolation of Completely
Monotone Integrands

## Abstract

Let \(R_{\mathbf n}(f)\) be the value at zero obtained by interpolating
composite trapezoidal approximations \(T_{n_j}(f)\) polynomially in
\(n_j^{-2}\), where \(n_1,\ldots,n_m\) are arbitrary distinct positive
integers. For every nonconstant finite positive Laplace transform \(f\) on a
compact interval and every integer refinement factor \(q\ge2\), we prove the
sharp inequality
\[
q^{-2m}
<
\frac{R_{q\mathbf n}(f)-I(f)}
     {R_{\mathbf n}(f)-I(f)}
<
q^{-1}.
\]
The constants are approached by exponential integrands in the limits of zero
and infinite decay rate. The ratio bound gives a computable strict enclosure
of the integral between two affine combinations of the coarse and refined
extrapolants. It also yields an exact classification of every affine blend as
a universal upper bound, a universal lower bound, or a rule with no fixed
sign. For twofold refinement, the lower endpoint is midpoint extrapolation and
the upper endpoint recovers the sharp midpoint-trapezoid threshold. The proof
uses a positive origin-interpolation formula for complete Bernstein kernels
and a sharp comparison between a decreasing spectral sum and its subsequence
indexed by multiples of \(q\).

## Comments

Proof-focused manuscript with companion Python and Kotlin transcription checks.

## Source package

Include sharp_mesh_dilation_bounds.tex and both files in verification. Exclude
repository administration, novelty notes, issue forms, and generated build
artifacts.
