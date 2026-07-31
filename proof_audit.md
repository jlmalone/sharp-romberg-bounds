# Proof audit

Static review date: 2026-07-31.

Dynamic verification status: deferred by resource-constrained mode.

## Foundation

The paper uses finite positive Borel measures on \([0,\infty)\), Tonelli's
theorem, finite linearity, elementary Lagrange interpolation, the product
formula for \(\sinh x/x\), dominated convergence, and the Riemann-sum theorem.
No smoothness or moment assumption on the representing measure is used.

## Obligation map

### Exponential trapezoidal identity

For \(f_t(x)=e^{-tx}\),

\[
\frac{T_n(f_t)}{I_t}=x\coth x,\qquad x=\frac{tL}{2n}.
\]

The geometric sum retains half weights at both endpoints. The \(t=0\) case is
an exactly integrated constant.

### Hyperbolic partial fraction

\[
x\coth x-1
=2\sum_{k\ge1}\frac{x^2}{x^2+\pi^2k^2}.
\]

All terms are positive for \(x>0\), so Tonelli applies after mixing over the
Laplace measure.

### Origin interpolation

The degree-\((m-1)\) interpolant of \(h_s(y)=y/(y+s)\) at distinct positive
nodes has value

\[
\prod_{j=1}^m\frac{y_j}{y_j+s}
\]

at zero. Direct checks for \(m=1\) and \(m=2\) agree with the factorization
proof.

### Dilation compatibility

Multiplying every interpolation node by \(q^{-2}\) leaves the Lagrange weights
at zero unchanged. The extrapolate at panel counts \(qn_j\) can therefore be
written using the original nodes and the dilated error profile.

### Spectral lower ratio

For

\[
a_k=\prod_{j=1}^m\frac{y_j}{y_j+ck^2},
\]

each factor in \(a_{qk}/a_k\) is strictly greater than \(q^{-2}\). Hence
\(a_{qk}>q^{-2m}a_k\).

### Spectral upper ratio

The sequence \(a_k\) is strictly decreasing. Every block
\(a_{q(k-1)+1},\ldots,a_{qk}\) has sum strictly greater than \(q a_{qk}\).
Summing the blocks gives

\[
\sum_{k\ge1}a_{qk}<q^{-1}\sum_{k\ge1}a_k.
\]

### Mixtures

The general error ratio is a weighted average of the single-exponential
ratios. The weights are positive because arbitrary-mesh extrapolation has the
positive product representation.

### Sharp lower endpoint

As \(t\to0^+\), \(c_t\to\infty\). After multiplication by \(c_t^m\), the
spectral term is dominated by

\[
\left(\prod_j y_j\right)k^{-2m},
\]

which is summable for every \(m\ge1\). The ratio tends to \(q^{-2m}\).

### Sharp upper endpoint

As \(t\to\infty\), \(c_t\to0\). The function

\[
g(u)=\prod_j\frac{y_j}{y_j+u^2}
\]

is continuous and integrable. The ordinary and \(q\)-subsampled spectral sums
are Riemann sums whose ratio tends to \(q^{-1}\).

## Edge cases

- Constant functions give zero error in both extrapolants, so the error ratio
  is stated only for nonconstant functions.
- Repeated panel counts are excluded because interpolation would be singular.
- Panel counts need not be ordered or geometrically related.
- The refinement factor is an integer because the upper constant uses the
  density of multiples of \(q\) in the spectral lattice.
- Unbounded Laplace support is allowed.
- Equality at either endpoint is impossible for a nonconstant integrand, but
  pure exponentials approach both endpoints.

## Static conclusion

No proof defect was found. The most sensitive steps for independent review are
the scaling convention for \(R_{q\mathbf n}\), the mixture-weight argument, and
the large-\(t\) Riemann-sum limit.
