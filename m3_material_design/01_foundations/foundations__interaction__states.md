---
title: "States"
slug: "foundations/interaction/states"
url: "https://m3.material.io/foundations/interaction/states"
---

# States

> States show the interaction status of a component or UI element

- States have two visual indicators to ensure accessibility
- States can be combined, such as selection and hover
- Apply states consistently across components

## Resources

[](http://goo.gle/m3-design-kit)

| Type | Link | Status |
| --- | --- | --- |
| Design | Design Kit | Available |

1. [Enabled
](/m3/pages/interaction-states/applying-states#39b2fc90-01db-41b5-b6f8-47be61ed1479)An enabled state communicates an interactive component or element.

2. [Disabled](/m3/pages/interaction-states/applying-states#4aff9c51-d20f-4580-a510-862d2e25e931)
A disabled state communicates an inoperable component or element.


![Enabled button has a strong contrast between container and text.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fly2n4rwz-1p-Enabled%20button.png?alt=media&token=d51bf56c-de76-4d8c-bcb7-29ef54bf1dd8)
*Enabled button*


![Disabled button has low contrast grey text on grey container.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fly2n5ghx-1p-Disabled%20button.png?alt=media&token=29088245-b4e1-4c89-8be5-2bd64aafca55)
*Disabled button*

3. [Hover](/m3/pages/interaction-states/applying-states#71c347c2-dd75-485b-892e-04d2900bd844)
A hover state communicates when a user has placed a cursor above an interactive element.

4. [Focused](/m3/pages/interaction-states/applying-states#bc6d6853-48ef-490e-8076-448e89e69f0f)
A focused state communicates when a user has highlighted an element, using an input method such as a keyboard or voice.


![Cursor positioned over a button.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fly2ndeib-1p-Hovered%20button.png?alt=media&token=82061a85-c778-492f-9faf-93ed72a377bf)
*Hovered button*


![The focused button has a strong contrast between container and text.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fly2negg3-1p-Focused%20button.png?alt=media&token=fd25e436-79cc-46fc-97f6-f0bc86545c5e)
*Focused button*

5. [Pressed](/m3/pages/interaction-states/applying-states#c3690714-b741-492d-97b0-5fc1960e43e6)
A pressed state communicates a user tap.

6. [Dragged](/m3/pages/interaction-states/applying-states#c97582c4-5fef-42ce-9c34-71f8dcc5b8ad)
A dragged state communicates when a user presses and moves an element.


![The pressed button has a strong contrast between container and text.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fly2qd3dz-1p-Pressed%20button.png?alt=media&token=dbcd674e-5bb0-4176-8362-e3451b8d538b)
*Pressed button*


![Dragged chip](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fly2qdrga-1p-Dragged%20chip.png?alt=media&token=84e211bb-d118-44c7-b4d9-cb4b86e9f884)
*Dragged chip*

## State layers

A state layer is a semi-transparent covering on an element that indicates its state. State layers provide a systematic approach to visualizing states by using opacity. A layer can be applied to an entire element or in a circular shape and only one state layer can be applied at a given time.

To transition from an enabled style to a stateful style requires the addition of a state layer. 

The state layer is an overlay with a fixed opacity for each state and uses the same color as the content. 

For example, if the enabled style uses the **secondary container** color for the container and **on secondary container** for content, the state layer will be an overlay using the **on secondary container** color.  

If the enabled style uses the **surface** color for the container and the **primary** color role for content, then the state layer will be an overlay using the **primary** color.


![State layer sandwiched between the container and content.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fly2qh1yi-1p_State%20Layers_1.png?alt=media&token=40e0a636-b5f9-4547-a0ad-90cccc333d70)
*1. Container
2. State layer
3. Content*

The size of state layers is 40dp while the interactive target size is 48dp.


![Interaction target is larger than the state layer.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fly2qhye4-1p_State%20Layers_2.png?alt=media&token=772b1316-6175-4fb3-bae0-d523c06b125b)
*1. State layer
2. Interactive target*

### On colors

By default, a component’s state layer color is derived from the color of its content, either the color of an icon or label text if no icon is present.

An [on color](/m3/pages/color-roles#19e75989-7485-4f5b-a769-940c4e4364bc) is a color role used by the content. Each container color has its own corresponding on color. 

For example, if a container color is **secondary container**, the content will use the **on secondary container** color role.


![State layer sandwiched between  the content and container.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fly2qisol-1p_on_colors.png?alt=media&token=8c883e0d-7010-4c6e-8a25-fc4aa4d92370)
*Order of surface layers shows the state layer (2) between the container (1) and content (3) layers*

### State layer tokens & values

The state layer uses a fixed percentage for the opacity for each state. A state layer uses the color used by content (usually the [on color](/m3/pages/color-roles#19e75989-7485-4f5b-a769-940c4e4364bc)) and the percentage opacity for its respective state.


![The opacity values of four overlay states.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fly2qnx2g-1p_state_layer_tokens%26value.png?alt=media&token=bf0becab-a73a-4693-bd6b-bd89c858de30)
*Four overlay states and their values:

1. Hover +8% opacity 
2. Focus +10% opacity 
3. Press +10% opacity
4. Drag +16% opacity*

### Activated states

Unlike hover, focus, pressed, and dragged states that use state layers, the container and content of activated components change color directly. 

Activated components use the **secondary container** color for the component container and change the content color to **on secondary container**.


![Activated flights container with color changed compared to containers for hotels, and restaurants both not in activated state. ](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flfrg32kk-1p_Activated.png?alt=media&token=d0fe8b48-f43f-4af2-b466-0c87ca0da817)
*Activated state*

## Enabled

An enabled state communicates an interactive component or element. Enabled states use the default styling for each interactive component.


![Enabled state of 4 components.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fly2qpw4a-1.png?alt=media&token=0bec13e6-8bd8-4dc3-9439-6e9d9ff68b4e)
*Enabled states for:

1. Button
2. FAB
3. Switch
4. Text field*

## Disabled

A disabled state communicates when a component or element isn’t interactive. This state is visually communicated through color changes and reduced elevation.

**Disabled states don't need to meet Material's contrast requirements.**


![Low opacity solitary button labeled disabled, indicates a  disabled/inoperable state.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fly2qrzig-2.png?alt=media&token=fc37d2ef-40ff-4d49-9c55-d1c580dae532)
*Disabled button*

Disabled states **are** inherited by action, selection, and input components:

- Buttons
- Cards
- Checkboxes
- Chips
- List items
- Radio buttons
- Switches
- Text fields


![Inoperable state of 4 components.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fly2qtg0r-3.png?alt=media&token=469e37f6-f896-42c0-8d8a-2024ad74a6ee)
*Disabled states for:

1. Checkbox
2. Icon button
3. Radio button
4. Segmented button*

Disabled states **aren't** inherited by communication, containment, navigation, and some actions components: 

- App bars
- Badges
- Dialogs
- Floating action buttons (FABs)
- Menus
- Navigation bar, drawer, and rail
- Sheets
- Tabs
- Tooltips


![Screen erroneously showing edit FAB in inoperable state, though the edit function is unavailable.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fly2quyuj-4.png?alt=media&token=3366711f-aafa-4b7e-ab99-1a0f6c67378e)
*If the action represented in the FAB is unavailable, the FAB shouldn't appear*

### Behavior

Disabled components can’t be focused, dragged, or pressed, and they don’t change state when tapped or hovered over.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fly2vtf7p-GM3-Foundations-Interaction-States-DisabledBehavior-v01.mp4?alt=media&token=929e0dbc-d1ff-4268-8c6a-d41e1a476f0e)

There can be any number of disabled states in a layout.


![Disabled components on a screen.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flyml0ix7-6.png?alt=media&token=c6c1365d-7e53-471d-a1e4-8babbf37b298)
*1. Disabled redo icon button
2. Disabled checklist icon button*

## Hover

Hover states are initiated by the user pausing over an interactive element using a cursor.

The lower-emphasis surface overlay for hover states can be applied to the entire component, elements within a component, or as a circular shape over part of the component.


![Cursor moves toward button which reads “enabled” and when the cursor touches the button text changes to “hovered.”](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fly2vwc74-7.png?alt=media&token=728c0b8a-43b5-49a2-8640-a0c368d2e966)
*Hovered button*

Hover states **are** inherited by action, selection, and input components:

- Buttons
- Cards
- Checkbox
- Chips
- Date and time pickers
- List items
- Slider
- Switch
- Text fields


![Hover state of 4 components.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fly2vxl8i-8.png?alt=media&token=4f810fc7-a579-445c-a178-ebc7a19caeb2)
*Hovered states for:

1. FAB
2. Icon button
3. Chip
4. Segmented buttons*

Hover states **aren’t** inherited by communication, containment, or navigation components: 

- App bars
- Badges
- Dialogs
- Menus
- Navigation bar, drawer, and rail
- Sheets
- Tabs


![Mobile screen with the whole  app bar wrongly in hover state.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fly2vymng-9.png?alt=media&token=5cf648b1-7239-43fd-a65f-9198fd11f289)
*The individual components that are actionable within the app bar inherit hover states, not the whole app bar*

### Behavior

Hover states are initiated by the user pausing over an interactive element using a cursor.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fly2w0utg-GM3-Foundations-Interaction-States-HoverBehavior-1-v01.mp4?alt=media&token=d62f89ca-33da-4b39-97a9-f766f45ba432)

Hover states can be combined with focused, activated, selected, or pressed states.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmq7s3uq1-03%20-%20GM3-Foundations-Interaction-States-HoverBehavior-2-v02.mp4?alt=media&token=76495a04-e402-450d-b673-ecfaabfa552a)

There can only be one hover state at a time in a layout.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fly2w8zl5-GM3-Foundations-Interaction-States-HoverBehavior-3-v01.mp4?alt=media&token=e1229bd4-baf1-44c7-9f68-3f0f56c1afb7)

## Focused

A focused state communicates when a user has highlighted an element using a keyboard or voice. Focus states apply to all interactive components.

The higher-emphasis surface overlay for focused states can be applied to the entire component, elements within a component, or as a circular shape over part of the component.


![A button in focused state.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fly2wa79s-13.png?alt=media&token=7bf4ddd9-516f-4ab7-b20e-e19ab3a26cdd)
*Focused button*

Focus states **are** inherited by action, selection, and input components:

- Buttons
- Cards
- Checkbox
- Chips
- Date and time pickers
- List items
- Selection controls
- Text fields


![Focus state of 4 components.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fly2wb5y9-14.png?alt=media&token=292090a2-1e6f-4e36-84ba-5ebafeb0e3af)
*Focused states for:

1. FAB
2. Icon button
3. Chip
4. Segmented buttons*

Focus states **aren’t** inherited by most communication, containment, or navigation components:

- App bars
- Badges
- Banner
- Card
- Dialogs
- Navigation bar, drawer, and rail
- Sheets


![Mobile screen showing  entire app bar in focus state, which is an error.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fly2wctgk-15.png?alt=media&token=871fc6c8-7cf7-48d9-aed1-4f876be97c67)
*The individual components that are actionable within the app bar inherit focus states, not the whole app bar*

### Keyboard focus indicator

Many people use the **Tab** key or other shortcut to navigate the interactive elements of a web page, like links, buttons, and chips.

When an element is tabbed to, it appears in its focused state with a ring-like **keyboard focus indicator**. This indicator helps web users know where they are on the page.

While focused, an element can be acted on with the keyboard.


![A single filled button in focused state, displaying the keyboard focus indicator.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fly2wdmfh-16.png?alt=media&token=68221847-c082-4963-8058-4e75a9c38465)
*Keyboard focus indicator for a filled button*

### Behavior

Focus states are initiated by the user by pressing the **Tab **key on the keyboard (or equivalent).

Focus states can be represented in combination with hover, activated, or selected states.


![A single filter chip simultaneously in selected state and focus state.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fly2wf3qd-17.png?alt=media&token=67048848-6bba-4299-9dfd-3b5fe3c753ec)
*A selected filter chip in both selected and focused states*

There can only be one focus state at a time in a layout.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fly2whda4-GM3-Foundations-Interaction-States-FocusedBehavior-v01.mp4?alt=media&token=7154f56e-a56d-423e-8de2-fea1290c9ef4)

## Activated

Activated states indicate which item from a set of options is currently being viewed. They are initiated either by default or user choice, using input methods such as a tap, cursor, keyboard, or voice input. 

Activated states are higher emphasis and signified by an overlay, color change, or other visual treatments applied to elements or segments within a component.


![The activated tab has a different color and shape than the enabled tabs.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Flfolqn12-3p_activated_1.png?alt=media&token=2db1c6af-1682-476e-8a49-7ca93e7b6330)
*Activated tab*

An activated state differs from a selected state because it communicates a highlighted destination.

Activation states **are** inherited by items within some navigation components: 

✓ Navigation bar, drawer, and rail
✓ Tabs


![The tab and navigation drawer are both in activated states.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Flfolsseo-3p_activated_2.png?alt=media&token=44703c08-3bcd-4475-868f-8902f0aad1a1)
*Tab (1) and navigation drawer (2) with activated states*

Activation states **aren’t** inherited by action, communication, containment, selection, or input components: 

✗ App bars
✗ Badges
✗ Buttons
✗ Checkbox
✗ Chips
✗ Dialogs
✗ Sheets
✗ Sliders
✗ Switch
✗ Text fields


![Activated state applied to a button in error.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Flfolueb4-3p_activated_3.png?alt=media&token=362222a2-ad95-4eae-9334-46c37c281508)
*Activated states are applied to navigational components, like tab items, to indicate a destination, not action components, like a button*

### Behavior

Activated states can be represented in combination with hover and focus states.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Flfom53fi-3P-activated%20behavior.mp4?alt=media&token=4b2f5111-e74c-482a-b79c-ba2d5db457ab)

Activated states can be represented in combination with hover and focus states.


![Screen showing inbox in focus and activated states.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Flfom7o2z-3p_activated_behavior_2.png?alt=media&token=d2028996-ef61-4df6-88f9-a054032e34ed)
*Activated states can be represented in combination with hover and focus*

Within a single set of options, only one activated state may be present at a time.


![Email settings navigation list “inbox”is activated. The selection components has one activated state as well.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Flfomaa0z-3p_activated_behavior_2x_3.png?alt=media&token=3477507b-fd06-406b-8721-c3540983f949)
*This activated navigational list differs from the selection components in the same layout*

## Pressed

A pressed state communicates a user-initiated tap or click via cursor, keyboard, or voice input. This state applies to all interactive components.

Pressed states trigger a change in composition and should be high-emphasis.

A ripple overlay signifies a pressed state. It can be applied to an entire component or elements within a component, or as a circular shape over part of the component.


![Button using a ripple overlay to signify it’s in a pressed state.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fly2wj03t-25.png?alt=media&token=776a2614-2e05-4545-96d3-a6bc53947375)
*Pressed button*

Some components, such as buttons or cards, can inherit elevation to signify a pressed state.


![Button using elevation to signify it’s in a pressed state.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fly2wk46q-26.png?alt=media&token=b1e55cc3-9ee8-44b0-8722-0a4a23636069)
*Pressed button with elevation*

Pressed states **are** inherited by action, selection, and some containment components: 

- Buttons
- Cards
- Checkbox
- Chips
- List items
- Text fields


![Four components shown in pressed state.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fly2wl23l-27.png?alt=media&token=b2dff0e0-1bf2-47fb-a689-040b778ffcf1)
*Pressed states for:

1. FAB
2. Icon button
3. Chip
4. Segmented button*

Pressed states **aren’t** inherited by communication, navigation, or some containment components: 

- App bars
- Badges
- Bottom navigation
- Dialogs
- Menus
- Sheets
- Tabs


![Mobile screen showing  entire app bar in pressed state is an error.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fly2wo5k7-28.png?alt=media&token=93cc4266-18d2-497d-a0d9-55a73988142f)
*The individual components that are actionable within the app bar inherit pressed states, not the whole app bar*

### Behavior

Pressed states are initiated by user keyboard or voice input on an interactive element.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fly2wqo0t-GM3-Foundations-Interaction-States-PressedBehavior-1-v01.mp4?alt=media&token=878dc910-8f6c-4cb6-bfdb-3fb88a2ee40a)

Pressed states can be combined with hovered, focused, activated, or selected states.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fly2wrufa-GM3-Foundations-Interaction-States-PressedBehavior-2-v01.mp4?alt=media&token=37adbaa0-9c13-4684-90fe-ed200d7f9013)

There may only be a single pressed state at a time in a layout.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fly2wt4k8-GM3-Foundations-Interaction-States-PressedBehavior-3-v01.mp4?alt=media&token=6f49e3db-4639-4ed8-8556-54a86ee743c4)

## Dragged

A dragged state occurs when a user presses and moves an element or component. Dragged states should be low emphasis, to avoid distracting users from their task.

Dragged states use a lower emphasis overlay. It can be applied to the entire component or to elements within a component.

Some components, such as list items, chips, or cards, can inherit elevation to signify a dragged state.


![List item shown in dragged state.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fly2wu37e-32.png?alt=media&token=fefabf35-2cb5-4fdd-b0bb-4cee4442d148)
*Dragged list item*

Dragged states **are** inherited by some containment and selection components: 

- Cards
- Chips
- List items
- Sliders


![A chip and a card both shown in dragged state.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fly2wv77r-33.png?alt=media&token=a3ace83a-7173-47cb-9fc2-60646fe0dd47)
*Dragged states for:

1. Chip
2. Card*

Dragged states **aren’t** inherited by action, communication, navigation, or some containment components: 

- App bars
- Badges
- Buttons
- Dialogs
- Menus
- Navigation bar, drawer, and rail


![Mobile screen with app bar in dragged state is an error.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fly2ww2lq-34.png?alt=media&token=33a7d3f2-493d-4137-a038-289ddac5488c)
*Components like an app bar that require consistent placement should not inherit dragged states*

### Behavior

Dragged states are initiated when users touch and hold elements, using an input method such as a tap or click.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fly2wxglg-GM3-Foundations-Interaction-States-DraggedBehavior-1-v01.mp4?alt=media&token=1a50ed48-7d69-4df1-b0d2-37034ad3431b)

There may only be a single dragged state at a time within a layout.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fly2wyr5e-GM3-Foundations-Interaction-States-DraggedBehavior-2-v01.mp4?alt=media&token=6ea2d536-318a-4749-9233-52eefb9baab5)
