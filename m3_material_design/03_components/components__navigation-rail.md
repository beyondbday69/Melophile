---
title: "Navigation rail"
slug: "components/navigation-rail"
url: "https://m3.material.io/components/navigation-rail"
---

# Navigation rail

- Use navigation rails in medium, expanded, large, or extra-large window sizes
- Can contain 3-7 destinations plus an optional FAB
- Always put the rail in the same place, even on different screens of an app


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmbs2h57x-GM3_Expressive_Nav-Rail_Overview_01_IA_v02.mp4?alt=media&token=c7d344f8-10e6-4d28-af46-31980258435c)

## Availability & resources

## M3 Expressive update

**May 2025**

A **collapsed** and **expanded** navigation rail have been introduced to replace the baseline nav rail. The expanded nav rail is meant to replace the navigation drawer. [More on M3 Expressive](https://m3.material.io/blog/building-with-m3-expressive)

Variants and naming:

- 

The baseline **navigation rail** is no longer recommended
- 

Added two wider navigation rails:

  - 

**Collapsed:** replaces baseline nav rail
  - 

**Expanded**: replaces navigation drawer

Configurations:

- 

Expanded rail modality:

  - 

Non-modal
  - 

Modal

- 

Expanded behavior:

  - 

Transition to collapsed navigation rail
  - 

Hide when collapsed

- 

Color:

  - 

Active label on vertical items changed from **on surface variant** to **secondary **


![A collapsed and expanded navigation rail.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0fsz2c4-2.png?alt=media&token=e1cbb29b-89b7-4662-9465-2c39f2bd9edd)
*The collapsed and expanded navigation rails match visually and can transition into each other*

## Differences from M2

- Behavior: Predictive back interaction
- Color: New color mappings and compatibility with dynamic color
- States: The active destination can be indicated with a pill shape in a contrasting color


![M2 navigation rail with 1 colored and filled icon showing the active state and 3 inactive icons.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0dtdapc-3.png?alt=media&token=643d52f9-213d-4aed-90fd-029c44fce678)
*M2: The navigation rail uses icon color, weight, and fill to communicate which destination is active*


![M3 navigation rail with 1 icon surrounded by a pill shape in contrasting color to show the active state.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0fszcgl-4.png?alt=media&token=8b26ebe5-396b-4148-b571-247ef9634f50)
*M3: The navigation rail uses a pill-shaped active indicator to communicate which destination is active*

## Variants


![2 variants of navigation rails.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmal9f2lt-01-3P.png?alt=media&token=e3d39033-8b0a-4cfb-834c-708b8a39d735)
*1. 

Collapsed navigation rail
2. 

Expanded navigation rail*

### Baseline variants

The baseline navigation rail is no longer recommended, and should be replaced by the collapsed navigation rail. [View baseline tokens](/m3/pages/navigation-rail/specs#d4d97764-20ec-496f-a6f3-0d423940ec5a)


![Baseline navigation rail.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmkvenn9g-02-3P.png?alt=media&token=ef44fc56-2069-4191-8b8e-fdd672e4f136)
*1. 

The baseline navigation rail is no longer recommended*

****

| Variant | M3 | M3 Expressive |
| --- | --- | --- |
| Collapsed navigation rail | -- | Available |
| Expanded navigation rail | -- | Available |
| Navigation rail (baseline) | Available | Not recommended.Use collapsed navigation rail. |

## Configurations


![Standard and modal layouts of navigation rail.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmal9fbzs-03-3P.png?alt=media&token=86384321-4a5b-4da1-8b96-269c108187c6)
*1. Expanded layout: standard
2. Expanded layout: modal*

| Category | Configuration | M3 | M3 Expressive |
| --- | --- | --- | --- |
| Expanded layout | Standard (default) | Available as navigation drawer | Available |
| Modal | Available as navigation drawer | Available |  |
| Expanded behavior | Hide when collapsed | -- | Available |

## Tokens & specs

Browse the component elements, attributes, tokens, and their values. [Learn about design tokens](/m3/pages/design-tokens/overview/)

## Anatomy


![9 elements of collapsed and expanded navigation rails.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmg0uznol-04-3P.png?alt=media&token=bb0f488f-96f8-4128-9266-c8ff9bb70ce2)
*Collapsed and expanded navigation rail elements:

1. Container
2. Menu (optional)
3. FAB or Extended FAB (optional)
4. Icon
5. Active indicator
6. Label text
7. Large badge (optional)
8. Large badge label (optional)
9. Small badge (optional)*

## Color

Color values are implemented through design tokens. For designers, this means working with color values that correspond with tokens; in implementation, a color value will be a token that references a value. [Learn more about design tokens](/m3/pages/design-tokens/overview)


![Color roles of 9 elements of collapsed and expanded navigation rails in light and dark color schemes.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmmyzgq71-05-3P.png?alt=media&token=ac37fe4a-fbe3-4d2d-abf9-f753d00fc5f7)
*Navigation rail color roles used for light and dark schemes:

1. 

Surface container (optional)
2. 

On secondary container
3. 

Secondary container
4. 

Secondary (vertical), On secondary container (horizontal)
5. 

On surface variant
6. 

On surface variant
7. 

Error
8. 

On error
9. 

Error*

## States

States are visual representations used to communicate the status of a component or an interactive element.

The navigation item’s target area always spans the full width of the nav rail, even if the item container hugs its contents.


![4 states of collapsed navigation rails.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmal9s5f0-06-3P.png?alt=media&token=3900b54e-403f-4138-952a-22d9c5d47a68)
**


![4 states of expanded navigation rails.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmal9sbiq-07-3P.png?alt=media&token=e367b797-932d-49fa-a72a-3ab34851205a)
*1. Enabled
2. Hovered
3. Focused
4. Pressed*

## Measurements


![Padding and measurements for expanded and collapsed navigation rails.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmal9t1mb-08-3P.png?alt=media&token=8e32163c-6c4e-4d28-bf1b-5da584c4b46a)
*Navigation rail padding and size measurements*

## Common layouts


![4 common layouts of collapsed navigation rail.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmal9twwq-09-3P.png?alt=media&token=9065bce5-6d1f-4c23-9090-27dc31f73d63)

![4 common layouts of expanded navigation rail.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmal9u2w3-10-3P.png?alt=media&token=5b5ada66-3e11-40a7-a7f2-8559cb16e2ad)
*1. Three navigation items 
2. Three navigation items with a menu
3. Three navigation items with a FAB
4. Three navigation items with a menu and FAB*

## Baseline navigation rail


![8 elements of baseline navigation rail.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmal9uv1q-11-3P.png?alt=media&token=15c53656-c999-4f96-9e1d-77a16ca00b3a)
*1. Container
2. Menu icon (optional)
3. Icon
4. Active indicator
5. Label text
6. Large badge label (optional)
7. Large badge (optional)
8. Badge (optional)*

### Tokens & specs

### Color

Color values are implemented through design tokens. For design, this means working with color values that correspond with tokens. For implementation, a color value will be a token that references a value. [Learn more about design tokens](/m3/pages/design-tokens/overview)


![8 color roles of baseline navigation rail.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmal9vzs8-12-3P.png?alt=media&token=4d01ee27-7870-4c37-8095-067a9d64d37e)
*Navigation rail color roles used for light and dark themes:

1. On secondary container
2. Secondary container
3. On surface
4. On surface variant
5. On surface variant
6. Error
7. On error
8. Error*

### States

States are visual representations used to communicate the status of a component or interactive element.


![8 states of baseline navigation rail.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmal9x9ol-13-3P.png?alt=media&token=69b0b5ae-6f96-4d67-8a45-e77efecd6b17)
*Navigation rail states:

1. Enabled (on active destination)
2. Hovered (on active destination)
3. Focused (on active destination)
4. Pressed (on active destination)
5. Enabled (on inactive destination)
6. Hovered (on inactive destination)
7. Focused (on inactive destination)
8. Pressed (on inactive destination)*

### Measurements


![Baseline nav rail size measurements.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmal9xiu7-14-3P.png?alt=media&token=5970c991-6782-4784-bcbb-4fc5ed1dd423)
*Navigation rail size measurements*


![Baseline nav rail padding and margin measurements.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmal9xyxt-15-3P.png?alt=media&token=3229f7c0-780c-4bbc-9e2f-aa8a5d83e591)
*Navigation rail padding and margin measurements*

### Configurations

Common arrangements of elements within a navigation rail.


![5 configurations of the baseline navigation rail.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmal9yote-16-3P.png?alt=media&token=551b3b7f-1fd2-4e64-a69f-a57c4a7fe835)
*1. With a menu
2. With a FAB
3. With menu and FAB, without labels
4. All destinations with text labels
5. With menu, FAB, and label text for all destinations*


![Colorful, purple navigation rail shown collapsed and expanded.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0fueyh1-01.png?alt=media&token=c9edba18-9dee-44b1-9657-4dd1cdf2ff74)
*Use the menu icon to transition between collapsed and expanded navigation rails*

## Usage

The navigation rail can display navigation items, a menu, and a floating action button (FAB) in a vertical orientation.

There are two variants of navigation rails, **collapsed** and **expanded**, which can easily transform into each other when the menu button is selected.

### Collapsed

The **collapsed** nav rail runs along the leading edge of the window, and should contain 3–7 navigation items. It should not be hidden.

It can be used in medium to extra large breakpoints, such as tablets and desktop. In  medium windows with few destinations, consider using a navigation bar instead. Compact windows should always use a navigation bar.


![Collapsed navigation rail with “timer” icon on FAB.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmalbc2cg-02.png?alt=media&token=8df4aa93-c874-4f14-98aa-8ebadcee272f)
*A navigation rail should be the only visible navigation element*

### Expanded

The **expanded** navigation rail can be standard or modal, and should always open from a menu icon. An expanded rail can reveal secondary destinations not visible when collapsed.

The **standard** configuration is placed beside body content. It’s best for larger windows with lots of available space.

The **modal** configuration overlaps the body content, and should be opened from a menu icon. Use the modal configuration for:

- Information dense layouts where space is limited
- Products with many navigation items


![Expanded navigation rail shown expanded by default and expanded over screen content.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0fuf9qz-03.png?alt=media&token=3377b89c-7c1c-4a94-8282-d48530c4d81e)
*A navigation rail can be expanded by default on larger screen sizes, or can be expanded over content on smaller screen sizes*

In immersive experiences, the expanded navigation rail can be hidden entirely, appearing only when the menu icon is selected.

The collapsed navigation rail should not be hidden.


![Navigation rail and hidden navigation rail with menu icon button for expansion.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0fufhuq-04.png?alt=media&token=e13c10f7-7c71-4702-a6e5-a4d6842ad1b9)
*The expanded navigation rail can also be hidden, appearing only when the menu icon is selected*

## Anatomy


![10 elements of expanded and collapsed navigation rails.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0fug0sy-05.png?alt=media&token=465bf07d-fe91-4cfa-97c3-e9624609ed50)
*1. Container
2. Menu (optional)

3. Floating action button (FAB) (optional)

4. Icon - active

5. Label text - active

6. Active indicator

7. Icon - inactive

8. Large badge (optional)

9. Large badge label

10. Small badge

11. Label text - inactive*

### Container

The navigation rail should be placed on the leading edge of the window. This is the left side for left-to-right languages, and the right side for right-to-left languages.

The container fill can be turned off so the nav rail appears directly on the surface. When doing this, make sure all items have a minimum of 3:1 color contrast.


![Right-to-left navigation rail in Hebrew, and left-to-right navigation rail in English.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0fugb5b-06.png?alt=media&token=9bd7ddad-b14f-4131-bdd4-bbdb07283569)
*The navigation rail should be placed on the leading edge of the window*

The navigation rail should always run vertically along the side of a layout. Don’t make it horizontal.

Use a navigation bar for horizontal navigation.


![Horizontal navigation rail on timer screen.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmacys6lz-07.png?alt=media&token=274b5d1b-6978-4123-9456-4a822df58bc7)
*Don’t use the navigation rail horizontally. Use a navigation bar instead.*

Navigation rail items can be aligned as a group to the top or center of a layout. On tablets, use center alignment to make it easier to reach items.

The menu icon and FAB should always be top-aligned.


![Navigation rails with different alignments.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0fuh0i5-08.png?alt=media&token=32641191-5400-4aba-829c-138e2f3c6d1e)
*Top and center aligned rail destination placement*

### Menu (optional)

The menu button can transition between the **collapsed **and **expanded** navigation rails.

Once expanded, the rail can reveal secondary destinations.

When the navigation rail is expanded, the menu icon should change to represent that it can be collapsed.


![Expanded and collapsed navigation rails controlled by a menu icon button.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0fuh7km-09.png?alt=media&token=ae84210a-74d7-4fac-82f1-f7a0356b23d0)
*A navigation rail can expand to reveal more destinations*

### Floating action button (FAB) (optional)

The container of the navigation rail is ideal for anchoring the FAB to the top of a screen, placing the app’s key action above navigation destinations.

When nested within another component, such as the navigation rail, the FAB's resting elevation should be [level 0](/m3/pages/elevation/applying-elevation).


![Navigation rail with a FAB button at the top of the screen.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmalbkctg-10.png?alt=media&token=41722433-f09f-412e-983f-1b585481a362)
*A top-aligned FAB in the navigation rail*


![Navigation rail with a FAB button at the bottom of the screen.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0fuhrl8-11.png?alt=media&token=5e953e4b-20d1-4d7f-99f0-c89d39b052e9)
*Avoid placing the FAB below navigation items*

The top of the rail can also be used for a logo, however avoid using logos that could be mistaken as buttons.

Don’t use a logo as a menu button to expand the navigation rail.


![Navigation rail with Material design logo at the top of the screen.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0fuhyto-12.png?alt=media&token=3e219498-f5cc-4a46-824c-4fb660bc7743)
*Use caution when placing logos in the rail where they might be confused with an action or destination*

### Active indicator

The active indicator shows which page is being displayed.


![Navigation rail with active indicators present for the current screen.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0fuicr9-13.png?alt=media&token=7755879a-0c18-40e2-89c8-e51757d7c7b2)
*Use the active indicator only for the current open page*


![Navigation rail with active indicators present for all navigation items.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0fujiob-14.png?alt=media&token=ed0b9441-30cf-444e-9fde-c84be948b56d)
*Don’t use the active indicator for more than one navigation item at a time*

The active indicator hugs the label text in the expanded nav rail. To achieve a similar style to the baseline navigation drawer, consider modifying the active indicator to fill the container.

The target area should always span the full width.


![Navigation rail with active indicator that hugs the text and icon.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0fujt7v-15.png?alt=media&token=8405c84a-2929-4d68-8bbd-97120c4c41ab)
*The active indicator hugs contents in the expanded nav rail*


![Navigation rail with active indicator that is larger than the content within it.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0fujxyu-16.png?alt=media&token=bb433446-a6c0-433d-bdcb-181431e6e126)
*Override the indicator to fill the container to more closely resemble the baseline navigation drawer*

### Icons

Navigation rail items must use icons that symbolize the content of their page. Browse popular icons on [Google Fonts](http://fonts.google.com/icons).


![Navigation rail with icons that fit the destinations, like a timer icon and label leading to a timer feature.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0fuk4ze-17.png?alt=media&token=8210a6c6-b488-46e3-aa46-ec3c18a3021d)
*Icons should symbolize the content of the page they open*

When a destination is selected, the icon fills and changes color. An active indicator appears behind the icon.


![Icons with and without an active indicator.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0fukavr-18.png?alt=media&token=fa0ab3db-3f7a-47ff-84c2-b0d63ac890f7)
*Selected navigation items have an active indicator, a filled icon, and a more prominent color*

### Label text

The label text should be a short, meaningful description of each navigation destination and another way for users to understand an icon’s meaning.

All navigation items require a one word label text.


![Navigation rail with clear text labels.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0fukhnw-19.png?alt=media&token=a2fe92ce-45d4-4c7b-8c51-5066b3cf7596)
*Write clear and concise labels that describe the destination page*

Avoid wrapping long labels when possible. If necessary, create a line break between words, or hyphenate longer words.


![Navigation rail with lengthy text labels.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0fune8p-20.png?alt=media&token=aaa6d4fd-a007-4a85-ac3c-8063f4f0767e)
*Break up longer phrases into two text lines if necessary*

Labels should be short enough to not be truncated. Don’t shrink the type scale to fit longer text labels.


![Navigation rail with truncated text label with ellipses. ](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0funlrw-21.png?alt=media&token=756fbcf4-1cf8-4157-8a41-69c43e76bc2e)
*Don’t truncate or display an ellipsis in place of label text*


![Navigation rail with small text label.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0funqlw-22.png?alt=media&token=f25512e4-0821-4d53-9b71-41f2870830fc)
*Don’t reduce the type size to fit more characters into a destination label*

### Badges

Navigation rail icons can include badges to communicate dynamic information about the  destination, such as counts or status.

In compact nav rails, the badge is placed in the upper right corner of the icon. In expanded nav rails, the badge should be placed next to the label text.


![Navigation rail with badges on each icon.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0fuomdi-23.png?alt=media&token=faa3cb30-f478-4450-946a-5be944155717)
*1. Small badge on a rail destination 
2. Large badge with a number
3. Large badge with a maximum character count*

### Divider (optional)

A vertical divider can help separate the rail from app content. The divider should be positioned on the edge of the rail container that’s adjacent to the app’s content area.


![Navigation rail with divider separating it from screen content.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0fuouq0-24.png?alt=media&token=92b886dc-b887-4991-9487-e7b720f909a8)
*A divider can make the navigation rail container distinct from other on-screen content*

## Placement

In adaptive layouts, the navigation rail should be placed outside any panes, always along the leading edge of the window. Don’t place it within body content.

When the navigation rail is hidden, the body content can fill in the remaining space as long as the menu icon is still accessible.

Tabs can be used alongside a navigation rail to create an extra layer of visible navigation.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmapifwv9-GM3_Expressive_Nav-Rail_Guidelines_32_IA_V10.mp4?alt=media&token=27d22a5d-f968-4b02-8f93-f1f8ed02abc9)

## Adaptive design

For more, see [adaptive design](/m3/pages/layout-overview/adaptive-design/).

### Resizing

When moving from a large screen to a small screen, a navigation rail can transform into a navigation bar, providing the same quick access in a configuration that’s easier to use on smaller displays. Never use the navigation rail and navigation bar simultaneously. 

Only use navigation rails for medium breakpoints and larger. Don’t use a navigation bar. If there are more than five destinations, consider using a modal expanded nav rail instead.

**Compact:** Don’t use a standard navigation rail for compact layouts due to space constraints. Use a navigation bar instead.

**Medium:** Use a navigation rail, especially if prioritizing persistent vertical navigation over maximizing vertical content space.

**Expanded to extra-large:** Use a navigation rail, not a navigation bar. Consider available horizontal space and the number of destinations when choosing between standard and modal.


![Navigation bar on a phone screen and navigation rail on a tablet screen.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0fupcvd-26.png?alt=media&token=b1915086-e325-4b2a-946e-eb41d37a9ae7)
*On smaller devices, use a navigation bar. On larger displays, use a navigation rail.*

### Presentation

When the navigation rail transitions from collapsed to expanded, the contents of the page should automatically adjust to fit.

The contents of the navigation rail also expand to fill the space. For example, the FAB should transition into an extended FAB. 

Extra destinations can be shown in an expanded nav rail.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmalqalnw-GM3_Expressive_Nav-Rail_Guidelines_27_IA_V07.mp4?alt=media&token=3769dd07-2a42-43fe-96d0-fac669ee36e3)

## Behavior

### Scrolling

Destinations in the navigation rail should remain visible and fixed when scrolling vertically.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm5v1vvd7-J%20-%20Scrolling%20RENDER%20-%202x.mp4?alt=media&token=1f63cc9d-8eab-4033-b6b6-ebce8724011e)

