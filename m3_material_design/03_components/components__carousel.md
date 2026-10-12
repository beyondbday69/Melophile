---
title: "Carousel"
slug: "components/carousel"
url: "https://m3.material.io/components/carousel"
---

# Carousel

- 

Contain visual items like images or video, along with optional label text
- 

Six layouts: Multi-browse, uncontained, uncontained multi-aspect ratio, hero, center-aligned hero and full-screen
- 

Layouts can be start-aligned or center-aligned
- 

Item visuals have a parallax effect when scrolled
- 

Items change size as they move through the carousel


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flwuthdnj-GM3-Components-Carousel-Overview-1-v01.mp4?alt=media&token=fdc84e41-6db8-4bbb-8474-60d754219b63)

## Availability & resources

## Updates

**November 2025**

New carousel layout:

- 

Uncontained multi-aspect ratio

**2023 **

Additional layouts and configurations:

- 

Uncontained
- 

Full-screen
- 

Centered carousels
- 

Hero carousel layout
- 

Multi-browse layout


![Items of different widths in an uncontained multi-aspect ratio layout.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmljue787-02.png?alt=media&token=995bfea7-ba9d-4d3a-b922-96ed26cf7de6)
*New carousel layout: uncontained multi-aspect ratio*

## Differences from M2

This component is new in Material 3.

- **Shape**: Dynamic carousel items change shape when scrolled
- **Motion**: Carousel items move at a different speed than their content, creating a parallax effect
- **Interaction**: When scrolled, carousel items snap into place to maintain the same layout. Hero carousels swipe through one item at a time. Multi-browse carousels scroll through many items at once.


![Carousel in a contained layout with a large item and a small item.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flwur4ddz-3.png?alt=media&token=a42f76c5-c4fd-46fd-89a1-04b492638986)
*Hero carousels scroll through one large item at a time*

## Research

The Material Research Team conducted two studies (quantitative and qualitative) with over 200 participants to understand their perspectives of five different carousel designs. The studies measured their understanding of how to interact with each carousel, their expectations of the number of items in each design, and how they expected carousels to be used.

**Summary of findings:**

- 

Participants thought carousels were a good way to explore many different kinds of content
- 

A previewed or squished item strongly indicated that there was more content to swipe through
- 

Participants expected around 10 items in a carousel that scrolled multiple items at once
- 

While some contexts were considered better for some carousel designs, all designs were considered similarly usable


![4 elements of a carousel.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Flyr7cydt-1.png?alt=media&token=457c501d-9caa-4940-8089-c857014a3246)
*1. Container
2. Large carousel item
3. Medium carousel item
4. Small carousel item*

## Tokens & specs

Browse the component elements, attributes, tokens, and their values.

## Color

Color values are implemented through design tokens. For design, this means working with color values that correspond with tokens. For implementation, a color value will be a token that references a value. [Learn more about design tokens](/m3/pages/design-tokens/overview/)


![2 color roles of a carousel.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Flyr7dtgs-2.png?alt=media&token=88ace94e-9b83-4829-af0d-580102726097)
*Carousel color roles used for light and dark schemes:

1. Container
2. Surface*

## States

States are visual representations used to communicate the status of a component or interactive element. [Learn more about interaction states](/m3/pages/interaction-states/overview)


![5 states of a carousel in light and dark schemes.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fm8tafq6c-3.png?alt=media&token=89db79c9-95d1-48ac-b027-f80fce7811b0)
*1. Enabled
2. Hovered
3. Focused
4. Pressed
5. Disabled*

## Carousel item dynamic widths

All kinds of carousel items dynamically adapt to the width of the container.

Large items have a customizable maximum width that's used to optimally fit carousel items into the available space.

Small carousel items have a minimum width of 40dp and a maximum width of 56dp.

Items change size as they move through the carousel layout.


![Measurements for a small carousel item.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmi9mff4k-04-3P.png?alt=media&token=07ae8548-be08-4e25-baf1-c4b314c683d5)
*Small carousel items have a minimum and maximum width*

## Multi-browse

The multi-browse layout shows at least one large, medium, and small carousel item.


