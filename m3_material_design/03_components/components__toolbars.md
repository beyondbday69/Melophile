---
title: "Toolbars"
slug: "components/toolbars"
url: "https://m3.material.io/components/toolbars"
---

# Toolbars

- 

Two expressive variants: **docked toolbar** and **floating toolbar**
- 

Use the vibrant color style for greater emphasis
- 

Can display a wide variety of control types, like buttons, icon buttons, and text fields
- 

Can be paired with FABs to emphasize certain actions
- 

Don’t show at the same time as a navigation bar


![2 variants of toolbars.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0aionfm-01.png?alt=media&token=0f1d71f5-1d22-4820-859d-fd952e995cf9)
*Configurations of floating toolbars*

## Availability & resources

## M3 Expressive update

The **bottom app bar** is no longer recommended and should be replaced with the **docked toolbar**, which functions similarly, but is shorter and has more flexibility. The **floating toolbar** was created for more versatility, greater amounts of actions, and more variety in where it's placed. [More on GM3 Expressive](https://m3.material.io/blog/building-with-m3-expressive)

Variants and naming:

- 

Added **docked toolbar **to replace **bottom app bar**

  - 

Size: Shorter height
  - 

Color: Standard or vibrant
  - 

Flexibility: More layout and element options

- 

Added **floating toolbar** with the following configurations:

  - 

Layout: Horizontal or vertical
  - 

Color: Standard or vibrant
  - 

Flexibility: Can hold many elements and components. Can be paired with FAB.

- 

**Bottom app bar **is still available, but not recommended


![2 examples of toolbar variants.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0aiswog-02.png?alt=media&token=e5523e45-647f-4168-b257-c773be63adf6)
*1. Floating, vibrant color scheme and paired with FAB
2. Docked with embedded primary action instead of FAB*

## Differences from M2

- Color: New color mappings and compatibility with dynamic color
- Elevation: No shadow
- Layout: Container height is taller and the FAB is now contained within the app bar container


![M2 bottom app bar.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0e7liab-2.png?alt=media&token=16c3ad53-7e83-4079-85ad-9b096bbb56fc)
*M2: Bottom app bar had higher elevation of 8dp and didn't contain the FAB*


![M3 bottom app bar.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0e7mh6v-3.png?alt=media&token=9dfb6612-f40c-4d4c-a773-463109db7c5f)
*M3: Bottom app bar has new colors, a taller container, no elevation or shadow, and contains the FAB*

## Variants


![2 variants of toolbars.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fma23ultc-01-3P.png?alt=media&token=f06dc965-479c-428e-b1b5-3c549f018f8e)
*1. Docked toolbar
2. Floating toolbar*

### Baseline variant

The baseline bottom app bar is no longer recommended. It should be replaced with the docked toolbar, which is very similar and more flexible.


![Baseline bottom app bar, which looks like the docked toolbar, but is not recommended.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fma24egim-1-deprecated.png?alt=media&token=1081969c-6b00-4ff2-832a-6c81df9e30eb)
*1. Bottom app bar (not recommended)*

****

| Variant | M3 | M3 Expressive |
| --- | --- | --- |
| Docked toolbar | -- | Available |
| Floating toolbar | -- | Available |
| Bottom app bar | Available | Not recommended.  Use docked toolbar. |

starNote:Implementation differs per platform. On Jetpack Compose, the floating toolbar is a separate component from the docked toolbar and bottom app bar.

## Configurations


![Color configuration of toolbars.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fma24i061-02-3P.png?alt=media&token=ea746f3b-39a6-4cb4-bc49-0b8c2d935f3f)
*1. Standard and vibrant toolbars
2. Vertical floating toolbar
3. Floating toolbar with FAB*

| Category | Configuration | M3 | M3 Expressive |
| --- | --- | --- | --- |
| Color | Standard (default) | Available as bottom app bar | Available |
| Vibrant | -- | Available |  |
| Floating toolbar layout | Horizontal (default) | -- | Available |
| Vertical | -- | Available |  |
| Other elements | With FAB | Available as bottom app bar | Available* |