If a layout scrolls horizontally, the rail can scroll off-screen or remain fixed. To distinguish that content is scrolling underneath the rail, use a divider or add elevation to the rail.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0gtjndl-K%20-%20Side%20Scroll%20RENDER%20-2x_240821.mp4?alt=media&token=629dce43-bf6e-4ad6-89ca-71bb39bec10e)


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmbs2jj48-GM3_Expressive_Nav-Rail_Guidelines_30_IA_v01%20(1).mp4?alt=media&token=cbb06c17-5435-4dc4-a925-655fb6d559f3)

### Selection

When a destination is tapped, the destination screen uses a [top level](/m3/pages/motion-transitions/transition-patterns#f852afd2-396f-49fd-a265-5f6d96680e16) transition pattern. In addition, the icon becomes filled and the active indicator expands from the center of the icon.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0fvmjbe-L%20-%20NAV%20Selection%20-%20RENDER%202x_240821.mp4?alt=media&token=cd5079bc-e9e6-4dc1-952d-a07105f70120)

### Back

On Android, a gesture called predictive back allows people to swipe left or right on the screen to go back or dismiss modal components.

- Previous screen is revealed in a preview to signal the destination

- Predictive back only applies to the **modal expanded** navigation rail.

A list of compatible components is available on the [gestures page](/m3/pages/gestures/).


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmapifi58-GM3_Expressive_Nav-Rail_Guidelines_PredictiveBack_IA_V02.mp4?alt=media&token=95c874c8-3be9-48c9-bbf3-3f1edb7cab09)

