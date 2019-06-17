/*
 * Copyright (c) 2017, 2019, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have
 * questions.
 */
package jdk.incubator.vector;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ReadOnlyBufferException;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.IntUnaryOperator;

import jdk.internal.misc.Unsafe;
import jdk.internal.vm.annotation.ForceInline;
import jdk.internal.vm.annotation.Stable;

import static jdk.incubator.vector.VectorIntrinsics.*;
import static jdk.incubator.vector.VectorOperators.*;

// -- This file was mechanically generated: Do not edit! -- //

@SuppressWarnings("cast")  // warning: redundant cast
final class FloatMaxVector extends FloatVector {
    static final FloatSpecies VSPECIES =
        (FloatSpecies) FloatVector.SPECIES_MAX;

    static final VectorShape VSHAPE =
        VSPECIES.vectorShape();

    static final Class<FloatMaxVector> VCLASS = FloatMaxVector.class;

    static final int VSIZE = VSPECIES.vectorBitSize();

    static final int VLENGTH = VSPECIES.laneCount();

    static final Class<Float> ETYPE = float.class;

    // The JVM expects to find the state here.
    private final float[] vec; // Don't access directly, use getElements() instead.

    FloatMaxVector(float[] v) {
        vec = v;
    }

    // For compatibility as FloatMaxVector::new,
    // stored into species.vectorFactory.
    FloatMaxVector(Object v) {
        this((float[]) v);
    }

    static final FloatMaxVector ZERO = new FloatMaxVector(new float[VLENGTH]);
    static final FloatMaxVector IOTA = new FloatMaxVector(VSPECIES.iotaArray());

    static {
        // Warm up a few species caches.
        // If we do this too much we will
        // get NPEs from bootstrap circularity.
        VSPECIES.dummyVector();
        VSPECIES.withLanes(LaneType.BYTE);
    }

    // Specialized extractors

    @ForceInline
    final @Override
    public FloatSpecies vspecies() {
        // ISSUE:  This should probably be a @Stable
        // field inside AbstractVector, rather than
        // a megamorphic method.
        return VSPECIES;
    }


    /*package-private*/
    @ForceInline
    final @Override
    float[] getElements() {
        return VectorIntrinsics.maybeRebox(this).vec;
    }

    // Virtualized constructors

    @Override
    @ForceInline
    public final FloatMaxVector broadcast(float e) {
        return (FloatMaxVector) super.broadcastTemplate(e);  // specialize
    }

    @Override
    @ForceInline
    public final FloatMaxVector broadcast(long e) {
        return (FloatMaxVector) super.broadcastTemplate(e);  // specialize
    }

    @Override
    @ForceInline
    FloatMaxMask maskFromArray(boolean[] bits) {
        return new FloatMaxMask(bits);
    }

    @Override
    @ForceInline
    FloatMaxShuffle iotaShuffle() { return FloatMaxShuffle.IOTA; }

    @Override
    @ForceInline
    FloatMaxShuffle shuffleFromBytes(byte[] reorder) { return new FloatMaxShuffle(reorder); }

    @Override
    @ForceInline
    FloatMaxShuffle shuffleFromArray(int[] indexes, int i) { return new FloatMaxShuffle(indexes, i); }

    @Override
    @ForceInline
    FloatMaxShuffle shuffleFromOp(IntUnaryOperator fn) { return new FloatMaxShuffle(fn); }

    // Make a vector of the same species but the given elements:
    @ForceInline
    final @Override
    FloatMaxVector vectorFactory(float[] vec) {
        return new FloatMaxVector(vec);
    }

    @ForceInline
    final @Override
    ByteMaxVector asByteVectorRaw() {
        return (ByteMaxVector) super.asByteVectorRawTemplate();  // specialize
    }

    @ForceInline
    final @Override
    AbstractVector<?> asVectorRaw(LaneType laneType) {
        return super.asVectorRawTemplate(laneType);  // specialize
    }

    // Unary operator

    final @Override
    FloatMaxVector uOp(FUnOp f) {
        return (FloatMaxVector) super.uOp(f);  // specialize
    }

    @ForceInline
    final @Override
    FloatMaxVector uOp(VectorMask<Float> m, FUnOp f) {
        return (FloatMaxVector) super.uOp((FloatMaxMask)m, f);  // specialize
    }

    // Binary operator

