---
title: "XR components"
slug: "foundations/xr/components"
url: "https://m3.material.io/foundations/xr/components"
---

# XR components

> Learn how Material 3 Expressive components adapt to extended reality devices 

- 

Leverage depth and space to create believable environments
- 

Design for comfort to minimize motion sickness and physical strain
- 

Map interactions, like gaze and gestures, to real-world expectations
- 

Group UI elements on floating spatial panels
- 

Provide feedback through spatial audio, haptics, and visual cues


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmoxffyh5-toolbars-xr-06%20(2).mp4?alt=media&token=6e469f8c-03d3-48d4-9b78-9977c32d6fd8)

## Material XR components

The following Material components are adapted for XR:

- 

[App bars](/m3/pages/xr-components/app-bars)
- 

[Dialogs](/m3/pages/xr-components/dialogs)
- 

[Navigation bar](/m3/pages/xr-components/nav-bar)
- 

[Navigation rail](/m3/pages/xr-components/nav-rail)
- 

[Toolbars](/m3/pages/xr-components/toolbars)


![Side view of an XR dialog showcasing spatial elevation.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmoxgb0ta-06.png?alt=media&token=b18f5559-f8f7-4203-b59e-c7ee2755b6b0)
*A dialog can be spatially elevated on the Z-axis above spatial panels*

starNote:XR guidelines are primarily intended for designers. Find what’s implemented in code in the [design kit](https://www.figma.com/community/file/1035203688168086460).

Extended reality (XR) interfaces have special design requirements, like showing apps in 3D space. Material has an XR app bar with custom specs and guidance. See [XR developer documentation](https://developer.android.com/design/ui/xr/guides/foundations) for more details.

## Variants & configurations

There is one app bar orbiter. It closely aligns with the small app bar. It can be configured to be center-aligned or left-aligned.


![Center and left-aligned app bar orbiters.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmp5ntwab-01-3P.png?alt=media&token=2957c931-0cbd-43e0-9817-180865d9d141)
*1. Center-aligned app bar
2. Left-aligned app bar*

## Anatomy


![Diagrams of app bar orbiters identifying 4 internal elements.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmp5nuh2w-02-3P.png?alt=media&token=c1c9552e-52b3-4d01-bc82-bf895d38f2b8)
*1. Container
2. Headline
3. Trailing icons
4. Leading icon*

## Color & elevation

XR uses color to communicate the elevation of UI elements and orbiters. With [spatial elevation](https://developer.android.com/design/ui/xr/guides/spatial-ui#spatial-elevation), the app bar displays above the spatial panel on the Z-axis. Elevated app bars can use any of these color options:


![3 versions of app bar elevation color strategy.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmp5nuy7e-03-3P.png?alt=media&token=386a2df0-b2db-448a-b116-e43036aa6d86)
*1. Surface container
2. Surface container high
3. Surface container highest*

## Measurements


![Diagrams with measurements and padding for app bar orbiters.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmq7w2v1g-04-3P.png?alt=media&token=c0d818b6-9e1a-4d92-85de-d1b8e55a581f)
*Measurements and padding for app bar orbiters*

## Usage

An app bar can appear in an orbiter for a more immersive experience. Currently, this spatial capability is only available in full space. In home space, use a regular app bar on the same plane as the body content to mimic a 2D experience.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmowvrdts-XR_Top-App-Bar_Usage_01_IA_v03%20(1).mp4?alt=media&token=e1562dba-a3c5-47c5-8684-03296fa98418)

## Behavior

### Global context

When placed in global context, the orbiter is centered at the top of the app it controls. 

It stays anchored to the app during layout or content changes. 

This ensures navigation elements are always easy to find and use.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmowvuyn4-XR_Top-App-Bar_Behavior_01_IA_v03%20(1).mp4?alt=media&token=fd8f18d8-abfd-40af-b56b-8ba4fae68525)

### Local context

When placed in local context, the orbiter is centered at the top of the spatial panel it controls. 

It repositions in response to layout or content changes.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmowvzhjn-XR_Top-App-Bar_Behavior_02_IA_v03%20(1).mp4?alt=media&token=b99a382c-3120-4735-9d93-65be00ff1c0c)