### Selection

When a destination is tapped, several transitions occur:

- A touch ripple appears
- The outgoing icon color changes from a brand color to grey
- The outgoing icon moves down to align with the deactivated tabs, and then the label fades out
- The incoming icon color changes from grey to a brand color
- The incoming icon moves up, and the incoming label fades in


![A tapped destination transitions from inactive to active](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fktug7t17-3_Mid.png?alt=media&token=95c45efd-2c43-4b6a-90c7-fb429faa565f)
*Tapping a destination changes the outgoing icon color to grey, aligns the icon with deactivated tabs, allowing for the incoming icon to move up and change to a brand color*

starNote:This is a rapidly changing space. Guidelines are primarily intended for designers at this time. Find what’s implemented in code in the [design kit](https://www.figma.com/design/cVjQvQ0moD8wkPWH2rn2c6/XR?node-id=294149-10229&t=AFAz42CCMXa5470T-4).

Extended reality (XR) interfaces have special design requirements, like showing apps in 3D space. Material has XR-specific navigation rails with custom specs and guidance. See [XR developer documentation](http://developer.android.com/design/ui/xr/guides/foundations) for more details.

## Variants

There are two variants of navigation rail orbiters: the contained FAB and spatialized FAB navigation rails.


![Navigation bar orbiters with a contained FAB and a spatialized FAB.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmaffw03x-n.png?alt=media&token=e680c62c-dbec-434f-b520-3c26a949eb3c)
*1. Contained FAB rail
2. Spatialized FAB rail*

## Anatomy


![Diagram of navigation rail orbiter identifying 9 internal elements of the component.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmaffwe2f-n.png?alt=media&token=4c0eb5aa-b543-4b2d-8841-4801fbcd97b0)
*1. Container
2. Active indicator
3. Large badge (optional)
4. Badge (optional)
5. Large badge label (optional)
6. Label text
7. Icon
8. Embedded or spatialized FAB (optional)
9. Menu icon (optional)*

## Color & elevation

On XR, color is used to highlight elevated UI elements and orbiters. With [spatial elevation](https://developer.android.com/design/ui/xr/guides/spatial-ui#spatial-elevation), the navigation bar displays above the spatial panel, on the Z-axis. Color communicates elevation on UI elements and orbiters. Elevated nav rails can use any of these color options:


![4 versions of elevation color strategy.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmaffwte4-n.png?alt=media&token=0aca4594-af07-4400-a169-d5c7457f4e2a)
*1. Surface container with tertiary FAB
2. Surface container high with tertiary fixed dim FAB
3. Surface container highest with tertiary fixed dim FAB
4. Tertiary container with primary FAB*

## Measurements


![Measurements and padding for navigation rail orbiter with contained FAB.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmaffxdwj-n.png?alt=media&token=0b5ce234-1a62-4d70-ae6a-77f6700e5e9b)
*Navigation rail orbiter padding and measurements with contained FAB*


![Measurements and padding for navigation rail orbiter with spatialized FAB.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmaffxpih-n.png?alt=media&token=ccad0142-d640-4813-8a1a-cfb99a554bce)
*Navigation rail orbiter padding and measurements with spatialized FAB*

## Usage

In full space, a navigation rail can appear in an orbiter for a more immersive experience. Currently, spatial capabilities, such as orbiters, are only available in full space. In home space, use a regular navigation rail on the same plane as the body content to mimic a 2D experience.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm4cmf60w-navrail_01.mp4?alt=media&token=295cb22d-7530-4a52-9a20-18a2f310899b)

## Behavior

### Global context

Intended for global navigation, a nav rail orbiter should be centered along the left or right edge of the app it controls. It stays anchored to the app during layout or content changes to ensure controls are easy to find.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fma4acdxe-XR_Nav-Rail_Behavior_01_V02.mp4?alt=media&token=9414cfde-c786-4936-831e-4a0a3d3e89cb)

### Local context

Don’t place a navigation rail orbiter in local context or [between spatial panels](/m3/pages/navigation-rail/xr#519a112b-51d6-4200-96a9-54af92fb787d). Local placement can make controls hard to find. Nav rails are designed for app-level navigation, so should only use the global context.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fma4acsak-XR_Nav-Rail_Behavior_02_V02.mp4?alt=media&token=8a2a2f30-3484-4757-94a1-6fa7d256e6b1)

## Placement

### Navigation context

The position of the navigation rail orbiter should communicate its navigational context:

- Use **offset positioning** for global actions that affect the overall app experience
- Use **inset positioning** for local actions that are specific to a spatial panel

A navigation rail orbiter can either overlap or be positioned adjacent to spatial panels with a 20dp margin for visual separation.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm4cmibon-navrail_02.mp4?alt=media&token=c132f2cc-74da-4941-aa64-1ed3ef57c3ab)

### Inset positioning

Don’t obstruct content. To ensure a balanced and uncluttered layout, a navigation rail orbiter should overlap spatial panels by 12dp and no more than half their width.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm4cmliqr-navrail_03.mp4?alt=media&token=7a97c857-ff26-44e9-a28b-826411da15cc)

