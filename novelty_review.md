# Novelty review

Review date: 2026-07-31.

## Bounded claim

The proposed contribution is the sharp arbitrary-mesh error-ratio interval

\[
q^{-2m}
<
\frac{R_{q\mathbf n}(f)-I(f)}
     {R_{\mathbf n}(f)-I(f)}
<
q^{-1}
\]

for finite positive Laplace transforms, together with sharpness inside the
integrand class, the computable two-sided enclosure, the complete affine-blend
classification, and the refinement-new-node interpretation.

The manuscript does not claim Richardson extrapolation, classical Romberg
positivity, Bernstein-Widder representation, the Mittag-Leffler expansion, or
midpoint-trapezoid averaging as new.

## Closest located results

Germund Dahlquist and Åke Björck, Numerical Methods in Scientific Computing,
Volume I, Chapter 5, describe interpolation in squared step size for general
step sequences and positivity of the Peano kernels for the classical Romberg
table.

Torsten Ström published:

- “Strict error bounds in Romberg quadrature,” BIT 7 (1967), 314–321.
- “Monotonicity in Romberg quadrature,” Mathematics of Computation 26 (1972),
  461–465.

The accessible metadata establishes close subject overlap. The complete papers
must be checked for an equivalent ratio theorem.

J. Albrecht, “Intervallschachtelungen beim Romberg-Verfahren,” ZAMM 52
(1972), 433–435, is the closest title-level match to the enclosure corollary.
The complete article was not accessible during this review. It is a critical
pre-submission check, not evidence for or against the bounded novelty claim.

H. Brass and J.-W. Fischer, “Error bounds for Romberg quadrature,” Numerische
Mathematik 82 (1999), 389–408, estimates constants in classical Romberg error
bounds. The accessible abstract describes derivative-norm and variation
bounds, not a ratio between a mesh and its common dilation.

K.-J. Förster, “Fehlerschranken bei der Romberg-Quadratur,” ZAMM 62 (1982),
133–135, is another close archival error-bound paper whose complete text must
be inspected.

J.-W. Fischer, “Romberg quadrature using the Bulirsch sequence,” Numerische
Mathematik 90 (2002), 509–519, proves definiteness for a nonclassical sequence.

Zheng Liu, “More on the averaged midpoint-trapezoid type rules,” Applied
Mathematics and Computation 218 (2011), 1389–1398, studies error inequalities
for averaged rules.

H. Brass and K. Petras, *Quadrature Theory* (AMS, 2011), treats Romberg
quadrature, definiteness, monotonicity, and error bounds. Its relevant chapters
must be checked line by line against the ratio and blend statements.

## Search outcome

Accessible searches covered exact threshold formulas, arbitrary-step Romberg
positivity, Romberg interval enclosures, midpoint-trapezoid Richardson
extrapolation, complete Bernstein error representations, and mesh-dilation
terminology. No inspected source was found that states both sharp constants
\(q^{-2m}\) and \(q^{-1}\), proves them for arbitrary positive interpolation
meshes on the finite-positive-Laplace-transform cone, and derives the full
affine-blend trichotomy. Several close archival sources were located but not
fully inspected, so this negative search result is provisional.

This is an apparently novel result, not certified novelty.

## Remaining risks

1. Albrecht's interval-enclosure paper may contain an equivalent consequence.
2. Ström's full papers may encode the ratio bounds in older notation.
3. Förster or Brass--Fischer may contain a stronger comparison theorem.
4. Definiteness monographs may contain a stronger comparison theorem.
5. The product-kernel representation may be known in the Stieltjes-function
   interpolation literature.
6. A referee may view the block estimate as an elementary corollary of
   established monotonicity theory.

## Required independent search

Before submission:

1. inspect the complete Albrecht 1972, Ström 1967 and 1972, Förster 1982, and
   Brass--Fischer 1999 papers;
2. search MathSciNet and zbMATH using “error ratio,” “successive Romberg,”
   “interval enclosure,” “definite quadrature,” and “arbitrary step sequence,”
   including German equivalents;
3. inspect the Romberg and definiteness chapters of Brass--Petras and another
   modern quadrature monograph;
4. ask a numerical-integration specialist whether the interval
   \((q^{-2m},q^{-1})\) is known.
