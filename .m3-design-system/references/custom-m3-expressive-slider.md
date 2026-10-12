# Custom M3 Expressive Slider: Build Guide

How the slider page is built, with the math and from-scratch code for every major UI stack. No official Material components are used anywhere (no MDC, no Compose `Slider`, no Flutter `Slider`). Every snippet draws and handles input itself, so you control every pixel.

## 1. Anatomy

An Expressive slider is three track segments plus a handle:

- **Inactive segment(s):** tonal, rounded outer end, small inner radius.
- **Active segment:** primary color, same rule.
- **Handle:** a thin vertical pill, not a circle. It narrows when pressed.
- **Gap:** empty space between the handle and both segments.
- **Stops (optional):** small dots for discrete values.
- **Value bubble (optional):** label above the handle, shown only while pressed.
- **Inset icon (optional):** inside the active segment, only on tall tracks.

## 2. Numbers used

| Part | Value |
| --- | --- |
| Track height XS / S / M / L | 16 / 24 / 40 / 56 |
| Handle width | 4 (2 when pressed) |
| Handle height | max(44, track height + 8) |
| Gap per side | 8 (half handle + 6) |
| Outer corner radius | track height / 2 |
| Inner corner radius | 4 |
| Stop dot | 4, hidden within 12 of a handle |
| Value bubble radius | 16 |
| Wave (wavy variant) | wavelength 40, amplitude 4, stroke 8 |
| Wave speed | 3.1 rad/s |
| Handle press animation | 150 ms, slight overshoot |

The gap is a constant 8 so it does not jitter when the handle width animates.

## 3. Core math (language independent)

```
xFromValue(v) = (v - min) / (max - min) * W
valueFromX(x) = clamp(round((min + clamp(x / W, 0, 1) * (max - min)) / step) * step, min, max)
```

Segments per mode (x = handle position, W = width, c = W / 2). Each segment lists its left edge, right edge, and whether each edge touches a handle.

```
std:     [0,0] | [0,x] handle on right | [x,W] handle on left
center:  x >= c:  [0,c] | [c,x] handle right | [x,W] handle left
         x <  c:  [0,x] handle right | [x,c] handle left | [c,W]
range:   [0,x0] handle right | [x0,x1] handles both | [x1,W] handle left
```

Drawing a segment:

1. `l2 = l + (leftIsHandle ? 8 : 0)` and `r2 = r - (rightIsHandle ? 8 : 0)`.
2. If `r2 - l2 < 1`, skip it.
3. Left radius = `h/2` if the raw left edge is 0, else 4. Right radius = `h/2` if the raw right edge is W, else 4.
4. Active segment uses primary, others use the tonal track color.

Stops: for each step position, draw a dot. Use the on-primary color if it lies inside the active interval, otherwise primary. Hide it if it is within 12 of any handle.

## 4. Interaction rules

- **Pointer down:** pick the nearest handle (matters for range), mark pressed, set the value from the pointer, capture the pointer.
- **Pointer move:** update only while pressed.
- **Pointer up or cancel:** clear pressed. Always handle cancel, or the handle stays narrow after a scroll gesture.
- **Range limits:** handle 0 may not pass `v1 - step`, handle 1 may not go below `v0 + step`.
- **Haptics:** one short tick on press, optional tick on each stop crossing for discrete sliders.
- **Bubble:** show on press, scale from 0.6 and fade in, hide on release.
- **Keyboard:** arrows move by one step, Home and End jump to the ends.
- **Touch scrolling:** disable the browser or parent scroll for this element while dragging.

## 5. Wavy variant

Only the active segment is wavy; the inactive part is a flat line with a round cap.

- Path from 6 to `x - 8`, `y = center + a * sin(2*pi*x/40 - phase)`.
- `a = 4 * env(fraction)`, where `env` ramps 0 to 1 over the first 10 percent and 1 to 0 over the last 5 percent, smoothed with `t*t*(3-2*t)`.
- `phase += dt * 3.1` every frame, so the wave flows to the right.
- Redraw only wavy sliders per frame; plain sliders redraw on input only.

## 6. Web: HTML + vanilla JS

```html
<div class='sld' tabindex='0' role='slider'>
  <i class='sg'></i><i class='sg a'></i><i class='sg'></i>
  <i class='hd'></i>
</div>
```

