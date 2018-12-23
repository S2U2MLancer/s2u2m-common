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

/**
 *
 * @param <ET> Top Enum Layer must be a enum
 * @author Amos Xia
 */
public interface TopEnumLayer<ET extends Enum<ET>>
        extends EnumLayerable {

    /**
     * Calculated value from top to itself.
     * @return calculated value
     */
    @Override
    default int serialize() {
        return this.getValue();
    }

    /**
     * Get total token bits from top to itself.
     * @return total token bits.
     */
    @Override
    default int totalBits() {
        return this.bits();
    }
}
