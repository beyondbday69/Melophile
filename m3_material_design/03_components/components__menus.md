---
title: "Menus"
slug: "components/menus"
url: "https://m3.material.io/components/menus"
---

# Menus

- Use a **menu** to show a temporary set of actions. To show actions on screen at all times, use a **toolbar** instead
- Menus can open from many components, including icon buttons, split buttons, and text fields
- **Context menus** provide actions for a specific element, like an image or highlighted text, and usually open with a secondary click


![1 vertical menu with vibrant colors opens from a split button, and 1 vertical menu with a submenu.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmhkajj6i-01.png?alt=media&token=b7e5017f-ef26-4526-8d0c-759c47445705)
*Vertical menus can include vibrant colors, gaps, dividers, and submenus to organize a list of choices*

## Availability & resources

## M3 Expressive update

**November 2025**

**Vertical menus** were introduced with new shapes, color styles, selection states, and refined submenu motion. Gaps can be used for a more flexible layout on Android. [More on M3 Expressive](https://m3.material.io/blog/building-with-m3-expressive)

Variants:

- Added **vertical menus**, recommended for new designs
- Baseline **menu** is still available  

Color styles: 

- Standard
- Vibrant


![A vertical menu using shape and vibrant color to show a selected state.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmhk9jsmw-02.png?alt=media&token=3e48c83b-95fa-49ff-b870-925785fb4a04)
*Vibrant colors help selected menu items stand out*

## Differences from M2

- **Color**: New color mappings and compatibility with dynamic color
- **Variants**: Dropdown menu and exposed dropdown menu are now both referred to as menu, since they differ only in the element which opens the menu surface


![Menu with gray color.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmi5n1krh-4.png?alt=media&token=64d1bd04-5d8f-4ac4-9ed8-83081bf03bc8)
*M2: Former menu colors don’t contrast with the background*


![Menu with purple background and outline.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmhlt7xid-04.png?alt=media&token=10f6b199-9664-4a24-b811-2980270f499c)
*M3: Menus feature new color mappings and dynamic color*

## Variants

### Vertical menus

Use vertical menus for a more expressive look and feel, including rounded corners, standard and vibrant color styles, more selection states, and submenu motion.


![2 vertical menus use shape and color to indicate selected state.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmhp3tuy7-01-3P.png?alt=media&token=53c53a22-2391-40c9-bb50-0a1bae72569e)
*1. Vertical menu with gap
2. Vertical menu with divider*

### Baseline variant

In M3 Expressive, baseline menu is still available to use, but doesn’t have the latest shapes, color styles, selection states, and motion. [See baseline menu specs](/m3/pages/menus/specs#a80df2f9-8610-4ce0-b3a3-b9ee749d5c98)


![A baseline menu variant with square corners and standard colors.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmhp47zoj-02-3P.png?alt=media&token=b7fa2ee6-ebb2-4d3c-a6e1-19e4a8edc452)
*A baseline **menu** has square corners, as compared to a **vertical menu’s** round corners and expressive styling*

****

****

****

| Variant | M3 | M3 Expressive |
| --- | --- | --- |
| Vertical menus | -- | Available |
| Menu (baseline) | Available | Available |

## Configurations

### Vertical menus layout


![2 menus: 1 standard, and 1 with a gap, creating groups.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmhp4bydj-03-3P.png?alt=media&token=2eb83f0c-2aa9-4736-9acc-8f10b6052dbf)
*1. Standard
2. Grouped*

**
****
**************

| Category | Configuration | M3 | M3 Expressive |
| --- | --- | --- | --- |
| Color | Standard | Available | Available |
| Vibrant | -- | Available |  |
| Layout | Standard | Available | Available |
| Grouped | -- | Available |  |

## Tokens & specs

Browse the component elements, attributes, tokens, and their values. [Learn about design tokens](/m3/pages/design-tokens/overview)

## Anatomy

### Vertical menus


![A diagram of a vertical menu.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmhp4i2in-05-3P.png?alt=media&token=8b39eb86-be0a-42d3-80cd-60b703d6180b)
*1. Menu item 
2. Leading icon (optional)
3. Menu item text
4. Trailing icon (optional)
5. Badge (optional)
6. Trailing text (optional)
7. Container
8. Supporting text (optional)
9. Label text (optional)
10. Gap (optional)
11. Divider (optional)*

## Color

Color values are implemented through design tokens. For designers, this means working with color values that correspond with tokens. In implementation, a color value will be a token that references a value. [Learn more about design tokens](/m3/pages/design-tokens/overview)

Menus have two color mappings:

- Standard: Surface-based
- Vibrant: Tertiary-based

These mappings provide options for lower or higher visual emphasis. Vibrant menus are more prominent so should be used sparingly.


![2 vertical menus: 1 with lower visual emphasis, and 1 vibrant menu with bold shades.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmhp4jv7k-07-3P.png?alt=media&token=f38379bf-4e9c-46ae-9d1f-83b879e3f729)
*1. Standard color scheme
2. Vibrant color scheme*

### Standard colors


![2 vertical menus with standard color roles mapped to 11 elements.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmhp4r65m-08-3P.png?alt=media&token=e7a0a642-b2a5-4124-8710-f9fc5e508f25)
*Vertical menus color roles used for light and dark themes:

1. On surface variant
2. On surface
3. On surface (state layer)
4. Surface container low
5. On surface variant
6. On surface variant
7. Tertiary container (selected)
8. On tertiary container (selected)
9. On surface variant
10. On surface variant
11. On tertiary container (selected)*

### Vibrant colors


![2 vertical menus with vibrant color roles mapped to 11 elements.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmhp4scno-10-3P.png?alt=media&token=3e3303c5-3778-46df-929d-8433ac2a6d90)
*Vertical menus color roles used for light and dark themes:

1. On tertiary container
2. On tertiary container
3. On tertiary container (state layer)
4. Tertiary container
5. On tertiary container
6. On tertiary container
7. Tertiary (selected)
8. On tertiary (selected)
9. On tertiary container
10. On tertiary container
11. On tertiary (selected)*

## States

States are visual representations used to communicate the status of a component or an interactive element. [More on interaction states](/m3/pages/interaction-states/overview)

Shape morphing in vertical menus creates an expressive active state. As focus moves between submenus, the corner shape changes to highlight the active menu. [More on menu focus](/m3/pages/menus/guidelines#7cc1d01b-a454-48c7-8306-e60347ffd17f)


![6 vertical menu states in light and dark themes.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmhp4upew-14-3P.png?alt=media&token=9a23182f-ffb3-4156-ae3c-9e6daf5e22f0)
*1. Enabled
2. Disabled
3. Hovered
4. Focused
5. Pressed
6. Active (main menu reveals submenu)*

## Measurements


![Vertical menu marked with spacing and padding measurements.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmi5nwcqt-17-3P.png?alt=media&token=0bbb9bfb-50f9-405c-af84-504554eebb6f)
*Vertical menu padding and size measurements*

## Menu (baseline)

The baseline menu variant is available and continues to work in existing products. However, M3 expressive vertical menus are recommended for new designs.

### Baseline tokens & specs

Browse the component elements, attributes, tokens, and their values. [Learn about design tokens](/m3/pages/design-tokens/overview)

### Anatomy


![Diagram of 6 elements of a baseline menu.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmhp5x7zn-19-3P.png?alt=media&token=bb80c591-2185-4774-938d-ade6c787d2cb)
*1. List item 
2. List item leading icon 
3. List item trailing icon 
4. Container
5. List item trailing text
6. Divider*

### Color


![9 color roles of a baseline menu in light and dark themes.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmhp5xzj4-20-3P.png?alt=media&token=608252b6-49b6-4fbb-950e-e34bb3b23874)
*Baseline menu color roles used for light and dark themes:

1. On surface variant
2. On surface
3. On surface - opacity: 0.08
4. Surface container
5. On surface variant
6. On surface variant
7. On surface variant
8. Surface container highest
9. Outline variant*

### States

#### Default menu items


![Diagram numbering the 5 default states of a baseline menu.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmhp6035c-21-3P.png?alt=media&token=0a96dac7-f21a-4e63-aab3-d77b4e2687d2)
*1. Enabled
2. Disabled
3. Hovered
4. Focused
5. Pressed*

#### Selected menu items


![5 states of a selected baseline menu item.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmhp61n8r-22-3P.png?alt=media&token=c0c51609-bc02-4275-8017-41bedcf4de55)
*1. Enabled
2. Disabled
3. Hovered
4. Focused
5. Pressed*

[State specs are in the token module above](/m3/pages/menus/specs#c811d2fa-469a-4e4e-9d9f-0f535c5c9b4c)

### Measurements


![Diagram of a baseline menu’s padding, text alignment, height, and width.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmhp6b6dr-23-3P.png?alt=media&token=be7b69e3-6291-4725-ab2c-ec443cfe07f0)
*Baseline menu padding and size measurements*

| Attribute | Value |
| --- | --- |
| Container width | 112dp min, 280dp max |
| Corner radius | 4dp |
| Vertical label text alignment | Center-aligned |
| Horizontal label text alignment | Start-aligned |
| Left/right padding | 12dp |
| Left/right padding with-icon | 12dp |
| List item height | 48dp |
| Padding between elements within a list item | 12dp |
| Divider top/bottom padding | 8dp |
| Divider height | 1dp |
| Divider width | Dynamic |
| Leading/trailing icon size | 24dp |

### Configurations

A baseline menu appears when a person interacts with a button, action, or other control. 

A few examples:

1. Button
2. Text field
3. Icon button
4. Selected text


![Examples of 4 baseline menu inputs.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmhp6c83m-24-3P.png?alt=media&token=98b74598-7650-44d3-9fa5-ec73ed8b6564)

> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmhlk1jwz-GM3_Menus_Guidelines%2001_IA_v01.mp4?alt=media&token=2e7dfcc9-2447-4808-8234-83c313d82df8)

## Usage

Use a menu to show a temporary set of actions. To show actions on screen at all times, use a toolbar instead. 

A menu takes up less space than a set of radio buttons or chips. 

### Color options

Menus have two color mappings:

- 

Standard: Surface-based, lower visual emphasis
- 

Vibrant: Tertiary-based, higher visual emphasis

Vibrant menus are more prominent, and should be used sparingly.


![Menu shows item “Line spacing” opening a submenu. In the second menu, “Custom 1.2” is selected with vibrant color.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmi5nyyrq-02.png?alt=media&token=7fed7dc7-af6e-49f3-90d3-829500c8e99d)
*On web, menus can open submenus*

### Opening menus

Menus temporarily appear in front of all other permanent UI elements.

A menu should open when a person:

- 

Selects an element, such as an icon, button, or text field
- 

Performs a specific action to trigger the menu, like right-click or press-and-hold

Use menus in situations that need extra actions, like: 

- 

Overflow menus
- 

Text field dropdown menus
- 

Select menus
- 

Context menus


![A grouped menu with Undo, Redo, Cut, Copy, and Paste options appear over highlighted text in an ebook.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmhlkhju5-04.png?alt=media&token=ab3df2c7-0424-482c-ad21-d5cf945ca16b)
*Menus appear in front of all other UI elements*

### Menu groups

Vertical menu items can be grouped by adding a divider or small gap. Use groups to bundle similar actions together.  

[Gaps and dividers guidelines](/m3/pages/menus/guidelines#d75ac70c-9122-4b4c-bd60-b856bc66c9bc)


![2 vertical menus: a standard menu with no gap and a grouped menu with 1 gap.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmi5o0xc8-05.png?alt=media&token=6e9ba56b-a4e8-4fd0-bacd-9fb1333f2cf9)
*Menu items can be grouped to be more scannable:

1. Standard vertical menu
2. Grouped vertical menu*

### Context menus

Context menus provide a list of additional actions a person can take on an item. A secondary click, like a right-click on a mouse or a two-finger tap on a trackpad, opens a context menu.


![A context menu pops up from a newspaper link. The menu items are: Open in new window, Save link as, Copy address, and Inspect.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmhlkwxab-06.png?alt=media&token=f570d7bb-c80a-43dd-8dd0-9ba78258920f)
*A context menu appears when right clicking with a mouse or trackpad. It can reveal key actions related to the associated content.*

## Anatomy


![Diagram outlining 11 elements of a menu’s anatomy.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmigxx4lp-07.png?alt=media&token=cb02e392-139e-4360-bdcd-eb6a8d8501df)
*1. Menu item 
2. Leading icon (optional)
3. Menu item text
4. Trailing icon (optional)
5. Badge (optional)
6. Trailing text (optional)
7. Container
8. Supporting text (optional)
9. Label text (optional)
10. Gap (optional)
11. Divider (optional)*

### Menu items

Menu items can include label text, leading icons, trailing icons, and keyboard commands. 

When a menu item can only be used under specific conditions, it should appear disabled rather than be removed.


![Menu shows 1 item that’s  disabled, “Redo”. The text color of the disabled item is lighter than the active items.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmhll6qil-08.png?alt=media&token=ae8d6aaa-5646-493e-8c90-4b21457ff03f)
*The **Redo** action is disabled when that action isn’t available*

### Gaps & dividers (optional)

Gaps and dividers can be used to separate and group menu items.

**Gaps **

Use a gap to visually divide menu items into distinct groups. Gaps are more expressive than dividers and make the relationship between items clear.

- Avoid changing the size of the gap
- Limit the number of gaps in a menu to one or two
- Don’t use gaps in scrollable menus


![2 vertical menus with 5 items. A gap separates items into a group of 3 and group of 2.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmi5o2orv-09.png?alt=media&token=ddab60a9-d164-4ab9-aa6a-271b4bdd7210)
*Gaps separate menu items using expressive shapes*

starNote:Gaps are not currently available on web

**Dividers** 

Dividers create a more subtle separation between items. Use a divider for:

- Scrollable menus
- Text fields with a dropdown menu, where a grouped treatment isn’t appropriate

On web, use a divider to separate menu items.


![A menu on a web interface with items separated by a divider line.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmigxyr8q-10.png?alt=media&token=1917b371-7cd4-4221-a770-e1800d56b806)
*Dividers separate menu items in baseline menus and on web*

## Flexibility & slots

Menus have custom slots that support more flexible item layouts.

When creating a complicated menu, think of the menu item as a container with a swappable slot.

Slots work best with simple content such as:

- Images
- Progress indicators
- Color swatches


![A menu showing an undefined slot that could be used for a different element, such as an image.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmhllndkg-11.png?alt=media&token=94c23245-a894-465a-a657-4cc1ec9e4e59)
*Slots can appear anywhere in a menu*

**Slot accessibility**

Use caution when adding slots to menus:

- Make sure the menu remains accessible
- Elements must follow the rules and interaction patterns of the menu component
- Keep the same menu item padding
- Targets should be 48x48dp or larger

Don't add buttons, switches, or other direct actions into the menu item. Nested elements should only perform one action. Adding multiple actions can break keyboard navigation and screen reader functionality.

[More on required accessibility guidelines](/m3/pages/menus/accessibility/)


![1 diagram and 1 menu showing icons in each item’s leading slot.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmhllqs3p-12-alt.png?alt=media&token=10ba86ca-a5f2-4c70-8f5d-29f4c5a33dfa)
*Reserve the use of slots for use cases that maintain the menu’s accessibility and functionality*

## Placement

A menu is positioned relative to the window edge. It typically appears below, next to, or in front of the element that generates it.

If a menu is in a position to be cut off, it should automatically reposition to appear to the left, right, or above the element that generates it.


![6 abstract shapes showing how a menu can extend from the edge of the screen.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmhllv77j-13.png?alt=media&token=284a2b26-be83-4564-845a-e8e8f55b168e)
*Menus can appear around or in front of the element that opened them*

### Submenus

Submenus should open next to the parent menu item without overlapping it.

Submenus are best used on large screens where there's space. [See adaptive guidance](/m3/pages/menus/guidelines#e588ae16-7a76-4bf9-8532-8d931a13ca35) for alternatives on mobile.


![A submenu opens to the right of its parent menu item, and doesn’t cover it. A selected submenu item includes a checkmark and vibrant highlight.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmhlm1f1q-15.png?alt=media&token=57bbf425-3173-4f90-b96c-93de307cf824)
*Position submenus to the side of the parent item*

starNote:Submenus are not currently available on Jetpack Compose

## Adaptive design

### Compact breakpoints

Consider adapting menus into bottom sheets on small screens. They have more space to display additional items and longer labels.


![A bottom sheet shows longer labels and improved readability on a compact window.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmhlm7yx1-16.png?alt=media&token=efd00c45-20e0-4bdd-936c-3a2383306983)
*A bottom sheet can replace a menu on smaller screens*

### Other breakpoints

On medium and expanded windows, menus are most effective as they appear in context with the content. On larger screens, menus can also display more items, and can use submenus to organize complex sets of options.


![A menu with vibrant color on a mid-size screen, with the same elements as a bottom sheet.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmhlmbxni-17.png?alt=media&token=e4e83032-18f6-4c94-bdb8-62007e8524d3)
*On large screens, a menu is often more appropriate than a bottom sheet*

## Behavior

### Appearing

A menu can appear when a person interacts with an element on the page, like a button, text field, filter chip, or highlighted text.

A menu’s position on screen affects where and how it appears. If opened at the top of the screen, it expands downwards to avoid being cropped.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmhln3y77-GM3_Menus_Guidelines%2018_IA_v02.mp4?alt=media&token=a29213c9-b557-45f5-b1e8-e7644b8dbf3c)


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmhln8qym-GM3_Menus_Guidelines%2019_IA_v01.mp4?alt=media&token=45bfd643-e872-488d-a588-c626a0c77cd0)


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmhlna20p-GM3_Menus_Guidelines%2020_IA_v02.mp4?alt=media&token=517b43c3-99e7-4448-9b1a-d0738806d810)


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmhlnd4tn-GM3_Menus_Guidelines%2021_IA_v01.mp4?alt=media&token=c02842fd-822d-427d-adff-6b23be4ceb42)


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmhlnelhq-GM3_Menus_Guidelines%2022_IA_v02.mp4?alt=media&token=f6f6128f-8a69-49f7-be7b-5bf472840dbf)