```css
.sld { position: relative; height: 48px; touch-action: none; }
.sg  { position: absolute; top: 50%; height: 40px; transform: translateY(-50%); background: var(--tr); }
.sg.a{ background: var(--p); }
.hd  { position: absolute; top: 50%; width: 4px; height: 48px; border-radius: 2px;
       background: var(--p); transform: translate(-50%, -50%);
       transition: width .15s cubic-bezier(.3, 0, 0, 1.4); }
.hd.pr { width: 2px; }
```

```js
const el = document.querySelector('.sld'), segs = el.querySelectorAll('.sg'), hd = el.querySelector('.hd');
let v = 0.45, down = false;
const G = 8, H = 40;
function seg(e, l, r, sl, sr) {
  const a = sl ? l + G : l, b = sr ? r - G : r, W = el.clientWidth;
  if (b - a < 1) { e.style.display = 'none'; return; }
  const rl = l <= 0 ? H / 2 : 4, rr = r >= W ? H / 2 : 4;
  e.style.cssText += 'display:;left:' + a + 'px;width:' + (b - a) + 'px;border-radius:' + rl + 'px ' + rr + 'px ' + rr + 'px ' + rl + 'px';
}
function draw() {
  const W = el.clientWidth, x = v * W;
  segs[0].style.display = 'none';
  seg(segs[1], 0, x, false, true);
  seg(segs[2], x, W, true, false);
  hd.style.left = x + 'px';
  hd.classList.toggle('pr', down);
}
function set(e) {
  const r = el.getBoundingClientRect();
  v = Math.min(1, Math.max(0, (e.clientX - r.left) / r.width));
  draw();
}
el.addEventListener('pointerdown', e => { down = true; el.setPointerCapture(e.pointerId); set(e); });
el.addEventListener('pointermove', e => { if (down) set(e); });
el.addEventListener('pointerup', () => { down = false; draw(); });
el.addEventListener('pointercancel', () => { down = false; draw(); });
addEventListener('resize', draw);
draw();
```

## 7. React

```tsx
import { useLayoutEffect, useRef, useState } from 'react';

export function Slider({ value, onChange, h = 40 }: { value: number; onChange: (v: number) => void; h?: number }) {
  const ref = useRef<HTMLDivElement>(null);
  const [W, setW] = useState(0);
  const [pressed, setPressed] = useState(false);

  useLayoutEffect(() => {
    const ro = new ResizeObserver(() => setW(ref.current!.clientWidth));
    ro.observe(ref.current!);
    return () => ro.disconnect();
  }, []);

  const upd = (e: React.PointerEvent) => {
    const r = ref.current!.getBoundingClientRect();
    onChange(Math.min(1, Math.max(0, (e.clientX - r.left) / r.width)));
  };
  const x = value * W, G = 8, hh = Math.max(44, h + 8), hw = pressed ? 2 : 4;
  const top = (hh - h) / 2;

  return (
    <div ref={ref} role='slider' tabIndex={0}
      aria-valuemin={0} aria-valuemax={100} aria-valuenow={Math.round(value * 100)}
      onPointerDown={e => { e.currentTarget.setPointerCapture(e.pointerId); setPressed(true); upd(e); }}
      onPointerMove={e => pressed && upd(e)}
      onPointerUp={() => setPressed(false)}
      onPointerCancel={() => setPressed(false)}
      onKeyDown={e => {
        if (e.key === 'ArrowRight') onChange(Math.min(1, value + 0.01));
        if (e.key === 'ArrowLeft') onChange(Math.max(0, value - 0.01));
      }}
      style={{ position: 'relative', height: hh, touchAction: 'none' }}>
      <i style={{ position: 'absolute', top, left: 0, width: Math.max(0, x - G), height: h,
        background: 'var(--p)', borderRadius: h / 2 + 'px 4px 4px ' + h / 2 + 'px' }} />
      <i style={{ position: 'absolute', top, left: x + G, width: Math.max(0, W - x - G), height: h,
        background: 'var(--tr)', borderRadius: '4px ' + h / 2 + 'px ' + h / 2 + 'px 4px' }} />
      <i style={{ position: 'absolute', top: 0, left: x - hw / 2, width: hw, height: hh,
        borderRadius: hw / 2, background: 'var(--p)', transition: 'width .15s cubic-bezier(.3,0,0,1.4)' }} />
    </div>
  );
}
```