### Vertical alignment

A navigation rail orbiter can be aligned to the top, middle, or center of spatialized panels, providing different levels of visual prominence and accessibility.

Align the navigation rail orbiter based on the specific design and user experience goals for the application.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm4cmooik-navrail_04.mp4?alt=media&token=405385c1-aae1-493e-bf4b-b5dd5a8a8ccb)

The navigation rail orbiter placement shouldn't exceed the height of adjacent spatial panels.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm4cmqshd-navrail_05.mp4?alt=media&token=4031093e-48a3-4455-84c2-3e929e225dd9)

### Spatial panel alignment

Avoid placing a navigation rail orbiter between spatial panels. This negatively affects the interface structure.

For layouts that span more than two spatial panels, consider using a navigation bar orbiter.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm4cmtuvy-navrail_06.mp4?alt=media&token=3f0ba332-4e3d-4857-a5db-0f6b7331f6d5)

## Spatialized FAB

There are two variants of navigation rail orbiters with different FAB treatments:

- 

**Contained FAB rail:** A contained FAB within the rail. This offers a compact and familiar layout.
- 

**Spatialized FAB rail:** The FAB becomes an orbiter of it’s own and is placed outside the navigation rail orbiter. Use this for higher emphasis and a distinct spatial effect.

Use the spatialized FAB rail to emphasize key actions and leverage XR hierarchy. Use the contained FAB rail to be more subtle, and align the experience with the baseline navigation bar.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm4cmvtu0-navrail_07.mp4?alt=media&token=6434118a-824e-4513-ad25-0230b3b08a03)

