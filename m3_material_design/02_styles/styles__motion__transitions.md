---
title: "Transitions"
slug: "styles/motion/transitions"
url: "https://m3.material.io/styles/motion/transitions"
---

# Transitions

> Transitions help guide people as they navigate an app

starNote:M3 transitions use the legacy easing and duration system. They'll eventually be updated to use the motion physics system.

Transitions are short animations that connect individual elements or full-screen views of an app. They are fundamental to a great user experience because they help users understand how an app works. Well-designed transitions makes an experience feel high quality and expressive. They should be the top priority for a strong motion implementation. 

These are six common transition patterns:

1. [Container transform](/m3/pages/motion-transitions/transition-patterns#b67cba74-6240-4663-a423-d537b6d21187)
2. [Forward and backward](/m3/pages/motion-transitions/transition-patterns#df9c7d76-1454-47f3-ad1c-268a31f58bad)
3. [Lateral](/m3/pages/motion-transitions/transition-patterns#8d4ec98f-60dc-47a9-901e-88fa2c43f18a)
4. [Top level](/m3/pages/motion-transitions/transition-patterns#f852afd2-396f-49fd-a265-5f6d96680e16)
5. [Enter and exit](/m3/pages/motion-transitions/transition-patterns#e1c2a650-d7a4-4a6d-9025-e6b7845291ed)
6. [Skeleton loaders](/m3/pages/motion-transitions/transition-patterns#b39a0641-1b44-4864-83f5-fac38e0bd94a)


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flyfqbdij-GM3-Transitions-TransitionPatterns-1-v01.mp4?alt=media&token=37344f9a-dc0e-4bf7-86a3-826d46ba788b)

## Container transform

This pattern is used to seamlessly transform an element to show more detail, like a Card expanding into a details page.

- 

**Commonly used with:** Cards, lists, image galleries, search boxes, sheets, FABs, and chips
- 

**Read more:** [UX Research](https://material.io/blog/motion-research-container-transform), [Guidelines](/m3/pages/motion-transitions/applying-transitions#50f9fc3f-c7e2-4099-b614-7c36b1c5285d), [Android implementation](https://github.com/material-components/material-components-android/blob/master/docs/theming/Motion.md#container-transform)

Persistent elements are used to seamlessly connect the start and end state of the transition. The most common persistent element is a container, which is a shape used to represent an enclosed area. It can also be an important element, like a hero image. Of all transition patterns, this one creates the strongest relationship between elements. It's also perceived to be the most expressive.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flyfqc556-GM3-Transitions-TransitionPatterns-2-v01.mp4?alt=media&token=0c23dd89-b0ec-445b-80fe-50c04378ed4d)

#### Between full-screen views


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flyfqdipk-GM3-Transitions-TransitionPatterns-3-1-v01.mp4?alt=media&token=e5a7ca2c-e68c-42f9-8e15-669bc5136c9a)


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flyfrbw3w-GM3-Transitions-TransitionPatterns-3-2-v01.mp4?alt=media&token=30769f42-b523-4302-83a3-3bc23f0f7e11)


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flyfrcr6e-GM3-Transitions-TransitionPatterns-3-3-v01.mp4?alt=media&token=8dd88ac4-99df-4d53-966f-2e9021a23b4b)

#### Within a screen


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flyfrdrxa-GM3-Transitions-TransitionPatterns-3-4-v01.mp4?alt=media&token=7bf2552b-c418-4154-87c4-3ccb336861c0)


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flyfrem0q-GM3-Transitions-TransitionPatterns-3-5-v01.mp4?alt=media&token=7c4de1b8-3f8f-460a-b2f6-5c52f845a5cf)


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flyfrf9ld-GM3-Transitions-TransitionPatterns-3-6-v01.mp4?alt=media&token=a5dc8901-3704-4be7-b8a6-ddb0889fe4cd)

## Forward and backward

This pattern is used for navigating between screens at consecutive levels of hierarchy, like navigating from an inbox to a message thread.

