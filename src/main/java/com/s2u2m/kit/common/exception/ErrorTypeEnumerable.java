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

/**
 * ErrorTypeEnumerable create on 2018/11/1
 *
 * @author Amos Xia
 */
public interface ErrorTypeEnumerable extends IntEnumerable {


    /**
     * Bit count taken by error type in an integer(32 bits).
     * @return bit count
     */
    int offsetBits();

}
