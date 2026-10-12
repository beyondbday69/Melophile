---
title: "FAB menu"
slug: "components/fab-menu"
url: "https://m3.material.io/components/fab-menu"
---

# FAB menu

- Opens from a FAB to show 2–6 related actions floating on screen
- One FAB menu size for all sizes of FABs
- Not used with extended FABs
- Available in primary, secondary, and tertiary color sets


![3 FAB menus in different color schemes.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0aj37mw-01.png?alt=media&token=1ac9e775-2541-4a63-8818-d76cf8570699)
*The FAB menu comes in three color sets: primary, secondary, tertiary*

## Availability & resources

## M3 Expressive update

**May 2025**

The FAB menu adds more options to the FAB. It should replace the speed dial and any usage of stacked small FABs. [More on M3 Expressive](https://m3.material.io/blog/building-with-m3-expressive)

New component added to catalog:

- One menu size that pairs with any FAB
- Replaces any usage of stacked small FABs

Color:

- Contrasting close button and item colors
- Supports dynamic color
- Compatible with any FAB color style


![4 screens. The FAB menu is on the first, and 3 FABs of different sizes are on the others.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0aj3ip6-02.png?alt=media&token=fde56cc4-c285-45ef-9019-faa06da95454)
*The FAB menu uses contrasting color and large items to focus attention. It can open from any size FAB.*

## Differences from M2


![M2 speed dial.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0aj3w24-Diff%20GM2.png?alt=media&token=e358569f-0a63-4ead-a844-ad98804cee2d)
*M2: The speed dial used small round FABs*


![GM3 FAB menu.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0aj42vs-Diff%20GM3%20Expressive.png?alt=media&token=b0d9f87d-66c0-48f4-9a11-e312b5b207ef)
*M3: The FAB menu uses dynamic color and a larger item size*

## Variants


![The FAB menu in its single variant.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fma3q0apv-01-3p.png?alt=media&token=6052e914-0b73-49b5-bb94-06710b22c865)
*There’s one variant of FAB menu*

| Variant | M3 | M3 Expressive |
| --- | --- | --- |
| FAB menu | -- | Available |

## Configurations


![3 color configurations of FAB menus.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fma3q1bvc-02-3p.png?alt=media&token=999f9b38-e4fc-4555-9211-9a525aa24230)
*Three color sets:

1. 

Primary
2. 

Secondary
3. 

Tertiary*

| Category | Configuration | M3 | M3 Expressive |
| --- | --- | --- | --- |
| Color | Primary set, secondary set, tertiary set | -- | Available |

## Tokens & specs

Use the table's menu to switch token sets. The FAB menu has a common token set and six color sets, three for each element (close button and menu item). [Learn about design tokens](/m3/pages/design-tokens/overview/)

## Anatomy


![2 elements of a FAB menu.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fma3q41o4-03-3p.png?alt=media&token=0a40cd6b-762e-4236-b0a9-29363434bd34)
*1. Close button
2. Menu item*


![5 FAB menus showing the range of 2–6 items.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fma3q4pvy-04-3p.png?alt=media&token=aeafe6af-aacf-413b-91ec-ae1551e1721f)
*The FAB menu can have up to six items*

## Color

Color values are implemented through design tokens. For designers, this means working with color values that correspond with tokens. In implementation, a color value will be a token that references a value. [Learn more about design tokens](/m3/pages/design-tokens/overview)


![12 colors of the FAB menu.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fma3q6ipd-05-3p.png?alt=media&token=faa394aa-1701-4038-a704-c838e6b7e5f9)
*1. On primary container
2. Primary container
3. On primary
4. Primary
5. On secondary container
6. Secondary container
7. On secondary
8. Secondary
9. On tertiary container
10. Tertiary container
11. On tertiary
12. Tertiary*

## States

States are visual representations used to communicate the status of a component or interactive element. [Learn more about interaction states](/m3/pages/interaction-states)

### Close button


![4 states of the FAB menu close button.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fma3q7h0v-06-3p.png?alt=media&token=9965cb9f-131e-4e9d-951d-e2d96a9fa291)
*Close button states in light and dark themes: 

1. 

Enabled
2. 

Hovered
3. 

Focused
4. 

Pressed*

### Menu item


![4 states of the FAB menu items.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fma3q8bqk-07-3p.png?alt=media&token=66a1f615-4317-43a0-b515-c235023816c1)
*Menu item states in light and dark themes:

1. 

Enabled
2. 

Hovered
3. 

Focused
4. 

Pressed*

## Measurements

FAB menu items share the same measurements as the medium button specs. 

The close button should always be 56dp.


![FAB menu size measurements.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fma3qcpur-08-3p.png?alt=media&token=cc0e1910-0146-45cb-a930-422d7dd365ab)
*FAB menu size measurements*

The FAB menu animates from the top trailing edge of the FAB to ensure a smooth animation.


![FAB on a mobile screen with 16dp margins annotated.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fma3qf9rm-08-3p.png?alt=media&token=69caec5c-3f45-4ee5-9f75-b7acd8485b0b)
*The FAB should always have 16dp margins*


![FAB menu opened from a FAB has matching margins of 16dp.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fma3qfqs9-10-3p.png?alt=media&token=a87058b9-e220-4aca-ab98-eeba2686baa5)
*The close button and FAB share the top trailing corner as an anchor and appear in the same place*

Larger FABs will place the FAB menu slightly higher, with larger margins underneath.


![Medium FAB on a mobile screen with 16dp margins annotated.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fma3qhher-11-3p.png?alt=media&token=e738416d-1893-44e5-814a-c614176b3709)
*The medium FAB placement has 16dp margins*


![FAB menu opened from the medium FAB has a 40dp margin from bottom of screen.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fma3qhtwe-12-3p.png?alt=media&token=f67723d9-0b12-44fd-9c53-5072affd847a)
*The close button is placed higher to align with the top of the medium FAB*


![Large FAB on a mobile screen with 16dp margins annotated.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fma3qj0c0-13-3p.png?alt=media&token=c0c1bcf6-a3a5-4579-8b5f-934d4a89ae59)
*The large FAB placement has 16dp margins*


![FAB menu opened from the large FAB has a 56dp margin from bottom of screen.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fma3qjff7-14-3p.png?alt=media&token=2882a5b7-fdeb-4fb5-b3dc-c1ef40868c81)
*The close button is placed higher to align with the top of the large FAB*

On web, the FAB menu opens from the FAB, and inherits its states and specs from the baseline menu component. 

The gap between the FAB and menu can vary, but 4dp is recommended.


![FAB menu on web states and specifications.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmn7ox1xs-04-3P.png?alt=media&token=3539736b-2946-4a4b-afe4-50c9e6ed50e6)
*Spacing and interaction on FAB menu for web:

1. 

Enabled
2. 

Hovered
3. 

Selected*


![On a page of music albums, a FAB menu shows options to make a new playlist, collection, or station.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0al2scq-01.png?alt=media&token=933b5d53-2e43-42c9-8f5b-6dd54b507ed1)
*Use the FAB menu to show multiple related actions in a prominent, expressive style*

## Usage

A FAB menu opens from a FAB to show multiple related actions. It should always appear in the same place as the FAB that opened it.

This makes actions immediately accessible, and keeps the UI clean by concealing actions when they’re not needed.

Don’t open a FAB menu from an extended FAB or any other component.


![1 mobile screen with a FAB, 1 with a FAB menu. Both are right aligned.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0al39xa-02.png?alt=media&token=8593c439-72f3-4e9a-bab4-31fb9b508f96)
*The FAB menu should always open from a FAB*

The FAB menu should be aligned to the trailing edge of the window. 

In right-to-left (RTL) languages, this means the FAB and FAB menu should be aligned to the left edge, and the layout of elements should be mirrored.


![1 mobile screen with a FAB, 1 with a FAB menu. Both are left aligned and mirrored for a right-to-left language.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0al3tfx-03.png?alt=media&token=5f6b948a-7cfb-433e-b36c-e6039479f05f)
*In RTL languages, the FAB menu should be left-aligned with the icon and text placement mirrored*

FAB menus can contain 2–6 items. These should be closely related under a single action, like **Share**. 

Avoid grouping unrelated actions in the same FAB menu.


![A FAB menu with 5 options on a photo gallery UI.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0al46l0-04.png?alt=media&token=99e07baf-b16a-429a-8bd2-f577e1f0ab03)
*FAB menus can have 2-6 items*


![A FAB menu with 1 option on a photo gallery UI.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0al4jux-05.png?alt=media&token=4c31fc95-1876-44d9-bd9a-74dfa92d7e4e)
*Don’t use a FAB menu with one item*

When a FAB is paired with other components, like the floating toolbar or navigation rail, don’t use the FAB menu. This prevents cognitive overload and interface clutter.


![A toolbar with a FAB directly next to it.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0al73q3-06.png?alt=media&token=7530f997-8c27-4487-b57b-d0257454ef76)
*FABs can be placed next to toolbars and other components*


![A toolbar with a FAB menu next to it.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0al7run-07.png?alt=media&token=35de4220-c99b-4cdf-9903-cd4c4df92f87)
*Don't use a FAB menu with a toolbar or navigation rail*

### Color sets

FAB menus have three color sets: primary, secondary, and tertiary. Use the color set that best matches the FAB color style.   

Use the primary FAB menu color set with the **primary** or **primary container** FAB color styles.


![A FAB menu using the primary color set. ](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0al91ki-08.png?alt=media&token=ef35ad96-9369-44fa-8547-d3df63456e56)
*A primary FAB is paired with a primary FAB menu*

Use the secondary FAB menu color set with the **secondary** or **secondary container** FAB color styles.


![A FAB menu using the secondary color set.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0alljh0-09.png?alt=media&token=ed402cb2-4a9b-4116-9562-9e3805d5d8f6)
*A secondary FAB is paired with a secondary FAB menu*

Use the tertiary FAB menu color set with the **tertiary** or **tertiary container** FAB color styles.


![A FAB menu using the tertiary color set.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0almfyg-10.png?alt=media&token=67b04c16-7eeb-47fa-86c1-4ed567e43115)
*A tertiary FAB is paired with a tertiary FAB menu*

## Anatomy


![2 elements of a FAB menu.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0alqq5m-11.png?alt=media&token=e0479ac2-8344-4f44-8bb0-c566b5231015)
*1. Close button
2. List item*

FAB menu items should always have label text. The icons shouldn’t be removed since they make each item easy to identify.


![A FAB menu with 3 options for selecting Food, People, or Nature. There are no icons next to the text.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0alqzgn-12.png?alt=media&token=9f30f3f1-7e25-49f9-be70-457be0bed35f)
*Only remove the icon if necessary. The icon provides a differentiation between items.*


![A FAB menu with 3 options for selecting Food, People, or Nature. The options are only icons, no text.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0feodak-13.png?alt=media&token=eec6074a-71e9-47d1-96ad-fea96e8f63a7)
*Don’t remove the label*

The list item should always hug its contents and look consistent. Avoid truncating text or setting fixed widths. All FAB menu elements should be rounded.


![A FAB menu used out of the box with no configurations.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0alssof-14.png?alt=media&token=83c41c88-207b-4692-b117-89a41eb96f38)
*Keep the padding between the container and icon, icon and text, and text and container consistent*


![FAB menu items are equal width despite having different lengths of text.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0alt3lp-15.png?alt=media&token=f8796c60-e6ec-48f7-9ff9-255bb1a6fa66)
*Don’t expand container sizes*


![FAB menu items are square instead of round.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0altd3s-16.png?alt=media&token=911d5b4e-aa6b-4f62-a85c-b64c402bb265)
*Don’t change FAB menu shapes*

## Adaptive layout

The FAB menu can open from any sized FAB. Use with a FAB size suitable for the window size class. For example, larger FABs are recommended for larger windows.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmc0tzxol-GM3_Expressive_FAB_Guidelines_FAB-Menu%20D_v01.mp4?alt=media&token=9f57a56c-c58c-4a79-920e-5b21485d655a)

