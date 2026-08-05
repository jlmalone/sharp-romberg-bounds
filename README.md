# Sharp Romberg Bounds

This repository contains a research manuscript and verification material for a
sharp error-ratio theorem in arbitrary-mesh Romberg extrapolation.

For a finite positive Laplace transform \(f\), let \(R_{\mathbf n}(f)\) be the
origin extrapolate of composite trapezoidal values at distinct panel counts
\(\mathbf n=(n_1,\ldots,n_m)\). For every integer \(q\ge 2\) and every
nonconstant \(f\) in the stated class, the main theorem is

\[
q^{-2m}
<
\frac{R_{q\mathbf n}(f)-I(f)}
     {R_{\mathbf n}(f)-I(f)}
<
q^{-1}.
\]

Both constants are sharp. Small exponentials approach the lower endpoint and
large exponentials approach the upper endpoint. Solving the ratio inequality
for the unknown integral gives the certified enclosure

\[
\frac{qR_{q\mathbf n}(f)-R_{\mathbf n}(f)}{q-1}
<
I(f)
<
\frac{q^{2m}R_{q\mathbf n}(f)-R_{\mathbf n}(f)}
     {q^{2m}-1}.
\]

The proof uses a positive product formula for origin extrapolation of
\(y/(y+s)\), the Mittag-Leffler expansion of \(x\coth x-1\), and a sharp
comparison of a decreasing spectral sum with its subsequence indexed by
multiples of \(q\).

## Repository map

- [PUBLICATION_HANDOFF.md](PUBLICATION_HANDOFF.md): exact publication state,
  unresolved gates, stop conditions, and the recommended route to submission.
- [sharp_mesh_dilation_bounds.tex](sharp_mesh_dilation_bounds.tex): manuscript.
- [proof_audit.md](proof_audit.md): proof obligations, edge cases, and static
  validation record.
- [novelty_review.md](novelty_review.md): closest known results and the bounded
  novelty claim.
- [verification](verification): independent Python and Kotlin transcription
  checks.
- [submission](submission): arXiv metadata and a referee packet.
- [RESOURCE_CONSTRAINED_FUTURE_GATE.sh](RESOURCE_CONSTRAINED_FUTURE_GATE.sh):
  clean-checkout verification and TeX build gate for a future unconstrained
  session.

## Research status

The manuscript proof has received a static adversarial pass. The numerical
verification programs and TeX build have not been executed in the
resource-constrained preparation pass. The historical Romberg literature also
needs an independent full-text review before a novelty claim is used in a
formal submission.

The [publication handoff](PUBLICATION_HANDOFF.md) is the durable session
closeout and the authoritative checklist for future work.

The proof is authoritative. The programs check transcription, endpoint
behavior, and representative finite mixtures.

## Future verification

After resource constraints are lifted, run:

    ./RESOURCE_CONSTRAINED_FUTURE_GATE.sh --verify

The gate requires a clean checkout, runs both verification programs, and
builds the manuscript in a temporary directory.

## Public review

Proof corrections and direct prior-art matches are welcome. Use the focused
GitHub issue forms so that a mathematical objection can be tied to an exact
statement, equation, or source.