starNote:*Implementation differs per platform. On Jetpack Compose, floating toolbar with FAB is [fully supported](https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#HorizontalFloatingToolbar(kotlin.Boolean,androidx.compose.ui.Modifier,androidx.compose.material3.FloatingToolbarColors,androidx.compose.foundation.layout.PaddingValues,androidx.compose.material3.FloatingToolbarScrollBehavior,androidx.compose.ui.graphics.Shape,kotlin.Function1,kotlin.Function1,androidx.compose.ui.unit.Dp,androidx.compose.ui.unit.Dp,kotlin.Function1)). On other platforms, each component needs to be added separately.

## Tokens & specs

Browse the component elements, attributes, tokens, and their values. [Jump to baseline bottom app bar specs](/m3/pages/toolbars/specs#ad142675-3e3b-43b8-ba53-12c1f0b7138d)

## Anatomy


![2 elements of a toolbar.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fma24poz2-03-3P.png?alt=media&token=01853411-fd2a-46df-a634-5ba6f213ddaf)
*1. Container
2. Placed components*

### Flexibility & slots

When configuring a toolbar, think of it as a container with several slots. 

Each slot can be a different element. The most common elements are icon buttons, buttons, and text fields.


![A toolbar with 5 slots, conceptual spaces for UI elements, next to each other.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fma25cmf1-04-3P.png?alt=media&token=aab2da97-79dc-4533-a948-f51575cd6e31)
*A toolbar is essentially a container with configurable slots*

## Color

Color values are implemented through design tokens. For design, this means working with color values that correspond with tokens. For implementation, a color value will be a token that references a value. [Learn more about design tokens](/m3/pages/design-tokens/overview)

### Standard


![4 color roles in the standard color scheme  of the floating toolbar in light and dark scheme.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fma25fg6c-05-3P.png?alt=media&token=1575d435-2053-4e77-aef6-f5ec25af2580)
*Standard color schemes and icon button types:

1. Surface container
2. Filled button (Primary, On primary)

3. Toggle tonal button (Secondary container, On secondary container)
4. Standard button (Primary)*

### Vibrant


![4 color roles in the vibrant color scheme of the floating toolbar in light and dark scheme.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fma25hban-06-3P.png?alt=media&token=59ff67bd-7807-47ec-93de-beab552a6360)
*Vibrant color scheme and icon button types:

1. Primary container
2. Filled button (Primary, On primary)
3. Toggle tonal button: (Surface container, On surface)
4. Standard button (On primary container)*

## Measurements

By default all toolbars are 64dp high, center-aligned, have equal padding between items, and have a minimum outside padding of 16dp.

### Docked toolbar


![Default internal padding of a docked toolbar.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fma25j41t-07-3P.png?alt=media&token=64118047-e221-4b16-b995-7dd09d62df2c)
*1. Default margins and padding
2. Margins and padding with leading, middle, and trailing content*


![2 docked toolbars with different margins and alignment.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fma25jqfl-08-3P.png?alt=media&token=69b2890a-a4ee-4057-875c-6ac032fa1fe1)
*Alignment and padding can be configured to create unique layouts:

1. Left and right alignment
2. Center-aligned, 8dp padding between items*

### Floating toolbar


![Diagram noting margin around edge of floating toolbar.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fma25l6kh-09-3P.png?alt=media&token=88fb2262-a466-463c-839f-c5a397cbea75)
*Default padding of floating toolbar*


![Diagram noting layout measurements.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fma25numd-10-3P.png?alt=media&token=65655b16-bb45-4154-904e-98dfa7e77b77)
*Floating toolbar size and padding measurements*


![Diagram noting layout margins.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fma25or07-11-3P.png?alt=media&token=d5e97936-d6ec-4c04-8fb9-7dbfab981bde)
*Floating toolbar margins*

## Bottom app bar (baseline)


![Diagram of bottom app bar indicating the container.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fma25qmnu-12-3P.png?alt=media&token=526e6241-99db-49b5-b795-5a06b9127bfa)
*1. Container*

### Tokens & specs

Bottom app bar tokens are in one token set.

### Color

Color values are implemented through design tokens. For designers, this means working with color values that correspond with tokens. In implementation, a color value will be a token that references a value. [Learn more about design tokens](/m3/pages/design-tokens/overview)


![Diagram of bottom app bar indicating its color mappings.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fma25vwtc-13-3P.png?alt=media&token=f5f362b3-e75c-46cd-b4b5-a38075e10434)
*Bottom app bar color role used for light and dark themes:

1. 

Surface container*

### Measurements


![Diagram showing layout values and paddings for bottom app bar.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fma25wn0p-14-3P.png?alt=media&token=6aeb9904-fefe-4dda-919b-3e0a6a46aa0b)
*Bottom app bar padding and size measurements*

### Common layouts


![Side by side view of bottom app bars in different configurations.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fma25xkg5-15-3P.png?alt=media&token=2b89e735-e35e-408d-8c57-263f9a37339a)
*1. Icon buttons and FAB
2. Icon buttons and no FAB*


![5 toolbars of various colors, elements, and actions.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm7xi1w6d-01.png?alt=media&token=c58c89f5-5f11-410e-83e9-527495d6a51c)
*Toolbars can be used for a wide variety of use cases*

## Usage

Use a toolbar to provide actions related to the current page. 

Toolbars can contain many actions and can scale to show more actions in larger windows.


![Vibrant toolbar at bottom of mobile screen.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0akotbi-02.png?alt=media&token=7c57efcb-0ced-4383-923c-2bb9b0d8bb25)
*A toolbar provides actions related to the current page*

There are two variants of toolbars:

- 

**Docked toolbar** 
Spans the full width of the window. It’s best used for global actions that remain the same across multiple pages.

- 

**Floating toolbar** 
Floats above the body content. It’s best used for contextual actions relevant to the body content or the specific page.

The baseline **bottom app bar **is no longer recommended, but is still supported.


![Docked toolbar example.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0akx0bq-03.png?alt=media&token=2ddeb9e7-904c-48c4-82d2-8eb2df8b511f)
*Docked toolbar shows global controls*


![Floating toolbar example.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0akxxgm-04.png?alt=media&token=d1f96ec5-e3c6-4936-a0ff-9456fabe38b1)
*Floating toolbar show controls relevant to the current page*

When actions don’t fit in a toolbar, add a menu.


![Toolbar showing local navigation.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm2ytl4us-05.png?alt=media&token=3beb745d-8031-460f-b3b5-400d481b0f7d)
*Toolbar actions can open a menu*

There are two color configurations:

- **Standard** 
A low-emphasis color scheme best used for focusing attention on the body content.

- **Vibrant** 
A high-emphasis color scheme that draws attention to the controls. It can also indicate a temporary change in the page behavior, such as entering edit mode.

Consider using alternative color roles to create greater or lesser emphasis depending on the needs of the app. Experiment with different color roles to achieve different effects.


![Toolbar with low-emphasis controls.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0al28lo-06.png?alt=media&token=1f6d5d66-1407-4636-9e31-635eec6aac7b)
*Use the standard color scheme to draw focus to content outside the toolbar*


![Toolbar with high-emphasis controls.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0al3avf-07.png?alt=media&token=c786ee9c-8199-4d30-99d7-ed8b8d659cb2)
*Use the vibrant color scheme to emphasize controls or actions*

### Toolbars & navigation bars

The toolbar and navigation bar are both placed at the bottom of the window, so should **not** be shown at the same time. Show the navigation bar on primary pages, and toolbars on subsequent pages with actions.


![A navigation bar shown on the main email Inbox page, and a toolbar shown when reading the email.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0al4vpg-08.png?alt=media&token=006079a0-0c27-40f7-a3c6-72c86706ebe5)
*1. Navigation bar on a primary page

2. Toolbar on a secondary page with contextual actions*

Floating toolbars can be used as tabs between related subsequent pages in the product hierarchy. 

This helps group similar pages together, and shows that the selection affects the body content underneath.


![Floating toolbar with secondary navigation labels.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0alklw0-09.png?alt=media&token=864f5358-84b4-4b4e-b438-9a58fae853f0)
*Keep navigation distinct, and use a toolbar to display local navigation on a specific page*

Consider the existing app hierarchy when using a toolbar for local navigation. 

Avoid redundant or confusing navigation combinations in the same view.


![Floating toolbar with secondary navigation labels displaying above a bottom navigation bar.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0aln2r9-10.png?alt=media&token=f547cd2c-f7aa-4780-a701-89480ad92506)
*Don’t show a navigation bar and a toolbar with navigation controls at the same time*

## Anatomy


![Diagram of toolbar layouts.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm7xiiwbe-11.png?alt=media&token=df3567cb-3de9-4130-87b2-d6496e2b50d6)
*1. Container

2. Elements*

### Container

The docked toolbar’s container spans the full width of the window. 

Avoid applying rounded corners to the container. This can imply the container expands or changes upon interaction.


![Docked toolbar with square corners.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0als248-12.png?alt=media&token=5de2e6dd-e260-460d-8b24-ec4616d068f3)
*Use straight corners for docked toolbars*


![Docked toolbar with rounded corners.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0alr7n0-13.png?alt=media&token=67d0f0c3-77bf-413d-b028-a3704d62ddde)
*Avoid modifying the container shape*

As long as there's a minimum of 16dp padding on the leading and trailing edge, arrange controls inside however you see fit. The 32dp padding between items is just the default. 

All elements need a minimum 48x48dp target area to be accessible.

Be cautious of including too many controls as it can be overwhelming.


![Docked toolbar with too many controls.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0altzpa-22.png?alt=media&token=982f6de8-05e5-4918-8395-3172ba98a767)
*Don’t overwhelm people with too many controls*

The floating toolbar’s container should be fully visible on screen. If more actions are needed, use an overflow menu.


![Floating toolbar with overflow menu icon.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0alvr9d-14.png?alt=media&token=43b6b0e0-3785-4b32-b814-9334d1df3604)
*Choose the most essential actions to show on screen by default*


![Floating toolbar that expands off edge of screen.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0alwqwe-15.png?alt=media&token=34941365-0b02-426e-98aa-5a7182146ef3)
*Floating toolbars shouldn’t exceed the edge of the window or pane*

#### Elevation

Floating toolbars have elevation by default. 

If the content beneath the toolbar is visually distinct, elevation can be removed.


![Vibrant floating toolbar that's easy to see in front of a neutral text background.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm7xiazxd-17.png?alt=media&token=1fb4d961-4c77-46d8-8e1a-bacee6d1344e)
*The elevation on floating toolbars can be removed if on a visually distinct background*

### Flexibility & slots

When configuring a toolbar, think of it as a container with several slots.

These slots can be populated by buttons, icon buttons, images, text fields, or any kind of custom component.

Icon buttons provide an even hierarchy of controls. Mixing in a filled icon button can help add emphasis to a single action.


![5 toolbars with slots, and various combinations of buttons, icon buttons, filled icon buttons, and text fields.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0aly7tp-16.png?alt=media&token=eef76f70-653b-4316-a4e5-b4c3efe04ec9)
*Toolbars are made of slots that can contain many kinds of actions*

Visually emphasizing a single action more than others is an effective way to create hierarchy and guide people to controls they use most often. Avoid emphasizing more than one action at a time.

Some common ways to add emphasis to toolbar actions include:

- Use different icon button color styles, such as filled, tonal, and standard
- Customize the color roles of a single action, such as a primary or secondary palette
- Use wide and narrow icon buttons 

- Pair the toolbar with a FAB


![2 floating toolbars, 1 with a filled action button and 1 paired with a FAB.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0am06ae-18.png?alt=media&token=b78bd5dd-7b9b-4026-8782-bd7299a30180)
*Two different ways to create a high emphasis action in toolbars*


![Floating toolbar with primary action and FAB.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0am2tq8-19.png?alt=media&token=ade3aae3-04a8-48c5-ad34-a44e19f82385)
*Don’t emphasize multiple buttons with bold, primary colors, such as a button and FAB together. Emphasize one action at a time.*


![Floating toolbar with different control designs.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0am3wil-20.png?alt=media&token=fa9517c1-99a1-41ec-bb2a-3add03657f0a)
*Avoid mixing too many different controls in the same toolbar. A consistent control design keeps things clear.*

Avoid using square icon buttons in floating toolbars. Their square shape conflicts with the fully-rounded shape of the floating toolbar container.

Square buttons can be used in the docked toolbar.


![A floating toolbar, which is rounded, with squared icon buttons inside.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm7xigk3q-22.png?alt=media&token=834f844e-bf4a-482b-a55c-58b8e2a5dc96)
*Don’t use square filled icon buttons in floating toolbars*

### Floating toolbar with FAB

A FAB can be placed next to a floating toolbar to present one high-priority action alongside a unified set of toolbar actions.

Use a FAB for the highest-priority action in the view, or to complement the controls.


![3 toolbars paired with FABs.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0am631l-21.png?alt=media&token=ea7acd11-1ec9-4fee-b237-adf2c9636730)
*Floating toolbars can be paired with FABs*

## Position & orientation

Only place docked toolbars at the bottom of the window. 

If using other bottom-aligned elements, such as a navigation bar, don't use a docked toolbar.


![Docked toolbar on mobile.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0ama5z3-22.png?alt=media&token=fcca3ccd-3347-4482-9732-ed9ce3b21b72)
*Docked toolbars are always at the bottom of the window*

Floating toolbars can be horizontal or vertical. 

Horizontal toolbars should have a minimum 16dp margin from the edge of the window.


![Floating toolbar on mobile.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0amca2n-23.png?alt=media&token=f1c23645-3eec-4330-8159-b7add81a9481)
*Horizontal floating toolbars should be at least 16dp from the edge of the window*

In larger breakpoints, floating toolbars can be vertical and placed on either side of the screen.

Vertical toolbars should have a minimum 24dp margin.


![Vertical floating toolbar with 24dp margin.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm7ximrxw-26.png?alt=media&token=e501bab9-334b-4454-ade0-c6f31b83ff75)
*Maintain at least a 24dp margin for vertical toolbars*

To keep vertical toolbars compact, don’t use wide icon buttons. 

Use narrow or default icon buttons instead.


![Toolbar showing local navigation.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0amgeqc-24.png?alt=media&token=aad0bae2-9282-40cc-a472-3025c8addcdb)
*Using wide buttons with vertical toolbars can unnecessarily widen toolbar containers and hide other UI elements*

Vertical toolbars should be positioned opposite the navigation rail to balance out the screen and keep actions easy to access.

When showing a navigation rail and vertical floating toolbar at once, use the centered configuration of the navigation rail.


![Large screen UI showing both a navigation rail and vertical floating toolbar.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0amkwhr-TBD.png?alt=media&token=596f104c-915a-47fb-a863-6bf778a8331a)
*When a nav rail is visible, the floating toolbar should be vertical on the opposite edge of the window*

## Adaptive design

Adaptive design allows an interface to respond or change based on context, such as the user, device, and usage. [More on adaptive design](/m3/pages/layout-overview/adaptive-design)

### Resizing

#### Docked

The docked toolbar should always span 100% of the screen width.

In compact breakpoints, elements in the toolbar should be evenly spaced.

In medium breakpoints and larger, adjust the padding between controls to create a comfortable layout. This can be achieved by: 

- 

Centering all elements
- 

Customizing to center a key action, and aligning other elements to the edges


![Docked toolbar with evenly spaced elements.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fma2vff01-29.png?alt=media&token=4ac502e5-7602-445e-834d-286226583b65)
*Docked toolbar items should be evenly spaced in compact windows*


![Docked toolbar with centered elements.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fma6y2uq6-30.png?alt=media&token=deab82d9-57fa-4c3f-a2da-b60dc77a01b4)
*In medium breakpoints and larger, create a spacious layout by centering all elements*


![Docked toolbar with central action and some elements pushed to the edge.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fma6y2g0q-31.png?alt=media&token=06384f75-0a0a-43ce-9b0a-f8af3c53130e)
*Align controls to the edge of the screen to make them easier to reach on tablets, and to better highlight a primary action in the middle*

On web and large screens, the docked toolbar can be rounded. Dividers can be used to organize large amounts of items. Only shrink the height and use extra small buttons if vertical space is limited.


![Docked toolbar with 15 actions for text editing on large screens, organized with dividers.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmbv5jv5l-33%20Old.png?alt=media&token=ac965689-4c43-4519-9ae8-bd2d100f65c4)
*On web and other large screens, docked toolbars can be rounded and placed in different parts of the page*

#### Floating

The container should only be as big as needed to hold the items inside before reaching the 16dp margin.

If there’s not enough space for all items, put them in an overflow menu in the trailing slot. As the breakpoint expands, more actions can be revealed.

The floating toolbar width can also be capped to keep it smaller and hide more elements.


![Floating toolbar in compact window with excess padding.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fma1lkstz-32.png?alt=media&token=7c230191-dce6-498c-b7c6-e7a680fec810)
*Don’t add extra space to a toolbar beyond its necessary items*


![Floating toolbar in expanded window class.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fma1lm7ro-33%20(1).png?alt=media&token=415f33b0-19f9-4b55-87e1-d94689e61122)
*At larger screen sizes, the container can display more controls before hitting the 16dp margin*

Vertical toolbars aren’t recommended for compact windows.

They take up a significant area of the screen and may feel visually overwhelming, especially on screens with complex layouts.

Only use them when the screen is simple or when the toolbar has a few controls.


![Vertical toolbar in a compact window.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm7xmqa2l-34.png?alt=media&token=b12e1381-1bc0-41e6-8898-1603c9aadc57)
*Vertical toolbars can cover important content in compact windows*

### Presentation

In larger breakpoints, floating toolbars can be aligned to opposite edges of the screen so they're easy to reach and group similar actions. For example, consider placing the undo and redo actions in one toolbar, and editing controls like highlight, erase, and select in another. Stylistic differences can help emphasize each toolbar’s purpose and clarify hierarchy.


![2 toolbars, each with distinct stylistic treatment and actions.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0amsqw6-29.png?alt=media&token=a2c29c55-b09f-40a1-9a0d-48d3316f5811)
*Multiple toolbars with different stylistic treatments can create hierarchy and distinguish different kinds of actions*

Don’t use multiple toolbars in compact windows. There typically isn’t enough room on screen. 

Instead, use one toolbar for all actions.


![Multiple toolbars in a compact window.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm7xmw01r-36.png?alt=media&token=488e8d77-d650-48d5-abed-a727d821d65c)
*Avoid using multiple toolbars in smaller windows*

Actions at the trailing edge of the toolbar can collapse into an overflow menu at smaller breakpoints, and become visible again at larger sizes.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fma1lvic4-GM3_Adaptive_ToolBar_Guidelines_37_IA_V01.mp4?alt=media&token=89841766-ea1d-4db4-8860-6068b771961d)

### Right-to-left languages

In right-to-left (RTL) languages, mirror individual items that need it, like icons and text direction. If the order of actions is important, flip the order of the actions as well.


![Next button is on trailing edge for a LTR language.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm7xmygdb-37.png?alt=media&token=9f00e9fc-1b60-4dac-8974-353a25084a2b)
*In LTR languages, the **Next** button is intentionally placed on the trailing (right) edge*


![Next button is now on the trailing edge, at left, for RTL language.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm7xmz6n4-38.png?alt=media&token=0be61ff8-5427-4330-9ddf-e59303f1c9b5)
*In RTL languages, reverse the order so **Next** remains on the trailing edge when flipped, now on the left. Text is not translated to illustrate mirroring.*

## Behavior

### Scrolling

Docked toolbars can either remain on the screen during scroll, or animate offscreen.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm6kw6oe9-Toolbar%20GUidelines%2031a%20-%20RENDER%202x.mp4?alt=media&token=7cbad4e7-a289-4342-846a-63f7b8054dff)

Floating toolbars can remain on the screen, animate offscreen, or collapse into a single, high-emphasis action on scroll.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm6kwfvv5-Toolbar%20Guidelines%2032a%20-%20RENDER.mp4?alt=media&token=8f26be69-b22b-46d1-900d-433e72180126)