## 8. Jetpack Compose (custom Canvas, no Material Slider)

```kotlin
@Composable
fun ExpressiveSlider(
  value: Float, onValueChange: (Float) -> Unit,
  active: Color, inactive: Color,
  modifier: Modifier = Modifier, trackHeight: Dp = 40.dp
) {
  var pressed by remember { mutableStateOf(false) }
  val hw by animateDpAsState(if (pressed) 2.dp else 4.dp)
  val handleH = maxOf(44.dp, trackHeight + 8.dp)

  Canvas(
    modifier.fillMaxWidth().height(handleH).pointerInput(Unit) {
      awaitEachGesture {
        val down = awaitFirstDown()
        pressed = true
        onValueChange((down.position.x / size.width).coerceIn(0f, 1f))
        drag(down.id) { c ->
          onValueChange((c.position.x / size.width).coerceIn(0f, 1f))
          c.consume()
        }
        pressed = false
      }
    }
  ) {
    val h = trackHeight.toPx()
    val g = 8.dp.toPx()
    val x = value * size.width

    fun seg(l: Float, r: Float, sl: Boolean, sr: Boolean, col: Color) {
      val a = if (sl) l + g else l
      val b = if (sr) r - g else r
      if (b - a < 1f) return
      val rl = CornerRadius(if (l <= 0f) h / 2 else 4.dp.toPx())
      val rr = CornerRadius(if (r >= size.width) h / 2 else 4.dp.toPx())
      val top = (size.height - h) / 2
      val p = Path().apply {
        addRoundRect(RoundRect(Rect(a, top, b, top + h),
          topLeft = rl, bottomLeft = rl, topRight = rr, bottomRight = rr))
      }
      drawPath(p, col)
    }

    seg(0f, x, false, true, active)
    seg(x, size.width, true, false, inactive)
    val w = hw.toPx()
    drawRoundRect(active, Offset(x - w / 2, 0f), Size(w, size.height), CornerRadius(w / 2))
  }
}
```

For range or centered modes, keep a list of handle fractions, pick the nearest one on `awaitFirstDown`, and reuse the same `seg` calls with the tables from section 3.

## 9. Android Views (custom View)

```kotlin
class ExpSlider(c: Context, a: AttributeSet? = null) : View(c, a) {
  var value = 0.5f
  var onChange: ((Float) -> Unit)? = null
  var active = 0xFF6750A4.toInt()
  var inactive = 0xFFE8DEF8.toInt()
  private val p = Paint(Paint.ANTI_ALIAS_FLAG)
  private val d = resources.displayMetrics.density
  private val path = Path()
  private var hw = 4 * d

  private fun animateHandle(to: Float) {
    ValueAnimator.ofFloat(hw, to * d).apply {
      duration = 150
      interpolator = OvershootInterpolator(1.4f)
      addUpdateListener { hw = it.animatedValue as Float; invalidate() }
    }.start()
  }

  private fun seg(cv: Canvas, l: Float, r: Float, sl: Boolean, sr: Boolean, col: Int) {
    val g = 8 * d
    val h = 40 * d
    val a = if (sl) l + g else l
    val b = if (sr) r - g else r
    if (b - a < 1f) return
    val rl = if (l <= 0f) h / 2 else 4 * d
    val rr = if (r >= width) h / 2 else 4 * d
    val top = (height - h) / 2
    path.reset()
    path.addRoundRect(a, top, b, top + h, floatArrayOf(rl, rl, rr, rr, rr, rr, rl, rl), Path.Direction.CW)
    p.color = col
    cv.drawPath(path, p)
  }

  override fun onDraw(cv: Canvas) {
    val x = value * width
    seg(cv, 0f, x, false, true, active)
    seg(cv, x, width.toFloat(), true, false, inactive)
    p.color = active
    cv.drawRoundRect(x - hw / 2, 0f, x + hw / 2, height.toFloat(), hw / 2, hw / 2, p)
  }

  override fun onTouchEvent(e: MotionEvent): Boolean {
    when (e.actionMasked) {
      MotionEvent.ACTION_DOWN -> { parent.requestDisallowInterceptTouchEvent(true); animateHandle(2f) }
      MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> animateHandle(4f)
    }
    value = (e.x / width).coerceIn(0f, 1f)
    onChange?.invoke(value)
    invalidate()
    return true
  }
}
```

