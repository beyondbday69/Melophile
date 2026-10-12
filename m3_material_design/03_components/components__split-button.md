---
title: "Split buttons"
slug: "components/split-button"
url: "https://m3.material.io/components/split-button"
---

# Split buttons

- Use to show an action with a menu of related actions
- Same size range as buttons and icon buttons: XS, S, M, L, XL


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm4ilqvqh-Split-Button-B.mp4?alt=media&token=2a33379b-01c6-44e5-926a-4af1b897652b)

## Availability & resources

## M3 Expressive update

**May 2025**

The split button has a separate menu button that spins and changes shape when activated. It can be used alongside other buttons of the same size. [More on M3 Expressive](https://m3.material.io/blog/building-with-m3-expressive)

New component added to catalog.

Sizes:

- Extra small
- Small
- Medium
- Large
- Extra large

Color styles:

- Elevated
- Filled
- Tonal
- Outlined


![5 sizes of split buttons.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0dmjd8r-2.png?alt=media&token=62fdcac6-024e-4e46-8b38-7c4e6da472a8)
*Split buttons have the same five recommended sizes as label and icon buttons*

## Variants


![1 type of split button.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fma3o4mdr-1-3P.png?alt=media&token=1ccc3f10-8e6e-42e6-b8b8-1c0c728e68e2)
**

| Variant | M3 | M3 Expressive |
| --- | --- | --- |
| Split button | -- | Available |

## Configurations


![4 colors and 5 sizes of split buttons.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fma3o55u8-2-3P.png?alt=media&token=9bebc1e1-45eb-4425-853d-06e6a5182a32)
*1. Color configurations: Elevated, filled, tonal, outlined

2. Size configurations: XS, S, M, L, XL*

| Category | Configuration | M3 | M3 Expressive |
| --- | --- | --- | --- |
| Size | XS, S, M, L, XL | -- | Available |
| Color | Elevated, filled, tonal, outlined | -- | Available |

## Tokens & specs

Use the table's menu to select a token set. Split button token sets are organized by size. [Learn about design tokens](/m3/pages/design-tokens/overview/)

## Anatomy


![4 elements of a split button.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fma3o642r-3-3P.png?alt=media&token=01e1f664-be22-431f-adae-f93b4adba0d0)
*1. Leading button

2. Icon

3. Label text

4. Trailing button*

The leading button in split buttons can have an icon, label text, or both. The trailing button should always have a menu icon.


![3 customizations of the leading button in the split button.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fma3o6fum-4-3P.png?alt=media&token=97f5490e-2d3f-47fc-af1b-146fa020f08f)
*1. Label + icon

2. Label

3. Icon*

## Color

Color values are implemented through design tokens. For designers, this means working with color values that correspond with tokens; in implementation, a color value will be a token that references a value.

Split buttons use the same color schemes as standard buttons. However, unlike toggle buttons, the split button color doesn’t change when selected—only a state layer is applied.

Split buttons use the same colors and state layers as buttons, shown in the following token module. [Go to buttons](/m3/pages/common-buttons/overview) for more details.


![4 color roles of the split button when unselected and selected in light and dark theme.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fma3oa90h-5-3P.png?alt=media&token=e3ae73ad-cbc5-4cd7-adc7-d947456fd70b)
*A: Unselected, B: Selected trailing icon

1. Elevated

2. Filled

3. Tonal

4. Outlined*

## States

States are visual representations used to communicate the status of a component or an interactive element. 

Split button states use the same colors and state layers as buttons and icon buttons. Go to those specs for details.

### Leading button shape

The inner corners change shape for hovered, focused, and pressed states.


![5 states of the leading button in the split button.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fma3oaphp-6-3P.png?alt=media&token=b62c03ab-eed1-43e8-b6cd-14619a24b107)
*1. Enabled

2. Disabled

3. Hovered

4. Focused

5. Pressed, pressed with focus*

### Trailing button shape

The inner corners change shape for hovered, focused, and pressed states, and the icon becomes centered when selected.


![6 states of the trailing menu button in the split button.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fma3ob3p2-7-3P.png?alt=media&token=26516240-0901-4684-85a7-a42eb3336c7b)
*1. Enabled

2. Disabled

3. Hovered

4. Focused

5. Pressed, pressed with focus

6. Selected, selected with focus*

## Measurements

Text and icons are optically centered when the buttons are asymmetrical. They’re centered normally when symmetrical.


![Padding and size measurements of the split button.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fma5hz28q-8-3P.png?alt=media&token=66b410e7-ae0a-4f9a-bf57-d2b1bbb5910d)
*Menu icon offset when unselected:

1. XS: -1dp from center
2. S: -1dp from center
3. M: -2dp from center
4. L: -3dp from center
5. XL: -6dp from center*

The inner corner radius changes depending on button sizing. The space should always be 2dp.


![Inner padding and inner corner measurements of the split button.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fma3ocky0-9-3P.png?alt=media&token=9524b24f-f746-4de8-8544-26ddcae0df17)
*1. Extra small 4dp

2. Small 4dp

3. Medium 4dp

4. Large 8dp

5. Extra large 12dp*

## High contrast

Maximize legibility by using a limited color palette, following the same guidance for buttons.


![4 states of the split button in high contrast mode.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm4ikihat-10.png?alt=media&token=36f970df-aca7-4711-bcc8-4397f38be008)
*1. Enabled

2. Focused leading button

3. Focused trailing button

4. Selected trailing button*


![Split buttons of many colors and sizes scattered. ](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm36akp9w-1.png?alt=media&token=954a37e5-dbe9-4715-8f37-fd34afd699e0)
*Split buttons come in many sizes and colors*

## Usage

Split buttons are used to add a menu of actions alongside a main action. This reduces visual complexity by hiding extra options. Split buttons work well alone or alongside common buttons and icon buttons.


![A split button applied a filter of “Canada” to a list of activities. Three narrow buttons are next to it to share, favorite, and bookmark.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmaei547w-2-alt.png?alt=media&token=442ef5f8-434c-4615-a622-926623edc416)
*Split buttons on their own can grab attention*

Split buttons have five recommended sizes. These sizes match the sizes offered on buttons and icon buttons:

- 

Extra small
- 

Small (default)
- 

Medium
- 

Large
- 

Extra large

Scale up the split button in large breakpoints, or to create more emphasis in smaller windows.


![A large split button in a compact window draws attention to buying an enamel mug in an online store.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0dosrjf-3.png?alt=media&token=1b565df6-4454-4460-9e64-f0dd199fe87c)
*Using large split buttons on small screens can add extra emphasis for hero moments*

Split buttons can be used alongside other buttons and button groups.


![A vibrant split button for starting a car drive is next to 2 muted icon buttons for bookmarking and sharing the trip.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmaehs3k3-4_ALT.png?alt=media&token=be30a6a7-53a8-4e4d-a190-5293714dd79b)
*Split buttons work harmoniously with regular buttons*

Split buttons can be of different sizes from other buttons on the page, especially since they take up more space.


![A media player has a split button for changing the speed quickly, or opening a menu of options.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmaehumyk-5-alt.png?alt=media&token=51014525-7e5f-43cb-8e6a-04ecd54f2ae8)
*The most prominent controls can be larger while secondary controls in a split button can be smaller*

The split button typically opens a menu, but can be customized to open other components like cards.


![A split button opens a menu with a vibrant color scheme.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm4il1tni-6-alt.png?alt=media&token=bccedc39-a096-4dfa-b654-14d575be9f12)
*Open a menu from a split button*


![A split button opens a menu with an irregular shape highlighting the selected item.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm4il1xwy-7-alt.png?alt=media&token=fb95faee-bb6d-447b-a38f-24f0ef87b0a4)
*Avoid modifying the menu in unusual ways*

## Anatomy


![4 elements of a split button.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0dp22eb-8.png?alt=media&token=7aadf139-d85b-4439-8514-204c2d6c571d)
*1. Leading button

2. Icon

3. Label text

4. Trailing button*

The leading button should be brief, just one or two words, with an icon that best matches the action.

The trailing button should always have the expand and collapse icon since it rotates when selected. Avoid modifying the icon.


![A split button for starting driving directions has a label “32 minutes away” and a refresh icon instead of a menu icon.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0dxuxjz-9.png?alt=media&token=6dbd3d91-8792-44cf-a5b2-f03e5621deb2)
*Avoid using very long labels or changing the trailing icon*

In right-to-left languages, the component layout is mirrored.


![The split button elements are reversed in a right-to-left language.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmtsg1tzf-10.png?alt=media&token=3bf9bf26-a379-43fa-a7c0-fc30a2d86759)
*Split buttons mirror the order of elements in right-to-left languages*

## Behavior

The split button uses the standard motion scheme (not the expressive motion scheme) when rotating the menu button.

The menu button rotates inwards 180° when opened and closed.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm4ilqjvz-Split-Button-B.mp4?alt=media&token=0dbfe0fc-ca7b-4e12-afd7-60a6cceb140a)

