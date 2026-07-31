# Referee packet

## Principal claim

For arbitrary distinct panel counts and finite positive Laplace transforms, the
ratio of the refined and coarse arbitrary-mesh Romberg errors lies sharply
between \(q^{-2m}\) and \(q^{-1}\).

## Short proof route

1. Reduce by the positive Laplace representation to exponentials.
2. Express each exponential trapezoidal error using \(x\coth x-1\).
3. Extrapolate each partial-fraction kernel at zero to obtain
   \(a_k=\prod_j y_j/(y_j+ck^2)\).
4. Compare \(\sum a_{qk}\) with \(\sum a_k\).
5. Recover finite positive mixtures by a positive weighted average.
6. Prove sharpness using dominated convergence and a Riemann-sum limit.

## Requested checks

- Confirm the origin-interpolation identity.
- Confirm common node scaling leaves the interpolation weights unchanged.
- Check both endpoint limits.
- Search for an antecedent containing the same sharp ratio interval.
- Assess whether the enclosure supports a focused numerical-analysis note.

## Known overlap

Classical Romberg positivity, monotonicity, Richardson weights, and
midpoint-trapezoid inequalities are established topics. The manuscript's claim
is confined to the sharp ratio interval and its exact consequences.
