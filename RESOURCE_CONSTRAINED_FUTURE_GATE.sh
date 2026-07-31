#!/bin/sh
set -eu

repo_name=sharp-romberg-bounds
repo_root=$(CDPATH= cd "$(dirname "$0")" && pwd -P)
verify=0

die() {
    printf '%s\n' "future gate: $*" >&2
    exit 1
}

usage() {
    printf '%s\n' "usage: ./RESOURCE_CONSTRAINED_FUTURE_GATE.sh --verify"
}

while [ "$#" -gt 0 ]; do
    case "$1" in
        --verify) verify=1 ;;
        --help) usage; exit 0 ;;
        *) die "unknown option: $1" ;;
    esac
    shift
done

[ "$verify" -eq 1 ] || {
    usage
    exit 0
}

[ "$(basename "$repo_root")" = "$repo_name" ] ||
    die "expected repository basename $repo_name"
git_root=$(git -C "$repo_root" rev-parse --show-toplevel 2>/dev/null) ||
    die "not a Git worktree"
[ "$git_root" = "$repo_root" ] ||
    die "script must remain at the Git worktree root"
[ -z "$(git -C "$repo_root" status --porcelain)" ] ||
    die "a clean checkout is required"

required_files="
README.md
sharp_mesh_dilation_bounds.tex
proof_audit.md
novelty_review.md
verification/verify_bounds.py
verification/verify_bounds.main.kts
"
for required_file in $required_files; do
    [ -f "$repo_root/$required_file" ] ||
        die "missing required file: $required_file"
done

command -v python3 >/dev/null 2>&1 || die "Python 3 is required"
command -v kotlin >/dev/null 2>&1 || die "Kotlin is required"
command -v latexmk >/dev/null 2>&1 || die "latexmk is required"

git -C "$repo_root" diff --check
python3 "$repo_root/verification/verify_bounds.py"
kotlin "$repo_root/verification/verify_bounds.main.kts"

build_dir=$(mktemp -d)
trap 'rm -rf "$build_dir"' EXIT HUP INT TERM
latexmk \
    -pdf \
    -interaction=nonstopmode \
    -halt-on-error \
    -outdir="$build_dir" \
    "$repo_root/sharp_mesh_dilation_bounds.tex"

[ -f "$build_dir/sharp_mesh_dilation_bounds.pdf" ] ||
    die "expected manuscript PDF was not produced"
printf '%s\n' "Verification programs passed and the manuscript built successfully."
