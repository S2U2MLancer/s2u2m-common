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

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * IntEnumParserTest create on 2018/11/1
 *
 * @author Amos Xia
 */
class IntEnumParserTest {

    private enum TestIntEnum implements IntEnumerable {
        UNKNOWN(0),
        ;

        private final int value;
        TestIntEnum(int value) {
            this.value = value;
        }

        @Override
        public int getValue() {
            return this.value;
        }
    }

    @Test
    void convert__success() {
        TestIntEnum input = TestIntEnum.UNKNOWN;
        TestIntEnum output = IntEnumParser.convert(input.getValue(), TestIntEnum.class);
        assertEquals(input, output);
    }
}