$ErrorActionPreference = "Stop"

$exePath = $env:EXE_PATH
$appId  = $env:APP_ID

# Environment variables
$navX = $env:NAV_X
$homeY = $env:HOME_Y
$searchY = $env:SEARCH_Y
$libraryY = $env:LIBRARY_Y
$settingsY = $env:SETTINGS_Y
$winX = $env:WINDOW_X
$winY = $env:WINDOW_Y

Write-Host "Nav click X: $navX"
Write-Host "Home Y: $homeY, Search Y: $searchY, Library Y: $libraryY, Settings Y: $settingsY"
Write-Host "Window offset X: $winX, Y: $winY"

New-Item -ItemType Directory -Force -Path screenshots

# ─── Win32 helpers ───────────────────────────────────────────────────────────────

Add-Type @"
  using System;
  using System.Runtime.InteropServices;
  public class Win32 {
    [DllImport("user32.dll")]
    public static extern bool GetWindowRect(IntPtr hWnd, out RECT lpRect);
    [DllImport("user32.dll")]
    public static extern bool SetCursorPos(int X, int Y);
    [DllImport("user32.dll")]
    public static extern void mouse_event(uint dwFlags, int dx, int dy, int dwData, int dwExtraInfo);
    public const uint MOUSEEVENTF_LEFTDOWN = 0x0002;
    public const uint MOUSEEVENTF_LEFTUP   = 0x0004;
    [StructLayout(LayoutKind.Sequential)]
    public struct RECT { public int Left, Top, Right, Bottom; }
  }
"@

function Get-WindowRect {
  param([IntPtr]$hwnd)
  $rect = New-Object Win32+RECT
  [void][Win32]::GetWindowRect($hwnd, [ref]$rect)
  return $rect
}

function Set-CursorPos {
  param([int]$x, [int]$y)
  [void][Win32]::SetCursorPos($x, $y)
}

function Send-LeftClick {
  param([int]$x, [int]$y)
  [void][Win32]::SetCursorPos($x, $y)
  Start-Sleep -Milliseconds 100
  [void][Win32]::mouse_event([Win32]::MOUSEEVENTF_LEFTDOWN, 0, 0, 0, 0)
  [void][Win32]::mouse_event([Win32]::MOUSEEVENTF_LEFTUP,   0, 0, 0, 0)
}

# ─── Screenshot helpers ────────────────────────────────────────────────────────

Add-Type -AssemblyName System.Windows.Forms
Add-Type -AssemblyName System.Drawing

function Get-WindowBitmap {
  param([IntPtr]$hwnd)
  $rect = Get-WindowRect -hwnd $hwnd
  $w = $rect.Right - $rect.Left
  $h = $rect.Bottom - $rect.Top
  $bmp = New-Object System.Drawing.Bitmap($w, $h)
  $g   = [System.Drawing.Graphics]::FromImage($bmp)
  $g.CopyFromScreen($rect.Left, $rect.Top, 0, 0, (New-Object System.Drawing.Size($w, $h)))
  $g.Dispose()
  return $bmp
}

function Save-WindowScreenshot {
  param([string]$name, [int]$waitSec = 3)

  Start-Sleep -Seconds $waitSec

  # Try to find the app window
  $procs = Get-Process | Where-Object {
    $_.MainWindowHandle -ne [IntPtr]::Zero -and
    ($_.MainWindowTitle -like "*SimpMusic*" -or $_.MainWindowTitle -like "*Melophile*")
  }

  if ($procs) {
    $hwnd = ($procs | Select-Object -First 1).MainWindowHandle
    $bmp = Get-WindowBitmap -hwnd $hwnd
    $bmp.Save("screenshots/$name.png", [System.Drawing.Imaging.ImageFormat]::Png)
    $bmp.Dispose()
    Write-Host "Captured window: screenshots/$name.png"
  } else {
    # Fallback: full screen
    $bounds = [System.Windows.Forms.Screen]::PrimaryScreen.Bounds
    $bmp = New-Object System.Drawing.Bitmap($bounds.Width, $bounds.Height)
    $g   = [System.Drawing.Graphics]::FromImage($bmp)
    $g.CopyFromScreen($bounds.Location, [System.Drawing.Point]::Empty, $bounds.Size)
    $bmp.Save("screenshots/$name.png", [System.Drawing.Imaging.ImageFormat]::Png)
    $g.Dispose()
    $bmp.Dispose()
    Write-Host "Captured full screen: screenshots/$name.png"
  }
}