![4 elements of a multi-browse carousel layout.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Flyr7hu8z-5.png?alt=media&token=0fbbafde-d1ed-4c82-b8e8-c792c2f39794)
*1. Container
2. Large carousel item
3. Medium carousel item
4. Small carousel item*

### Measurements


![Measurements of a multi-browse carousel layout.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmi9meptr-06-3P.png?alt=media&token=0caf8655-c1bd-40af-a64f-7dc9606544c0)
*Multi-browse carousels have padding on both sides of the container*

| Attribute | Value |
| --- | --- |
| Alignment | Vertically centered |
| Leading/trailing padding | 16dp |
| Top/bottom padding | 8dp |
| Padding between elements | 8dp |
| Large item width | Dynamic, or user-set |
| Medium item width | Dynamic |
| Small item width | 40–56dp, dynamic |
| Item corner radius | 28dp |

## Uncontained

The uncontained layout shows items that scroll to the edge of the container.


![4 elements of an uncontained carousel layout.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Flyr7jh4c-7.png?alt=media&token=295f37e4-7b0d-4bea-a256-b2a38e9d0d3f)
*1. Container
2. Large carousel item*

### Measurements


![Measurements of an uncontained carousel layout.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmi9mdco2-08-3P.png?alt=media&token=0878f292-7ad5-4738-9d2a-67b39cd69670)
*Uncontained carousel items bleed over the padding on each side when scrolling*

| Attribute | Value |
| --- | --- |
| Alignment | Vertically centered |
| Leading padding | 16dp |
| Top/bottom padding | 8dp |
| Padding between elements | 8dp |
| Item corner radius | 28dp |

## Uncontained mutli-aspect ratio

The uncontained multi-aspect ratio layout shows carousel items of various widths.


![4 elements of an uncontained carousel layout](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmi9lyvnk-09-3P.png?alt=media&token=8fcbc1ce-ab70-44b7-8d93-62b4920f74b6)
*1. Container
2. Carousel item (16:9)
3. Carousel item (9:16)
4. Carousel item (1:1)
5. Carousel item (3:4)*

### Measurements


![Material Design 3 Illustration](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmi9m4p2v-10-3P.png?alt=media&token=cbaae1be-c4fd-47b6-9ccd-c3ad2ed7d9a6)
*Uncontained multi-aspect ratio carousels only have leading padding, with 8dp of padding between items.*

| Attribute | Value |
| --- | --- |
| Alignment | Vertically centered |
| Leading padding | 16dp |
| Top/bottom padding | 8dp |
| Padding between elements | 8dp |
| Item corner radius | 28dp |

## Hero

The hero layout shows at least one large item and one small item.


![3 elements of a hero carousel layout.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Flyr7l425-9.png?alt=media&token=a5d4ade3-7977-496d-9605-174848266c7b)
*1. Container
2. Large carousel item
3. Small carousel item*

### Measurements


![Measurements of a hero carousel layout.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmi9macpj-12-3P.png?alt=media&token=78cc4e71-d6af-4b8b-80c6-798e5845d1d0)
*Hero carousels have padding on both sides of the container*

| Attribute | Value |
| --- | --- |
| Alignment | Vertically centered |
| Leading/Trailing padding | 16dp |
| Top/bottom padding | 8dp |
| Padding between elements | 8dp |
| Large item width | Dynamic |
| Small item width | 40-56dp, dynamic |
| Item corner radius | 28dp |

## Center-aligned hero

The center-aligned hero layout shows at least one large item and two small items.


![3 elements of a center-aligned hero carousel layout.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Flyr7mksj-11.png?alt=media&token=5530baf8-e9d0-44a1-9327-dbe0594233b8)
*1. Container
2. Large carousel item
3. Small carousel item*

### Measurements


![Measurements of a center-aligned hero carousel layout.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmi9mb8nd-14-3P.png?alt=media&token=781e5673-c04c-4e31-b767-9235ccf87af7)
*Center-aligned hero carousels have padding on both sides of the container*

| Attribute | Value |
| --- | --- |
| Alignment | Vertically centered |
| Leading/Trailing padding | 16dp |
| Top/bottom padding | 8dp |
| Padding between elements | 8dp |
| Large item width | Dynamic |
| Small item width | 40-56dp, dynamic |
| Item corner radius | 28dp |

