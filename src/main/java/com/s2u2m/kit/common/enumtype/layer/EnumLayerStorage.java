package com.s2u2m.kit.common.enumtype.layer;

import java.util.DuplicateFormatFlagsException;

/**
 * EnumLayerStorage create on 2018/12/5
 *
 * @author Amos Xia
 */
public interface EnumLayerStorage {

    /**
     * Store enum serialize value and related layer enum.
     *
     * @param <ET>           the layered enum type
     * @param enumLayerClass the layered enum class
     * @throws DuplicateFormatFlagsException if exists any duplicate serialize value
     */
    <ET extends Enum<?> & EnumLayerable>
    void store(Class<ET> enumLayerClass) throws DuplicateFormatFlagsException;

    /**
     * Get layered enum by serialize value
     *
     * @param serializeValue the serialize value
     * @return layered enum
     */
    Enum<?> get(int serializeValue);
}