function Invoke-Click {
  param([int]$x, [int]$y, [int]$waitSec = 2)
  Send-LeftClick -x $x -y $y
  Write-Host "Clicked at ($x, $y)"
  Start-Sleep -Seconds $waitSec
}

# ─── Main ─────────────────────────────────────────────────────────────────────

Write-Host "Launching app: $exePath"
$proc = Start-Process -FilePath $exePath -ArgumentList $appId -PassThru
Write-Host "Process started with PID: $($proc.Id)"

# Give the app extra time to render
Write-Host "Waiting 15s for app to load..."
Start-Sleep -Seconds 15

# --- 1. Home ---
Write-Host "`n=== Screenshot: Home ==="
Save-WindowScreenshot -name "01-home" -waitSec 3

# --- 2. Search ---
Write-Host "`n=== Navigating to Search ==="
Invoke-Click -x ([int]$winX + [int]$navX) -y ([int]$winY + [int]$searchY) -waitSec 3
Save-WindowScreenshot -name "02-search" -waitSec 3

# --- 3. Library ---
Write-Host "`n=== Navigating to Library ==="
Invoke-Click -x ([int]$winX + [int]$navX) -y ([int]$winY + [int]$libraryY) -waitSec 3
Save-WindowScreenshot -name "03-library" -waitSec 3

# --- 4. Settings ---
Write-Host "`n=== Navigating to Settings ==="
Invoke-Click -x ([int]$winX + [int]$navX) -y ([int]$winY + [int]$settingsY) -waitSec 4
Save-WindowScreenshot -name "04-settings" -waitSec 4

# --- Back to Home ---
Write-Host "`n=== Back to Home ==="
Invoke-Click -x ([int]$winX + [int]$navX) -y ([int]$winY + [int]$homeY) -waitSec 3

# --- 5. Now Playing ---
Write-Host "`n=== Clicking Now Playing area ==="
$npX = [int]$winX + [int]$env:WINDOW_WIDTH / 2
$npY = [int]$winY + [int]$env:WINDOW_HEIGHT - 80
Invoke-Click -x $npX -y $npY -waitSec 6
Save-WindowScreenshot -name "05-now-playing" -waitSec 3

# Close Now Playing
[System.Windows.Forms.SendKeys]::SendWait("{ESC}")
Start-Sleep -Seconds 2

# --- 6. Settings > Playback section ---
Write-Host "`n=== Settings > Playback ==="
Invoke-Click -x ([int]$winX + [int]$navX) -y ([int]$winY + [int]$settingsY) -waitSec 3
Save-WindowScreenshot -name "06-settings-playback" -waitSec 3

# --- 7. Settings > Appearance (scroll down) ---
Write-Host "`n=== Scrolling Settings to Appearance ==="
[System.Windows.Forms.SendKeys]::SendWait("{PGDN}")
Start-Sleep -Seconds 2
[System.Windows.Forms.SendKeys]::SendWait("{PGDN}")
Start-Sleep -Seconds 2
[System.Windows.Forms.SendKeys]::SendWait("{PGDN}")
Start-Sleep -Seconds 2
Save-WindowScreenshot -name "07-settings-appearance" -waitSec 3

Write-Host "`n=== All screenshots captured! ==="
Get-ChildItem screenshots | ForEach-Object {
  Write-Host "  $($_.Name) ($($_.Length) bytes)"
}

# Cleanup
if ($proc -and -not $proc.HasExited) {
  Stop-Process -Id $proc.Id -Force -ErrorAction SilentlyContinue
}