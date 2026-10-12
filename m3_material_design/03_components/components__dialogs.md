---
title: "Dialogs"
slug: "components/dialogs"
url: "https://m3.material.io/components/dialogs"
---

# Dialogs

- 

Use dialogs to make sure users act on information
- 

Two variants: basic and full-screen
- 

Should be dedicated to completing a single task
- 

Can also display information relevant to the task
- 

Commonly used to confirm high-risk actions like deleting progress


![Basic and full-screen dialog.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fml9yk7iw-01.png?alt=media&token=50a39047-4d06-477e-a9d8-84dd6a5afae9)
*1. Basic dialog
2. Full-screen dialog*

## Availability & resources

## Differences from M2

- Color: New color mappings and compatibility with dynamic color
- Layout: Greater padding to account for the increased corner-radius and title size
- Position: Option for custom basic dialog positioning
- Shape: Increased corner-radius
- Typography: Larger and darker headline


![Basic dialog with rounded corner, larger headline.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fml9ykeuk-02.png?alt=media&token=02244cb3-7889-4321-9a65-aa3040bbadc1)
*New updates to color, layout, position, shape, and typography*

## Tokens & specs

Select a component variant below to see its elements, attributes, tokens, and their values.

## Basic dialogs


![Anatomy diagram numbering dialog elements.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmk29t71i-01-3P.png?alt=media&token=107d201b-e3d7-4dcc-aa58-71f053c9364c)
*1. Container
2. Icon (optional) 
3. Headline (optional)
4. Supporting text 
5. Divider (optional) 
6. Button label text
7. Scrim*

### Basic dialog color

Color values are implemented through design tokens. For design, this means working with color values that correspond with tokens. For implementation, a color value will be a token that references a value. [Learn more about design tokens](/m3/pages/design-tokens/overview)


![Color mapping diagram labeling 6 color roles across the dialog and scrim.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fml9yledn-02-3P.png?alt=media&token=d1f0e8c8-54da-41e3-81a8-ad46dcb5d3b7)
*Basic dialog color roles used for light and dark themes:

1. Surface container high 
2. Secondary 
3. On surface 
4. On surface variant
5. Primary 
6. Scrim*

### Basic dialog measurements


![Annotated diagram showing padding values.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fml9ynrkv-03-3P.png?alt=media&token=8e554b7e-0509-4f02-8563-7453e0bb40e9)
*Basic dialog padding and size measurements*

| Attribute | Value |
| --- | --- |
| Container shape | 28dp corner radius |
| Container height | Dynamic |
| Container width | Min 280dp; Max 560dp |
| Divider height | 1dp |
| Icon size | 24dp |
| Minimum width | 280dp |
| Maximum width | 560dp |
| Alignment with icon | Center-aligned |
| Alignment without icon | Start-aligned |
| Top/Left/right/bottom padding | 24dp |
| Padding between buttons | 8dp |
| Padding between title and body | 16dp |
| Padding between icon and title | 16dp |
| Padding between body and actions | 24dp |

## Full-screen dialogs


![Diagram numbering 6 full-screen dialog elements.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fm9h7yor0-04-3P.png?alt=media&token=26b84960-cb4d-4ddc-b996-2fdc771c1432)
*1. Container 
2. Header 
3. Icon (close affordance) 
4. Headline (optional) 
5. Text button 
6. Divider (optional)*

### Full-screen dialog color

Color values are implemented through design tokens. For design, this means working with color values that correspond with tokens. For implementation, a color value will be a token that references a value.


![Color mapping diagram shows 5 callout markers across the dialog.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fm9h7z23b-05-3P.png?alt=media&token=0db0c4c5-bdf2-4697-964e-4d38cf508d2b)
*Full-screen dialog color roles used for light and dark themes:

1. Surface container high 
2. On surface
3. On surface
4. Primary
5. On surface variant*

### Full-screen dialog measurements