The FAB menu should remain anchored to the same corner or edge regardless of window size. 

In large and extra large windows, the FAB and FAB menu margins should increase from 16dp to 24dp.


![A FAB menu with 24dp margins from the edge of the window.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0alwg82-18.png?alt=media&token=4fbeae7c-3a0b-43ab-9dde-fa6fdaf5fa93)
*On desktop, use larger FABs and margins*

On web, the FAB menu uses a menu component for an experience that's consistent with other desktop apps.


![A FAB menu using menu component on web and traditional FAB menu on compact screen.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmn7ozpxv-18%20-%20Web%20vs.%20Mobile%20-%203P.png?alt=media&token=acc5a489-c566-4633-901b-093de2973a0c)
*The same FAB menu options on both large window (left) and an Android compact window (right)*

## Behavior

### Appearing

The FAB should transform into the close button of the FAB menu. The menu items should appear using the [enter and exit](/m3/pages/motion-transitions/transition-patterns#e1c2a650-d7a4-4a6d-9025-e6b7845291ed) transition.

Originate the transition from one of the FAB's trailing corners, preferably the top-aligned corner.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm23g9t73-FAB-Menu-A-1.mp4?alt=media&token=bdf9b02f-b3a7-403e-854b-e1866dc67724)

To ensure accessibility for keyboard users on the web, avoid positioning the FAB menu to completely obscure the focus indicator of an actionable element. 

Partially covering the desired element is fine, as long as the focus indicator is visible.


![FAB menu doesn’t obscure actionable element and its focus indicator.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm4hkgdmm-21.png?alt=media&token=f82bf9f8-0945-44a8-b5b9-644fecd6c509)
*Ensure the actionable element and its focus indicator are visible behind the FAB menu*


![FAB menu obscures both an actionable element and its focus indicator.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm4hkgqnr-22.png?alt=media&token=c61ae0da-15e0-4c67-8ed2-e27877fd51d5)
*Don’t block an actionable element and its focus indicator completely with the FAB menu*

### Scrolling

When window height is limited, like when viewing phones in horizontal orientation, FAB menu items can scroll. 

The items should scroll behind the close button.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0fevuhp-FAB-Menu-B.mp4?alt=media&token=30bc4800-73ce-4680-9067-a006d8713410)