**Motion**

Menus use an enter and exit transition. This animation creates a relationship between the menu and the element that generates it.

When a menu expands, the trigger element becomes pressed. When an item is selected, a ripple appears on touch.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmhlnmpgd-GM3_Menus_Guidelines%2024_IA_v02.mp4?alt=media&token=cfabbb98-5329-45e0-9125-8bf6ff2bc8e7)

In dense products, such as on desktop, menus can open instantly to reduce motion.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmhlnu8z2-GM3_Menus_Guidelines%2023_IA_v01.mp4?alt=media&token=2e847220-9445-4f63-bad7-927212f9181a)

### Filtering

A menu can include a text field to filter options. This pattern is also known as autocomplete. 

As someone types, the list of menu options filters to show relevant results. This helps people quickly find the right option from a long list. 

Menu items ease into their new position as the menu is filtered.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmhloarxl-GM3_Menus_Guidelines%2025_IA_v01.mp4?alt=media&token=94b90c05-7d4d-4c5a-aa43-0e81ee74becf)

### Scrolling

Menus can scroll when all menu items can’t display at once. In this state, menus show a persistent scrollbar.

Don’t use gaps if a menu scrolls; this is currently unsupported.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmhloe6n0-GM3_Menus_Guidelines%2027_IA_v01.mp4?alt=media&token=03dcc47d-f5b5-4f68-b4c5-393b2beb4d01)