![Diagram noting layout measurements for padding values, title, height, and action regions.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmafq2x4i-06-3P.png?alt=media&token=4cc6deb6-b269-43d4-b21a-7234ae0d2d49)
*Full-screen dialog padding and size measurements*

| Attribute | Value |
| --- | --- |
| Container shape | 0dp corner radius |
| Container height | Dynamic |
| Container width | Container width; Max 560dp |
| Header height | 56dp |
| Header width | Container width |
| Headline text alignment | Start-aligned |
| Divider height | 1dp |
| Icon (close affordance) size | 24dp |
| Bottom action bar height | 56dp |
| Bottom action bar width | Container width |
| Top/left/right padding | 24dp |
| Padding between elements | 8dp |


![Basic dialog in isolation](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm8sf9qay-01.png?alt=media&token=5e8c7d9f-1451-4104-b5a4-c64b2e98296f)
*A basic dialog*

## Usage

A dialog is a modal window that appears in front of app content to provide critical information or ask for a decision. Dialogs disable all app functionality when they appear, and remain on screen until confirmed, dismissed, or a required action has been taken.

Dialogs are purposefully interruptive, so they should be used sparingly. A less disruptive alternative is to use a dropdown menu, which provides options without interrupting a user’s experience.


![Diagram of basic and full-screen dialogs.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm8sfc7pz-02.png?alt=media&token=48e473e3-7381-4089-8e91-11de31b32586)
*There are two variants of dialogs:

1. 

Basic dialog
2. 

Full-screen dialog*


![Dialog in front of app content.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm8sfcqhr-03_do.png?alt=media&token=3655fc78-54b9-44f6-a239-d93670bb087e)
*Use dialogs for prompts that block an app’s normal operation, and for critical information that requires a specific user task, decision, or acknowledgement*


![Low-priority dialog in front of app content.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm8sfdc9w-03_dont.png?alt=media&token=6a548db0-3131-46af-9f44-d30b74de4904)
*Don’t use dialogs for low- or medium-priority information. Instead use a snackbar, which can be dismissed or disappear automatically.*

### Similar components

Snackbars are also designed to show important messages.

Choose the right component based on the importance of the message. This component messaging strategy helps avoid overusing dialogs.


![Snackbar on a phone saying that new photos were synced to the device. No buttons exist.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmk29xmfo-05-3P.png?alt=media&token=89e12852-9fec-44ab-82ca-d281460b0ba7)
*Snackbars can disappear automatically*

************

| Component | Importance | Action needed |
| --- | --- | --- |
| Snackbar | Low importance | Optional: Snackbars may not have a button, and can disappear automatically |
| Dialog | High importance | Required: Dialogs block the main content until an action is confirmed |

## Anatomy

### Basic dialog


![Diagram of 7 elements of basic dialog.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm8sfhos4-07.png?alt=media&token=c26b3dc0-c5bd-4fe6-8081-13019a21814c)
*1. Container
2. Icon (optional)
3. Headline (optional)
4. Supporting text
5. Divider (optional)
6. Buttons label text
7. Scrim*

### Full-screen dialog


![6 elements of full-screen dialog.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm8sfick9-08.png?alt=media&token=7287dba3-0638-4a9f-afde-dcc94ce608e2)
*1. Container
2. Header region
3. Icon (close affordance)
4. Headline (optional)
5. Button label text
6. Divider (optional)*

### Container and scrim

Dialog containers appear above other screen elements and hold the dialog’s headline, text, buttons, and list items.

To focus attention on the dialog, surfaces behind the container are scrimmed with a temporary overlay to make them less prominent.


![Basic dialog shown above a scrim overlay that reduces the prominence of the background elements.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm8sfkf69-09.png?alt=media&token=76b7d3c2-c1db-4dc4-aabb-8df0851f4dd7)
*Basic dialogs appear over a background scrim*

### Headline (optional)

A dialog’s purpose should be communicated by its headline and buttons or actionable items.