To maintain visual association, place the spatialized FAB in close proximity to the navigation rail orbiter. Material recommends a 20dp margin.

The spatialized FAB can be placed above or below the navigation rail orbiter.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm4cmyijj-navrail_08.mp4?alt=media&token=03c63c28-32cb-42f8-a56e-58be8ea2fb8b)

While the spatialized FAB and navigation rail orbiter are typically positioned together, their placement is adaptable.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm4cn0kcj-navrail_09.mp4?alt=media&token=f9165d61-d8e1-4b44-ba35-f238087e45cf)

## Accessibility considerations

[XR accessibility](https://developer.android.com/design/ui/xr/guides/get-started#make-app) guidelines are still evolving. XR navigation rails should follow applicable Material [nav rail accessibility standards](/m3/pages/navigation-rail/accessibility).

## Use cases

People should be able to do the following using the assistive technology:

- Navigate between navigation destinations
- Select a particular navigation destination from a set
- Get appropriate feedback based on input type

## Interaction & style

When a navigation item is tapped, the active indicator appears, providing the following feedback to the user that it is selected:

- A ripple passes through the indicator
- The icon switches from outlined to filled
- The icon and text change color

When hovered, the hover state appears, providing a visual cue that the destination is interactive.


![Colorful, purple navigation rail shown collapsed and expanded.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0guemtv-01-static.png?alt=media&token=3758e551-ad90-4db7-92a7-d8c19e95e973)
*Touch: Tap*


![Tap indicator on a collapsed nav rail.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0guep9g-02-static.png?alt=media&token=bac52c48-506a-47e5-8124-90a1d960485a)
*Cursor: Hover, Click*

The target area for expanded navigation rails spans the full width of the container, even though the active indicator visually hugs the content.


![Touch indicator on a nav rail.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0gud02c-03-static.png?alt=media&token=e49fb92a-9671-4422-8c7f-25750ab3ca13)
*Touch: Tap*

Use a filled icon for the active destination and outlined icons for inactive destinations.

Active and inactive icon colors need sufficient contrast against the container.


![Navigation rail with filled element.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0fut28q-04.png?alt=media&token=2d41d871-683b-485e-befb-2770dfb712d6)
*Use the default color scheme to ensure proper contrast and emphasis on the active destination*


![Nav rail with multiple navigation destinations and multi-colored contrast.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0fut791-05.png?alt=media&token=c9ae906c-6e53-474e-83a7-c26f630a8eb5)
*Don’t use more than two colors for destinations or low-contrast colors in the navigation rail. This will make distinguishing active items difficult.*

If an icon doesn’t have a filled style, use the semibold icon weight instead.


![Icon button with semibold weight, without filled options.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm2kvazns-09.png?alt=media&token=91143806-b128-41b8-a02f-9c97f171d073)
*An icon with no filled option should use the semibold weight when active*

### Text scaling and truncation

When someone sets their device to show a larger text size, the navigation rail items should grow vertically to accommodate larger labels while retaining the default padding. It’s okay for scaled text to wrap in navigation items.

To remain accessible, ensure the full label is always visible on-screen at up to 2x text sizing. Beyond this size, text can truncate.


![Nav rail with text scaled to 1.5x size. All labels are on one line.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm36brxfu-04.png?alt=media&token=a23060fe-42ba-4e21-8eec-36fe12115a0d)
*Text scaled to 1.5 size*


![Nav rail with text scaled to 2x size. Some labels wrap to two lines.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm36bs1jw-05.png?alt=media&token=fd8f29ed-e05c-4397-8443-13902974567a)
*Text scaled to 2x size*

### Initial focus

Initial focus lands directly on the first interactive item, whether it’s the menu, the FAB, or the first navigation item.

From the FAB or menu, **Tab** brings the person to the navigation items. **Tab** or **Arrows** then navigate between items.


![Arrows help people move between pages.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0futkj8-07.png?alt=media&token=96c198ca-69ed-4388-81d4-76785f7c7960)
*Use arrows to move between navigation items*


![Space/enter help people choose a navigation destination.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0futowb-08.png?alt=media&token=6a263d5a-f534-4cc7-b669-c5828633d338)
*Use space/enter to activate the focused navigation item*

### Visual indicators

Icons give the dominant cue of the navigation state. Use a filled icon for the selected destination to contrast with outlined icons for the non-selected destinations.


![Nav bar with an active, filled icon button.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0futxf6-09.png?alt=media&token=813363dc-6ed6-40c4-951b-d996abdf8b44)
*Use a filled icon variant on the selected navigation item to differentiate from inactive navigation items*


![Selected navigation item without filled icon style.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0fv1bxc-10.png?alt=media&token=07e1a3b5-ec7f-48ef-b48b-d8e4c1aaa4e1)
*Avoid using the same unfilled icon style for both selected and unselected items because it lacks important visual feedback cue*

## Keyboard navigation

| Keys | Actions |
| --- | --- |
| Tab / Arrows | Navigate between interactive elements |
| Space / Enter | Selects an interactive element |

## Labeling elements

The accessibility label for a navigation item is typically the same as the adjacent text label.

When the visible UI text is ambiguous, accessibility labels need to be more descriptive. For example, a navigation item visibly labeled **Recent** would benefit from additional information in its accessibility label to clarify the destination's intent.

Note: On Android Views (MDC-Android), a more descriptive accessibility label is not available and the role is not announced.


![“Maps” is both the icon label text and the accessibility label.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm0fuvxlx-11.png?alt=media&token=ec0c7c6d-3c55-4515-81d8-6c0a220e1f92)
*While the visible label text reads **Recent**, the accessibility label for this switch clarifies its function: **Recent images***