On Jetpack Compose, the floating toolbar can collapse to a FAB or key action on scroll.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm6kwpm0p-Toolbar%20Guidelines%2033a-%20RENDER%202x.mp4?alt=media&token=a55b9c88-559c-46cd-af7b-313b49a5de04)

Don't collapse actions and scroll at the same time.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm9ipmok3-GM3_Expressive_Toolbar_Guidelines_42_IA_v05.mp4?alt=media&token=aed3f7cb-b269-4eb8-8e39-5ae379f2dc34)

starNote:This is a rapidly changing space. Guidelines are primarily intended for designers at this time. Find what’s implemented in code in the [design kit](https://www.figma.com/community/file/1035203688168086460).

Extended reality (XR) interfaces have special design requirements, like showing apps in 3D space. Material has an XR-specific toolbar with custom specs and guidance. Read [XR developer documentation](https://developer.android.com/design/ui/xr/guides/foundations) for more details.

## Variants

There is one toolbar orbiter. It closely aligns with the floating toolbar. It can be configured to be horizontal or vertical.


![Horizontal and vertical toolbar orbiters.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmffkg5cl-01-3P.png?alt=media&token=4506fb4c-5c92-4f21-a204-384287d534db)
*1. Horizontal floating toolbar
2. Vertical floating toolbar*

## Anatomy


![2 elements of a toolbar orbiter: container and placed components. ](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmffkgmzs-02-3P.png?alt=media&token=0f036aab-1db4-4af8-b665-bd1974994145)
*1. Container
2. Placed components*

## Color & elevation

XR uses color to communicate the elevation of UI elements and orbiters. With [spatial elevation](https://developer.android.com/design/ui/xr/guides/spatial-ui#spatial-elevation), the toolbar displays above the spatial panel on the Z-axis. Elevated toolbars can use any of these color options:


![4 versions of toolbar elevation color strategy.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmffkgx1z-03-3P.png?alt=media&token=44dbf8f1-e123-460f-932d-23d39029d139)
*1. Surface container
2. Surface container high
3. Surface container highest
4. Tertiary container*

## Measurements


![Diagram with measurements for toolbar orbiters.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmffkhel2-04-3P.png?alt=media&token=e27346c4-675e-4edb-9a67-ff0b446c5c3c)
*Measurements for toolbar orbiters*


![Diagram with 12dp padding for toolbar orbiters.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmffkhmg5-05-3P.png?alt=media&token=b2e3fbe8-b01f-483b-a988-3b2ab863b456)
*Padding for toolbar orbiters*

## Usage

A toolbar can appear in an orbiter for a more immersive experience. Currently, this spatial capability is only available in full space. In home space, use a regular toolbar on the same plane as the body content to mimic a 2D experience.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmf2kzdta-toolbars-xr-06.mp4?alt=media&token=44193d29-a985-43ec-8857-f6b25485b8da)

## Behavior

### Local context (recommended)

When placed in local context, the toolbar orbiter is centered at the bottom of the spatial panel it controls. 

It repositions in response to layout or content changes.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmf2kzsyd-toolbars-xr-07.mp4?alt=media&token=c7cb300f-f88b-442b-9b35-0d5bc70bd535)

### Global context

When placed in global context, the toolbar orbiter is centered at the bottom of the app. 

It stays anchored to the app during layout or content changes.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmf2l0gt9-toolbars-xr-08.mp4?alt=media&token=08bd73ef-0eca-463e-9178-e1cfd526586d)