## Full-screen

The full-screen layout shows one edge-to-edge large item.


![2 elements of a full-screen carousel layout.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Flyr7o850-13.png?alt=media&token=2075e453-f5f1-4372-8305-b05c2d7e0f3c)
*1. Container
2. Large carousel item*

### Measurements


![Measurements of a full-screen carousel layout.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fm3%2Fimages%2Fmi9mbqt5-16-3P.png?alt=media&token=5bc05830-cde0-428a-a596-1cc30c659e33)
*Full-screen carousels fill the window edge-to-edge*

| Attribute | Value |
| --- | --- |
| Alignment | Centered |
| Leading/Trailing padding | 0dp |
| Top/bottom padding | 0dp |
| Padding between elements | 16dp |


![Carousel items adapting dynamically to device size by shrinking and reducing in number.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flwusb1kq-1.png?alt=media&token=31e0f0e5-44dc-40dc-88a3-810d49d9d32a)
*Carousel items adapt dynamically based on breakpoint*

## Usage

Carousels display a scrollable list of items. Carousel items emphasize visuals, but can also contain brief text that adapts to the item size.

There are four carousel layouts:

- Multi-browse
- Uncontained
- Hero
- Full-screen

All of these layouts can be centered, though center-aligned hero is the most common centered carousel.

Carousels can scroll in two ways:

- **Default**: Standard scrolling. Recommended for uncontained layouts.
- **Snap-scrolling**: Scrolled items snap to the carousel's layout. Recommended for multi-browse, hero, and full-screen layouts


![Contained carousel with a large, medium, and small item. Text is hidden on the small item.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flwusbuoo-2.png?alt=media&token=fe9b4940-fc2b-453f-b10c-1eb4f1bb5250)
*A carousel can contain different sized items*

Choose the best carousel layout for your product. Some layouts are more visual-focused, while others are more customizable.

[](/m3/pages/carousel/guidelines#d95cefa4-53df-45e2-bbb2-3aeeecbe9639)[](/m3/pages/carousel/guidelines#96c5c157-fe5b-4ee3-a9b4-72bf8efab7e9)[](/m3/pages/carousel/guidelines#5991f961-79aa-4955-b86e-3e15432108e6)[
](/m3/pages/carousel/guidelines#a9f8dcde-e5c5-464f-b488-d9ded9ae4a4a)[
](/m3/pages/carousel/guidelines#ae0f1566-a956-4c4b-b153-d50ee20c32e7)

| Layout | Best used for |
| --- | --- |
| Multi-browse | Browsing many visual items at once (like photos), dynamic designs |
| Uncontained | Highly-customized or text-heavy carousels, stacked imaged and text, traditional carousel behavior |
| Hero | Spotlighting very large visual items (like a movie or featured app) |
| Center-aligned hero | Centered, large visual items |
| Full-screen | Vertically-scrolling video or image feeds, immersive experiences |

Carousel items must be fully visible on-screen (except for the uncontained layout). When scrolled, items automatically change size and snap into place to maintain the same layout.


![Multi-browse carousel with all images and text easily legible.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flwuscosc-3-do.png?alt=media&token=ddae19a2-c2a5-4a4f-a57a-89ebf9924d44)
*Set the large carousel item size to ensure the images and text are easy to read and recognize*


![Multi-browse carousel with a very thin small item, making the image unrecognizable.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flwusd3wu-3-don't.png?alt=media&token=9d93fc2f-8f36-44df-b19e-3b541f3dc539)
*Avoid setting carousel items so small that the image isn't recognizable*

### Accessibility requirements on scrolling pages

On vertically-scrolling pages, carousels require an accessible way to view all the items without horizontally scrolling. (This requirement doesn't apply to full-screen carousels.)

Material recommends adding a **Show all** button below the carousel, which opens a dedicated vertically-scrolling page of all carousel items.

If the carousel has a header, you can use an arrow icon button instead. 

View the [accessibility tab](/m3/pages/carousel/accessibility) for more details and alternate solutions.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flwutlc75-GM3-Components-Carousel-Guidelines-1-v01.mp4?alt=media&token=ec8a087b-ca5a-496a-b35a-a04c6a0c9a20)

### Multi-browse

The multi-browse layout is best for browsing many items at once, like photos or event feeds. Snap-scrolling is recommended to ensure items are recognizable and consistently sized.

On larger screens, more large and medium items are visible in this layout.

Avoid using this layout if the carousel items need lots of text or have complicated imagery.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flwutm3x8-GM3-Components-Carousel-Guidelines-2-v01.mp4?alt=media&token=e67990c5-f2f8-4f84-bf6d-196c8f7f8dbb)

At compact breakpoints, only show up to three carousel items if they have text.

If you need to show more than three items, make sure the images and content are easy to understand and recognize.


![Carousel on a phone. 2 large, 1 medium, and 1 small item are fully visible but thin. The items don't have text.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flwusga4v-6.png?alt=media&token=79689475-2747-4ee9-839c-b8c1eecba850)
*At compact breakpoints, only show more than three items if the items are easy to understand and recognize*

### Uncontained

The uncontained layout is most similar to a traditional carousel, where items are a single size and flow past the edge of the screen.

Both default scrolling and snap-scrolling work well with this layout.

Since items don't change size, this layout can be customized to show more text or other UI above or below each item without the text being masked or cropped.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flwutpwf6-GM3-Components-Carousel-Guidelines-3-v01.mp4?alt=media&token=02d55f05-f085-4e6e-a2ed-272891024672)

### Uncontained multi-aspect ratio

The uncontained multi-aspect ratio carousel has the same layout as the uncontained carousel but contains items of various sizes.

Item widths can range anywhere between 9:16 for min width size to 16:9 for max width.

Only use this carousel layout if the items have various widths.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Fmgtvndn1-GM3_Expressive_Carousel_Guidelines%2008_IA_v02.mp4?alt=media&token=f6b392c9-3542-45a7-99e7-e2798fd3bfa5)

### Hero

The hero layout is best for spotlighting content that needs more attention, like movie, shows, or other media thumbnails. It highlights one large image to focus on, while providing a sneak peek of what’s next. 

On larger screens, more large items are visible.

Use snap-scrolling so users can easily cycle through items one at a time.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flwutqti0-GM3-Components-Carousel-Guidelines-4-v01.mp4?alt=media&token=8516a459-51b7-47d3-acdd-ca2b69709b21)