    @ForceInline
    final @Override
    FloatMaxVector bOp(Vector<Float> o, FBinOp f) {
        return (FloatMaxVector) super.bOp((FloatMaxVector)o, f);  // specialize
    }

    @ForceInline
    final @Override
    FloatMaxVector bOp(Vector<Float> o,
                     VectorMask<Float> m, FBinOp f) {
        return (FloatMaxVector) super.bOp((FloatMaxVector)o, (FloatMaxMask)m,
                                        f);  // specialize
    }

    // Ternary operator

    @ForceInline
    final @Override
    FloatMaxVector tOp(Vector<Float> o1, Vector<Float> o2, FTriOp f) {
        return (FloatMaxVector) super.tOp((FloatMaxVector)o1, (FloatMaxVector)o2,
                                        f);  // specialize
    }

    @ForceInline
    final @Override
    FloatMaxVector tOp(Vector<Float> o1, Vector<Float> o2,
                     VectorMask<Float> m, FTriOp f) {
        return (FloatMaxVector) super.tOp((FloatMaxVector)o1, (FloatMaxVector)o2,
                                        (FloatMaxMask)m, f);  // specialize
    }

    @ForceInline
    final @Override
    float rOp(float v, FBinOp f) {
        return super.rOp(v, f);  // specialize
    }

    @Override
    @ForceInline
    public final <F>
    Vector<F> convertShape(VectorOperators.Conversion<Float,F> conv,
                           VectorSpecies<F> rsp, int part) {
        return super.convertShapeTemplate(conv, rsp, part);  // specialize
    }

    @Override
    @ForceInline
    public final <F>
    Vector<F> reinterpretShape(VectorSpecies<F> toSpecies, int part) {
        return super.reinterpretShapeTemplate(toSpecies, part);  // specialize
    }

    // Specialized algebraic operations:

    // The following definition forces a specialized version of this
    // crucial method into the v-table of this class.  A call to add()
    // will inline to a call to lanewise(ADD,), at which point the JIT
    // intrinsic will have the opcode of ADD, plus all the metadata
    // for this particular class, enabling it to generate precise
    // code.
    //
    // There is probably no benefit to the JIT to specialize the
    // masked or broadcast versions of the lanewise method.

    @Override
    @ForceInline
    public FloatMaxVector lanewise(Unary op) {
        return (FloatMaxVector) super.lanewiseTemplate(op);  // specialize
    }

    @Override
    @ForceInline
    public FloatMaxVector lanewise(Binary op, Vector<Float> v) {
        return (FloatMaxVector) super.lanewiseTemplate(op, v);  // specialize
    }


    /*package-private*/
    @Override
    @ForceInline
    public final
    FloatMaxVector
    lanewise(VectorOperators.Ternary op, Vector<Float> v1, Vector<Float> v2) {
        return (FloatMaxVector) super.lanewiseTemplate(op, v1, v2);  // specialize
    }

    @Override
    @ForceInline
    public final
    FloatMaxVector addIndex(int scale) {
        return (FloatMaxVector) super.addIndexTemplate(scale);  // specialize
    }

    // Type specific horizontal reductions

    @Override
    @ForceInline
    public final float reduceLanes(VectorOperators.Associative op) {
        return super.reduceLanesTemplate(op);  // specialized
    }

    @Override
    @ForceInline
    public final float reduceLanes(VectorOperators.Associative op,
                                    VectorMask<Float> m) {
        return super.reduceLanesTemplate(op, m);  // specialized
    }

    @Override
    @ForceInline
    public final long reduceLanesToLong(VectorOperators.Associative op) {
        return (long) super.reduceLanesTemplate(op);  // specialized
    }

    @Override
    @ForceInline
    public final long reduceLanesToLong(VectorOperators.Associative op,
                                        VectorMask<Float> m) {
        return (long) super.reduceLanesTemplate(op, m);  // specialized
    }

