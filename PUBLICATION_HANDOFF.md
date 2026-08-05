# Publication handoff

Status date: 2026-08-05.

The canonical public repository is
<https://github.com/jlmalone/sharp-romberg-bounds>. The package is ready for
public mathematical review. Journal or preprint submission still depends on
the verification and novelty gates below.

## Mathematical contribution

For a nonconstant finite positive Laplace transform and arbitrary distinct
panel counts \(\mathbf n=(n_1,\ldots,n_m)\), the manuscript proves

\[
q^{-2m}
<
\frac{R_{q\mathbf n}(f)-I(f)}
     {R_{\mathbf n}(f)-I(f)}
<
q^{-1}
\]

for every integer \(q\ge2\). Both constants are sharp within the exponential
subfamily. The main consequences are a strict computable enclosure for the
integral, a complete classification of affine coarse-refined blends, and an
equal-weight interpretation on the new refinement nodes.

The proof is in [sharp_mesh_dilation_bounds.tex](sharp_mesh_dilation_bounds.tex).
The sensitive proof steps and edge cases are indexed in
[proof_audit.md](proof_audit.md).

## Completed work

- The theorem, sharpness limits, enclosure, blend classification, and
  refinement-new-node rule have complete written proofs.
- A static adversarial audit found no proof defect.
- The accessible literature search is recorded with a bounded novelty claim
  in [novelty_review.md](novelty_review.md).
- Independent Python and Kotlin transcriptions cover the exponential identity,
  representative arbitrary meshes, finite positive mixtures, the ratio
  interval, the enclosure, and the midpoint identity.
- Submission metadata and a short referee packet are in [submission](submission).
- Focused GitHub issue forms accept proof objections and exact prior-art
  matches.

Dynamic verification and TeX compilation were deferred during the
resource-constrained preparation. No journal, preprint server, DOI archive, or
release has accepted this work.

## Required gates before submission

### 1. Execute the clean-checkout gate

In an unconstrained session, start from a clean checkout and run:

    ./RESOURCE_CONSTRAINED_FUTURE_GATE.sh --verify

The gate checks the repository state, runs both verification programs, and
builds the manuscript with `latexmk` in a temporary directory. A successful
exit is required before any claim that the code runs or the manuscript builds.
The gate deletes its temporary build, so create a retained, gitignored review
copy after it passes:

    mkdir -p tmp/review-pdf
    latexmk -pdf -interaction=nonstopmode -halt-on-error \
        -outdir=tmp/review-pdf sharp_mesh_dilation_bounds.tex
    pdftoppm -png -r 150 \
        tmp/review-pdf/sharp_mesh_dilation_bounds.pdf \
        tmp/review-pdf/page

Inspect every rendered page for clipped equations, broken references, bad line
breaks, and bibliography overflow.

If the gate fails, repair the underlying defect, rerun the whole gate, and
record the verified command and environment in the repository.

### 2. Obtain independent proof review

The reviewer should check these points directly rather than relying on the
verification programs:

1. the exponential trapezoidal identity and partial-fraction expansion;
2. interchange of finite interpolation with the positive integral and series;
3. the origin-interpolation product formula;
4. common mesh scaling and the identification of the refined spectral
   subsequence;
5. strictness for positive Laplace mixtures;
6. dominated convergence at the lower sharp endpoint;
7. the Riemann-sum argument at the upper sharp endpoint; and
8. the necessity directions in both affine-blend classifications.

Any objection should identify an exact theorem, equation, or limiting step.
Resolve substantive proof issues before changing exposition or adding
applications.

### 3. Complete the archival novelty review

The highest-risk sources are Bulirsch--Stoer (1966 and 1967), Stroud (1965),
Meir--Sharma (1965), Lynch (1967), Ström (1967 and 1972), Albrecht (1972),
Förster (1982), Brass--Fischer (1999), Fischer (2002), and the Romberg chapters
of Brass--Petras. Full bibliographic details are in
[novelty_review.md](novelty_review.md).

For each source, record the exact theorem and page and answer:

- Does it cover arbitrary positive interpolation meshes or only a classical
  Romberg sequence?
- Does it prove a ratio between a mesh and its common dilation, or a different
  derivative-norm or asymptotic bound?
- Are both constants \(q^{-2m}\) and \(q^{-1}\) present and sharp?
- Is the function class the same finite-positive-Laplace-transform cone?
- Does it imply the full affine-blend classification or the new-node rule?

MathSciNet and zbMATH searches should include English and German variants of
“Romberg error ratio,” “successive Romberg bounds,” “interval enclosure,”
“definite quadrature,” and “arbitrary step sequence.” A numerical-integration
specialist should review the final comparison.

### 4. Apply the novelty decision

- If a source proves the same theorem under the same or weaker hypotheses,
  withdraw the current novelty claim. Reframe only around a rigorously distinct
  contribution.
- If prior work covers the classical dyadic table but not arbitrary meshes,
  sharp two-endpoint ratios, or the blend classification, state that boundary
  theorem by theorem.
- If the full-text search finds no antecedent and the proof review passes,
  describe the result as apparently new and let the editor and referees assess
  priority.

## Submission preparation

After all three technical gates pass:

1. freeze a versioned source archive and verified PDF;
2. complete the venue-required identity and affiliation metadata;
3. choose explicit licensing for the manuscript and verification code;
4. add citation metadata matching the frozen version;
5. update [submission/arxiv_metadata.md](submission/arxiv_metadata.md) from the
   final manuscript;
6. select a specialist numerical-analysis short-note venue whose scope accepts
   theorem-first quadrature work; and
7. submit the same frozen theorem statement, abstract, and bibliography to the
   preprint and journal workflows.

The concise manuscript is an asset. Add numerical illustrations only when they
clarify the sharp endpoint mechanisms or the usefulness of the certified
enclosure. Do not dilute the paper with generic Romberg exposition or
applications unsupported by a worked example.

## Completion and stop conditions

The package is submission-ready only when the clean-checkout gate passes, the
PDF receives visual inspection, an independent reviewer finds no unresolved
proof defect, and the archival review preserves a precise contribution.

Stop submission if an equivalent theorem is located, a sharpness limit fails,
the mixture argument has an unresolved measure-theoretic gap, or the generated
PDF does not match the reviewed source. Record the exact failure and resume
from that gate after a concrete repair.

## Next action

Run the clean-checkout gate after resource constraints are lifted. In parallel
with independent proof review, obtain the full texts listed above and turn the
provisional novelty review into a page-level comparison table. Those two tasks
determine whether the manuscript proceeds unchanged, narrows its claim, or
stops.
