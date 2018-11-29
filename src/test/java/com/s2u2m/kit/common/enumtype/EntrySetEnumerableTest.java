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


import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * EntrySetEnumerableTest create on 2018/11/1
 *
 * @author Amos Xia
 */
public class EntrySetEnumerableTest {

    private static final int OFFSET_BITS = 10;

    private enum TestErrorTypeEnum implements EntrySetKeysEnumerable {
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

    private enum TestInnerEntrySetValuesEnum implements EntrySetValuesEnumerable<TestErrorTypeEnum> {
        UNKNOWN(1),
        ;

        private final int value;
        TestInnerEntrySetValuesEnum(int value) {
            this.value = value;
        }

        @Override
        public TestErrorTypeEnum getKey() {
            return TestErrorTypeEnum.INNER;
        }

        @Override
        public int getValue() {
            return this.value;
        }
    }

    @Test
    public void getCode__success() {
        TestInnerEntrySetValuesEnum input = TestInnerEntrySetValuesEnum.UNKNOWN;
        int expect = input.getKey().getValue() << 10 | input.getValue();
        int result = input.getCode();
        assertEquals(expect, result);
    }
}