- **Commonly used with**: Lists, cards, buttons, links
- **Read more:** [Guidelines](/m3/pages/motion-transitions/applying-transitions#41b11a78-b88f-4972-904c-880bc348acc8), [Android implementation](https://github.com/material-components/material-components-android/blob/master/docs/theming/Motion.md#shared-axis)

A horizontal sliding motion indicates moving forward or backward between screens. Android and iOS have different default styles:

**1. Android** uses a fade as screens slide. This reduces the amount of motion, since the screens don't have to slide the full width of the device. 

**2. ****iOS** uses a parallax effect, meaning the background slides slower than the foreground. This also reduces the amount of motion.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flyfrilua-GM3-Transitions-TransitionPatterns-4-v01.mp4?alt=media&token=fa5b76e6-9baf-41a5-b0bb-a46b1eae0b07)


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flyfrjhm3-GM3-Transitions-TransitionPatterns-4-1-v01.mp4?alt=media&token=f5f1caac-0bba-4402-9e12-d7550905bc9f)


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flyfrkcfn-GM3-Transitions-TransitionPatterns-4-2-v01.mp4?alt=media&token=31167b8c-9aa6-44e0-8583-f1159a42aba2)


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flyfrldpn-GM3-Transitions-TransitionPatterns-4-3-v01.mp4?alt=media&token=6ca0882e-0f25-4aa5-bcfe-a834b427c409)


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flyfrmnfg-GM3-Transitions-TransitionPatterns-4-4-v01.mp4?alt=media&token=f7c9ab56-e46f-43db-8569-b50966797597)

## Lateral

This pattern is used for navigating between peer content at the same level of hierarchy, like swiping between tabs of a content library.

- **Commonly used with: **Tabs, carousels, and image galleries
- **Read more:** [Guidelines](/m3/pages/motion-transitions/applying-transitions#3d5c16ce-7350-4a33-9d2b-598a7591d4e6)

Lateral transitions use a sliding motion similar to a forward and backward pattern, but it does not use a fade or parallax effect. Instead elements are grouped and slide in unison, creating a strong peer relationship. This also hints at being able to gesturally swipe elements to navigate.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flyfrnslm-GM3-Transitions-TransitionPatterns-5-1-v01.mp4?alt=media&token=09d0ed16-e36b-4ce4-af48-cf21f4f82b5c)


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flyfroshf-GM3-Transitions-TransitionPatterns-5-2-v01.mp4?alt=media&token=2927b2a2-8ac7-4f9c-9f90-607efa692e93)


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flyfrpmn2-GM3-Transitions-TransitionPatterns-5-3-v01.mp4?alt=media&token=6ceb61d3-2aef-4e50-aa04-cf1f5087f874)

## Top level

This pattern is used to navigate between top-level destinations of an app, like tapping a destination in a Navigation bar.

- **Commonly used with:** Navigation bar, navigation rail, and navigation drawer
- **Read more:** [Guidelines](/m3/pages/motion-transitions/applying-transitions#ab8885f6-5517-419d-80de-bea50cd10467), [Android implementation](https://github.com/material-components/material-components-android/blob/master/docs/theming/Motion.md#fade-through)

The exiting screen quickly fades out and then the entering screen fades in. Since the content of top level destinations isn't necessarily related, the motion intentionally does not use grouping or persistent elements to create a strong relationship between screens.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flyfrrw39-GM3-Transitions-TransitionPatterns-6-1-v01.mp4?alt=media&token=870012dd-e750-4cab-b482-38664dc86ad3)


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flyfs0nn2-GM3-Transitions-TransitionPatterns-6-2-v01.mp4?alt=media&token=b0b86abe-7f8b-4c46-badd-809fbf94192e)


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flyfs1jfp-GM3-Transitions-TransitionPatterns-6-3-v01.mp4?alt=media&token=e4eacdac-a0ee-4a02-8d09-e50445052491)

## Enter and exit

This pattern is used to introduce or remove a component on the screen. Components can enter and exit within the screen bounds, like a dialog appearing over an app. They can also enter and exit by crossing the screen bounds, like a navigation drawer or bottom sheet that slides on and off screen.

### Within screen bounds

- **Commonly used with:** FABs, dialogs, menus, snackbars, time pickers and tooltips
- **Read more: **[Guidelines](/m3/pages/motion-transitions/applying-transitions#56675bd6-5e69-4fa8-b075-d694e8cb3ad4)

**Android** components expand and collapse along the x or y axis as they enter and exit. Scale and z-axis motion is avoided since they imply elevation change, which doesn't match M3's reduced elevation model.

**iOS** components uniformly scale as they enter and fade out to exit.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flyfs2znj-GM3-Transitions-TransitionPatterns-7-1-v01.mp4?alt=media&token=cc20bc71-2e97-42c5-b08c-4cf7f32ce059)

