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

package com.s2u2m.kit.common.exception;

import com.s2u2m.kit.common.enumtype.IntEnumerable;

import java.text.MessageFormat;

/**
 * ErrorValueEnumerable create on 2018/11/1
 *
 * @author Amos Xia
 */
public interface ErrorValueEnumerable<ET extends ErrorTypeEnumerable> extends IntEnumerable {

    /** get specific error type for this kind of error values.
     *
     * @return specific error type
     */
    ET getErrorType();

    /**
     * Get integer error getCode by error type and error value.
     *
     * @return integer error getCode
     */
    default int getCode() {
        ET errorType = this.getErrorType();
        int offset = errorType.offsetBits();
        if (offset >= Integer.SIZE) {
            String error = MessageFormat.format(
                    "{0} offset[{1}] must smaller than max offset[{2}]",
                    errorType.getClass().getTypeName(), offset, Integer.SIZE);
            throw new IndexOutOfBoundsException(error);
        }

        int maxErrorValue = 1 << offset;
        if (this.getValue() >= maxErrorValue) {
            String error = MessageFormat.format("{0}[{1}] out of max bound[{2}]",
                    this.getClass().getTypeName(), this.getValue(), maxErrorValue);
            throw new IndexOutOfBoundsException(error);
        }

        return errorType.getValue() << offset | this.getValue();
    }
}