### Additional app bars

In most cases, apps should only have one app bar orbiter, placed in global context.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmoww3e8a-XR_Top-App-Bar_Behavior_03_IA_v03%20(1).mp4?alt=media&token=b91ce31f-fba5-4d03-af5f-0e79b831fc68)

## Placement

### Offset & inset positioning

In full space, an app bar orbiter can be positioned:

- 

Offset 20dp from the spatial panel
- 

Inset 12dp, overlapping spatial panel without obstructing content


![App bar orbiter with offset positioning.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmowwbs16-09.png?alt=media&token=3fb5177d-cc1e-4a78-823a-ee721def6c50)
*An app bar orbiter with a 20dp offset creates visual separation from the spatial panel*


![App bar orbiter with inset positioning that obstructs content..](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmowwe02f-10.png?alt=media&token=8a89faa8-6e01-4e00-93c9-740a22b9eedb)
*Don’t overlap the app bar orbiter and spatial panel more than 12dp*

### Horizontal alignment


![An app bar orbiter placed within the bounds of its spatial panel.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmowwevxe-11.png?alt=media&token=bd0e4a8c-d279-4391-bc8e-d3487772377e)
*Always align the app bar orbiter within the bounds of nearby spatial panels*


![An app bar orbiter that extends beyond the width of its spatial panel.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmowwgpq6-12.png?alt=media&token=aaa39a97-16d1-468c-8089-2cc25f6a0bab)
*The app bar orbiter shouldn’t exceed the width of adjacent spatial panels*

### Spatial panel alignment

By default, app bar orbiters are center-aligned to the spatial panel. Their width and placement can be adjusted to accommodate specific user needs, such as improved ergonomics or [right-to-left (RTL) languages](/m3/pages/bidirectionality-rtl).


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmowxpzvt-XR_Top-App-Bar_Spatial-pane-alignment_01_IA_v03%20(1).mp4?alt=media&token=f797df52-a9ab-45c9-84d2-e80f8a0e824c)

### Width boundaries