The direction a component enters is informed by their location on screen, expanding away from the device edge. A menu at the top of the screen expands downwards, and a snackbar at the bottom of the screen expands upwards.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flyfs4dk9-GM3-Transitions-TransitionPatterns-7-2-v01.mp4?alt=media&token=5ea9b2aa-b72c-4a22-b72b-4a08fa05f323)


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flyfs56ki-GM3-Transitions-TransitionPatterns-7-3-v01.mp4?alt=media&token=7ff7b7a9-3ab7-482b-98cd-8ea1add1fa77)

### Beyond screen bounds

- **Commonly used with: **App bars, banners, navigation bar, navigation rail, navigation drawer, and sheets
- **Read more:** [Guidelines](/m3/pages/motion-transitions/applying-transitions#1b704202-167d-48d5-bca1-614cf050de1b)

**Android** components expand and collapse along the x or y axis as they slide on and off screen. This emphasizes their shape, making an otherwise simple transition more expressive.

**iOS** components slide on and off screen without changing shape.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flyfs6out-GM3-Transitions-TransitionPatterns-7-4-v01.mp4?alt=media&token=ff23a41c-989a-45a4-95ab-1d898b942148)

Components like a side sheet can also enter and exit at the same elevation as the main content. Coplanar sheets shrink the available area for content.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flygxnh2p-GM3-Transitions-TransitionPatterns-7-5-v01.mp4?alt=media&token=5e781c81-9041-407b-bea7-58a93d13f889)

Components can enter and exit from beyond the screen bounds based on a scroll gesture. This allows for more screen space to browse.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flygxorck-GM3-Transitions-TransitionPatterns-7-6-v01.mp4?alt=media&token=bc2ebd28-4182-4699-a155-d2a2e8dcee0b)


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flygxpdy1-GM3-Transitions-TransitionPatterns-7-7-v01.mp4?alt=media&token=c4e1de2b-4fe7-4f6d-869c-b905fd4dcd7f)

The location components enter and exit help establish a coherent spatial model of an app:

1. A **notification** enters from the top indicating the notification drawer can also be pulled down from the top
2. A **nav drawer** enters from the left helping users understand where it's located when it's off screen
3. A **bottom sheet** and the keyboard enters from the bottom of the screen. This is a sensible default location for sheets to enter since the bottom of the screen is easiest to reach.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flygxqtbv-GM3-Transitions-TransitionPatterns-7-8-v01.mp4?alt=media&token=2a6aaa1b-6445-41f7-84d5-4c49d6d54cfe)

## Skeleton loaders

This pattern is used to transition from a temporary loading state to a fully loaded UI.

- 

**Read more**: [Guidelines](/m3/pages/motion-transitions/applying-transitions#b82b5150-609b-4540-903b-2b900ef830aa)

Skeleton loaders are UI abstractions that hint at where content will appear once it's loaded. They're used in combination with other transitions to reduce perceived latency and stabilize layouts as content loads.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flygxs24v-GM3-Transitions-TransitionPatterns-8-1-v01.mp4?alt=media&token=2caf8946-4b3f-4f1b-98a2-baad902a2430)

Skeleton loaders have a subtle pulsing animation to indicate indeterminate progress. It starts at the top left of the screen and moves down to the bottom right.

Once content is loaded, it quickly fades in on top of the skeleton loader.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flygxtkjx-GM3-Transitions-TransitionPatterns-8-2-v01.mp4?alt=media&token=199f90b4-51da-4019-a29d-06158f78f198)


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flygxuime-GM3-Transitions-TransitionPatterns-8-3-v01.mp4?alt=media&token=9909e287-adde-4135-88a4-ce1bfeb9f646)

starNote:M3 transitions use the legacy easing and duration system. They'll eventually be updated to use the motion physics system.

## What makes a good transition?

Well-designed transitions should have these characteristics:

### Follows accessibility settings

Most platforms have a reduced animation setting to help users with a sensitivity to motion. If that setting is on, transitions should:

- Use subtle fades instead of intense sliding or scaling animations
- Disable decorative effects like parallax or shape morphing


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flygy2t4f-GM3-Styles-Motion-Transitions-ApplyingTransitions-1-v01.mp4?alt=media&token=fa2a937d-d78a-40e2-b173-1df231d43a25)

