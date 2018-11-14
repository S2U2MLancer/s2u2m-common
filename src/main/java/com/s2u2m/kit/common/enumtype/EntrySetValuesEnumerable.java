/*
 * Copyright 2018 s2u2m
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

package com.s2u2m.kit.common.enumtype;

import java.text.MessageFormat;

/**
 * EntrySetValuesEnumerable create on 2018/11/1
 *
 * @author Amos Xia
 */
public interface EntrySetValuesEnumerable<KT extends EntrySetKeysEnumerable> extends IntEnumerable {

    /** get specific entry set key type for this kind of values.
     *
     * @return specific key type
     */
    KT getKey();

    /**
     * Get integer code by key and value.
     *
     * @return integer code
     */
    default int getCode() {
        KT key = this.getKey();
        int offset = key.offsetBits();
        if (offset >= Integer.SIZE) {
            String error = MessageFormat.format(
                    "{0} offset[{1}] must smaller than max offset[{2}]",
                    key.getClass().getTypeName(), offset, Integer.SIZE);
            throw new IndexOutOfBoundsException(error);
        }

        int maxValue = 1 << offset;
        if (this.getValue() >= maxValue) {
            String error = MessageFormat.format(
                    "{0}[{1}] out of max bound[{2}], pls divide into pieces",
                    this.getClass().getTypeName(), this.getValue(), maxValue);
            throw new IndexOutOfBoundsException(error);
        }

        return key.getValue() << offset | this.getValue();
    }
}