An app bar orbiter’s width should adjust to stay in a person’s [field of view](https://developer.android.com/design/ui/xr/guides/spatial-ui#where-place). 

This makes crucial navigation elements easy to find.


![An app bar orbiter with a width that fits in a person’s field of view.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmowy6885-14.png?alt=media&token=f1863e50-7df0-4855-a040-b174905b4dda)
*Adjust the width of the app bar orbiter to fit in a person’s field of view*

It’s not recommended to increase the width of an app bar orbiter beyond a person’s natural [field of view](https://developer.android.com/design/ui/xr/guides/spatial-ui#where-place). 

This creates a visual imbalance and makes it difficult to find navigation elements.


![An app bar orbiter that exceeds the panel’s width and a person’s field of view.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmowy73ja-15.png?alt=media&token=099daf73-2edb-44d7-8d32-6bc52a374044)
*Avoid expanding the app bar orbiter beyond the adjacent panel’s width and a person’s field of view*

### Adaptable width

When placed in a local context, an app bar orbiter can expand to the width of its adjacent spatial panel.   

Be sure the orbiter stays in a person’s field of view, and test for usability.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmp1yzz8i-XR_Top-App-Bar_Adaptable-width_01_IA_v03.mp4?alt=media&token=a10d0813-23ba-40f8-931f-89492e8f9d4d)

## Accessibility considerations

[XR accessibility](/m3/pages/xr-design/accessibility) guidelines are still evolving. XR app bars should follow applicable Material [app bar accessibility standards](/m3/pages/app-bars/accessibility).

starNote:XR guidelines are primarily intended for designers. Find what’s implemented in code in the [design kit](https://www.figma.com/community/file/1035203688168086460).

Extended reality (XR) introduces spatial capabilities, such as using depth to make dialogs stand out from the background. Currently, [spatial dialogs](https://www.figma.com/community/file/1035203688168086460) are only available in full space. For home space, follow Material’s general [dialog guidance](/m3/pages/dialogs/overview).

## Color & elevation

XR uses color roles to communicate the elevation of UI elements. Dialogs can use two color options: **surface container high** or **surface container highest**.starNote:Color and elevation for spatial dialogs aren’t available in Jetpack Compose yet. These need to be customized manually.


![2 spatially elevated dialogs with surface-container-high and surface-container-highest color roles.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmp5rmvr7-01-3P.png?alt=media&token=1dde2ef8-1536-4fd5-932b-4701ab757581)
*1. Surface container high
2. Surface container highest*

For effective visual hierarchy, a dialog should be the most prominent element. 

Add a scrim behind a dialog to improve its visibility. Scrims prevent other content from being selected until the dialog action is complete.


![Dialog with surface-container-highest color and a scrim.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmox7g5yi-02.png?alt=media&token=7a21ccfd-5b6b-4840-a471-7dd6e803a83f)
*Make sure a spatial dialog’s color is higher than all other UI elements, and use a scrim*

The dialog should have the highest elevation in the product.

For example, if the dialog is **surface container high**, don’t use **surface container highest** for any other elements.


![Dialog with surface-container-high color and no scrim. An orbiter is at a higher elevation than the dialog.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmox7nrut-03.png?alt=media&token=91aeb244-3d82-4210-bcfc-a4588dafbd4a)
*If a dialog’s color is **surface container high**, don’t use **surface container highest** for any other element*

## Usage

Only use [basic dialogs](/m3/pages/dialogs/guidelines#97ac3858-3932-4084-ae8e-73e42b7cb752) in XR. This keeps the required action in the person’s [field of view](https://developer.android.com/design/ui/xr/guides/spatial-ui#where-place).


![Basic dialog in XR.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmox7s3sr-04.png?alt=media&token=5665a385-4ac8-4c18-a89d-7d3857645d09)
*A basic dialog elevated above an app in home space*


![Full-screen dialog in XR.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmox7ytt7-05.png?alt=media&token=592b5c9f-8a12-46d1-9660-eae5205e2334)
*Avoid using full-screen dialogs in XR. Required actions could appear beyond a person’s field of view.*

## Spatial dialogs

In full space, dialogs can be elevated spatially via [overrides](https://developer.android.com/develop/xr/jetpack-xr-sdk/material-design#use-enablexrcomponentoverrides). This helps dialogs stand out from their background in XR.


![Side view of basic dialog showcasing spatial elevation.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmox83eps-06.png?alt=media&token=dda2abbf-f23a-4c77-bb5d-bd5e429707e1)
*Side view of a basic dialog with spatial elevation in full space*

## Behavior

### Effect

The spatial dialog should scale uniformly. It also fades in when appearing, and fades out when disappearing. 

The dialog's scrim only fades in and out.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmox8cric-XR_Dialog_Behavior_01_IA_v02%20.mp4?alt=media&token=e460f495-9098-4668-954c-27b70eea9d29)

### Movement

When activated, the spatial dialog rises from the app to the highest resting level on the Z-axis. 

When the action is complete, it returns to a normal resting level.

The dialog's scrim stays at the app content level at all times.

To prevent motion sickness, use [standard easing](/m3/pages/motion-easing-and-duration/tokens-specs#601d5552-a6e6-4d74-9886-ff8f24b9ec35) and [long duration](/m3/pages/motion-easing-and-duration/tokens-specs#48bf653e-46f9-48f5-87e0-eaf8ea3fe716) motion tokens.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmox8drwx-XR_Dialog_Behavior_02_IA_v02%20.mp4?alt=media&token=e278a231-4ee1-4db8-93eb-3defa565b07d)

## Placement

Consider factors like field of view, viewing distance, and possible interactions when deciding where to place dialogs in XR.

A dialog’s placement can be adjusted to accommodate specific needs, such as improved ergonomics or [right-to-left (RTL) languages](/m3/pages/bidirectionality-rtl).

### Elevation: Highest resting level

Display spatial dialogs at the **highest resting level**. When setting the depth value of the highest resting level, make sure the elevated dialog is at a comfortable viewing distance from the person. [More on spatial elevation](https://developer.android.com/design/ui/xr/guides/spatial-ui#spatial-elevation)


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmox8ubbg-GM3_XR_Dialogs_Motion_Placement_01_IA_v03.mp4?alt=media&token=dcaaeff6-4d44-4d07-8288-f57acf7e9a40)

### Center spatial dialogs in field of view

Spatial dialogs should be centered in a person’s [field of view](https://developer.android.com/design/ui/xr/guides/spatial-ui#where-place). If the dialog **can’t** track head movements, position it in the center of the app’s content. 

If the dialog **can** track head movements, configure it with a lazy follow behavior. This keeps the dialog anchored to the center of a person’s field of view until an action is taken.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmox8yi7x-GM3_XR_Dialogs_Motion_Placement_02_IA_v05.mp4?alt=media&token=6f357063-b36f-4819-9064-2cc7421f7960)

## Accessibility considerations

[XR accessibility](/m3/pages/xr-design/accessibility) guidelines are still evolving. Spatial dialogs should follow applicable Material [dialog accessibility standards](/m3/pages/dialogs/accessibility).

starNote:XR guidelines are primarily intended for designers. Find what’s implemented in code in the [design kit](https://www.figma.com/community/file/1035203688168086460).

Extended reality (XR) interfaces have special design requirements, like showing apps in 3D space. Material has an XR navigation bar with custom specs and guidance. See [XR developer documentation](http://developer.android.com/design/ui/xr/guides/foundations) for more details.

## Anatomy


![Diagram of navigation bar orbiter identifying 7 internal elements of the component.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmp5rw7th-01-3P.png?alt=media&token=9f654553-9782-4442-80e6-c6f81a39479c)
*1. Container
2. Icon
3. Active indicator 
4. Small badge (optional)
5. Large badge (optional)
6. Large badge label (optional)
7. Label text*

## Color & elevation

On XR, color is used to highlight elevated UI elements and orbiters. With [spatial elevation](https://developer.android.com/design/ui/xr/guides/spatial-ui#spatial-elevation), the navigation bar displays above the spatial panel, on the Z-axis. Color communicates elevation on UI elements and orbiters. Elevated nav bars can use any of these color options:


![4 versions of elevation color strategy.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmp5rwtar-02-3P.png?alt=media&token=949b1a5c-1fa5-4880-8a2c-28b4ccef1e93)
*1. Surface container
2. Surface container high
3. Surface container highest
4. Tertiary container*

## Measurements


![Measurements and padding for navigation bar orbiter.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmp5rxeyw-03-3P.png?alt=media&token=7a9e8b61-55f8-4a7d-9a39-2da0d21c98fb)
*Navigation bar orbiter padding and measurements*

## Usage

In full space, a navigation bar can appear in an orbiter for a more immersive experience. Currently, spatial capabilities, such as orbiters, are only available in full space. In home space, use a regular navigation bar on the same plane as the body content to mimic a 2D experience.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm4cj01s5-navbar_01.mp4?alt=media&token=598ad15c-e79d-4684-bcda-a2eaf0f76071)

## Behavior

### Global context

When placed in global context, the navigation bar orbiter is centered at the bottom of the app it controls. It stays anchored to the app during layout or content changes. This ensures navigation elements are easy to find and use.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmp0xfkl2-navbar_07.mp4?alt=media&token=204309ca-e287-47b0-b809-cf98d19d1881)

### Local context

When placed in local context, the navigation bar orbiter is centered at the bottom of the spatial panel it controls. It repositions in response to layout or content changes.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fma4aas8k-XR_Nav-Bar_Behavior_02_V02.mp4?alt=media&token=3fb3e553-5fc0-426a-8c0d-5c6275ab064f)

## Placement

### Navigation context

The position of the navigation bar orbiter should communicate its navigational context:

- 

Use **offset positioning** for global actions that affect the overall app experience
- 

Use **inset positioning** for local actions that are specific to a spatial panel

A navigation bar orbiter can either overlap or be positioned adjacent to spatial panels with a 20dp margin for visual separation.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmoxalxwd-navbar_02.mp4?alt=media&token=bf9cf606-d1f6-4bdc-b555-a6c1bfb5c10b)

### Inset positioning

Don’t obstruct content. To ensure a balanced and uncluttered layout, a navigation bar orbiter should overlap spatial panels by 12dp and no more than half their height.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmoxaokg7-navbar_04.mp4?alt=media&token=b226855d-f99c-4e69-b64b-a394911579aa)

### Horizontal alignment

The navigation bar orbiter placement shouldn't exceed the width of adjacent spatial panels.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmoxaqxbc-navbar_05.mp4?alt=media&token=2dc24b69-cf71-45d0-afbf-ca41f39f2f1e)

### Spatial panel alignment

A navigation bar orbiter should always be placed at the bottom of a spatial panel and within the immediate field of view (FOV).

Their placement can be adjusted to accommodate specific needs, such as improved ergonomics or [right-to-left (RTL) languages](/m3/pages/bidirectionality-rtl).

Avoid placing the navigation bar orbiter at the top of a spatial panel, as this area is typically reserved for app bar orbiters or other critical UI elements.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmoxaupni-navbar_06.mp4?alt=media&token=3c9ac7c4-18a7-43d4-9e1d-81457cbfd801)

## Accessibility considerations

XR navigation bars should follow applicable Material [nav bar accessibility standards](/m3/pages/navigation-bar/accessibility). [More on XR accessibility](/m3/pages/xr-design/accessibility)

starNote:XR guidelines are primarily intended for designers. Find what’s implemented in code in the [design kit](https://www.figma.com/community/file/1035203688168086460).

Extended reality (XR) interfaces have special design requirements, like showing apps in 3D space. Material has an XR navigation rail with custom specs and guidance. See [XR developer documentation](http://developer.android.com/design/ui/xr/guides/foundations) for more details.

## Variants

There are two variants of navigation rail orbiters: the contained FAB and spatialized FAB navigation rails.


![Navigation bar orbiters with a contained FAB and a spatialized FAB.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmp5sirou-01-3P.png?alt=media&token=9e7d6650-d675-40ff-9598-318041a54cc4)
*1. Contained FAB rail
2. Spatialized FAB rail*

## Anatomy


![Diagram of navigation rail orbiter identifying 9 internal elements of the component.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmp5sjxq7-02-3P.png?alt=media&token=fdcd5c66-8926-47f1-8b1b-713fea02f3e4)
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

On XR, color is used to highlight elevated UI elements and orbiters. With [spatial elevation](https://developer.android.com/design/ui/xr/guides/spatial-ui#spatial-elevation), the navigation bar displays above the [spatial panel](https://developer.android.com/design/ui/xr/guides/spatial-ui#spatial-panels), on the Z-axis. Color communicates elevation on UI elements and orbiters. Elevated nav rails can use any of these color options:


![4 versions of elevation color strategy.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmp5smtp2-03-3P.png?alt=media&token=d455db2e-29cb-48be-9f31-8e8f60bb8956)
*1. Surface container with tertiary FAB
2. Surface container high with tertiary fixed dim FAB
3. Surface container highest with tertiary fixed dim FAB
4. Tertiary container with primary FAB*

## Measurements


![Measurements and padding for navigation rail orbiter with contained FAB.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmp5sl333-04-3P.png?alt=media&token=c4c50bc2-0f1f-4955-9b4d-1fdfb6eb3d9a)
*Navigation rail orbiter padding and measurements with contained FAB*


![Measurements and padding for navigation rail orbiter with spatialized FAB.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmp5slo7l-05-3P.png?alt=media&token=431aeed3-0cb7-4811-b04f-c759f09bb4af)
*Navigation rail orbiter padding and measurements with spatialized FAB*

## Usage

In full space, a navigation rail can appear in an orbiter for a more immersive experience. Currently, spatial capabilities, such as orbiters, are only available in full space. In home space, use a regular navigation rail on the same plane as the body content to mimic a 2D experience.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmoxcb5no-navrail_01.mp4?alt=media&token=616861d0-6177-412c-b4f4-2b9ae67b09cb)

## Behavior

### Global context

Intended for global navigation, a nav rail orbiter should be centered along the left or right edge of the app it controls. It stays anchored to the app during layout or content changes to ensure controls are easy to find.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fma4acdxe-XR_Nav-Rail_Behavior_01_V02.mp4?alt=media&token=9414cfde-c786-4936-831e-4a0a3d3e89cb)

### Local context

Don’t place a navigation rail orbiter in local context or [between spatial panels](/m3/pages/xr-components/nav-rail#d965ae72-bd1b-45a9-b4d3-d1d542e72087). Local placement can make controls hard to find. Nav rails are designed for app-level navigation, so should only use the global context.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fma4acsak-XR_Nav-Rail_Behavior_02_V02.mp4?alt=media&token=8a2a2f30-3484-4757-94a1-6fa7d256e6b1)

## Placement

### Navigation context

The position of the navigation rail orbiter should communicate its navigational context:

- 

Use **offset positioning** for global actions that affect the overall app experience
- 

Use **inset positioning** for local actions that are specific to a spatial panel

A navigation rail orbiter can either overlap or be positioned adjacent to spatial panels with a 20dp margin for visual separation.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmoxck8bj-navrail_02.mp4?alt=media&token=1abcf86d-a2ef-4121-9e76-ff9848bb9fdf)

### Inset positioning

Don’t obstruct content. To ensure a balanced and uncluttered layout, a navigation rail orbiter should overlap spatial panels by 12dp and no more than half their width.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmoxcmy1y-navrail_03.mp4?alt=media&token=155ce8ef-0eb9-41c2-97c9-82da93c89626)

### Vertical alignment

A navigation rail orbiter can be aligned to the top, middle, or center of spatialized panels, providing different levels of visual prominence and accessibility.

Align the navigation rail orbiter based on the specific design and user experience goals for the application.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmoxcqi3i-navrail_04.mp4?alt=media&token=36f84d94-add6-482b-9145-4be5935278ec)

The navigation rail orbiter placement shouldn't exceed the height of adjacent spatial panels.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmoxd1s5j-navrail_05.mp4?alt=media&token=dda5a4c9-6862-477f-9db6-6dd3f3a304de)

### Spatial panel alignment

Avoid placing a navigation rail orbiter between spatial panels. This negatively affects the interface structure.

Navigation rail placement can be adjusted to accommodate specific needs, such as improved ergonomics or [right-to-left (RTL) languages](/m3/pages/bidirectionality-rtl).

For layouts that span more than two spatial panels, consider using a [navigation bar orbiter](/m3/pages/xr-components/nav-bar).


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmoxdaai7-navrail_06.mp4?alt=media&token=f31cafb8-b8a6-4b94-a85a-d85b0a5e98d8)

## Spatialized FAB

There are two variants of navigation rail orbiters with different FAB treatments:

- 

**Contained FAB rail:** A contained FAB within the rail. This offers a compact and familiar layout.
- 

**Spatialized FAB rail:** The FAB becomes an orbiter of its own and is placed outside the navigation rail orbiter. Use this for higher emphasis and a distinct spatial effect.

Use the spatialized FAB rail to emphasize key actions and leverage XR hierarchy. Use the contained FAB rail to be more subtle, and align the experience with the baseline navigation bar.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmoxd8z8l-navrail_07.mp4?alt=media&token=de8986f3-4eed-430b-a165-7e4d8e9aa284)

