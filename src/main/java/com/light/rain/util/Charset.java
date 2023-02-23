package com.light.rain.util;

import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.StandardCharsets;

/**
 * @Author: LightRain
 * @Description: 字符集增强版
 * @DateTime: 2023-02-23 17:44
 * @Version：1.0
 **/
public class Charset extends java.nio.charset.Charset {
    public static final java.nio.charset.Charset UTF_8 = StandardCharsets.UTF_8;
    public static final java.nio.charset.Charset UTF_16LE = StandardCharsets.UTF_16LE;
    public static final java.nio.charset.Charset UTF_16BE = StandardCharsets.UTF_16BE;
    public static final java.nio.charset.Charset UTF_16 = StandardCharsets.UTF_16;
    public static final java.nio.charset.Charset US_ASCII = StandardCharsets.US_ASCII;
    public static final java.nio.charset.Charset ISO_8859_1 = StandardCharsets.ISO_8859_1;
    public static final java.nio.charset.Charset UNICODE = java.nio.charset.Charset.forName("Unicode");
    public static final java.nio.charset.Charset GBK = java.nio.charset.Charset.forName("GBK");
    public static final java.nio.charset.Charset GB2312 = java.nio.charset.Charset.forName("GB2312");
    public static final java.nio.charset.Charset GB18030 = java.nio.charset.Charset.forName("GB18030");

    /**
     * Initializes a new charset with the given canonical name and alias
     * set.
     *
     * @param canonicalName The canonical name of this charset
     * @param aliases       An array of this charset's aliases, or null if it has no aliases
     * @throws IllegalCharsetNameException If the canonical name or any of the aliases are illegal
     */
    protected Charset(String canonicalName, String[] aliases) {
        super(canonicalName, aliases);
    }

    /**
     * Tells whether or not this charset contains the given charset.
     *
     * <p> A charset <i>C</i> is said to <i>contain</i> a charset <i>D</i> if,
     * and only if, every character representable in <i>D</i> is also
     * representable in <i>C</i>.  If this relationship holds then it is
     * guaranteed that every string that can be encoded in <i>D</i> can also be
     * encoded in <i>C</i> without performing any replacements.
     *
     * <p> That <i>C</i> contains <i>D</i> does not imply that each character
     * representable in <i>C</i> by a particular byte sequence is represented
     * in <i>D</i> by the same byte sequence, although sometimes this is the
     * case.
     *
     * <p> Every charset contains itself.
     *
     * <p> This method computes an approximation of the containment relation:
     * If it returns {@code true} then the given charset is known to be
     * contained by this charset; if it returns {@code false}, however, then
     * it is not necessarily the case that the given charset is not contained
     * in this charset.
     *
     * @param cs The given charset
     * @return {@code true} if the given charset is contained in this charset
     */
    @Override
    public boolean contains(java.nio.charset.Charset cs) {
        return false;
    }

    /**
     * Constructs a new decoder for this charset.
     *
     * @return A new decoder for this charset
     */
    @Override
    public CharsetDecoder newDecoder() {
        return null;
    }

    /**
     * Constructs a new encoder for this charset.
     *
     * @return A new encoder for this charset
     * @throws UnsupportedOperationException If this charset does not support encoding
     */
    @Override
    public CharsetEncoder newEncoder() {
        return null;
    }

}
