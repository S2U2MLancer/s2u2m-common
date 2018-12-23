/*
 *    Copyright [2018] [s2u2m]
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.s2u2m.kit.common.enumtype.layer;

import java.text.MessageFormat;

/**
 * Make enum in multiple layers to make definition clear.
 * @author Amos Xia
 */
public interface EnumLayerable extends EnumLayerDefine {

    /**
     * Check token bits valid of layered enum,
     * used in constructor of Enum
     * @param value layer value for layered enum
     * @throws IndexOutOfBoundsException if longer than {@link Integer#SIZE}
     */
    default void checkEnumValid(int value) {
        if (this.totalBits() > Integer.SIZE) {
            String error = MessageFormat.format(
                    "Layered Enum[{0}] is too long, token bits must less than Integer.SIZE[{1}]",
                    this.getClass().getSimpleName(), Integer.SIZE);
            throw new IndexOutOfBoundsException(error);
        }

        int layerMaxValue = 1 << this.bits();
        if (value >= layerMaxValue) {
            String error = MessageFormat.format(
                    "Layered Enum[{0}] Value[{1}] must less than max value[{2}] in this layer.",
                    this.getClass().getSimpleName(), value, layerMaxValue);
            throw new IndexOutOfBoundsException(error);
        }
    }

    /**
     * Calculated value from top to itself.
     * @return calculated value
     */
    int serialize();

    /**
     * Get total token bits from top to itself.
     * @return total token bits.
     */
    int totalBits();
}