To maintain visual association, place the spatialized FAB in close proximity to the navigation rail orbiter. Material recommends a 20dp margin.

The spatialized FAB can be placed above or below the navigation rail orbiter.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmoxdgsuy-navrail_08.mp4?alt=media&token=d13e9164-41d5-44e4-8006-711220831ec7)

While the spatialized FAB and navigation rail orbiter are typically positioned together, their placement is adaptable.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fm4cn0kcj-navrail_09.mp4?alt=media&token=f9165d61-d8e1-4b44-ba35-f238087e45cf)

## Accessibility considerations

[XR accessibility](/m3/pages/xr-design/accessibility) guidelines are still evolving. XR navigation rails should follow applicable Material [nav rail accessibility standards](/m3/pages/navigation-rail/accessibility).

starNote:XR guidelines are primarily intended for designers. Find what’s implemented in code in the [design kit](https://www.figma.com/community/file/1035203688168086460).

Extended reality (XR) interfaces have special design requirements, like showing apps in 3D space. Material has an XR toolbar with custom specs and guidance. Read [XR developer documentation](https://developer.android.com/design/ui/xr/guides/foundations) for more details.

## Variants

There is one toolbar orbiter. It closely aligns with the floating toolbar. It can be configured to be horizontal or vertical. [Go to XR toolbar API reference](https://developer.android.com/reference/kotlin/androidx/xr/compose/material3/package-summary#HorizontalFloatingToolbar(kotlin.Boolean,androidx.compose.ui.Modifier,androidx.compose.material3.FloatingToolbarColors,androidx.compose.foundation.layout.PaddingValues,androidx.compose.material3.FloatingToolbarScrollBehavior,kotlin.Function1,kotlin.Function1,kotlin.Function1))


![Horizontal and vertical toolbar orbiters.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmp5sscqe-01-1P.png?alt=media&token=bdd0c440-0cd1-4d30-bd53-b36dead8cbc0)
*1. Horizontal floating toolbar
2. Vertical floating toolbar*

## Anatomy


![2 elements of a toolbar orbiter: container and placed components. ](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmp5stimq-02-1P.png?alt=media&token=faf847b2-0a96-4506-b5e5-755d7eecdf08)
*1. Container
2. Placed components*

## Color & elevation

XR uses color to communicate the elevation of UI elements and orbiters. With [spatial elevation](https://developer.android.com/design/ui/xr/guides/spatial-ui#spatial-elevation), the toolbar displays above the spatial panel on the Z-axis. Elevated toolbars can use any of these color options:


![4 versions of toolbar elevation color strategy.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmp5sum97-03-1P.png?alt=media&token=f8d0d1a2-d67a-4f4a-88f8-a6ec5c184fa9)
*1. Surface container
2. Surface container high
3. Surface container highest
4. Tertiary container*

## Measurements


![Diagram with measurements for toolbar orbiters.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmp5svaqo-04-1P.png?alt=media&token=92f20043-78db-4d1b-b604-d9e00a2bc9bc)
*Measurements for toolbar orbiters*


![Diagram with 12dp padding for toolbar orbiters.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmp5sw649-05-1P.png?alt=media&token=ac0dda53-ac9a-4d64-b735-6d78144c7dbe)
*Padding for toolbar orbiters*

## Usage

A toolbar can appear in an orbiter for a more immersive experience. Currently, this spatial capability is only available in full space. In home space, use a regular toolbar on the same plane as the body content to mimic a 2D experience.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmoxe8vd0-toolbars-xr-06%20(1).mp4?alt=media&token=2824457a-7cee-4649-801e-79cffd355aae)

## Behavior

### Local context (recommended)

When placed in local context, the toolbar orbiter is centered at the bottom of the spatial panel it controls. 

It repositions in response to layout or content changes.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmp0wl8ix-toolbars-xr-07.mp4?alt=media&token=d27f646b-5bb6-4bfd-9e58-4b6cd3f6546f)

### Global context

When placed in global context, the toolbar orbiter is centered at the bottom of the app. 

It stays anchored to the app during layout or content changes.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmp0woole-toolbars-xr-08.mp4?alt=media&token=15aa6f6b-377c-4aae-a7a2-a3af70f09055)

### Expand & collapse

Toolbar orbiters with more than five items can expand and collapse to reveal or hide additional content.

When a toolbar orbiter expands, it stays within the bounds of the adjacent spatial panel.

Alternatively, more complex toolbars can be split into multiple toolbars.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmp0wrkvj-toolbars-xr-09.mp4?alt=media&token=6b1d51a9-3408-4ac9-bd5a-f204de662100)