### Menu placement

When using the split button with a menu, align the menu with the trailing button when possible.


![A split button with an open menu. The leading edge of the menu is aligned to the leading edge of the menu button.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm4il8isz-12.png?alt=media&token=7dea4867-f593-4b35-a7dd-f8059c252ee5)
*Align the menu with the trailing button*

If there’s not enough room, align the menu to one of the sides of the button.


![A split button with an open menu. The trailing edge of the menu is aligned to the trailing edge of the menu button.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm4ilatqh-13.png?alt=media&token=c60db39f-3af3-4fe8-92c7-09c26c255a71)
*If not possible, align the menu to the side of the leading or trailing button*

Depending on breakpoint, scroll position, and other factors, the menu may need to appear elsewhere around the button. Always try to align it with one of the edges of the button.

The menu should be 4dp from the split button.


![6 other ways the menu can align to the split button.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm4iz4b9v-14.png?alt=media&token=7596fabd-1a2c-4d83-a0d6-03963942cbf5)
*1. Top aligned to trailing button

2. Bottom aligned to trailing button

3. Top right-aligned

4. Top left-aligned

5. Bottom right-aligned 

6. Bottom left-aligned*

## Use cases

People should be able to do the following using assistive technology:

- Navigate to each button and interact with them
- Navigate to any element opened by the trailing button
- Understand the current selection state of the button