Set the view height to 48dp in XML. Add `onMeasure` if you want the height to follow the track size.

## 10. Flutter (CustomPainter, no Slider widget)

```dart
class ExpressiveSlider extends StatefulWidget {
  final double value;
  final ValueChanged<double> onChanged;
  final Color active, inactive;
  final double trackHeight;
  const ExpressiveSlider({super.key, required this.value, required this.onChanged,
    required this.active, required this.inactive, this.trackHeight = 40});
  @override
  State<ExpressiveSlider> createState() => _ExpState();
}

class _ExpState extends State<ExpressiveSlider> {
  bool pressed = false;

  @override
  Widget build(BuildContext context) {
    final hh = widget.trackHeight + 8 < 44 ? 44.0 : widget.trackHeight + 8;
    return LayoutBuilder(builder: (context, box) {
      void set(Offset o) => widget.onChanged((o.dx / box.maxWidth).clamp(0.0, 1.0));
      return GestureDetector(
        onPanDown: (d) { setState(() => pressed = true); set(d.localPosition); },
        onPanUpdate: (d) => set(d.localPosition),
        onPanEnd: (_) => setState(() => pressed = false),
        onPanCancel: () => setState(() => pressed = false),
        child: TweenAnimationBuilder<double>(
          tween: Tween(end: pressed ? 2.0 : 4.0),
          duration: const Duration(milliseconds: 150),
          builder: (_, hw, __) => CustomPaint(
            size: Size(box.maxWidth, hh),
            painter: _SliderPainter(widget.value, hw, widget.trackHeight, widget.active, widget.inactive),
          ),
        ),
      );
    });
  }
}

class _SliderPainter extends CustomPainter {
  final double v, hw, h;
  final Color a, i;
  _SliderPainter(this.v, this.hw, this.h, this.a, this.i);

  void seg(Canvas c, Size s, double l, double r, bool sl, bool sr, Color col) {
    const g = 8.0;
    final x0 = sl ? l + g : l;
    final x1 = sr ? r - g : r;
    if (x1 - x0 < 1) return;
    final rl = Radius.circular(l <= 0 ? h / 2 : 4);
    final rr = Radius.circular(r >= s.width ? h / 2 : 4);
    final top = (s.height - h) / 2;
    c.drawRRect(
      RRect.fromLTRBAndCorners(x0, top, x1, top + h,
        topLeft: rl, bottomLeft: rl, topRight: rr, bottomRight: rr),
      Paint()..color = col);
  }

  @override
  void paint(Canvas c, Size s) {
    final x = v * s.width;
    seg(c, s, 0, x, false, true, a);
    seg(c, s, x, s.width, true, false, i);
    c.drawRRect(RRect.fromLTRBR(x - hw / 2, 0, x + hw / 2, s.height, Radius.circular(hw / 2)),
      Paint()..color = a);
  }

  @override
  bool shouldRepaint(_SliderPainter o) => o.v != v || o.hw != hw || o.h != h;
}
```

## 11. SwiftUI (iOS 16+, custom shapes)

```swift
struct ExpressiveSlider: View {
  @Binding var value: Double
  var h: CGFloat = 40
  var active: Color = .purple
  var inactive: Color = .purple.opacity(0.2)
  @State private var pressed = false
  private let gap: CGFloat = 8

  var body: some View {
    GeometryReader { geo in
      let W = geo.size.width
      let x = CGFloat(value) * W
      let hw: CGFloat = pressed ? 2 : 4
      ZStack(alignment: .leading) {
        if x - gap > 1 {
          UnevenRoundedRectangle(topLeadingRadius: h / 2, bottomLeadingRadius: h / 2,
                                 bottomTrailingRadius: 4, topTrailingRadius: 4)
            .fill(active)
            .frame(width: x - gap, height: h)
        }
        if W - x - gap > 1 {
          UnevenRoundedRectangle(topLeadingRadius: 4, bottomLeadingRadius: 4,
                                 bottomTrailingRadius: h / 2, topTrailingRadius: h / 2)
            .fill(inactive)
            .frame(width: W - x - gap, height: h)
            .offset(x: x + gap)
        }
        Capsule().fill(active)
          .frame(width: hw, height: max(44, h + 8))
          .offset(x: x - hw / 2)
      }
      .frame(maxHeight: .infinity)
      .contentShape(Rectangle())
      .gesture(
        DragGesture(minimumDistance: 0)
          .onChanged { drag in
            pressed = true
            value = Double(min(max(drag.location.x / W, 0), 1))
          }
          .onEnded { _ in pressed = false }
      )
      .animation(.spring(response: 0.25, dampingFraction: 0.7), value: pressed)
    }
    .frame(height: max(44, h + 8))
  }
}
```

