---
title: "Side sheets"
slug: "components/side-sheets"
url: "https://m3.material.io/components/side-sheets"
---

# Side sheets

- 

Use side sheets to provide optional content and actions without interrupting the main content
- 

Two variants: standard and modal
- 

People can navigate to another region within the sheet
- 

Side sheets can contain a back icon for navigation


![The 2 variants of side sheets.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmgalv851-01.png?alt=media&token=191d453b-5ede-4308-8bf3-fce576d974a9)
*1. Standard side sheet

2. Modal side sheet*

## Availability & resources

## Differences from M2

- 

Right-to-left (RTL) language support with left side sheet
- 

Color: New color mappings and compatibility with dynamic color
- 

Shape: Modal side sheets have a 16dp corner radius


![A modal side sheet showing the 16dp corner radius.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmgalwhrt-02.png?alt=media&token=054fa34c-239b-4ffb-99d0-fb5c0f751873)
*Side sheets have new color mappings to support dynamic color*

## Tokens & specs

Browse the component elements, attributes, tokens, and their values. [Learn more about design tokens](/m3/pages/design-tokens/overview)

## Standard side sheet


![4 elements of a standard side sheet.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmgam1q2e-01-3P.png?alt=media&token=c481dd60-266a-4f45-b3e7-c5148f52d7a4)
*1. Divider (optional)
2. Headline
3. Container
4. Close icon button*

### Standard side sheet color

Color values are implemented through design tokens. For design, this means working with color values that correspond with tokens. For implementation, a color value will be a token that references a value. [Learn more about design tokens](/m3/pages/design-tokens/overview/)


![4 color roles applied to a side sheet in light and dark themes.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmgam3xno-02-3P.png?alt=media&token=a014f6d6-2b5c-4b1b-a365-35f44ff94849)
*Side sheet color roles used for light and dark themes:

1. Outline variant
2. On surface variant
3. Surface
4. On surface variant*

### Standard side sheet measurements


![Standard side sheet padding and size measurements.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmgam4wao-03-3P.png?alt=media&token=9ad396a2-516e-4f81-8ebd-00351e542220)
*Side sheet padding and size measurements*

| Attribute | Value |
| --- | --- |
| Start/end padding | 24dp |
| Padding between top elements | 12dp |
| Bottom actions height | 72dp |
| Bottom actions top padding | 16dp |
| Bottom actions bottom padding | 24dp |
| Bottom actions alignment (horizontal) | Left |
| Max-width | 400dp |
| Margins (when detached) | 16dp |

## Modal side sheet


![7 elements of a modal side sheet.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmgam6adp-04-3P.png?alt=media&token=9b5b4904-7ee2-4e28-b834-2d91602c1217)
*1. Back icon button (optional)
2. Headline
3. Container
4. Close icon button
5. Divider (optional)
6. Action buttons (optional)
7. Scrim*

### Modal side sheet color

Color values are implemented through design tokens. For design, this means working with color values that correspond with tokens. For implementation, a color value will be a token that references a value. [Learn more about design tokens](/m3/pages/design-tokens/overview/).


![4 color roles applied to a modal side sheet in light and dark themes.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmgam8seo-05-3P.png?alt=media&token=f1de3f6f-2bb5-4788-9b42-7cce55f744b7)
*Side sheet color roles used for light and dark themes:

1. 

On surface variant
2. 

On surface variant
3. 

Surface container low
4. 

On surface variant*

### Modal side sheet measurements


![Modal side sheet padding and size measurements](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmgamb5rx-06-3P.png?alt=media&token=882e119b-eae4-437b-9cdb-5fe69888fa1a)
*Modal side sheet padding and size measurements*

| Attribute | Value |
| --- | --- |
| Start/end padding | 24dp |
| Start padding with icon | 16dp |
| Padding between top elements | 12dp |
| Bottom actions height | 72dp |
| Bottom actions top padding | 16dp |
| Bottom actions bottom padding | 24dp |
| Bottom actions alignment (horizontal) | Left |
| Max-width | 400dp |
| Margins (when detached) | 16dp |


![Side by side comparison of a standard and a modal side sheet.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmeqqqohk-01.png?alt=media&token=51d3df6c-574b-4aaf-bd7a-b70c47c82c6a)
*1. Standard side sheet