### Center-aligned hero

When the hero layout is center-aligned, it adds an additional previewed item on the leading edge, making the large carousel item centered.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flwuts7ja-GM3-Components-Carousel-Guidelines-5-v01.mp4?alt=media&token=d60c2585-ab6f-4f79-ab1a-69d95a57612b)

### Full-screen

The full-screen layout is best used for immersive experiences like video articles, featured headlines, or items that are visually rich. It can contain text and other UI elements on top of the image.

This layout works best with content that is taller than it is wide, and scrolls vertically. It only works in portrait orientation in compact and medium breakpoints. Don't use this layout in landscape orientation.

Use snap-scrolling for full-screen layouts. Don't use default scrolling.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flwutt19y-GM3-Components-Carousel-Guidelines-6-v01.mp4?alt=media&token=9ce1694c-0db3-44a5-93c0-5f48158345dc)

## Anatomy


![4 elements of a carousel.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flwushw0z-11.png?alt=media&token=0ec8f8d6-6910-47b8-af5e-aec914857c5e)
*Multi-browse carousel (left) and hero carousel (right):

1. Container
2. Large carousel item
3. Medium carousel item
4. Small carousel item*

### Container

The carousel container holds all carousel items. The number of carousel items visible in the container can change based on the carousel layout and breakpoint.


![An empty rectangle representing a carousel container.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flwusikhn-12.png?alt=media&token=e53338c0-9e12-4500-a02a-6a3fa00a5be7)
*The carousel container is a rectangle. It can be used in many ways and stretched to any size.*

### Carousel items

Carousel items hold content. A carousel item has no fixed width; it changes width depending on the breakpoint or its position in the carousel's layout. There are three dynamic widths an item can be: **large**, **medium**, and **small**.


![3 sizes of carousel items.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flwusj1ii-13.png?alt=media&token=a8d53996-8396-4e21-a972-c74335739543)
*1. Large carousel item
2. Medium carousel item
3. Small carousel item*