## 12. React Native (Views + PanResponder)

```tsx
import { useRef, useState } from 'react';
import { View, PanResponder } from 'react-native';

const clamp = (n: number) => Math.min(1, Math.max(0, n));

export function ExpressiveSlider({ value, onChange, h = 40, active = '#6750a4', inactive = '#e8def8' }:
  { value: number; onChange: (v: number) => void; h?: number; active?: string; inactive?: string }) {
  const [W, setW] = useState(0);
  const [pressed, setPressed] = useState(false);
  const wRef = useRef(0);
  const start = useRef(0);
  const cb = useRef(onChange);
  cb.current = onChange;

  const pan = useRef(PanResponder.create({
    onStartShouldSetPanResponder: () => true,
    onPanResponderGrant: (e) => {
      start.current = e.nativeEvent.locationX;
      setPressed(true);
      cb.current(clamp(start.current / wRef.current));
    },
    onPanResponderMove: (_, g) => cb.current(clamp((start.current + g.dx) / wRef.current)),
    onPanResponderRelease: () => setPressed(false),
    onPanResponderTerminate: () => setPressed(false),
  })).current;

  const hh = Math.max(44, h + 8), top = (hh - h) / 2, G = 8, hw = pressed ? 2 : 4, x = value * W;

  return (
    <View
      onLayout={(e) => { wRef.current = e.nativeEvent.layout.width; setW(wRef.current); }}
      style={{ height: hh }} {...pan.panHandlers}>
      <View pointerEvents='none' style={{ flex: 1 }}>
        <View style={{ position: 'absolute', top, left: 0, width: Math.max(0, x - G), height: h,
          backgroundColor: active, borderTopLeftRadius: h / 2, borderBottomLeftRadius: h / 2,
          borderTopRightRadius: 4, borderBottomRightRadius: 4 }} />
        <View style={{ position: 'absolute', top, left: x + G, width: Math.max(0, W - x - G), height: h,
          backgroundColor: inactive, borderTopLeftRadius: 4, borderBottomLeftRadius: 4,
          borderTopRightRadius: h / 2, borderBottomRightRadius: h / 2 }} />
        <View style={{ position: 'absolute', top: 0, left: x - hw / 2, width: hw, height: hh,
          borderRadius: hw / 2, backgroundColor: active }} />
      </View>
    </View>
  );
}
```

For smoother width animation of the handle, wrap it in `Animated.View` and spring a value between 4 and 2.

## 13. Accessibility

- Expose role slider with min, max, and current value (`role='slider'`, `aria-valuenow` on web; `semantics` on Compose and Flutter; `accessibilityValue` on iOS and React Native).
- Provide step actions: increment and decrement.
- Keep the minimum touch height at 44, which is why the handle height never goes below 44.
- Do not rely on color alone: the gap and handle shape already separate the two states.
- Respect reduced motion: skip the wave phase animation and the bubble scale-in.

## 14. Common pitfalls

- Forgetting the pointer cancel event leaves the handle stuck narrow.
- Computing the radius from the shrunk edge instead of the raw edge makes inner corners wrong at the ends.
- A pressed handle width that changes the gap makes segments jitter; keep the gap constant at 8.
- On range sliders, always enforce the minimum distance between handles by one step.
- Stops should be hidden near handles or they overlap the gap.
- Inset icons must fade out when the active segment is shorter than about 50, or they spill into the gap.
- Reading width during layout in React and Compose needs a resize observer or `size`, not a cached value.

## 15. Tuning

| Want | Change |
| --- | --- |
| Softer handle | Handle width 6, pressed 3 |
| Tighter look | Gap 6, inner radius 2 |
| More playful | Overshoot easing 1.8 on handle width |
| Calmer wave | Amplitude 3, speed 2 rad/s |
| Bigger touch target | Handle height 56 |