### Expanding

Any FAB menu item can expand and adapt to any shape using a [container transform](/m3/pages/motion-transitions/transition-patterns#b67cba74-6240-4663-a423-d537b6d21187) transition pattern. This includes a surface that is part of the app structure, or a surface that spans the entire screen.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0few8ot-FAB-Menu-C.mp4?alt=media&token=3017a1d5-9e41-47dc-b66a-982e4e1e35db)

## Use cases

People should be able to do the following using assistive technology:

- Navigate and interact with the FAB menu
- Ensure focus is correct when navigating through the menu

## Interaction & style

FAB menu elements meet the minimum target size of 48dp.


![FAB menu measurement annotations. All elements are larger than the minimum target size.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0am5xpn-01.png?alt=media&token=537719c2-9f5f-4d5d-ba85-2b59cdcd2c5b)
*FAB menus have 48x48dp minimum width and sufficient spacing by default*

When the FAB menu can scroll, make sure the items scroll behind the close button. 

The close button should always be easy to access and unobstructed.


![FAB menu items are scrolling behind the close button.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0am8l9a-02.png?alt=media&token=989423d2-f7cd-4b11-8ac1-115565b2ccc2)
*Allow the menu items to scroll behind the close button*


![FAB menu items are scrolling in front of the close button.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0am8ym2-03.png?alt=media&token=5fa58f68-9fae-4806-8754-4fe611b73f45)
*Don’t obstruct the close button in short screens like horizontal orientation*

## Initial focus

When the FAB is selected, the FAB menu opens, and initial focus remains on the close button, which takes the place of the original FAB.

Then the focus moves from the top menu item to the bottom.


![4 FAB menus with the focus order labelled. Focus moves from the close button at the bottom to the topmost menu item next.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmaejeaf9-04.png?alt=media&token=d8a732b5-ad7b-4566-a92d-eccca8795f38)
*Focus lands on the close button. People can then navigate through all the items.

1. Close button
2. First menu item
3. Second menu item
4. Third menu item*

## Keyboard navigation

****

****

****

********

| Keys | Actions |
| --- | --- |
| Tab | Navigate to the next interactive element |
| Space or Enter | Activate the focused button or item |

## Labeling elements

### Android

On Android, a FAB menu’s close button should include a state to tell screen readers what action will occur when it's toggled. The close button should be labeled: 

- 

Label: Toggle menu
- 

Role: Button
- 

State: Expanded or collapsed


![Accessibility labels for the close button on an Android device.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmmmksaup-05.png?alt=media&token=0aabb7b0-89f8-49e9-9564-e07ee8b0c517)
*On Android, the **close button** accessibility labels should include a toggle menu label, button role, and an expanded or collapsed state*

FAB menu items should be labeled:

- 

Label: Match the item’s UI text, such as **Reply all **
- 

Role: Button


![Accessibility labels for a FAB menu item on an Android device.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmmmksh30-06.png?alt=media&token=469b8f96-0db8-4a1a-b379-e77aeed01160)
*Label FAB menu items to match their UI text, like **Reply all**, and use the button role*

### Web

On web, a FAB menu is a combination of a FAB and a menu component. The FAB opens the menu. Follow the [accessibility guidelines for FABs](/m3/pages/fab/accessibility) and [menus](/m3/pages/menus/accessibility).

The FAB's accessibility label should describe the menu that the FAB will open.
