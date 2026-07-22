# Implementation Plan - Add Preview for AddEditHabitScreen

This plan outlines the steps to add a `@Preview` composable for `AddEditHabitScreen` in `AddEditHabitScreen.kt`. As the target composable uses a `ViewModel`, a stateless content composable will be extracted first.

## User Review Required

> [!IMPORTANT]
> The `AddEditHabitScreen` will be refactored to extract a stateless `AddEditHabitContent` composable. This is necessary to provide a preview without a real `ViewModel`.

## Proposed Changes

### Presentation Layer

#### [MODIFY] [AddEditHabitScreen.kt](file:///C:/Users/dcahun/Documents/Habito66/app/src/main/java/com/example/habito66/presentation/habits/AddEditHabitScreen.kt)

- Extract the UI logic from `AddEditHabitScreen` into a new `@Composable` function `AddEditHabitContent`.
- `AddEditHabitContent` will take state variables (`id`, `habitName`) and callbacks (`onHabitNameChange`, `onNavigateBack`, `onSave`, `onDelete`) as parameters.
- Update `AddEditHabitScreen` to use `AddEditHabitContent`.
- Add two previews at the bottom of the file:
    - `AddHabitPreview`: Shows the screen in "Create" mode.
    - `EditHabitPreview`: Shows the screen in "Edit" mode with sample data.
- Use `Habito66Theme` for the previews.

## Verification Plan

### Manual Verification
- Render the newly created previews using the `render_compose_preview` tool to ensure they look correct.
- Check both "Create" and "Edit" states in the previews.