### Consistent

Consistently applying the right type of transition helps make apps feel cohesive and predictable to use.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flygy4536-GM3-Styles-Motion-Transitions-ApplyingTransitions-2-v01.mp4?alt=media&token=3e39ddab-c487-4fc7-a1b0-ed4d44607bea)

### Stable layouts

Use skeleton loaders so that UI elements are coherent and stable during a transition. Avoid content shifting positions or instantly popping in as it loads. It can be distracting and frustrating to use.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flygy6hgi-GM3-Styles-Motion-Transitions-ApplyingTransitions-3-1-v01.mp4?alt=media&token=cc8be2fb-be03-4593-b660-228e3ec752ff)


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flygy75ou-GM3-Styles-Motion-Transitions-ApplyingTransitions-3-2-v01.mp4?alt=media&token=63bb733f-6d3d-41a2-a555-07a17fdf7c3d)

### No jarring jump cuts

Jump cuts should generally be avoided as a default setting since they can be disorienting. Instantly transitioning from one screen to the next offers no clues to help a user orient themselves.

If pure efficiency is a top priority, like opening a menu in a productivity app, a jump cut may be preferred.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flygy7y9g-GM3-Styles-Motion-Transitions-ApplyingTransitions-4-1-v01.mp4?alt=media&token=0593dada-d203-46cb-94ff-e77013860d3f)


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flygy8zr2-GM3-Styles-Motion-Transitions-ApplyingTransitions-4-2-v01.mp4?alt=media&token=28543c41-8af9-4581-a2fe-ea1df61fe367)

### Coherent spatial model

Transitions are used to establish a coherent spatial model. This helps users understand the physical layout of an app.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flygya5u9-GM3-Styles-Motion-Transitions-ApplyingTransitions-5-1-v01.mp4?alt=media&token=52f9a8d8-4bad-4a86-9d73-0129dbf5755d)


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flygyav9v-GM3-Styles-Motion-Transitions-ApplyingTransitions-5-2-v01.mp4?alt=media&token=70a694a8-87f0-4aac-92ca-e35839278d67)

### Unified direction

A transition should have a unified direction of movement. Elements are grouped and move along a primary axis instead of moving in independent directions. Only important elements like hero images remain persistent throughout the transition. This helps guide a users focus.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flygybwyn-GM3-Styles-Motion-Transitions-ApplyingTransitions-6-1-v01.mp4?alt=media&token=8196d5ad-4a88-4115-9a49-85658394f5cf)


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flygycnpj-GM3-Styles-Motion-Transitions-ApplyingTransitions-6-2-v01.mp4?alt=media&token=d80d50cd-5995-43d4-8bc8-cbd915fdfe4b)

### Clean fades

Fully fade out content before fading new content in. This avoids the overlap of partially transparent elements resulting in distracting and messy frames.

If a cross fade needs to occur, keep it quick and hide it during the fastest part of the transition.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flygydts6-GM3-Styles-Motion-Transitions-ApplyingTransitions-7-1-v01.mp4?alt=media&token=78f2f378-2cf8-49b3-b4bb-5503c0fb3db5)


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flygyekln-GM3-Styles-Motion-Transitions-ApplyingTransitions-7-2-v01.mp4?alt=media&token=433fc94f-c7f5-4ab1-80e4-b623db721de2)

Don't slowly fade components on top of other content as they enter or exit. This creates distracting cross faded frames. If a fade is needed, like with a Dialog entering in the middle of the screen, the fade should use a short duration to hide that part of the transition.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flygyfxw6-GM3-Styles-Motion-Transitions-ApplyingTransitions-8-v01.mp4?alt=media&token=2376992f-18fd-4b9a-b261-20e362cc6721)

### Simple style

Transitions are not receptive to highly stylized motion. They're frequent, often occupy large portions of the screen, and are primarily meant to help users accomplish a task.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flygyi588-GM3-Styles-Motion-Transitions-ApplyingTransitions-9-1-v01.mp4?alt=media&token=f130ce91-0fc7-4fdf-ac08-88bc74fd4e9a)


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flygyja80-GM3-Styles-Motion-Transitions-ApplyingTransitions-9-2-v01.mp4?alt=media&token=0a238ff8-344a-4732-a814-16d3aa28d0fa)