Headlines should:

- Contain a brief, clear statement or question
- Avoid apologies (“Sorry for the interruption”), alarm (“Warning!”), or ambiguity (“Are you sure?”)


![Dialog title asking “Use location service?”](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm8sfl8j1-10_do.png?alt=media&token=6685809e-faa9-4002-a1ca-7b6355cc0c4a)
*This dialog title poses a specific question, concisely explains what’s involved in the request, and provides clear actions*


![Dialog title asking “Are you sure?”](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm8sflmm0-11_don't.png?alt=media&token=2b32fc1c-10ea-4041-8719-5a0bbd515d5e)
*Don’t use dialog titles that pose an ambiguous question*

Headlines should always be succinct. They can wrap to a second line if necessary, and be truncated.

In full-screen dialogs, long headlines or headlines of variable lengths (such as translations), can be placed in the content area instead of the app bar.


![Example full-screen dialog with truncated long headline.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm8sfm2yt-12_Caution.png?alt=media&token=bb20aab8-9a35-4eaf-9f8c-f2fc29f20a6c)
*Avoid placing long headlines in a full-screen dialog’s app bar (1), as the truncated text may lead to misunderstanding*


![Example full-screen dialog with short headline, and longer text in content area.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm8sfmezr-13_do.png?alt=media&token=5244a6e2-9faa-44ec-998d-12d486666021)
*Find ways to shorten app bar text, and place longer headlines into the content area (1) of a full-screen dialog*

### Buttons 

Dialog actions are most often represented as buttons and allow users to confirm, dismiss, or acknowledge something.

Buttons are aligned to the trailing edge of the dialog for easier interaction. The confirmation button is always closest to the edge. 

Button alignment responds automatically for right-to-left languages, where the confirmation button is aligned to the left edge.


![Dialog with the confirmation button disabled because a required radio selection is missing.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm8sfmqqd-14_do.png?alt=media&token=12972233-4c46-4c82-9b80-86136ae125ca)
*Disable confirming actions (1) until a choice is made. Dismissive actions are never disabled.*


![Dialog with the dismissing action "Cancel" on the right of the 2 buttons.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm8sfn276-15_don't.png?alt=media&token=51d3809f-8761-4ad0-91d1-d3b4b9bc21dc)
*Don’t place dismissive actions (1) to the right of confirming actions. Instead, place them to the left of confirming actions.*


![Dialog with a single-action button: “OK”.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm8sfq9tm-16_do.png?alt=media&token=1ac568e8-5f77-443d-9a0f-ee24e2d5ae71)
*A single action may be provided only if it’s an acknowledgement*


![Dialog with 2 button choices: “Cancel”, “Got it”.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm8sfqm2i-17_don't.png?alt=media&token=834d6d37-9c33-4d38-a72a-ba3fc1c84783)
*Avoid presenting people with unclear choices. **Cancel** doesn't make sense here because no clear action is proposed.*

Dialogs should contain a maximum of two actions.

- If a single action is provided, it must be an acknowledgement action
- If two actions are provided, one must be a confirming action, and the other a dismissing action


![Dialog with 2 buttons side-by-side: “Disagree”, “Agree”.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm8sfqzp1-18_do.png?alt=media&token=0c0f6350-ea79-41f6-a82b-8adcb6ef4f5d)
*Display two text buttons next to one another*


![Dialog with 2 stacked buttons: “Turn on speed boost”, “No thanks”.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm8sfre4i-19_caution.png?alt=media&token=e19e944e-1e50-437b-81e7-643e4dd9dd6b)
*Stacked buttons accommodate longer button text, but take up more room. Confirming actions appear above dismissive actions.*

Providing a third action, such as **Learn more**, is not recommended as it navigates the user away from the dialog, leaving the dialog task unfinished.

Rather than adding a third action, an inline expansion can display more information. If more extensive information is needed, provide it prior to entering the dialog.


![Dialog with 3 text buttons: Learn more, Disagree, Agree.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm8sfrskh-20.png?alt=media&token=ce701d0c-c122-4fda-9244-be331795f9a5)
*The **Learn more** action (1) navigates away from this dialog, potentially leaving it in an indeterminate state*

## Basic dialog

Basic dialogs interrupt users with urgent information, details, or actions. Common use cases for basic dialogs include alerts, quick selection, and confirmation.


![Example of basic dialog action request.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm8sfumns-21.png?alt=media&token=f60188e3-24f3-4ede-a5c2-ae41ebbda58f)
*Basic dialogs require a person to take action before it will close*


![Example of basic dialog confirmation.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm8sfuxlx-22.png?alt=media&token=356b591f-4aa3-4889-95f5-2c579e0c699f)
*Basic dialogs can give people the ability to provide confirmation of a choice before committing to it*

Basic dialogs most often appear as alerts or lists, but can have a variety of layouts and component combinations, including lists, date pickers, and time pickers.


![Date picker dialog.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm8sfzk1g-23.png?alt=media&token=5d0f7fb7-2646-4c2d-9450-71e463c72f7f)
*Date picker dialogs allow people to tap a date, then confirm it by tapping **OK***


![Time picker dialog.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm8sfxxnd-24.png?alt=media&token=3b205d81-1479-4bf5-aa8c-c803aa41e7de)
*Time picker dialogs allow people to move the clock hand and then confirm by tapping **OK***

## Full-screen dialog

Full-screen dialogs fill the entire screen, containing actions that require a series of tasks to complete. One example is creating a calendar entry with the event title, date, location, and time.

Because they take up the entire screen, full-screen dialogs are the only dialogs over which other dialogs can appear.

Use a [container transform](/m3/pages/motion-transitions/transition-patterns#b67cba74-6240-4663-a423-d537b6d21187) pattern to transition a FAB into a full-screen dialog.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmbh08c2v-GM3_Dialogs_Guidelines_25_IA_v02.mp4?alt=media&token=4d9bc267-de3b-41ba-a59a-b96aa5200dc8)

When a full-screen dialog is closed without being saved, a basic dialog appears in front of it to confirm selections should be discarded without saving changes.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmbh098mh-GM3_Dialogs_Guidelines_26_IA_v01.mp4?alt=media&token=77730ed0-06cc-49a3-a5cd-1c2c8cf1606a)

Full-screen dialogs may be used for content or tasks that meet any of these criteria:

- 

Dialogs that include components which require keyboard input, such as form fields
- 

When changes aren’t saved instantly
- 

When components within the dialog open additional dialogs

Full-screen dialogs are for compact breakpoints only, like mobile devices. For medium and expanded breakpoints, use a basic dialog.

### Saving selections

To save a selection in a full-screen dialog, use **Save**.  The close icon or dismissive action, such as **Cancel** or **Back**, should close the dialog.

### Confirmation

The confirmation action should be clear about what happens next, like **Send** or **Create**. Avoid using vague terms like **Done**, **OK**, or **Close**. Only trigger an additional basic dialog if the action fails. Don’t disable the confirmation button.


![Full-screen dialog with create button as confirmation action.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm8sg5wkr-27_do.png?alt=media&token=8aa1b2c7-d3e9-4c8d-a171-dd8f5920102a)
*A **Create** button is clear that the event will be created*


![Full-screen dialog with an additional basic dialog asking if you want to create this event.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm8sg67kv-28_don't.png?alt=media&token=a6a5c838-d758-4465-b05e-f29ac5eb0d3f)
*Don’t trigger a basic dialog when the confirming action is selected*

### Dismissing

When someone dismisses a full-screen dialog, a basic dialog should appear to confirm that they want to discard the unsaved changes.


![A basic dialog with options to either keep editing or discard unsaved changes.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm8sg91cq-29_do.png?alt=media&token=1829089e-6440-4fcf-8d7b-5b4dd688ad92)
*Use a basic dialog to confirm that the user wants to discard unsaved changes*


![A full-screen dialog with a Close button as the confirming action.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm8sg9bz5-30_don't.png?alt=media&token=0d99e0df-e3f2-4c02-9a9c-598c311f8e7c)
*Don’t use the confirming action to dismiss the full-screen dialog*

### Error messages

Errors about the dialog fields should always appear inline where they occur. Some components like text fields have built-in error messaging, while others like checkboxes and radio buttons need error messages to be added next to the fields.

General errors such as network issues preventing saving or submitting should appear in a basic dialog when the confirming action fails.

Error messages should clearly but briefly explain the source of the error and how to fix it. Show all errors on the page at once so people can fix everything before trying again.


![A full-screen dialog with inline error messages for text fields.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm8sg9nzm-31_do.png?alt=media&token=80b7cdd4-6892-4661-a674-6e85c62b9122)
*Error messages related to the fields should be displayed inline*


![A basic dialog mentioning that entries were not saved due to a connection issue.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm8sg9xoh-32_caution.png?alt=media&token=5d180627-2303-4b00-92e5-39d90741c8fd)
*Errors unrelated to the fields can be displayed in a basic dialog*

### Dialog windows

Launching a full-screen dialog temporarily resets the app’s perceived elevation, allowing simple menus or dialogs to appear above the full-screen dialog. They cover the screen and don’t appear as a floating modal window.

### Navigation

Because full-screen dialogs can only be completed, dismissed, or closed, the close “X” icon button should be the only navigation option in the app bar.

## Adaptive design

Dialogs can swap variants as the breakpoint changes. For example, a full-screen dialog can change into a basic dialog at larger breakpoints.


![Example of full-screen dialog on left, simple dialog on right](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm8sgbmj3-33.png?alt=media&token=17fd8879-6435-409a-a3a9-8bd390799892)
*1. Full-screen dialog on mobile
2. Dialog on a tablet*

### Medium breakpoint

Basic dialogs appear in a center position by default.

Their position can be overridden to provide a more ergonomic experience.


![Basic dialog on tablet photos app.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm8sgc676-34.png?alt=media&token=9bc72c88-69cf-49e2-a631-070890d60754)
*Dialog custom positioned on the right side of the screen*

### Expanded breakpoint

Dialogs on expanded breakpoints, like desktop, are modal windows above a scrim. This puts the dialog at the forefront of a person's view, calling attention to the action prompted in the dialog.


![Example of desktop dialog.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm8sgj2r1-35.png?alt=media&token=a9f16af1-69fe-43cc-9806-883818a87845)
*Desktop dialogs call attention to the required action*

Basic dialogs can be custom-positioned anywhere on larger screens, respecting margins to prevent edge collision.


![Basic dialog position diagram.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm8sgjir4-36.png?alt=media&token=e2dd2b5a-1e2e-4051-a0da-4ec15079f2ce)
*Custom placement area for basic dialogs that respects a 56dp margin from the edges of the screen*

## Behavior

### Appearing

Dialogs appear without warning, requiring users to stop their current task. They should be used sparingly, as not every choice or setting warrants interruption.

Dialogs use an [enter and exit](/m3/pages/motion-transitions/transition-patterns#e1c2a650-d7a4-4a6d-9025-e6b7845291ed) transition pattern to appear on screen.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmbh0yk3u-GM3_Dialogs_Guidelines_37_IA_v01.mp4?alt=media&token=2b4cff4a-041a-4ade-9ef3-5ba676b77f4d)

### Position

Dialogs retain focus until dismissed or an action has been taken, such as choosing a setting. They shouldn’t be obscured by other elements or appear partially on screen, with the exception of full-screen dialogs.


![A basic dialog covering a full-screen dialog.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm8sgqe15-38.png?alt=media&token=9a9c7716-0f17-4ee5-a3c9-a6fb5114547a)
*Dialogs shouldn’t be obscured by other elements except for full-screen dialogs*

### Scrolling

Most dialog content should avoid scrolling. Even when scrolling is required, the dialog title is pinned at the top, with buttons pinned at the bottom. This ensures selected content remains visible alongside the title and buttons, even upon scroll.

Dialogs don’t scroll with elements outside of the dialog, such as the background.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmbh0z34p-GM3_Dialogs_Guidelines_39_IA_v01.mp4?alt=media&token=5c6d756b-f497-46fa-9ccb-973051ae5d8b)

### Dismissing

To close a dialog used as an alert, one of its actions must be selected.

Other dialogs may be dismissed by:

- Tapping a **Cancel** button, if one is shown
- Selecting an actionable list item
- Pressing the keyboard **Esc** key
- Tapping the scrim (Android, iOS)
- Tapping the Android system **Back** button
- Using another standard cancel or escape action, such as iOS VoiceOver escape gesture

If the user’s ability to dismiss a dialog is disabled, the user must choose a dialog action to proceed.


![Example of dialog alert](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fktug7t17-3_Mid.png?alt=media&token=95c45efd-2c43-4b6a-90c7-fb429faa565f)
*Dismissing a dialog alert involves selecting an action*

When a dialog box is dismissed, the following transitions occur:

- The dialog box fades out
- The background scrim fades out


![Example of dialog box fading out](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fktug7t17-3_Mid.png?alt=media&token=95c45efd-2c43-4b6a-90c7-fb429faa565f)
*When dismissed, a dialog box fades out along with the background scrim*

starNote:This is a rapidly changing space. Guidelines are primarily intended for designers at this time. Find what’s implemented in code in the [design kit](https://www.figma.com/community/file/1035203688168086460).

Extended reality (XR) introduces spatial capabilities, such as using depth to make dialogs stand out from the background. Currently, spatial dialogs are only available in full space. For home space, follow Material’s general [dialog guidance](/m3/pages/dialogs/guidelines#b33988d3-88e6-432c-acb1-4461a84171c9).

## Color & elevation

XR uses [color roles](/m3/pages/color-roles/tab-1#89f972b1-e372-494c-aabc-69aea34ed591) to communicate the elevation of UI elements. Dialogs can use two color options: **surface container high** or **surface container highest**.

starNote:Color and elevation for spatial dialogs can be customized by makers and are not available in Jetpack Compose yet.


![2 spatially elevated dialogs with surface-container-high and surface-container-highest color roles.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmadfali7-01-3P.png?alt=media&token=f73d6ca4-0e57-4a9b-8625-6103063af093)
*1. Surface container high
2. Surface container highest*

For effective visual hierarchy, a dialog should be the most prominent element. 

Add a scrim behind a dialog to improve its visibility. Scrims prevent other content from being selected until the dialog action is complete.


![Dialog with surface-container-highest color and a scrim.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmalezhon-02-do.png?alt=media&token=b0ed1d99-a991-4fdd-be40-7ca7676dea33)
*Make sure a spatial dialog’s color is higher than all other UI elements, and use a scrim*

The dialog should have the highest elevation in the product.

For example, if a dialog is **surface container high**, don’t use **surface container highest** for any other elements.


![Dialog with surface-container-high color and no scrim. An orbiter is at a higher elevation than the dialog.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmalezlwb-03-dont.png?alt=media&token=0a9eb24b-d526-4918-8d26-dbb4a035490c)
*If a dialog’s color is **surface container high**, don’t use **surface container highest** for any other element*

## Usage

[Basic dialogs](/m3/pages/dialogs/guidelines#97ac3858-3932-4084-ae8e-73e42b7cb752) are recommended when designing for XR’s expanded window sizes. This keeps the required action in the person’s [field of view](https://developer.android.com/design/ui/xr/guides/spatial-ui#where-place). Limit use of [full-screen dialogs](/m3/pages/dialogs/guidelines#007536b9-76b1-474a-a152-2f340caaff6f) to compact window sizes, like mobile devices.


![Basic dialog in XR.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmadfk9dk-04.png?alt=media&token=a24cc9e9-0dff-4244-8d36-688fcfda5a48)
*A basic dialog elevated above an app in home space*


![Full-screen dialog in XR.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmadfn41n-05.png?alt=media&token=0217ac8a-8637-43ac-88f6-fd71dcbb335c)
*Avoid using full-screen dialogs in XR. Required actions could appear beyond a person’s field of view.*

## Spatial dialogs

In full space, dialogs can be elevated spatially via [overrides](https://developer.android.com/develop/xr/jetpack-xr-sdk/material-design#use-enablexrcomponentoverrides). This helps dialogs stand out from their background in XR.


![Side view of basic dialog showcasing spatial elevation.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmadfqce4-06.png?alt=media&token=2cf6eaca-492c-49c4-9e74-2ba00d818f66)
*Side view of a basic dialog with spatial elevation in full space*

## Behavior

### Effect

The spatial dialog should scale uniformly. It also fades in when appearing, and fades out when disappearing. 

The dialog's scrim only fades in and out.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmadfydkq-XR_Dialog_Behavior_01_IA_v02%20.mp4?alt=media&token=653f8f49-cd66-4843-a345-4f36e59c613c)

### Movement

When activated, the spatial dialog rises from the app to the highest resting level on the Z-axis. 

When the action is complete, it returns to a normal resting level.

The dialog's scrim stays at the app content level at all times. 

To prevent motion sickness, use [standard easing](/m3/pages/motion-easing-and-duration/tokens-specs#601d5552-a6e6-4d74-9886-ff8f24b9ec35) and [long duration](/m3/pages/motion-easing-and-duration/tokens-specs#48bf653e-46f9-48f5-87e0-eaf8ea3fe716) motion tokens.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmadg0a4h-XR_Dialog_Behavior_02_IA_v02%20.mp4?alt=media&token=b715431b-f741-47a2-977f-7fac8e1b32cf)

## Placement

Consider factors like field of view, viewing distance, and possible interactions when deciding where to place dialogs in XR.

### Elevation: highest resting level

Display spatial dialogs at the highest resting level. When setting the depth value of the highest resting level, make sure the elevated dialog is at a comfortable viewing distance from the person. [More on spatial elevation](https://developer.android.com/design/ui/xr/guides/spatial-ui#spatial-elevation)


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmadg3ndl-GM3_XR_Dialogs_Motion_Placement_01_IA_v03.mp4?alt=media&token=41a20938-11ad-455b-a67f-340b75bd969b)

### Center spatial dialogs in field of view

Spatial dialogs should be centered in a person’s [field of view](https://developer.android.com/design/ui/xr/guides/spatial-ui#where-place). If the dialog **can't** track head movements, position it in the center of the app’s content. 

If the dialog **can** track head movements, configure it with a lazy follow behavior. This keeps the dialog anchored to the center of a person’s field of view until an action is taken.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmadg6ja9-GM3_XR_Dialogs_Motion_Placement_02_IA_v05.mp4?alt=media&token=ca28bb9a-0d74-4508-b327-27dc0be6672b)

## Accessibility considerations

[XR accessibility](https://developer.android.com/design/ui/xr/guides/get-started#make-app) guidelines are still evolving. Spatial dialogs should follow applicable Material [dialog accessibility standards](/m3/pages/dialogs/accessibility).

## Use cases

People should be able to use assistive technology to:

- Open and close a dialog
- Provide and submit other inputs if the dialog is interactive, such as a text field or selectable list
- Scroll the dialog to access all of its contents if that content extends beyond the container of the dialog

## Interaction & style

### Use sparingly

Dialogs are purposefully interruptive. This means they appear in front of app content and disrupt the flow of content for people who may, for example, be using a screen reader to navigate the page.

As such, dialogs should be used sparingly and only to provide critical information. Less critical information should be presented in a non-blocking way within the flow of app content.


![An inline tooltip doesn’t block a photo app’s content on a mobile screen.
A modal dialog blocks the content of a photo app on a mobile screen.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm8sgve0z-01_do.png?alt=media&token=15a731c6-5d4e-4b65-afb3-23b6275d0935)
*Present non-critical information using other UI within the flow of app content*


![A modal dialog blocks the content of a photo app on a mobile screen.
A modal dialog blocks the content of a photo app on a mobile screen.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm8sgvt7t-02_don't.png?alt=media&token=f88b6702-ef51-4b3b-97fc-c338539dc884)
*Avoid putting non-critical information in a dialog*

### 200% text size

Avoid excessive text wrapping or truncation by choosing concise strings. 

On Android, headlines should be kept concise enough to fit within **four** lines after the text size is increased to 200%. If a headline exceeds this limit and gets truncated, provide an alternative way to access the full content in a single tap.


![A dialog with 200% text wraps multiple times in the header and description. It covers most of the mobile screen.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm8sh8mb2-03_caution.png?alt=media&token=f2a9bb8e-dfb7-46a9-a33f-20f895f8a834)
*Avoid excessive text wrapping or truncation by choosing concise strings*

### Elements within dialogs

Because dialogs can contain various elements within them, refer to the relevant accessibility guidelines for each element. 

Some common examples include:

1. Text fields
2. Typography
3. Buttons


![3 elements of a full-screen dialog.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm8x3m4cu-04.png?alt=media&token=4e645417-fc9a-48de-a66f-a3b5c5d16723)
*Full-screen dialogs can contain various elements such as (1) text fields, (2) typography, and (3) buttons, which each may have their own accessibility guidelines*

## Initial focus

When a dialog appears, focus should automatically land on the first interactive element within the dialog.


![A modal dialog titled “Permanently delete?” whose second interactive element is focused by selecting the Tab key.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm8wqpp2r-05.png?alt=media&token=d5214cf8-9fa2-458f-9e17-5db4e07235ba)
*Initial focus lands on the first interactive element within a dialog. The tab key moves focus through the next interactive elements in a cycle.*


![A modal dialog titled “Permanently delete?” whose previous interactive element is focused on by selecting both the Shift and Tab keys.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm8wqq56l-06.png?alt=media&token=2eae0221-4a71-4ca5-b873-6d3bf94cbd23)
*The shift and tab keys together move focus in the opposite direction. The space or enter key triggers or commits the action of the focused element.*

## Keyboard navigation

| Keys | Actions |
| --- | --- |
| Tab | Focus lands on the next interactive element contained in the dialog, or the first element if focus is currently on the last element |
| Shift + Tab | Focus lands on the previous interactive element contained in the dialog, or the last element if focus is currently on the first element |
| Space or Enter | Triggers or commits the action of the focused element |
| Escape | Closes the dialog |

## Labeling elements

The accessibility label for a dialog is typically the same as the dialog’s title or headline.

On web, basic dialogs should have the **alert dialog** role.


![An alert dialog with a title “Set up traffic updates?”  Its label is “Set up traffic updates?” and its role as “Alert Dialog.”](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmk2a1j0z-07.png?alt=media&token=f67fa051-a23b-4607-a062-bcd540ddcaef)
*Basic dialogs are known as alert dialogs on web*

Components contained within the dialog, such as buttons, should be labeled according to the guidelines specific to those components.

For common examples, see:

- Buttons
- Text fields


![A full-screen dialog titled “New event” containing a “Save” button and a text field, both with their own accessibility labels.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmk2a20m5-08.png?alt=media&token=0478cb5e-0c5f-4154-9888-0ba7de7a3247)
*Elements within a dialog should be labeled according to their guidelines*