### Selecting

When a menu is opened, the corresponding button or icon button should remain the same visually, with the addition of a pressed state.

This should happen even when opening from a keyboard shortcut.


![The overflow icon remains the same, even after the menu is opened.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmhlohkz8-28.png?alt=media&token=90813acd-a921-473b-8f98-cfc715b51d93)
*Tapping the icon triggers a menu. Choosing a menu option doesn’t change the icon generating the menu.*

### Single- and multi-select menus

Menus can allow either single-select or multi-select actions:

- **Single-select** menus can have one item selected at a time. When a new item is selected, the previously selected item is automatically unselected.
- **Multi-select** menus can have many selected items. They stay open until the person dismisses the menu.

[More on selection accessibility requirements](/m3/pages/menus/accessibility#149778c9-eb42-4a56-8a0b-9932181ac2cd)


![1 menu for dietary options shows a single selection, Vegan. Another menu shows Vegan and Nut-free selections at the same time. ](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmhkpbo8d-30.png?alt=media&token=a9f4da83-0329-4222-b296-68c399154082)
*Menus can be single- or multi-select*

## Focus

When a menu has multiple submenus, focus follows the current hovered or focused submenu. 

**Shape morphing **

As a person moves from one submenu to the next, the corners of the focused submenu become more rounded, while the unfocused submenu becomes less rounded. This adds a dynamic quality to menu interactions.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmhloky2g-GM3_Menus_Guidelines%2031_IA_v01.mp4?alt=media&token=17c40e5a-c95f-441f-9c38-d19756bc8acd)

