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

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Define a layered enum like below:
 * |2bits|4bits|10bits|
 */
public class EnumLayerableTest {

    private static final int LAYER_1_BITS = 2;
    private static final int LAYER_2_BITS = 4;
    private static final int LAYER_3_BITS = 10;

    private enum Layer1Enum implements TopEnumLayer<Layer1Enum> {
        CRITICAL(1),
        WARNING(2),
        ;

        private final int value;
        Layer1Enum(int value) {
            this.checkEnumValid(value);
            this.value = value;
        }

        @Override
        public int bits() {
            return LAYER_1_BITS;
        }

        @Override
        public int getValue() {
            return this.value;
        }
    }

    private enum Layer2Enum implements ChildEnumLayer<Layer1Enum, Layer2Enum> {
        LAYER2_ITEM_1(1),
        LAYER2_ITEM_2(2),
        ;

        private final int value;
        Layer2Enum(int value) {
            this.checkEnumValid(value);
            this.value = value;
        }

        @Override
        public Layer1Enum getParent() {
            return Layer1Enum.CRITICAL;
        }

        @Override
        public int bits() {
            return LAYER_2_BITS;
        }

        @Override
        public int getValue() {
            return this.value;
        }
    }

    private enum Layer3Enum implements ChildEnumLayer<Layer2Enum, Layer3Enum> {
        LAYER3_ITEM_1(1),
        LAYER3_ITEM_2(2),        ;

        private final int value;
        Layer3Enum(int value) {
            this.checkEnumValid(value);
            this.value = value;
        }

        @Override
        public Layer2Enum getParent() {
            return Layer2Enum.LAYER2_ITEM_2;
        }

        @Override
        public int bits() {
            return LAYER_3_BITS;
        }

        @Override
        public int getValue() {
            return this.value;
        }
    }

    @Test
    public void testLayer2Enum__success() {
        Layer3Enum layer3 = Layer3Enum.LAYER3_ITEM_2;
        int exp = Layer1Enum.CRITICAL.getValue() << (LAYER_2_BITS + LAYER_3_BITS)
                | Layer2Enum.LAYER2_ITEM_2.getValue() << LAYER_3_BITS
                | Layer3Enum.LAYER3_ITEM_2.getValue();
        assertEquals(exp, layer3.serialize());
    }

    @Test
    public void testEnumLayerStorage__success() {
        MemoryEnumLayerStorage storage = new MemoryEnumLayerStorage();
        storage.store(Layer3Enum.class);

        Layer3Enum layer3Enum = Layer3Enum.LAYER3_ITEM_2;
        int serialize = layer3Enum.serialize();

        Layer3Enum rst = (Layer3Enum) storage.get(serialize);
        assertEquals(layer3Enum, rst);
    }

}