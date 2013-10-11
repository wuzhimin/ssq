/**
 * $ $ License.
 *
 * Copyright $ L2FProd.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package util;
import java.awt.Toolkit;

/**
 * Provides methods related to the runtime environment.
 */
public class OS {

  private static final boolean osIsMacOsX;
  private static final boolean osIsWindows;
  private static final boolean osIsWindowsXP;
  private static final boolean osIsWindows2003;
  private static final boolean osIsLinux;

  static {
    String os = System.getProperty("os.name").toLowerCase();

    osIsMacOsX = "mac os x".equals(os);
    osIsWindows = os.indexOf("windows") != -1;
    osIsWindowsXP = "windows xp".equals(os);
    osIsWindows2003 = "windows 2003".equals(os);
    osIsLinux = "linux".equals(os);
  }

  /**
   * @return true if this VM is running on Mac OS X
   */
  public static boolean isMacOSX() {
    return osIsMacOsX;
  }

  /**
   * @return true if this VM is running on Windows
   */
  public static boolean isWindows() {
    return osIsWindows;
  }

  /**
   * @return true if this VM is running on Windows XP
   */
  public static boolean isWindowsXP() {
    return osIsWindowsXP;
  }

  /**
   * @return true if this VM is running on Windows 2003
   */
  public static boolean isWindows2003() {
    return osIsWindows2003;
  }

  /**
   * @return true if this VM is running on Linux
   */
  public static boolean isLinux() {
    return osIsLinux;
  }

  /**
   * @return true if the VM is running Windows and the Java
   *         application is rendered using XP Visual Styles.
   */
  public static boolean isUsingWindowsVisualStyles() {
    if (!isWindows()) {
      return false;
    }

    boolean xpthemeActive = Boolean.TRUE.equals(Toolkit.getDefaultToolkit()
        .getDesktopProperty("win.xpstyle.themeActive"));
    if (!xpthemeActive) {
      return false;
    }
	try {
        return System.getProperty("swing.noxp") != null;
      } catch (final RuntimeException e) {
        return true;
      }
  }

}