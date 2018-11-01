/* * Copyright 2018 s2u2m
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

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ErrorValueEnumerableTest create on 2018/11/1
 *
 * @author Amos Xia
 */
class ErrorValueEnumerableTest {

    private static final int OFFSET_BITS = 10;

    private enum TestErrorTypeEnum implements ErrorTypeEnumerable {
        UNKNOWN(0),
        INNER(1),
        ;

        private final int value;
        TestErrorTypeEnum(int value) {
            this.value = value;
        }

        @Override
        public int offsetBits() {
            return OFFSET_BITS;
        }

        @Override
        public int getValue() {
            return this.value;
        }
    }

    private enum TestInnerErrorValueEnum implements ErrorValueEnumerable<TestErrorTypeEnum> {
        UNKNOWN(1),
        ;

        private final int value;
        TestInnerErrorValueEnum(int value) {
            this.value = value;
        }

        @Override
        public TestErrorTypeEnum getErrorType() {
            return TestErrorTypeEnum.INNER;
        }

        @Override
        public int getValue() {
            return this.value;
        }
    }

    @Test
    void getCode__success() {
        TestInnerErrorValueEnum input = TestInnerErrorValueEnum.UNKNOWN;
        int expect = input.getErrorType().getValue() << 10 | input.getValue();
        int result = input.getCode();
        assertEquals(expect, result);
    }
}