## Interaction & style

Each button in the split button needs a minimum target area of 48x48dp. Extra small and small split buttons are shorter than 48dp, so the target areas around them need to be at least 48dp tall.


![Diagram showing extra small and small split buttons with visible 48x48dp target areas.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0dpv69h-1.png?alt=media&token=649cab35-4ea3-432c-b027-1dd04227d045)
*Target areas should be at least 48x48dp 

1. Extra small

2. Small*

## Initial focus

Focus should land on the leading button then move to the trailing button. This can depend on the operating system’s settings.


![Focus on the leading button and trailing button for both LTR and RTL languages.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmtsg0v46-02.png?alt=media&token=9d95dcf5-2299-47b2-9235-960c2c25cb90)
*1. 

Left to right
2. 

Right to left*

## Keyboard navigation

| Keys | Actions |
| --- | --- |
| Tab | Navigate between buttons |
| Space or enter | Activate focused button |

## Labeling elements

The accessibility label for the leading button is the same as buttons.


![“Watch later” is both the button label text and the accessibility label.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0dq2sfs-3.png?alt=media&token=85b75ca9-ea9b-4199-939d-9b42767d223b)
*Leading buttons should have the same labels as common buttons*

The trailing icon button should have an extra state or similar label indicating that the menu is expanded or collapsed.

Label the button to clearly indicate that there are more options. The label of the secondary button should indicate that it provides additional choices related to the action of the main button. For instance, if the main button says "Watch later," the secondary button should be something like "More watch options."

Label the opened menu according to the [menu accessibility guidance](/m3/pages/menus/accessibility/).


![Collapsed state indicated for the trailing button.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0dq40tg-4.png?alt=media&token=e787ddba-953d-423a-bdee-55583e4500a7)
*Trailing buttons should communicate the state of the menu and that more options are available*
