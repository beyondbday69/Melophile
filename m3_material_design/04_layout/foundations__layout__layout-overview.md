---
title: "Overview"
slug: "foundations/layout/layout-overview"
url: "https://m3.material.io/foundations/layout/layout-overview"
---

# Overview

> Layout is the visual and strategic arrangement of elements on a screen

- 

Use layout to organize all elements in a screen, signal hierarchy, and draw attention to key actions
- 

Adapt layouts to compact, medium, expanded, large, and extra-large breakpoints (previously window size classes)
- 

Build from an established [canonical layout example](/m3/pages/canonical-examples)
- 

Design for [bidirectionality](/m3/pages/bidirectionality-rtl) to support both left-to-right (LTR) and right-to-left (RTL) languages
- 

Apply consistent arrangement, sizing, and spacing to create a functional layout structure
- 

Material layout guidance is implemented on Android and applies to web


![7 elements of layout.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmp3ukjvm-01.png?alt=media&token=6f4fdd25-559d-4ed3-964a-ffb0c5f7b5f7)
*1. 

Column
2. 

Fold
3. 

Margin
4. 

Bar
5. 

Drag handle
6. 

Pane
7. 

Rail*

## Availability & resources

****

****

****

[](https://www.figma.com/community/file/1035203688168086460)

[](/m3/pages/spacing/overview)

[](https://developer.android.com/develop/ui/compose/layouts/adaptive/canonical-layouts)

[](https://developer.android.com/reference/kotlin/androidx/compose/ui/layout/Ruler)

[](https://github.com/android/user-interface-samples/tree/main/CanonicalLayouts)

[](https://developer.android.com/guide/navigation/navigation-3/migration-guide)

| Type | Resource | Status |
| --- | --- | --- |
| Design | M3 Design Kit (Figma) | Available |
| Spacing system & tokens | Available |  |
| Implementation | Jetpack Compose: Canonical layouts | Available |
| Jetpack Compose: Rulers | Available |  |
| Android Views (MDC-Android): Canonical layouts | Available |  |
| Jetpack Compose: Navigation3 | Available |  |

## What's new

When creating new layouts, use the layout scaffold, start from a canonical layout example, and ensure layouts scale across breakpoints.

##### **May 2026** 

Layout structure and design:

- 

Introduced layout scaffold, to create adaptive layouts efficiently
- 

New adaptive guidelines for mobile, desktop, and spatial devices
- 

Updated canonical layout examples
- 

[Spacing system](/m3/pages/spacing/overview)

Naming:

- 

Window size classes renamed to breakpoints
- 

Responsive layout renamed to [adaptive design](/m3/pages/layout-overview/adaptive-design)


![Compact and expanded layouts for differently sized screens.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmp3uxdvt-02.png?alt=media&token=a4cf0742-9a81-4697-8963-46b936eeb5f3)
*The Material layout scaffold enables layouts to adapt across different screen sizes*

## Layout terms

- 

**Adaptive design**: Techniques that allow an interface to dynamically respond to contexts like user preferences, device type, state, and breakpoints
- 

**Bars**: Can frame the page to help people navigate through a product, and typically house the app bar and bottom navigation bar
- 

**Bidirectionality**: A writing system that displays text and content from right-to-left (RTL) 
- 

**Breakpoints**: Opinionated window sizes where a layout changes to match available space, device conventions, and ergonomics (previously window size classes)
- 

**Column**: One or more vertical blocks of content within a pane
- 

**Drag handle**: The component that resizes panes
- 

**Fold**: A flexible area of the screen or a hinge that separates two displays on foldable devices
- 

**Gap**: The space between components or elements within a container
- 

**Margin**: The space between the edge of the screen and any elements inside of it
- 

**Multi-window mode**: Enables multiple apps to share the same screen simultaneously
- 

**Pane**: A layout container that houses other components and elements within a single app. A pane can be fixed, flexible, floating, or semi-permanent.
- 

**Rails**: The perimeter space surrounding panes that holds key elements such as navigation rails, toolbars, and pane control
- 

**Right-to-left (RTL) language**: Languages written and read right-to-left, such as Arabic, Hebrew, and Farsi, used by [over 2 billion people](https://www.w3.org/International/questions/qa-scripts.en.html)
- 

**Rulers**: An opinionated set of global alignment lines that help organize building blocks in a layout
- 

**Safety region**: Zones reserved for system UI elements outside the application space, such as status bar or gesture bar
- 

**Scaffold**: A fundamental UI design structure that provides a standard platform for assembling key screen components
- 

**Spacer**: The space between two panes on a foldable device

## Parts of layout

### Windows

A window frames and contains an app or product.

Many systems support multi-window views, which display multiple apps at once.

[Multi-window support guide for Android](https://developer.android.com/develop/ui/compose/layouts/adaptive/support-multi-window-mode)


![Stacked windows with a single taskbar underneath.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmp3yuwss-01.png?alt=media&token=1040af33-3239-4b03-bd3c-4196c3456b87)
*Two windows can be shown at once with a taskbar underneath*

On desktop, windows can be resized and moved around freely. They should adapt to various screen sizes.

[More on adaptive design](/m3/pages/layout-overview/adaptive-design)


![2 overlapping desktop windows.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmp3z1fl4-02.png?alt=media&token=431b13e9-b426-4874-8c2c-2b1e20882b6a)
*Windows can be moved around, resized, and adapt to different screen sizes*

### Grids

The layout grid is the foundation for every layout. It provides a structural framework for organizing components, content, and actions.

Use the grid to:

- 

Group related information in columns
- 

Apply spacing consistently
- 

Create focal points for primary actions
- 

Align building blocks like bars, rails, and panes

[More on grids](/m3/pages/grids-spacing/grids)


![Side-by-side compact and expanded windows with different grid spacing.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmp3z8y9f-03.png?alt=media&token=fc4a96db-308a-40f6-984b-8d70ff3451fe)
*Column count, width, and spacing dynamically adjust to different breakpoints*

## Layout scaffold

### Bars

Bars help people navigate through a product. Use bars to:

- 

Frame the main content
- 

Contain an app bar or navigation bar
- 

Span one or multiple panes  

[More on bars](/m3/pages/scaffold/bars)


![A photo app with an app bar below the safety region.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmp45sv4u-04.png?alt=media&token=b9c750d7-b9de-450e-809c-c6e6c0081531)
*1. App bars are placed at the top of the screen to help people navigate by providing a description of the screen and 1–2 essential actions, like search or back navigation*

### Rails

Rails are the next level in layout after bars, filling the perimeter space surrounding panes, or floating above them. They contain key elements such as navigation rails, toolbars, chat inputs, FABs, and other primary controls.

[More on rails](/m3/pages/scaffold/rails)


![Mobile layout with a toolbar, and a desktop layout with a start-aligned navigation rail.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmp45u0ar-05.png?alt=media&token=dcbfcab8-3ece-4159-90ea-8dd3456bd01b)
*1. 

On mobile, the rail region can contain a toolbar
2. 

On desktop, the rail region can contain the navigation rail*

### Panes

Just like panes of glass that make up a window in the real world, panes in Material make up most of the layout in a window.

All content must be in a pane. A layout can contain 1–3 panes of various widths, which adapt dynamically to the breakpoint (formerly window size class) and the person’s language setting. For right-to-left (RTL) languages, navigation components will be on the right.

People can navigate to or between panes. Presenting multiple panes at once can make a product more efficient and easier to use.

[More on panes](/m3/pages/scaffold/panes/)


![2-pane layout on a tablet.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmp46vx4x-07.png?alt=media&token=b1eae581-b735-4691-921c-4737a95d7ac8)
*1. 

First pane
2. 

Second pane*

#### Containment

On most devices, panes can blend in with the background. This is called implicit grouping, and helps show relationships between panes.

Explicit grouping uses distinct colors or outlines to visually delineate content.

[More on spacing to group content](/m3/pages/grids-spacing/spacing#e7e6d1ac-031a-4757-afcf-b223f23654ea)


![2 panes in an email app, with the same color as the background.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmp46z833-08.png?alt=media&token=9423401d-c17a-496d-bf1c-1155b8520bb9)
*In multiple-pane layouts, use color to show emphasis and close spacing to group related content*

In spatial environments, panes use a container color to separate them from the passthrough or virtual environment.


![2-pane layout in a spatialized environment, with a virtual reality background.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmp472v90-08.png?alt=media&token=a5d93025-d8c9-422b-84ac-92cb190ea868)
*Use contrast between panes and the background to create a spatial effect in XR*

### Drag handles

Drag handles can be used to resize panes in a layout. They can:

- 

Adjust the width of flexible panes
- 

Fully collapse and expand fixed panes to quickly switch between a single and two-pane layout


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmp4771l5-GM3_Applaying%20Layout_Understanding_Part_11_IA_v01.mp4?alt=media&token=3f0901d9-f02d-417c-b4cc-08937a5710ef)

### Rulers

Rulers are a set of global alignment lines. They help to align elements across all layers of the layout.

[How to implement rulers in Compose](https://developer.android.com/reference/kotlin/androidx/compose/ui/layout/Ruler)


![A 2-pane email in box, with rulers.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmp47dohx-10.png?alt=media&token=dc25aee8-0729-4814-b09f-8ec2c5d16c30)
*Rulers ensure global alignment across a product, keeping margins and placement consistent*

## What’s adaptive design?

Adaptive design is a set of techniques to change an interface to fit different contexts. While responsive design scales a single layout to fit any screen, adaptive design customizes a product to optimize the experience on each device.

Designing adaptive experiences goes beyond customizable properties like color, typography, and shape. The structure, individual components, and entire layouts can adapt based on:

- 

People: Individual preferences and settings
- 

Devices: Watch, phone, foldable, tablet, desktop, or XR device
- 

Usage: Screens dynamically change as a person resizes windows, changes orientation, or switches device

## Designing adaptive experiences

Layouts must be versatile, designed to adapt fluidly across three primary experience types: **mobile**, **desktop**, and **spatial**. Start with mobile and make sure your product's layout and components can scale and adapt seamlessly all the way up to spatial environments. 

While each experience has different primary input methods, designs should be built with all inputs in mind—touch, pointer, and physical keyboard—since users may use your product in a desktop environment regardless of their device type.

### Mobile

Mobile experiences include phones, foldables, and tablets.

On mobile, an app can be shown in several window modes:

- 

Full-screen: The app takes up the entire screen, the default for mobile
- 

Split-screen: Two or more apps share the screen simultaneously, common on tablets and foldables
- 

Bubbles: Floating windows that let people multitask without leaving their current context


![A mobile app in full screen mode.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmp3w1k0w-01.png?alt=media&token=9c6f0a31-d9d0-4bb1-8c6d-d4e17582281f)
*Mobile layouts default to a full-screen window*

### Desktop

Desktop experiences use free-form windows that adapt across breakpoints.

People can use split screens, floating windows, and free-form windows for multi-tasking.

A tablet can convert to a desktop experience when a physical keyboard and mouse are connected. Similarly, Android mobile devices can transition into a desktop-like environment when connected to an external monitor.


![2 desktop layouts adapted to large and medium breakpoints.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmp3w5izv-02.png?alt=media&token=49610536-6345-47a0-a231-7bc5c57d44ad)
*A desktop layout can adjust from three to two columns to fit a medium breakpoint*

### Spatial

Extended reality (XR) experiences use multiple free-form windows within virtually limitless screens. Immersive modes, such as Android XR’s [full space](https://developer.android.com/design/ui/xr/guides/foundations), allow components to be positioned freely in 3D space.

[More on XR design](/m3/pages/xr-design)


![An XR layout inimmersive full space mode.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmp3wl9n4-03.png?alt=media&token=71b00525-bfa6-4796-9f01-8fc05e966f15)
*In an XR full space layout, a navigation rail can become an orbiter, and float to the side of the main pane*

## Adaptive layouts

The Material 3 adaptive system uses panes and breakpoints to organize content into adaptive layouts.

Panes are the building blocks of layout; a pane is a single destination in the product. For example, in a messaging app, the list of messages is one pane, and and a specific conversation thread is another.


![Mobile and tablet in portrait layout with 1 pane. Tablet in landscape layout with 2 panes.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmp3wz2eh-04.png?alt=media&token=37210793-f4fe-4412-860b-9a45ad6a45be)
*Panes are the primary segments of a layout, and can change based on context*

As the pane or window resizes—or as someone navigates a product—panes may change size, enter and exit the screen, and reorganize themselves to make the experience more usable or easier to navigate. These patterns are called adaptive strategies. Material has three adaptive strategies that create a cohesive experience across breakpoints: [show and hide](/m3/pages/scaffold/panes#bbe68948-bc05-4f7c-b870-6254439e4fd8), [levitate](/m3/pages/scaffold/panes#96bf71b8-04b8-4fff-97c7-9bc782fbf401), and [reflow](/m3/pages/scaffold/panes#e0a573e9-8c62-4772-8d81-47955ff83196).


![A foldable open screen with 2 co-planar panes displayed side by side.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmp3xrm51-Placeholder.png?alt=media&token=0434aa91-113d-454e-bf02-6029ad242814)
*Co-planar: Panes are displayed side by side*


![A  foldable open screen with a floating pane displayed above other elements. ](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmp3xtdfv-Placeholder%20(1).png?alt=media&token=5825b45f-fde6-46a5-a345-946dcadef428)
*Floating: A pane is displayed above other panes or content, like a dialog*


![A  foldable open screen with a docked pane to the bottom of the screen displayed above other elements. ](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmp3xuwp5-Placeholder%20(Cmd%2BV%20to%20replace).png?alt=media&token=cb3ac17e-eb33-48f7-9297-1bee14918ffe)
*Docked: A pane is displayed above other panes and one of its edges extends beyond one side of the screen, like a bottom sheet*

In Compose, the [Navigation 3](https://developer.android.com/guide/navigation/navigation-3) library allows multiple destinations to be shown on screen at the same time, and enables layouts to adapt seamlessly across window sizes and screens.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmsz6rtgu-Layout%20-%20Adaptive%20Design%20-%20IA%20-%20v01.mp4?alt=media&token=7c47d5ad-bb46-4951-a9c5-a92b431b1375)

### Adapting components

Components can adapt in appearance, placement, and behavior based on factors like:

- 

Where components are placed in relation to their containers, content, and pane boundaries
- 

How components use space
- 

How components enable usage across different device and input types

Most Material components respond using three main strategies: resizing, showing and hiding, and presentation changes.

#### Resizing

Components should resize in response to their content and their placement in a layout.

For example, buttons may scale along with their parent container, or hug their contents and maintain a left or right alignment.


![A card with a button that spans the full width, and a card with a button that’s start-aligned and hugs its contents.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmp3xf0z5-05.png?alt=media&token=e3bbf0fb-d15b-48d1-b2f8-4962085f22e9)
*Buttons can hug their contents or span their containers based on context*

#### Showing & hiding

Components should show and hide information, or collapse and expand to selectively reveal content that best suits the space.

For example, list items may reveal descriptions or other additional information as their parent container scales.


![A list on mobile and on tablet. List items show additional text on tablet.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmp3xgph2-06.png?alt=media&token=78670577-b953-454b-b7c5-4f667f748970)
*List items can reveal more text on a tablet*

#### Presentation changes

Presentation changes include the orientation of elements and changes to specific properties, like color, type, and shape.

Components can also change configurations. For example, when a window size increases, a FAB can change to an extended FAB, and navigation rails can be automatically expanded.


![A large desktop window with an extended FAB. A smaller window shows a standard FAB. ](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmp3xiwiu-07.png?alt=media&token=e55520df-a90f-4ef3-af75-8d269ee6a97d)
*The extended FAB can change to a standard FAB when the window is smaller*

## Grouping

Grouping is a method for connecting related elements that share a context, such as an image grouped with a caption. It visually relates elements and establishes boundaries to differentiate unrelated elements.


![Photo of dumplings with a caption reading “restaurants in the area”](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flxwwvqdk-1.png?alt=media&token=ee88043a-8222-4c39-912f-f0b6336ee653)
*By placing a caption under an image this composition shows an explicit group*

**Explicit grouping** uses visual boundaries such as outlines, dividers, and shadows to group related elements in an enclosed area. Explicit grouping can also indicate that an item is interactive, such as list items contained between dividers, or a card displaying an image and its caption.


![Container of a contact grouped with photo and caption](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flxwwxa8g-2.png?alt=media&token=62385ee2-6bc3-492b-a60b-db12f75d2580)
*The elements in this card are explicitly grouped*

**Implicit grouping** uses close proximity and open space (rather than lines and shadows) to group related items. For example, a headline closely followed by a subhead and thumbnail image are implicitly grouped together by proximity and separated from other headline-subhead-thumbnail groups by open space.


![Carousel of images](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flxwwyais-3.png?alt=media&token=1f6813ec-a502-4adb-abd9-7cb648575f45)
*Images in a carousel are grouped by their proximity*

## Margins

Margins are the spaces between the edge of a window area and the elements within that window area.

Margin widths are defined using fixed or scaling values for each window size class. To better adapt to the window, the margin width can change at different breakpoints. Wider margins are more appropriate for larger screens, as they create more open space around the perimeter of content.

See margin measurements for each window class: compact, medium, expanded, large, and extra-large.


![Screen highlighting vertical blue margin on left side of screen](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flxwx04xw-1.png?alt=media&token=3a716354-4106-42c6-920c-3692da759a86)
*A margin separates the edge of the screen from the elements on the screen*

## Spacers

A spacer refers to the space between two panes in a layout. Spacers measure 24dp wide.


![Screen highlighting vertical blue margin on left side of screen](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flxwx1ijr-5.png?alt=media&token=ab82125d-28cd-4500-a322-fd4c9606dd3c)
*1. A spacer splits two panes from each other*

A spacer can contain a drag handle that adjusts the size and layout of the panes. The handle's touch target slightly overlaps the panes.


![Pane drag handle touch target overlapping two panes.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flxwx2hj1-6.png?alt=media&token=697c271a-165d-4dbd-af2b-e98b49b4a95d)
*1. Drag handle touch target*

## Padding

Padding refers to the space between UI elements. Padding can be measured vertically and horizontally and does not need to span the entire height or width of a layout. Padding is measured in increments of 4dp.


![Full screen-width photo with padding below it and text below the padding](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flxwx3xmp-3.png?alt=media&token=f9885d4f-f041-4a92-b7ea-2179ea08c0ab)
*1. Padding separates a headline from a image above*

## Overview

- Information density is the consideration of the amount of information visible on the screen
- The default target size should be at least 48x48 CSS pixels
- Users can change density as long as the density controls are accessible
- Apply density thoughtfully; not every layout needs it
- Layout and component scaling (component adaptation or component density) can allow users to scan, view, or compare more information at once


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm6fec25z-GM3-Components-Density-2-v03.mp4?alt=media&token=63117a65-7060-4f77-8a28-f2770b38d58a)


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm6fedlg1-GM3-Components-Density-1-v02.mp4?alt=media&token=e3ca199d-f4da-4b4d-9bcc-427e890f1270)

**Information density**

- Information density can be achieved through layout and design decisions without using component scaling
- Users may not benefit from increased density

**Component scaling**

- Components can adapt and change dimensions to allow users to scan, view, or compare different amounts of information
- Don't apply component scaling by default if it would result in a target below 48x48 CSS pixels


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm6feg3jn-GM3-Components-Density-3-v02.mp4?alt=media&token=2ce28a7e-1805-4925-804b-0420f2455db2)

## Information density

Information density refers to the amount of content (such as text, images, or videos) in a given screen space.

A layout’s spacing dimensions, including margins, spacers, and padding, can change to increase or decrease its information density. High density layouts can be useful when users need to scan, view, or compare a lot of information, such as in a data table. Increasing the layout density of lists, tables, and long forms makes more content available on-screen.

Consider density settings in the context of a device. Although a user may prefer a denser layout for desktop, they may not for mobile. Density shouldn’t automatically change across window-size classes or device orientation without users changing it.


![High density layout](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flzjckoeo-3.png?alt=media&token=1586220d-a76c-4bf0-b086-ac4b932144c2)
*Consider using higher density information design when users need to scan lots of information*


![News website on desktop displaying a high information density. ](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flzjcmxrs-4.png?alt=media&token=dc7bd078-f7d4-46e4-9685-d4a248f9b37b)
*Consider the amount and priority of information on-screen. Higher density can be useful for data-rich sites (news, financial portals, dashboards) where users expect lots of information quickly.*


![News website on desktop displaying a low information density. ](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flzjcnouc-5.png?alt=media&token=f05c1dff-6a12-4bb2-ba50-8ee6494a10b7)
*Lower density can be better for sites prioritizing aesthetics, a focused message, less information, or easier navigation*

## Component scaling

The component density scale controls the internal spacing of individual components.

The density scale is numbered, starting at 0 for a component’s default density. The scale moves to negative numbers (-1, -2, -3) as space decreases, creating higher density.

Higher density is typically applied by decreasing the top and bottom padding or overall height by 4dp.


![Three buttons, with densities  of +1, 0, and -1.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flzje0jnk-7.png?alt=media&token=3c357518-6c0a-4ca3-a637-e159430ff40f)
*Buttons in 3 different densities. Apply button density based on the needs and layout of a design.*

Center the grouped element within the component container.

Text size shouldn’t change as the container size scales.


![Stacked element showing 20 dp between label and input](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flzje23wv-8.png?alt=media&token=886dc00f-73a5-485e-8bd7-2b6271c65482)
*The measurement between the label and input is 20dp*


![Parent container showing label above input.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flzje3e0s-9.png?alt=media&token=13938623-bd72-4399-b745-99f5a4e34c4e)
*The label and input are centered within their parent container*


![Dropdown menu selectable space  height of 36 dp](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flzje4oe2-10-dont.png?alt=media&token=1e4ca720-f5d4-4657-b46e-95a328a8c983)
*Don’t increase density in UIs that involve focused tasks, such as selecting from a menu. It reduces usability by limiting selectable space.*


![Single-line snackbar](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flzje5tgh-11-dont.png?alt=media&token=fa510b50-4502-4398-a69a-8bcba6cbeb7a)
*Don't increase the density in components that alert the user of changes, such as snackbars or dialogs*

### Avoid applying component scaling by default

- Don't apply component scaling to layouts by default that lower the target size below a default size of 48x48 CSS pixels
- Allow users to opt for a higher density layout or theme, and provide a simple, accessible way to revert to default best practices

People should be able to **opt**** in** to dense layouts and components.

To ensure that density settings can be easily reverted, targets in settings interactions must follow defaults (48x48 CSS pixels).


![A density menu with large, medium, and small options to customize the screen layout of a table on desktop. ](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flzje9ukn-12a.png?alt=media&token=a179a73f-0d78-4809-ac1d-b1cc696d9901)
## Targets

Dense components can be less accessible because interactive elements are smaller, so use caution when increasing information density.


![Selectable target of only 40 dp](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flzjqo90o-13-caution.png?alt=media&token=6f36346e-923d-4b40-9453-764afeaaf502)
*Use caution when applying component scaling where selectable targets will be reduced to less than the 48x48dp best practice and only apply density where it provides a better user experience.*

Use caution when applying density to interaction targets. Following best practices, accessible targets should retain a minimum of 48x48dp, even if their visual element (such as an icon) is smaller.


![Settings button is 24 by 24 dp, but has interaction target of 48 x 48 dp.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flzk8ysbx-15.png?alt=media&token=51c7c9b4-9426-4e91-93b9-3c22e90ab6bf)
*The target should remain 48x48, even if the icon is smaller.*


![Button with height of 36 dp and interaction target of 48 dp](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flzjqrzlp-15.png?alt=media&token=b7dcf871-268a-403f-825b-09dc0c9e6ae1)
*The interaction target of a common button can be larger, as long as it meets the 48x48dp minimum size.*

## Pixel density

The number of pixels that fit into an inch is referred to as pixel density. High-density screens have more pixels per inch than low-density ones. As a result, UI elements of the same pixel dimensions appear larger on low-density screens, and smaller on high-density screens.

To calculate pixel density:

Screen density = Screen width (or height) in pixels / Screen width (or height) in inches


![Magnified UI element  showing a high number pixels in the focus area](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flzjquwp8-16.png?alt=media&token=1f84c301-af60-49ea-a158-aa8ff21bf1cc)
*A high-density ui element*


![Magnified UI element  showing the low number of pixels in the focus area ](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flzjqw7id-17.png?alt=media&token=5b626bbc-0279-4d3a-ad2b-d44931cc552a)
*A low-density UI element*

### Density-independent pixels

Density-independent pixels, written as dp, are flexible units that scale to have uniform dimensions on any screen. They provide a flexible way to accommodate a design across devices. Material design system uses density-independent pixels to display elements consistently on screens with different densities.

A dp is equal to one physical pixel on a screen with a density of 160.

To calculate dp: dp = (width in pixels * 160) / screen density


![Screen with grid representing  low number of pixels](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flzjqylun-18.png?alt=media&token=0d6a5447-b65e-4bfc-bc2e-0d155c09cea1)
*Low-density screen displayed with density independence*


![Screen with grid representing  high number of pixels](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flzjr0140-19.png?alt=media&token=ef52565c-7aef-4004-8bec-078cb24cbb93)
*High-density screen displayed with density independence*

| Screen physical width | Screen density | Screen width in pixels | Screen width in dps |
| --- | --- | --- | --- |
| 1.5 in | 120 | 180 px | 240 dp |
| 1.5 in | 160 | 240 px |  |
| 1.5 in | 240 | 360 px |  |

**Window size classes** provide the foundation for top level layout decisions, but display-specific considerations are also needed.

## Display cutout

A display cutout is an area on some devices that extends into the display surface. It allows for an edge-to-edge experience while providing space for important sensors on the screen of the device. 

Applications can extend around display cutouts or other features, but some parts of the UI might be obscured.


![Content safe area shown in portrait and in landscape mode.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flxx2cfdc-1.png?alt=media&token=8da1aff9-1abf-46f1-bfe3-1a697a379b11)
*A mobile device’s content-safe area around a display cutout for the front-facing camera*

## Foldable devices

Foldable devices use a folding mechanism to fold and unfold. They have unique characteristics to consider when designing layouts.

### Fold

The fold of a foldable device divides the screen into two portions, either horizontally or vertically. The fold can be a flexible area of the screen or, on dual-screen devices, a hinge that separates two displays.

A flexible fold is barely visible, although some users may feel a tactile difference on the screen surface. Content can flow over the fold fairly easily.


![Center fold of a foldable device layout.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flxx2dejk-2.png?alt=media&token=b1ae33fd-91b4-4985-a1df-b4945de2c03d)
*1. Folds are typically found in the center of the device screen and can present a seamless experience*

On devices with a physical hinge, designing the screen as two distinct sections (separate window areas or panes) allows a composition to work well across the hinge and screens.


![Center fold on a foldable device with a physical hinge.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flxx2eac2-3.png?alt=media&token=7b332b5a-8f26-4b4a-ab1e-f0fabd5efe39)
*A physical hinge separates two parts. There is no display hardware in this region.*

### Device state

Foldable devices can have several physical states: folded, open flat, and tabletop.

#### Folded

The folded state can include a front screen, which often fits in the compact window size class, just like a mobile phone in portrait orientation.


![Compact window of a folded device.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flxx2fbwo-4.png?alt=media&token=46676791-930a-4b6a-9c40-6adafcd13f73)
*The front screen of a foldable device*

#### Open flat

An open flat state refers to the fully opened screen, which usually increases the window size class to medium or expanded. An open device can be used in landscape or portrait orientations.


![Open portrait state of a mobile device.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flxx2g4q8-5.png?alt=media&token=ec23f27d-3b07-4964-aaa0-2b6c86e5ba02)
*In an open portrait state, the longer device edge is vertical while the shorter edge is horizontal*


![Open landscape state of a mobile device.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flxx2gth1-6.png?alt=media&token=a73f2de3-02d2-4457-86be-914222396201)
*In an open landscape state, the longer device edge is horizontal while the vertical edge is shorter*

#### Tabletop

Tabletop refers to a half-opened state forming a rough 90 degree angle, with one half of the device resting on a surface. This posture resembles a laptop.

UI controls near the fold can be difficult for users to access, and text overlaying the fold can be hard to read.


![Tabletop state of a mobile device showing camera ;ems  on the vertical plane.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flxx2hz6b-7.png?alt=media&token=d9d2412d-a772-42a7-87dd-6a7cf8400b04)
*If camera hardware is present, a tabletop device is best positioned on a side without any protruding hardware elements*

### Interaction

#### App continuity

When running on a foldable device, an app can transition from one screen to another automatically. After the transition, the app should resume in the same state and location, and the current task should continue seamlessly.


![A news app in compact mode compared to the open landscape state where the news app expands with a new column next to the compact news feed.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flxx2j1zj-8.png?alt=media&token=cc06940d-eaf9-424c-969f-15b6e32d5d06)
*A news app shows a feed in a compact and expanded window class when a foldable device switches device state*

#### Scrolling and multiple panes

Depending on how your app uses panes, the scroll behavior of a folded design may change in the unfolded design. 

If you expand a pane, you can decide whether the whole window will scroll together or if each side (each pane) scrolls independently.


![A foldable device screen in open landscape mode with a single pane showing vertical scroll arrows.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flxx2khfi-9.png?alt=media&token=50dc8429-6102-45a6-8195-2920d374207c)
*A single pane can scroll its inside content vertically and horizontally*

If your design has multiple panes, each pane can operate as an independently scrollable area.


![A foldable device screen in open portrait mode with double panes each with a vertical scroll arrow.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flxx2lo3u-10.png?alt=media&token=1ca93a5d-ceae-4193-ad58-e9e5eb0a1dce)
*Multiple panes can scroll inside content independently of one another*

## Multi-window mode

Multi-window mode is an Android system feature for **displaying multiple apps on the same screen. **This can be especially useful for multi-tasking, or workflows that depend on comparing information.

Note: This concept should not be confused with using multiple panes to display content from a single app. For more on that, see: Panes.


![2 apps appear side-by-side with a task bar below spanning the width of the screen.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flxx2mmo2-1.png?alt=media&token=e5f2fbca-c806-4e4a-8bc7-30be096f275d)
*Screen displaying an email app and a contacts app in multi-window mode*

### User needs

The ways that windows are created, arranged, and adjusted should feel straightforward for all users and across any window size class. Methods for seamless window management include:

- Apply smooth transitions as described in motion guidance
- Ensure that users can create multiple windows easily and move between them as needed
- Keep mental models and interaction patterns simple so that users aren’t required to think about which mode is appropriate for each task
- Design and implement window dynamics consistently across variations in foldable hardware, including those with a hinge that separates two displays

### Window creation and behavior

Android provides several ways for users to create a multi-window view.

### Taskbar

The taskbar provides a launching point for pinned and suggested apps to easily become a separate window. 

To create a new window, a user selects and drags an app from the taskbar and moves the app icon to indicate where the new window should be displayed.


![The taskbar is positioned at the bottom of a screen.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flxx2qc2a-2.png?alt=media&token=163847e4-1a26-4431-a633-7d29f9363d3b)
*Android taskbar*

### Context menu

Users can also create multiple windows through the overview by the app context menu.


![2 apps appear side-by-side with a task bar below spanning the width of the screen.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flxx2rmbq-3.png?alt=media&token=cff96393-8fae-4b77-bd8d-a3986c64ccf9)
*Multi-window mode can have vertical positioning*


![2 apps are stacked in landscape mode with a task bar below spanning the width of the screen.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flxx2uu5h-4.png?alt=media&token=b190166c-fce3-4d5b-b6f9-f5a3741c8ba5)
*Multi-window mode can have horizontal positioning*

### Adjusting window sizes

By default multiple windows are created as a 50/50 side-by-side split.

The windows can be adjusted further to 1:3 or 2:3 proportions. These ratios provide a primary and secondary window dynamic, offering greater flexibility and allowing focus on one application as needed.

When in a multi-window mode, the available screen area often changes from medium or expanded window class to compact. Layouts should adapt accordingly.


![2 apps appear side-by-side with the left-side app using two-thirds of the screen, and the right app one-third.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flxx2y99m-5.png?alt=media&token=04f48065-b71e-4a2d-9a01-f6de64b23b1c)
*The screen handle can be dragged and released to create the desired window ratio. The handle automatically adjusts to the closest snap point.*

[Over 2 billion people](https://www.w3.org/International/questions/qa-scripts.en.html) read and write in right-to-left (RTL) languages like Arabic, Hebrew, Farsi and Urdu. Layouts should support both left-to-right (LTR) and RTL languages through mirroring and other best practices to ensure content is easy for global audiences to understand and navigate. Consider the holistic experience including [global writing](/m3/pages/global-writing/overview#0d0f8403-e5ff-4579-be74-0c4dbcef7fcb), localizing voice and [design principles for culturally appropriate icons](/m3/pages/icons/designing-icons#1056d971-81ca-4abe-b931-42185dd76638). Material's components are built to support RTL, such as naming elements and tokens as "leading" and "trailing." However, extra configuration may be needed to achieve specific RTL situations.

## Mirroring

When a layout is changed from LTR to RTL (or vice-versa), and flipped horizontally, it’s often called mirroring. UI elements and text that typically appear on the left in LTR aligns to the right. Reading flow starts from the top right corner, instead of the top left.

Not all elements mirror with RTL languages. For example, graphs and charts maintain a LTR directionality for Persian and Urdu.


![Layout in LTR and mirrored for RTL language.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm6rr3ndk-1.png?alt=media&token=05901f6f-b35f-43a1-907c-fed9b464bfea)
*A mirrored layout in an RTL language reverses the alignment and ordering of elements.*

## Text rendering

Correct text rendering is foundational for a great user experience and it’s critical for readability and usability. Text rendering has two parts:

1. Alignment: How the edges of the text box are placed alongside other elements.
2. Directionality: How text and other elements flow within a text box, like left-to-right, or right-to-left.

In RTL languages, text is usually right-aligned, and elements flow from right-to-left.   Common issues with RTL language rendering are text entry, cursor position, punctuation, phone numbers, and URLs.   Improperly rendering text in RTL languages can create cognitive overload and negatively impact user sentiment and trust.


![Text field incorrectly displaying the word order of an email address and cursor placement.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm6rr60jz-2%20-%20A.png?alt=media&token=0187472e-4278-4a06-897a-612c671ce24e)
*Don't reverse the order of the email username and domain (@google.com). The domain should always be to the right of the username.  Usernames can still be written RTL, with the cursor moving to the left.

Note this example is not translated to illustrate a common issue with text rendering*


![Dialog window incorrectly displaying word order decreasing readability.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fm7cj5hjy-2%20-%20B.png?alt=media&token=17bb283b-ed96-4362-9053-e0c8ead1a3f1)
*Don’t apply LTR directionality to RTL content because it may scramble word order. To ensure readability across all languages. The content should have both RTL alignment and directionality.

Note this example is not translated to illustrate a common issue with text rendering*

## Icons and symbols

In RTL languages, directional UI icons, like back and forward, should be mirrored. However, in Hebrew timelines and media controls on a page should retain left-to-right directionality.

The meaning of icons and symbols can vary significantly across cultures.


![Back and forward icons in LTR and RTL.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm6rre3h2-3%20-%20A.png?alt=media&token=480dc1d4-5c16-4902-af1d-2218f293590e)
*Back and foward icons are mirrored in RTL*


![Send and question mark in LTR and RTL.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm6rrfly7-3%20-%20B.png?alt=media&token=050695fe-3339-4996-afa5-1b2876660828)
*Send buttons are mirrored in RTL. Help icons are mirrored in some RTL languages, like Urdu and Persian.*

## Time

Linear representations of time are often mirrored in RTL language experiences. 

Linear progress indicators should move from right to left for most RTL languages, except Hebrew where it should remain LTR.

Circular representations of time remain the same.


![RTL linear progress indicators filling from right to left  and circular progress indicators filling clockwise .](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm6rrip79-4.png?alt=media&token=c9e3ba41-c747-4bfb-b76a-70e3ae6258bd)
*1. RTL linear progress indicator starts to fill progress from the right 
2. Circular progress indicators move clockwise*

### Media players

Media controls for video or audio players are always LTR.


![Media player with control and progress in LTR and all other content is RTL.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm6rt3wci-5_urdu.png?alt=media&token=a69ecb25-1698-4a55-9bd3-5d954c939b56)
*In Urdu, controls and progress for media and a podcast title are shown in LTR, while all other content is RTL.*

### Clock

For RTL languages, the directionality of time remains LTR, and clocks still turn clockwise. However, the AM/PM symbols for 12h clocks should be placed to the left. The 24-hour clock is often used in countries where the primary language is not English.

Clock icons, circular refresh icons, and progress indicators with arrows pointing clockwise should not be mirrored.


![24 hour clock in RTL.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm6rt5hlh-6%20-%20A_hebrew.png?alt=media&token=b2ee3bb4-76e2-4337-8109-234dd7847476)
*24-hour clocks in RTL move clockwise, but mirror elements such as buttons*


![12 hour clock in RTL. ](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm6rt6mj7-3%20-%20B_arabic.png?alt=media&token=bcaabbc8-50d2-4baa-9b8e-799267cb02d9)
*12-hour clocks in RTL move clockwise, but mirror UI elements such as AM/PM and buttons*

## Canonical layout examples

### List-detail

The [list-detail](/m3/pages/canonical-layouts/list-detail) layout divides the app window into two side-by-side panes, and is mirrored in RTL.


![RTL list layout.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm6rt95z5-7_hebrew.png?alt=media&token=69d513fe-7989-4805-b65a-e3d6c9b39985)
*List-detail mirrored for RTL, where text and other elements are aligned to the right and flow from right to left*

### Feed

Use a [feed layout](/m3/pages/canonical-layouts/feed) to arrange content elements like cards in a configurable grid for quick, convenient viewing of a large amount of content. The feed layout is mirrored in RTL.


![RTL feed layout.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm6rtc29p-8.png?alt=media&token=c763352b-86e5-4455-a1ab-b84f51ab6841)
*Feed layout mirrored for RTL, where the order of text, grid, and other elements align to the right and flow from right to left*

### Supporting pane

Use the [supporting pane](/m3/pages/canonical-layouts/supporting-pane) layout to organize app content into primary and secondary display areas. The supporting pane layout is mirrored in RTL.


![RTL supporting pane in a RTL language.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm6rtekg5-9_urdu.png?alt=media&token=6b70a1dc-2d88-4d86-82c3-c03201df33a1)
*Supporting pane to the left of the primary content. Text and other elements within the pane are aligned to the right and flow from right to left.*

## Component examples

### Badges

Change the position and alignment of [badges](/m3/pages/badges/specs) for RTL languages.


![Small badge on the top left of the icon.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm6rtgvbk-10%20-%20A_urdu.png?alt=media&token=f90702ee-35c3-4470-b083-6d34d392e437)
*Small badge appears on the top left of the icon*


![Large badge on the top left of the icon.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm6rtjdwz-10%20-%20B_urdu.png?alt=media&token=2833dd15-ab4f-4300-adf4-524f48a5fc9d)
*Large badge appears on the top left of the icon*

### Toolbar

[Toolbars](/m3/pages/toolbars/guidelines) provide actions related to the current page. For RTL languages, mirror the order of the tools.


![RTL floating toolbar](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmall2gyv-11.png?alt=media&token=54ba3f8d-fc5e-42e5-8010-8b3887971dd0)
*Mirrored floating toolbar, where the FAB appears on the left of the screen*

### App bar

[App bars](/m3/pages/app-bars/overview) are placed at the top of the screen to help people navigate through a product.

Mirror app bar layout in RTL, and flip appropriate icons, such as arrows.


![3 app bars in RTL.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm6rtqm4b-12_urdu.png?alt=media&token=55e68798-60d9-46b5-ac42-c502f21a81a1)
*1. RTL center-aligned/small 
2. RTL medium flexible 
3. RTL large flexible*

### Navigation drawer

[Navigation drawers](/m3/pages/navigation-drawer) that open from the side are always placed on the leading edge of the screen, on the left for LTR languages, and on the right for RTL.


![RTL navigation drawer, including a mirrored icons.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm6rttjl0-13_arabic.png?alt=media&token=0316e66f-2626-4521-ba3a-abd21ce031e0)
*RTL navigation drawer, including a mirrored icon for outbox*

### Navigation rail

The [navigation rail](/m3/pages/navigation-rail/guidelines) is placed on the leading edge of the screen, on the left side for LTR, and on the right for RTL.


![Nav rail in the right side for RTL languages, and left side for LTR languages.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm6rtvteh-14_hebrew.png?alt=media&token=560c411f-1a34-43f6-90c5-6a9c7edbe837)
*Based on the language being used, a navigation rail is set on a screen’s leading edge. This is the right side for RTL languages, and left side for LTR languages.*

### Text fields

Icons in [text fields](/m3/pages/text-fields/guidelines#5c8a5f07-b1a5-455f-bf76-7ff0d724f6b0) are optional. Leading and trailing icons change their position based on LTR or RTL contexts.


![Text fields in RTL with leading and trailing icons.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm6rtykdl-15.png?alt=media&token=ae3edcc2-1c08-44dd-a767-7de671b9a584)
*Icons, symbols and label text for RTL: 

1. Icon signifier 
2. Valid or error icon 
3. Clear icon 
4. Voice input icon 
5. Dropdown icon 
6. Image*

### Chips

The leading icon of input chips can be an icon, logo, or circular image.

The trailing icon is always aligned to the end side of the container. It’s placed on the right for LTR and on the left for RTL.


![Filter chips in RTL layout.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm8rhslpk-16.png?alt=media&token=a7178733-70fc-4052-b74a-385de64cd9e4)
*Filter chips shown in an RTL layout. Note this example is not translated to help illustrate mirroring.*

## Swipe gestures

Gestures are the ways people interact with UI elements using touch or body motion.

People can navigate horizontally between peer views like tabs, and to complete actions. 

RTL swiping and gestures should mirror their counterparts in LTR. If an app includes a "delete" icon revealed when swiped from the right for LTR languages, the same should be possible on the left for RTL languages.


![RTL list layout with swipe gesture revealing additional actions.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm6ru2ith-17.png?alt=media&token=fccfef3f-de43-402f-a1c6-7240f9e4e66b)
*Swiping reveals additional action in RTL list layout*

On Android, [predictive back](https://github.com/material-components/material-components-android/blob/master/docs/foundations/PredictiveBack.md) allows people to swipe left or right on the screen to go back or dismiss modal components.

RTL predictive back features should mirror those found in a LTR context.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fm8p23uu6-GM3_Bidirectionality_Interaction%20Gestures_6_IA_v02.mp4?alt=media&token=0f898fcd-1712-4760-8ed7-89faacf4e640)
