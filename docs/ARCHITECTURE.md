# Architecture

- `android`: thin Android launcher and platform bridges.
- `core`: libGDX game lifecycle, rendering, simulation and domain logic.
- `assets`: JSON data and placeholders for original visual/audio assets.
- `docs`: design, control, tactical and development notes.

Keep simulation state independent from rendering. Advance the match using a fixed-step simulation clock; render interpolated state. Persist career and settings locally first. Cloud sync must be an optional adapter and must never block offline play.