Large items have a max width that can be adjusted. This changes how all other large, medium, and small carousel items fit on screen. Large items must remain big enough to be easy to understand and recognize.

Medium items adjust width dynamically to the carousel size and available space. 

Small items have a width range of 40–56dp.


![2 sizes of carousel items for hero layouts.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flwusjjti-14.png?alt=media&token=405ab913-2f35-4d0e-aa46-07e957c4e947)
*1. Large item
2. Small item*

When the large item's max width is narrow enough, more items can be shown on screen at once. At compact breakpoints, this is only recommended for carousels with simple imagery.


![3 sizes of carousel items for multi-browse layouts. 2 large items are visible.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flwuslefr-15.png?alt=media&token=93b132b6-bb28-4e57-9a57-f1c02ac20115)
*1. Large item
2. Medium item
3. Small item*

### Item text (optional)

Carousel items are primarily visual. Item text is optional and should be brief. 

If your carousel items require a lot of text, consider using the **uncontained layout**, which can show more information, or use a series of cards instead.


![Contained carousel items with 5 lines of content: a title, a description, and a label.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flwusmns4-16.png?alt=media&token=11d776e8-048f-4e1e-81d9-1a265271a7a6)
*Avoid exceeding two lines of text in carousel items at compact breakpoints unless the background is simple, like a single color*

Content within a carousel item can adapt dynamically based on the container and breakpoint. 

Text should always be understandable at each size. Consider adapting the text to use brief labels on smaller carousel items.


![A carousel item at 3 different sizes to show  how text changes at each size.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flwusqbrm-17.png?alt=media&token=bcc4c752-6b49-4820-960e-c22af7c9591b)
*1. Large carousel item with the full title and label text
2. Medium carousel item hiding the title text
3. Small carousel item abbreviating the label text*

## Adaptive design

As the carousel container size increases, so do the number of carousel items visible at a time.

At compact breakpoints, carousels can comfortably show up to three carousel items at once.

At larger breakpoints, more carousel items are added and scale in size.

Full-screen carousels only ever show one item at once.


![8 carousel items visible at once on a tablet in landscape orientation.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flwuso0q8-18.png?alt=media&token=1ca63117-b627-4361-afb1-6730a8ee93d3)
*Carousels can show more items at once on larger screens*

## Behavior

### Scrolling

Carousel items have a parallax effect when they're scrolled.

The **default** scrolling behavior doesn't snap items to a layout grid. They can stop anywhere in the container. Only use this for the uncontained layout.

The **snap-scrolling** behavior aligns carousel items to the layout grid after they're scrolled. Items snap to the grid when released. Use this for multi-browse, hero, and full-screen layouts.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flwutupr4-GM3-Components-Carousel-Guidelines-7-v01.mp4?alt=media&token=60140626-ce6d-48e5-8031-b93f5d56fadf)

In full-screen layouts, make sure the carousel items snap to the edges of the carousel container. Avoid letting them scroll freely or stop halfway.

Full-screen layouts must use snap-scrolling.


![Full-screen carousel with items stopping halfway off screen once scrolled.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flwuspl2g-20-don't.png?alt=media&token=71d2bacf-deaf-4fc7-84f2-7b8f5fd86747)
*Avoid scrolling freely on full-screen carousels*

## Use cases

Users should be able to do the following with assistive technology:

- Navigate to the carousel container
- Navigate between different carousel items
- Activate a carousel item
- Skip over the carousel items

## Requirements on scrolling pages

On vertically-scrolling pages, carousels require an accessible way to view all the items without horizontally scrolling. (This requirement doesn't apply to full-screen carousels.)

Material recommends adding a **Show all** button below the carousel, which opens a dedicated vertically-scrolling page of all carousel items.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flwutxg2r-GM3-Components-Carousel-Accesibility-1-v01.mp4?alt=media&token=481dab77-9f29-4618-b6a8-6e37eda180e4)


![Measurements of a "show all" button added below a carousel.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flwut0q8v-2.png?alt=media&token=fd99e7b1-4959-4151-a530-a820e6a115f6)
*The **Show all** button should have a padding of 4dp*

If the carousel has a header, you can use an arrow icon button instead. Place the arrow icon directly next to the header or in the same row.

Make sure the header is also displayed on the page of all carousel items.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flwutykt0-GM3-Components-Carousel-Accesibility-2-v01.mp4?alt=media&token=f22a4f28-192d-4adf-ae7a-f8e705e94062)