2. Modal side sheet*

## Usage

Standard side sheets are supplementary surfaces used mostly in medium to expanded breakpoints, like tablet and desktop. They provide a consistent and predictable surface for contextual actions and information.

Standard side sheets display content that complements the screen’s primary content. They remain visible while people interact with primary content.

Common uses include:

- 

Displaying a list of actions that affect the screen’s primary content, such as filters
- 

Displaying supplemental content and features


![Standard side sheet showing supplementary information about a photo.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmeqr6mnd-02.png?alt=media&token=8c90dc20-b3b0-4725-9130-ba118c870255)
*Information about a photo in a standard side sheet*

Modal side sheets are preferred in compact breakpoints, like mobile, due to limited screen size. 

They can display the same kinds of content as standard side sheets, but must be dismissed in order to interact with the underlying content.


![Modal side sheet showing filter controls.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmeqrab5f-03.png?alt=media&token=0729930f-cd91-430a-ad7a-3c74997f4771)
*Modal side sheet with filter controls*

Side sheets have a fixed width and typically span the height of the screen. 

Their dimensions depend on how the app’s layout is subdivided into UI regions.


![A modal sheet at the right of a screen, with the correct inset.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmeqrlvws-04-do.png?alt=media&token=1a29c903-cb7f-4b43-917f-8a584edc828b)
*Place side sheets along the edge of the screen, usually on the right side to avoid interference with any navigational components on the left edge. They can be slightly inset by 16dp.*


![A modal side sheet at the right of the screen with the wrong inset.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmeqrngv8-05-don't.png?alt=media&token=6167c058-b004-42fb-a408-c1f3f8bdea12)
*Don’t inset a side sheet from the screen edges far beyond the recommended margin. This makes the sheet’s position and scroll behavior unclear, while obscuring primary content.*

## Anatomy


![4 elements of a standard side sheet.  ](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmeqrqfpt-06.png?alt=media&token=b39ae6aa-ee8b-45cb-9d9b-c3aab1f16a67)
*1. Divider (optional)
2. Headline
3. Container
4. Close icon button*


![7 elements of a modal side sheet.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmeqrrjh7-07.png?alt=media&token=989dc9f2-073d-4b86-8889-02ea9651aaf1)
*1. Back icon button (optional)
2. Headline
3. Container 
4. Close icon button
5. Divider (optional) 
6. Action buttons (optional) 
7. Scrim*

### Container

Side sheet containers hold all side sheet elements. Their size is determined by the space those elements occupy.
 
The container is the only required element of a side sheet.


![A modal side sheet’s container.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmeqsampe-08.png?alt=media&token=6de272f2-1434-4002-8037-f40e904d54b8)
*1. Container*

### Back icon button (optional)

Icon buttons can provide ways to exit a side sheet or move to a different experience.

Because the primary content behind or beside a side sheet is always visible, it’s important to provide affordances for leaving a side sheet and returning to the primary content.


![Back icon button on the upper left of a modal side sheet.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmeqsgqgs-09.png?alt=media&token=77f536ad-a501-4715-9655-d5b7fdcab733)
*1. Back icon button*

### Close icon button (optional)

A close affordance provides a consistent method for dismissing a side sheet. 

A close icon button is highly recommended, increases accessibility, and makes focused side sheets easier to close.


![Close icon button on the upper right of a modal side sheet.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmeqsns8t-10.png?alt=media&token=94c3975a-172e-42aa-b2ab-a335f9c9e858)
*1. Close icon button*

### Action buttons (optional)

Buttons represent actions available from a side sheet. Examples: **Save**, **Edit**, **Download**

Use elevation, fill, and tone to call attention to specific actions.


![Save and cancel buttons at the bottom of a modal side sheet.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmeqsw3fe-11.png?alt=media&token=4452e717-8e08-4dc3-b1aa-a63252feeae1)
*1. Action buttons*

### Divider (optional)

Dividers can separate different kinds of content and create distinct regions in a side sheet. 

Use a divider to separate:

- 

Action buttons from content
- 

User-generated content from system-generated content


![Horizontal divider on a modal side sheet.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmeqsz2ma-12.png?alt=media&token=7c14f328-775a-4c98-9513-1600f48affbe)
*1. Divider*

### Content (optional)

Side sheets can display a wide variety of content and layouts, ranging from a list of actions to supplemental content in a tabular layout.


![2 side sheets with different content displayed side by side.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmeqt0pai-13.png?alt=media&token=9a10a8aa-ec40-4657-9bd9-b0447cbbb01e)
*Form controls shown in a side sheet for app settings*


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flw8ytzch-GM3-Components-SideSheets-Guidelines-1-v01.mp4?alt=media&token=1bf4052f-7d2f-4918-ab47-cac110c8a72b)