    @Override
    @ForceInline
    public VectorShuffle<Float> toShuffle() {
        float[] a = toArray();
        int[] sa = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            sa[i] = (int) a[i];
        }
        return VectorShuffle.fromArray(VSPECIES, sa, 0);
    }

    // Specialized comparisons

    @Override
    @ForceInline
    public final FloatMaxMask compare(Comparison op, Vector<Float> v) {
        return super.compareTemplate(FloatMaxMask.class, op, v);  // specialize
    }

    @Override
    @ForceInline
    public final FloatMaxMask compare(Comparison op, float s) {
        return super.compareTemplate(FloatMaxMask.class, op, s);  // specialize
    }

    @Override
    @ForceInline
    public final FloatMaxMask compare(Comparison op, long s) {
        return super.compareTemplate(FloatMaxMask.class, op, s);  // specialize
    }

    @Override
    @ForceInline
    public FloatMaxVector blend(Vector<Float> v, VectorMask<Float> m) {
        return (FloatMaxVector)
            super.blendTemplate(FloatMaxMask.class,
                                (FloatMaxVector) v,
                                (FloatMaxMask) m);  // specialize
    }

    @Override
    @ForceInline
    public FloatMaxVector slice(int origin, Vector<Float> v) {
        return (FloatMaxVector) super.sliceTemplate(origin, v);  // specialize
    }

    @Override
    @ForceInline
    public FloatMaxVector unslice(int origin, Vector<Float> w, int part) {
        return (FloatMaxVector) super.unsliceTemplate(origin, w, part);  // specialize
    }

    @Override
    @ForceInline
    public FloatMaxVector unslice(int origin, Vector<Float> w, int part, VectorMask<Float> m) {
        return (FloatMaxVector)
            super.unsliceTemplate(FloatMaxMask.class,
                                  origin, w, part,
                                  (FloatMaxMask) m);  // specialize
    }

    @Override
    @ForceInline
    public FloatMaxVector rearrange(VectorShuffle<Float> s) {
        return (FloatMaxVector)
            super.rearrangeTemplate(FloatMaxShuffle.class,
                                    (FloatMaxShuffle) s);  // specialize
    }

    @Override
    @ForceInline
    public FloatMaxVector rearrange(VectorShuffle<Float> shuffle,
                                  VectorMask<Float> m) {
        return (FloatMaxVector)
            super.rearrangeTemplate(FloatMaxShuffle.class,
                                    (FloatMaxShuffle) shuffle,
                                    (FloatMaxMask) m);  // specialize
    }

    @Override
    @ForceInline
    public FloatMaxVector rearrange(VectorShuffle<Float> s,
                                  Vector<Float> v) {
        return (FloatMaxVector)
            super.rearrangeTemplate(FloatMaxShuffle.class,
                                    (FloatMaxShuffle) s,
                                    (FloatMaxVector) v);  // specialize
    }

    @Override
    @ForceInline
    public FloatMaxVector selectFrom(Vector<Float> v) {
        return (FloatMaxVector)
            super.selectFromTemplate((FloatMaxVector) v);  // specialize
    }

    @Override
    @ForceInline
    public FloatMaxVector selectFrom(Vector<Float> v,
                                   VectorMask<Float> m) {
        return (FloatMaxVector)
            super.selectFromTemplate((FloatMaxVector) v,
                                     (FloatMaxMask) m);  // specialize
    }


    @Override
    public float lane(int i) {
        if (i < 0 || i >= VLENGTH) {
            throw new IllegalArgumentException("Index " + i + " must be zero or positive, and less than " + VLENGTH);
        }
        int bits = (int) VectorIntrinsics.extract(
                                VCLASS, ETYPE, VLENGTH,
                                this, i,
                                (vec, ix) -> {
                                    float[] vecarr = vec.getElements();
                                    return (long)Float.floatToIntBits(vecarr[ix]);
                                });
        return Float.intBitsToFloat(bits);
    }

    @Override
    public FloatMaxVector withLane(int i, float e) {
        if (i < 0 || i >= VLENGTH) {
            throw new IllegalArgumentException("Index " + i + " must be zero or positive, and less than " + VLENGTH);
        }
        return VectorIntrinsics.insert(
                                VCLASS, ETYPE, VLENGTH,
                                this, i, (long)Float.floatToIntBits(e),
                                (v, ix, bits) -> {
                                    float[] res = v.getElements().clone();
                                    res[ix] = Float.intBitsToFloat((int)bits);
                                    return v.vectorFactory(res);
                                });
    }

    // Mask

    static final class FloatMaxMask extends AbstractMask<Float> {

        private final boolean[] bits; // Don't access directly, use getBits() instead.

        public FloatMaxMask(boolean[] bits) {
            this(bits, 0);
        }

        public FloatMaxMask(boolean[] bits, int offset) {
            boolean[] a = new boolean[vspecies().laneCount()];
            for (int i = 0; i < a.length; i++) {
                a[i] = bits[offset + i];
            }
            this.bits = a;
        }

        public FloatMaxMask(boolean val) {
            boolean[] bits = new boolean[vspecies().laneCount()];
            Arrays.fill(bits, val);
            this.bits = bits;
        }

        @ForceInline
        final @Override
        public FloatSpecies vspecies() {
            // ISSUE:  This should probably be a @Stable
            // field inside AbstractMask, rather than
            // a megamorphic method.
            return VSPECIES;
        }

        boolean[] getBits() {
            return VectorIntrinsics.maybeRebox(this).bits;
        }

        @Override
        FloatMaxMask uOp(MUnOp f) {
            boolean[] res = new boolean[vspecies().laneCount()];
            boolean[] bits = getBits();
            for (int i = 0; i < res.length; i++) {
                res[i] = f.apply(i, bits[i]);
            }
            return new FloatMaxMask(res);
        }

        @Override
        FloatMaxMask bOp(VectorMask<Float> o, MBinOp f) {
            boolean[] res = new boolean[vspecies().laneCount()];
            boolean[] bits = getBits();
            boolean[] mbits = ((FloatMaxMask)o).getBits();
            for (int i = 0; i < res.length; i++) {
                res[i] = f.apply(i, bits[i], mbits[i]);
            }
            return new FloatMaxMask(res);
        }

        @ForceInline
        @Override
        public final
        FloatMaxVector toVector() {
            return (FloatMaxVector) super.toVectorTemplate();  // specialize
        }

        @Override
        @ForceInline
        public <E> VectorMask<E> cast(VectorSpecies<E> s) {
            AbstractSpecies<E> species = (AbstractSpecies<E>) s;
            if (length() != species.laneCount())
                throw new IllegalArgumentException("VectorMask length and species length differ");
            boolean[] maskArray = toArray();
            // enum-switches don't optimize properly JDK-8161245
            switch (species.laneType.switchKey) {
            case LaneType.SK_BYTE:
                return new ByteMaxVector.ByteMaxMask(maskArray).check(species);
            case LaneType.SK_SHORT:
                return new ShortMaxVector.ShortMaxMask(maskArray).check(species);
            case LaneType.SK_INT:
                return new IntMaxVector.IntMaxMask(maskArray).check(species);
            case LaneType.SK_LONG:
                return new LongMaxVector.LongMaxMask(maskArray).check(species);
            case LaneType.SK_FLOAT:
                return new FloatMaxVector.FloatMaxMask(maskArray).check(species);
            case LaneType.SK_DOUBLE:
                return new DoubleMaxVector.DoubleMaxMask(maskArray).check(species);
            }

            // Should not reach here.
            throw new AssertionError(species);
        }

        // Unary operations

        @Override
        @ForceInline
        public FloatMaxMask not() {
            return (FloatMaxMask) VectorIntrinsics.unaryOp(
                                             VECTOR_OP_NOT, FloatMaxMask.class, int.class, VLENGTH,
                                             this,
                                             (m1) -> m1.uOp((i, a) -> !a));
        }

        // Binary operations

        @Override
        @ForceInline
        public FloatMaxMask and(VectorMask<Float> o) {
            Objects.requireNonNull(o);
            FloatMaxMask m = (FloatMaxMask)o;
            return VectorIntrinsics.binaryOp(VECTOR_OP_AND, FloatMaxMask.class, int.class, VLENGTH,
                                             this, m,
                                             (m1, m2) -> m1.bOp(m2, (i, a, b) -> a & b));
        }

        @Override
        @ForceInline
        public FloatMaxMask or(VectorMask<Float> o) {
            Objects.requireNonNull(o);
            FloatMaxMask m = (FloatMaxMask)o;
            return VectorIntrinsics.binaryOp(VECTOR_OP_OR, FloatMaxMask.class, int.class, VLENGTH,
                                             this, m,
                                             (m1, m2) -> m1.bOp(m2, (i, a, b) -> a | b));
        }

        // Reductions

        @Override
        @ForceInline
        public boolean anyTrue() {
            return VectorIntrinsics.test(BT_ne, FloatMaxMask.class, int.class, VLENGTH,
                                         this, this,
                                         (m, __) -> anyTrueHelper(((FloatMaxMask)m).getBits()));
        }

        @Override
        @ForceInline
        public boolean allTrue() {
            return VectorIntrinsics.test(BT_overflow, FloatMaxMask.class, int.class, VLENGTH,
                                         this, vspecies().maskAll(true),
                                         (m, __) -> allTrueHelper(((FloatMaxMask)m).getBits()));
        }

        /*package-private*/
        static FloatMaxMask maskAll(boolean bit) {
            return bit ? TRUE_MASK : FALSE_MASK;
        }
        static final FloatMaxMask TRUE_MASK = new FloatMaxMask(true);
        static final FloatMaxMask FALSE_MASK = new FloatMaxMask(false);
    }

    // Shuffle

    static final class FloatMaxShuffle extends AbstractShuffle<Float> {
        FloatMaxShuffle(byte[] reorder) {
            super(reorder);
        }

        public FloatMaxShuffle(int[] reorder) {
            super(reorder);
        }

        public FloatMaxShuffle(int[] reorder, int i) {
            super(reorder, i);
        }

        public FloatMaxShuffle(IntUnaryOperator fn) {
            super(fn);
        }

        @Override
        public FloatSpecies vspecies() {
            return VSPECIES;
        }

        static {
            // There must be enough bits in the shuffle lanes to encode
            // VLENGTH valid indexes and VLENGTH exceptional ones.
            assert(VLENGTH < Byte.MAX_VALUE);
            assert(Byte.MIN_VALUE <= -VLENGTH);
        }
        static final FloatMaxShuffle IOTA = new FloatMaxShuffle(IDENTITY);

        @Override
        public FloatMaxVector toVector() {
            return (FloatMaxVector) super.toVectorTemplate();  // specialize
        }

        @Override
        @ForceInline
        public <F> VectorShuffle<F> cast(VectorSpecies<F> s) {
            AbstractSpecies<F> species = (AbstractSpecies<F>) s;
            if (length() != species.laneCount())
                throw new AssertionError("NYI: Shuffle length and species length differ");
            int[] shuffleArray = toArray();
            // enum-switches don't optimize properly JDK-8161245
            switch (species.laneType.switchKey) {
            case LaneType.SK_BYTE:
                return new ByteMaxVector.ByteMaxShuffle(shuffleArray).check(species);
            case LaneType.SK_SHORT:
                return new ShortMaxVector.ShortMaxShuffle(shuffleArray).check(species);
            case LaneType.SK_INT:
                return new IntMaxVector.IntMaxShuffle(shuffleArray).check(species);
            case LaneType.SK_LONG:
                return new LongMaxVector.LongMaxShuffle(shuffleArray).check(species);
            case LaneType.SK_FLOAT:
                return new FloatMaxVector.FloatMaxShuffle(shuffleArray).check(species);
            case LaneType.SK_DOUBLE:
                return new DoubleMaxVector.DoubleMaxShuffle(shuffleArray).check(species);
            }

            // Should not reach here.
            throw new AssertionError(species);
        }

        @Override
        public FloatMaxShuffle rearrange(VectorShuffle<Float> o) {
            FloatMaxShuffle s = (FloatMaxShuffle) o;
            byte[] r = new byte[reorder.length];
            for (int i = 0; i < reorder.length; i++) {
                int ssi = s.reorder[i];
                r[i] = this.reorder[ssi];  // throws on exceptional index
            }
            return new FloatMaxShuffle(r);
        }
    }

    // ================================================

    // Specialized low-level memory operations.

    @ForceInline
    @Override
    final
    FloatVector fromArray0(float[] a, int offset) {
        return super.fromArray0(a, offset);  // specialize
    }

    @ForceInline
    @Override
    final
    FloatVector fromByteArray0(byte[] a, int offset) {
        return super.fromByteArray0(a, offset);  // specialize
    }

    @ForceInline
    @Override
    final
    FloatVector fromByteBuffer0(ByteBuffer bb, int offset) {
        return super.fromByteBuffer0(bb, offset);  // specialize
    }

    @ForceInline
    @Override
    final
    void intoArray0(float[] a, int offset) {
        super.intoArray0(a, offset);  // specialize
    }

    @ForceInline
    @Override
    final
    void intoByteArray0(byte[] a, int offset) {
        super.intoByteArray0(a, offset);  // specialize
    }

    // End of specialized low-level memory operations.

    // ================================================

}