### Expand & collapse

Toolbar orbiters with more than five items can expand and collapse to reveal or hide additional content.

When a toolbar orbiter expands, it stays within the bounds of the adjacent spatial panel.

Alternatively, more complex toolbars can be split into multiple toolbars.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmf2l10hs-toolbars-xr-09.mp4?alt=media&token=0c8faeb2-836f-4a21-9b99-affd1c72164f)

### Additional toolbars

In some cases, full space apps can have more than one toolbar orbiter, placed in either global or local context.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmf2l1cvh-toolbars-xr-10.mp4?alt=media&token=3de90a6b-8563-4495-a6dc-e6ed921a86ec)

## Placement

### Offset & inset positioning

In full space, a toolbar orbiter can be positioned adjacent to or overlap a spatial panel.


![Toolbar orbiter with offset positioning.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmemeu04x-11-do.png?alt=media&token=49f94b58-722e-4afc-83df-a448962fdd24)
*The recommended toolbar orbiter position from the spatial panel is: 

- Offset by 20dp or 
- Inset by 12dp*


![Toolbar orbiter with inset positioning above 12dp that obstructs content on the spatial panel. ](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmemevhx8-12-don't.png?alt=media&token=449795aa-4920-46a8-8dfe-a4fe43ed40cd)
*To prevent content obstruction, don’t overlap the toolbar orbiter and spatial panel above a 12dp inset threshold*

### Horizontal alignment


![A horizontal toolbar orbiter placed within the bounds of its spatial panel.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmemey1hz-13-do.png?alt=media&token=c21a511f-8efe-4028-bdcd-c83163e60d48)
*Always align the toolbar orbiter within the horizontal bounds of nearby spatial panels*


![A horizontal toolbar orbiter that extends beyond the width of its spatial panel.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmemez2e1-14-don't.png?alt=media&token=fd153363-aee4-4dc7-86ba-1d85e3ddbb98)
*The toolbar orbiter shouldn’t exceed the width of adjacent spatial panels*

### Vertical alignment


![A vertical toolbar orbiter placed within the bounds of its spatial panel.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmemf0q6h-15-do.png?alt=media&token=8f923eb4-4477-4fcb-a60f-13b7f42bbe4d)
*Always align the toolbar orbiter within the vertical bounds of nearby spatial panels*


![A vertical toolbar orbiter that extends beyond the height of its spatial panel.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmemfyo8f-16-don't.png?alt=media&token=d9e5cd69-d3b6-4238-94fa-5d7dbd359c2c)
*The toolbar orbiter shouldn’t exceed the height of adjacent spatial panels*

### Spatial panel alignment

By default, toolbar orbiters are center-aligned to the spatial panel. Their placement can be adjusted to accommodate specific user needs, such as improved ergonomics or [right-to-left (RTL) languages](/m3/pages/understanding-layout/bidirectionality-rtl).


![Toolbar orbiter alignment options in relation to spatial panels.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmemfvpq3-17.png?alt=media&token=06472c3c-8a05-4dab-9e63-b4dac374c622)
*Depending on the configuration (horizontal or vertical) of the toolbar orbiter, it can align to the center, left, right, top, or bottom of a spatial panel*

Avoid placing a vertical toolbar orbiter between spatial panels. 

This negatively affects the interface structure and can make it difficult to find.


![A vertical toolbar orbiter is placed between 2 spatial panels.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmemfx943-18-don't.png?alt=media&token=ce865771-93db-4ded-ac2c-f0ca46254201)
*Don't place a vertical toolbar orbiter between spatial panels*

## Accessibility considerations

[XR accessibility](https://developer.android.com/design/ui/xr/guides/get-started#make-app) guidelines are still evolving. XR toolbars should follow applicable Material [toolbar accessibility standards](/m3/pages/toolbars/accessibility).

## Use cases

People should be able to the following with assistive technology:

- Navigate and activate any actions in the toolbar

- Select a destination from a menu

- Activate a back button

- Maintain access to toolbar controls when the content is scrolled or collapsed

## Interaction & style

The toolbar has no interactions by default. All interactions are with the elements placed inside.

**Touch**

- When tapping on an icon button in the toolbar, a touch ripple appears, indicating interaction feedback.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fma798ukw-GM3_Toolbar_Accessibility_1_IA_v02.mp4?alt=media&token=936128aa-9795-48b3-8b10-5b0f5cbd1317)

**Cursor**

- When hovered, the hover state provides a visual cue to the user that the element is interactive. 

- When clicked (in both active and inactive states), a ripple appears, showing the user feedback.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fma7996zt-GM3_Toolbar_Accessibility_2_IA_v02.mp4?alt=media&token=2d872434-fcb6-426b-897e-73be777ad489)

### Initial focus

Focus lands on the first interactive element. 

Use **Tab** to navigate through all other actions.


![Navigating the top app bar using arrow or tab on a keyboard.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0anu1gy-3.png?alt=media&token=6ce1a9cb-f8d9-4e2e-a0b5-5c3d56691c9a)
*Use **Tab** to navigate through interactive elements*


![Activating actions in the top app bar using space or enter on a keyboard.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0anv4t7-4.png?alt=media&token=83f51ce3-ac26-4425-a6ff-a97026cb1e74)
*Use **Space** or **Enter** to activate actions*

## Keyboard navigation

| Keys | Actions |
| --- | --- |
| Tab or Arrows | Navigate between interactive elements |
| Space or Enter | Activate the focused element |

### Labeling elements

On web, the toolbar container should have the **toolbar** role. 

On mobile, it can be a generic container. 

All actions inside the toolbar should follow their respective accessibility guidelines.


![A toolbar on web, with a “toolbar” role label.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0anynmv-5.png?alt=media&token=f53e2da8-9f81-47fa-9786-b445bc596dc8)
*On web, use the **toolbar** role*