## Density

On web only, density levels control the spacing between elements. Increasing density decreases the top and bottom padding. [More on layout density](/m3/pages/understanding-layout/density)


![4 menus becoming increasingly dense and compressed.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmhloonkj-32.png?alt=media&token=f60473a4-1b6d-4913-ba1e-002ea51339a2)
*Density of menus from 0 to -3*

## Use cases

People should be able to do the following using assistive technology:

- Navigate to, open, and close a menu
- Navigate between and select menu items

## Interaction & style

Menu items need certain cues to clearly show when they're selected: 

- By default, menu items change shape and color when selected
- The default color contrast is 3:1 between selected and unselected menu items
- It's recommended to include another visual cue, like a checkmark


![A state dropdown menu with the selected item Alaska highlighted in a vibrant color, with a checkmark icon.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmi5oa1gs-01.png?alt=media&token=3091ebcd-8922-4daf-87a4-802975dfd5e7)
*Use multiple visual cues like color, shape, and icons to show that an item is selected*

## Flexibility & slots

Use caution when adding slots to menus:

- Make sure the menu remains accessible
- Elements must follow the rules and interaction patterns of the menu component
- Keep the same menu item padding
- Targets should be 48x48dp or larger

Don't add buttons, switches, or other direct actions into the menu item. Nested elements should only perform one action. Adding multiple actions can break keyboard navigation and screen reader functionality.