![Measurements of an arrow icon button added next to a carousel header.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flwut172a-4.png?alt=media&token=79318ca5-9f6e-4973-ba50-09469633894b)
*Headers should align with the leading edge, and the arrow icon should have a size of 48dp*

Avoid customizing the accessibility solution when possible. However, if your product needs an alternative solution, consider adding a **Show all** button in nearby navigation, or add alternative control buttons close to the carousel.

Avoid adding UI elements, like arrows or other icons, within or beside the carousel.


![Arrow icons on the left and right of the carousel, reducing container width.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flwykx1vc-5a-don't.png?alt=media&token=88d824af-cca5-4743-a7f4-5dc1776d152e)
*Avoid adding buttons into the carousel container or beside it. Place any buttons above or below the carousel.*


![Arrow icons floating on top of the carousel edge, concealing items.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flwykxhsn-5b-don't.png?alt=media&token=7253d6f2-ad26-4cd6-854c-e5b47725c499)
*Don't cover the carousel with buttons or other UI*

## Interaction & style

### Touch

Tapping on a carousel item changes the shape slightly, and creates a touch ripple for interaction feedback.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flwuu2jae-GM3-Components-Carousel-Accesibility-3-v01.mp4?alt=media&token=2e678652-4107-4db0-bbde-ab3b09dc2b81)

### Cursor

The hover state provides a visual cue that the carousel item is interactive.

When the carousel item is clicked (in both active and inactive states), a ripple appears for interaction feedback.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flwuu32xk-GM3-Components-Carousel-Accesibility-4-v02.mp4?alt=media&token=f5315212-a834-42a4-a3be-5afeb59d29ff)

### Initial focus

When navigating to a carousel using assistive technology, use **Tab** to place initial focus on the first carousel item. Then, use **Tab** or the arrow keys to navigate the carousel items.

Use the up and down arrow keys to leave the carousel and focus on the next element on the page, like the **Show all** button.


![Diagram of how to navigate a carousel using assistive technology.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flwut9phs-8.png?alt=media&token=b672b540-9326-4e15-9ce3-3696370d6a10)
*Set initial focus on the first carousel item, and use arrows to navigate items*


![Diagram of how not to navigate a carousel using assistive technology.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flwuta5uo-9.png?alt=media&token=4e12e7df-cf8a-4535-9dea-182a8767f066)
*Avoid focusing on the carousel container*

## Keyboard navigation

********
********

| Keys | Actions |
| --- | --- |
| Tab or Arrows | Moves to the previous or next carousel item |
| Space or Enter | Activates the focused carousel item |

## Labeling elements

The carousel container has the **container** role.


![Accessibility labels of a carousel.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flwutav24-10.png?alt=media&token=cdceb9da-aa24-499f-a5ad-e9d8a028493f)
*The carousel container is labelled appropriately and has the **container** role*

Each carousel may have a different number of items, so the label reads out the total amount of items and the current item in focus.


![Accessibility labels of a carousel item.](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flwutbdy7-11.png?alt=media&token=d28c067c-4591-44c8-93a0-ccb5adc4c497)
*The carousel item label indicates the current item in focus and the total number of items*

## Reduced motion

When reduced motion settings are turned on, the parallax effect should be removed and carousel items should no longer expand as they come into view. All items are the same size.

Make sure carousels with reduced motion reach the edges of the window to avoid clipping visuals.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flwuu4mjv-GM3-Components-Carousel-Accesibility-5-v01.mp4?alt=media&token=6e5e6517-2ba7-40fd-98ba-db01729dfdf8)

For hero carousels with reduced motion, the small carousel item is only partially shown on screen.


> 📹 [Watch Video / Motion Spec](https://firebasestorage.googleapis.com/v0/b/design-spec/o/projects%2Fgoogle-material-3%2Fimages%2Flwuu5ddb-GM3-Components-Carousel-Accesibility-6-v01.mp4?alt=media&token=cd58d17b-d305-456b-8820-7b40a077577d)