### Additional toolbars

In some cases, full space apps can have more than one toolbar orbiter, placed in either global or local context.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmp0wtnt4-toolbars-xr-10.mp4?alt=media&token=fa37073a-17df-44b4-9443-913f6e4f2f09)

## Placement

### Offset & inset positioning

In full space, a toolbar orbiter can be positioned adjacent to or overlap a spatial panel.


![Toolbar orbiter with offset positioning.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmoxer5rt-11-do.png?alt=media&token=e4b3858b-33c8-4780-a480-bb736654fe44)
*The recommended toolbar orbiter position from the spatial panel is: 

- Offset by 20dp or 
- Inset by 12dp*


![Toolbar orbiter with inset positioning above 12dp that obstructs content on the spatial panel. ](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmoxetpx5-12-don't.png?alt=media&token=9785f369-7394-4db6-9b9f-d5cc51db2087)
*To prevent content obstruction, don’t overlap the toolbar orbiter and spatial panel above 12dp*

### Horizontal alignment


![A horizontal toolbar orbiter placed within the bounds of its spatial panel.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmoxewjln-13-do.png?alt=media&token=532608ce-b877-416f-b725-f6e3637560d5)
*Always align the toolbar orbiter within the horizontal bounds of nearby spatial panels*


![A horizontal toolbar orbiter that extends beyond the width of its spatial panel.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmoxeyben-14-don't.png?alt=media&token=51df328d-28b3-4aa4-9025-47ea3f674d36)
*The toolbar orbiter shouldn’t exceed the width of adjacent spatial panels*

### Vertical alignment


![A vertical toolbar orbiter placed within the bounds of its spatial panel.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmoxf0mbw-15-do.png?alt=media&token=29cbe69d-bd06-4ace-ba38-0e7e991cf121)
*Always align the toolbar orbiter within the vertical bounds of nearby spatial panels*


![A vertical toolbar orbiter that extends beyond the height of its spatial panel.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmemfyo8f-16-don't.png?alt=media&token=d9e5cd69-d3b6-4238-94fa-5d7dbd359c2c)
*The toolbar orbiter shouldn’t exceed the height of adjacent spatial panels*

### Spatial panel alignment

By default, toolbar orbiters are center-aligned to the spatial panel. Their placement can be adjusted to accommodate specific user needs, such as improved ergonomics or [right-to-left (RTL) languages](/m3/pages/bidirectionality-rtl).


![Toolbar orbiter alignment options in relation to spatial panels.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmemfvpq3-17.png?alt=media&token=06472c3c-8a05-4dab-9e63-b4dac374c622)
*Depending on the configuration (horizontal or vertical) of the toolbar orbiter, it can align to the center, left, right, top, or bottom of a spatial panel*

Avoid placing a vertical toolbar orbiter between spatial panels. 

This negatively affects the interface structure and can make it difficult to find.


![A vertical toolbar orbiter is placed between 2 spatial panels.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmemfx943-18-don't.png?alt=media&token=ce865771-93db-4ded-ac2c-f0ca46254201)
*Don't place a vertical toolbar orbiter between spatial panels*

## Accessibility considerations

[XR accessibility](/m3/pages/xr-design/accessibility) guidelines are still evolving. XR toolbars should follow applicable Material [toolbar accessibility standards](/m3/pages/toolbars/accessibility).