[More on slots in menus](/m3/pages/menus/guidelines#8a1684bb-99a5-4a73-91a0-068d0b406127)


![1 diagram and 1 menu showing icons in each item’s leading slot.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmhkskcq8-02-caution.png?alt=media&token=f56e6e61-6119-41d8-920c-26f6a7cdafff)
*Reserve the use of slots for use cases that maintain the menu’s accessibility and functionality*

## Focus

**Initial focus**

When a menu opens, focus should be placed on the first menu item. This allows people using a keyboard or other assistive technologies to begin navigating the menu immediately.

**Exiting a menu**

People expect to exit a menu by:

- Selecting an option
- Tapping **Escape** or outside of the menu 
- Using the system back button

Where focus is placed after closing the menu depends on the app.


![4 common keyboard navigation methods for menus on Android and web.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmhkewf5t-03.png?alt=media&token=374bb47a-9f22-409d-b8e5-f427a1f35c00)
*Keyboard navigation on Android and web:

1. **Tab** to select a menu item
2. **Space** or **Enter** to open a menu
3. **Space** or **Enter** to select a menu item
4. **Escape** to close a menu*

## Keyboard navigation

****
****
****

********

********

****************

| Keys | Actions |
| --- | --- |
| Tab | Focus lands on menu |
| Space or Enter | For closed menus: Opens menu or submenuFor open menus: Selects a menu item |
| Up and Down arrows | For closed menus: Opens menu For open menus: Moves focus to the next item |
| Left and Right arrows | Opens or closes a submenu |
| Letters | Focus moves to the next menu item starting with letter |
| Escape | Closes menu |

## Interactability

Disabled menu items can receive focus but aren't selectable.

Dividers and gaps can't receive focus.


![A disabled menu item “Share” is in focus.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmhkkp9r3-05-do.png?alt=media&token=4dda61ae-eb7f-45b1-b02f-d88aed80dd28)
*Disabled menu items can receive focus*


![A divider with focus.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmhkkqmt3-05-don't.png?alt=media&token=f3178013-9a18-4ac1-9d98-c21a2876ea6d)
*A divider or gap can’t receive focus*

On web, submenus have a **magic triangle** behavior for more intuitive mouse navigation. 

This keeps the submenu open when the mouse moves within a triangular area between the triggering item and the submenu.

[More on submenus](/m3/pages/menus/guidelines#5b3a92ec-a694-4ea7-87ad-0fbf87344cab)


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmhkkuch7-GM3_Menus_Accesibility%2006_IA_v02.mp4?alt=media&token=2bd4e87c-e5a9-461a-8764-a0ee9775916d)

## Labeling elements

Accessibility labels are used with assistive technology devices like screen readers. 

The accessibility label should be the same as the menu item text.

The role is [dependent on platform](/m3/pages/menus/accessibility#9c562e2c-da3a-4212-a2e3-ac91ba450b65).


![A “Preview” menu item has an accessibility label of ”preview”.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmhkl2sea-07.png?alt=media&token=9603d6f5-030f-41e2-84e6-2af9fd0deee5)
*The menu item’s accessibility label aligns with the UI text*

****

****

******
**

****

| Element | A11y label | Role (Web) | Role (Android Views) | Role (Jetpack Compose) |
| --- | --- | --- | --- | --- |
| Menu item text | Preview | Menu item | Generic actionable element | Generic actionable element |

For menu items with text and an icon, the icon’s accessibility label should be marked as **decorative** to avoid redundant verbalizations.


![A menu item icon of an eye next to the word “preview” has a note of “Decorative.”](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmhkkynea-08.png?alt=media&token=3adbfb1f-b0e8-4cd9-8d17-918d2830ddac)
*For menu items with text and an icon, the icon’s accessibility label is **decorative***