## Choosing a transition pattern

Consider the following to choose the right transition for a given use case:

### Container transform

This pattern is highly effective at creating a relationship between elements. It's also the most dramatic pattern in terms of style and should be reserved for the right context. Consider using it for:

1. Hero moments that should be expressive
2. Shallow hierarchies where you expand an element for more detail then collapse it
3. Creating a seamless connection between elements

**Read the research for the benefits of container transform**** [here](https://material.io/blog/motion-research-container-transform).**


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flygykdgf-GM3-Styles-Motion-Transitions-ApplyingTransitions-10-1-v01.mp4?alt=media&token=f68eb6c5-da7a-4083-b92d-12c5de44078b)


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flygyl1ui-GM3-Styles-Motion-Transitions-ApplyingTransitions-10-2-v01.mp4?alt=media&token=dbf61b87-7dd4-4bb9-a2b5-0089aa24655e)

Use a container transform transition for hero moments rather than a forward and backward transition.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flygyn2vj-GM3-Styles-Motion-Transitions-ApplyingTransitions-11-v01.mp4?alt=media&token=3dc00c81-255a-4949-aecc-9d7eea660815)

### Forward and backward

Both Android and iOS should use platform defaults for forward and backward navigation. It's easy to implement and stays current as platforms update. They have a simple motion style suitable for such a common transition.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flygyo6nk-GM3-Styles-Motion-Transitions-ApplyingTransitions-12-1-v01.mp4?alt=media&token=22d57293-6a2e-483c-bded-47eba23c5d2b)


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flygyoxdg-GM3-Styles-Motion-Transitions-ApplyingTransitions-12-2-v01.mp4?alt=media&token=45cdc5a3-f523-4333-8cf4-5d8d4cd36553)

### Lateral

Lateral transitions are used to browse peer content that's part of the same set, like navigating between tabs in a media library. By sliding content horizontally, it hints at being able to swipe the content area to navigate between peers.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flygyq7yo-GM3-Styles-Motion-Transitions-ApplyingTransitions-13-1-v01.mp4?alt=media&token=c39877a5-0920-4662-8380-90a83c5f0643)


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flygyr0oo-GM3-Styles-Motion-Transitions-ApplyingTransitions-13-2-v01.mp4?alt=media&token=cc21a566-1e6d-4994-adbd-12cd0607d5ed)

Don't use a Lateral transition for navigating hierarchical screens. Sliding content the full width of the screen is excessive for a high frequency transition. It also implies an equal peer relationship which isn't accurate to the hierarchy of the screens.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flygysi3q-GM3-Styles-Motion-Transitions-ApplyingTransitions-14-v01.mp4?alt=media&token=b0016c76-ff2f-4192-82cc-3291a864a9ec)

### Top level

When tapping a navigation bar, rail or drawer, a quick fade is used to transition to a new destination. Top level destinations aren't necessarily related, so the motion intentionally does not create a connection between screens.

A lateral transition pattern is not recommended for this type of navigation. It implies you can swipe between top level destinations which conflicts with other components like carousels or swipe-able list items.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flygytotc-GM3-Styles-Motion-Transitions-ApplyingTransitions-15-1-v01.mp4?alt=media&token=949878aa-a1be-410e-a1fc-a3e275e7bc06)


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flygyuc8i-GM3-Styles-Motion-Transitions-ApplyingTransitions-15-2-v01.mp4?alt=media&token=2490f24e-1064-4e3e-b45c-6ec8ebbfe4be)

### Enter and Exit

This transition pattern is used to introduce a component in context of the screen’s main UI. It can be modal, like a dialog requiring a user to take action. Or it can allow for simultaneously using both regions of the UI, like a standard bottom sheet over a map.

Don't use this pattern for navigating hierarchical screens. Sliding content the full height of the screen is excessive and it creates an unclear relationship between screens.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flygyvia4-GM3-Styles-Motion-Transitions-ApplyingTransitions-16-1-v01.mp4?alt=media&token=b38ee2a8-163e-4e4a-92c8-32ceb19c1ae7)


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flygywcfr-GM3-Styles-Motion-Transitions-ApplyingTransitions-16-2-v01.mp4?alt=media&token=418b8b05-eb02-4fcd-b251-9e38fa6ab2db)