## Adaptive design

Side sheets have a default width, but can be resized depending on the needs of the layout. 

When a standard side sheet opens, the body area shrinks to accommodate the sheet’s width while maintaining a margin on the body’s trailing edge.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flw8yw08l-GM3-Components-SideSheets-Guidelines-2-v01.mp4?alt=media&token=b05fcdca-80b4-4337-9505-251837e72d7d)

### RTL language support

In right-to-left (RTL) languages, side sheets should appear on the left edge of the window with all elements reversed.


![Side sheet along the left edge of a screen. All buttons and icons are reversed.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmeqwb27c-16.png?alt=media&token=5b9ae114-f5ae-4f03-ada2-88ffe3f7f5c1)
*Side sheet elements are reversed in RTL languages*

## Behavior

Side sheets can vertically scroll independent of the rest of the UI. 

This allows their scroll position and content to persist while the page is scrolled, and vice versa.

Side sheets cannot scroll horizontally.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flw8yz6fq-GM3-Components-SideSheets-Guidelines-3-v01.mp4?alt=media&token=9e3edaa2-0851-448a-85bd-983c57f63ad8)


![A side sheet appears to scroll horizontally.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flw8z3x56-19_don't.png?alt=media&token=b2f8c1b7-ea55-4f8a-b3de-6b9b51be72f9)
*Don’t allow horizontal scrolling or lay out the side sheet in a way that suggests horizontal scrolling. A side sheet’s narrow width leaves limited space to fully view items.*

### Predictive back

On Android, a gesture called [predictive back](https://github.com/material-components/material-components-android/blob/master/docs/foundations/PredictiveBack.md) allows a person to swipe left or right on the side sheet. 

When predictive back is used:

- 

The side sheet detaches from the top and bottom edges of the screen to signal it will close
- 

The previous screen is revealed in a preview
- 

The side sheet and its content always scales in the direction of the gesture

[Find a list of compatible components](/m3/pages/gestures#22462fb2-fbe8-4e0c-b3e7-9278bd18ea0d)


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flw8z7v0k-GM3-Components-SideSheets-Guidelines-4-v02.mp4?alt=media&token=77de11b6-dee0-4663-93b4-47229c2998bd)

## Use cases

People should be able to dismiss the side sheet using assistive technology.

## Interaction & style

Material requires that a close affordance, such as a close icon button, is always present within a side sheet.


![Side sheet correctly designed with close icon in upper right corner.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmgamgh0c-01-do.png?alt=media&token=726a6d30-dab6-4f51-ad2e-015ac54dd3c3)
*A close icon button makes the side sheet easy to dismiss*


![Side sheet incorrectly designed with no close icon button.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmgamhoet-02-don't.png?alt=media&token=39e0958b-98a1-439f-b7ba-a8a199550336)
*Without a close icon button, people can’t predict the opening and closing flow of side sheets, or know if the sheet is transient or permanent*

## Initial focus

Actions within a side sheet can be focused by tab order using a keyboard or switch control.


![Side sheet diagram showing the focus order of headline, close, save, cancel.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmgamkh0k-03.png?alt=media&token=0ecb7ab4-bff0-48b1-84a2-0c39cc936114)
*Visible focus shown on the available actions within a side sheet:

1. Headline
2. Close
3. Cancel
4. Save*

## Keyboard navigation

****

********

| Keys | Actions |
| --- | --- |
| Tab | Focus lands on (non-disabled) icon button |
| Space or Enter | Activates the (non-disabled) icon button |

## Labeling

The accessibility role for a side sheet is **Dialog**.


![Side sheet showing the accessibility role as dialog.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmgamoe2t-04.png?alt=media&token=f7ce54e1-ca6e-486f-9776-2057064b9beb)
*The role for side sheets is **Dialog***
