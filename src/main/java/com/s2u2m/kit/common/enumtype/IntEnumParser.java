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


import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author yangxue
 */
public final class IntEnumParser {

    /**
     *
     * @param value enum int value
     * @param etClass class of Enum Type
     * @param <ET> Enum Type implement IntEnumerable
     * @return   'Enum convert from value '
     */
    public static <ET extends Enum<ET> & IntEnumerable> ET convert(
            int value, Class<ET> etClass) {
        Optional<ET> valueOptional = Stream.of(etClass.getEnumConstants())
                .filter(et -> et.getValue() == value)
                .findAny();

        if (!valueOptional.isPresent()) {
            throw new EnumConstantNotPresentException(etClass, Integer.toString(value));
        }
        return valueOptional.get();
    }
}
