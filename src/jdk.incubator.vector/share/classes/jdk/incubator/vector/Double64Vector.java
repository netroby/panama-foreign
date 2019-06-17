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
import java.nio.DoubleBuffer;
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
final class Double64Vector extends DoubleVector {
    static final DoubleSpecies VSPECIES =
        (DoubleSpecies) DoubleVector.SPECIES_64;

    static final VectorShape VSHAPE =
        VSPECIES.vectorShape();

    static final Class<Double64Vector> VCLASS = Double64Vector.class;

    static final int VSIZE = VSPECIES.vectorBitSize();

    static final int VLENGTH = VSPECIES.laneCount();

    static final Class<Double> ETYPE = double.class;

    // The JVM expects to find the state here.
    private final double[] vec; // Don't access directly, use getElements() instead.

    Double64Vector(double[] v) {
        vec = v;
    }

    // For compatibility as Double64Vector::new,
    // stored into species.vectorFactory.
    Double64Vector(Object v) {
        this((double[]) v);
    }

    static final Double64Vector ZERO = new Double64Vector(new double[VLENGTH]);
    static final Double64Vector IOTA = new Double64Vector(VSPECIES.iotaArray());

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
    public DoubleSpecies vspecies() {
        // ISSUE:  This should probably be a @Stable
        // field inside AbstractVector, rather than
        // a megamorphic method.
        return VSPECIES;
    }


    /*package-private*/
    @ForceInline
    final @Override
    double[] getElements() {
        return VectorIntrinsics.maybeRebox(this).vec;
    }

    // Virtualized constructors

    @Override
    @ForceInline
    public final Double64Vector broadcast(double e) {
        return (Double64Vector) super.broadcastTemplate(e);  // specialize
    }

    @Override
    @ForceInline
    public final Double64Vector broadcast(long e) {
        return (Double64Vector) super.broadcastTemplate(e);  // specialize
    }

    @Override
    @ForceInline
    Double64Mask maskFromArray(boolean[] bits) {
        return new Double64Mask(bits);
    }

    @Override
    @ForceInline
    Double64Shuffle iotaShuffle() { return Double64Shuffle.IOTA; }

    @Override
    @ForceInline
    Double64Shuffle shuffleFromBytes(byte[] reorder) { return new Double64Shuffle(reorder); }

    @Override
    @ForceInline
    Double64Shuffle shuffleFromArray(int[] indexes, int i) { return new Double64Shuffle(indexes, i); }

    @Override
    @ForceInline
    Double64Shuffle shuffleFromOp(IntUnaryOperator fn) { return new Double64Shuffle(fn); }

    // Make a vector of the same species but the given elements:
    @ForceInline
    final @Override
    Double64Vector vectorFactory(double[] vec) {
        return new Double64Vector(vec);
    }

    @ForceInline
    final @Override
    Byte64Vector asByteVectorRaw() {
        return (Byte64Vector) super.asByteVectorRawTemplate();  // specialize
    }

    @ForceInline
    final @Override
    AbstractVector<?> asVectorRaw(LaneType laneType) {
        return super.asVectorRawTemplate(laneType);  // specialize
    }

    // Unary operator

    final @Override
    Double64Vector uOp(FUnOp f) {
        return (Double64Vector) super.uOp(f);  // specialize
    }

    @ForceInline
    final @Override
    Double64Vector uOp(VectorMask<Double> m, FUnOp f) {
        return (Double64Vector) super.uOp((Double64Mask)m, f);  // specialize
    }

    // Binary operator

    @ForceInline
    final @Override
    Double64Vector bOp(Vector<Double> o, FBinOp f) {
        return (Double64Vector) super.bOp((Double64Vector)o, f);  // specialize
    }

    @ForceInline
    final @Override
    Double64Vector bOp(Vector<Double> o,
                     VectorMask<Double> m, FBinOp f) {
        return (Double64Vector) super.bOp((Double64Vector)o, (Double64Mask)m,
                                        f);  // specialize
    }

    // Ternary operator

    @ForceInline
    final @Override
    Double64Vector tOp(Vector<Double> o1, Vector<Double> o2, FTriOp f) {
        return (Double64Vector) super.tOp((Double64Vector)o1, (Double64Vector)o2,
                                        f);  // specialize
    }

    @ForceInline
    final @Override
    Double64Vector tOp(Vector<Double> o1, Vector<Double> o2,
                     VectorMask<Double> m, FTriOp f) {
        return (Double64Vector) super.tOp((Double64Vector)o1, (Double64Vector)o2,
                                        (Double64Mask)m, f);  // specialize
    }

    @ForceInline
    final @Override
    double rOp(double v, FBinOp f) {
        return super.rOp(v, f);  // specialize
    }

    @Override
    @ForceInline
    public final <F>
    Vector<F> convertShape(VectorOperators.Conversion<Double,F> conv,
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
    public Double64Vector lanewise(Unary op) {
        return (Double64Vector) super.lanewiseTemplate(op);  // specialize
    }

    @Override
    @ForceInline
    public Double64Vector lanewise(Binary op, Vector<Double> v) {
        return (Double64Vector) super.lanewiseTemplate(op, v);  // specialize
    }


    /*package-private*/
    @Override
    @ForceInline
    public final
    Double64Vector
    lanewise(VectorOperators.Ternary op, Vector<Double> v1, Vector<Double> v2) {
        return (Double64Vector) super.lanewiseTemplate(op, v1, v2);  // specialize
    }

    @Override
    @ForceInline
    public final
    Double64Vector addIndex(int scale) {
        return (Double64Vector) super.addIndexTemplate(scale);  // specialize
    }

    // Type specific horizontal reductions

    @Override
    @ForceInline
    public final double reduceLanes(VectorOperators.Associative op) {
        return super.reduceLanesTemplate(op);  // specialized
    }

    @Override
    @ForceInline
    public final double reduceLanes(VectorOperators.Associative op,
                                    VectorMask<Double> m) {
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
                                        VectorMask<Double> m) {
        return (long) super.reduceLanesTemplate(op, m);  // specialized
    }

    @Override
    @ForceInline
    public VectorShuffle<Double> toShuffle() {
        double[] a = toArray();
        int[] sa = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            sa[i] = (int) a[i];
        }
        return VectorShuffle.fromArray(VSPECIES, sa, 0);
    }

    // Specialized comparisons

    @Override
    @ForceInline
    public final Double64Mask compare(Comparison op, Vector<Double> v) {
        return super.compareTemplate(Double64Mask.class, op, v);  // specialize
    }

    @Override
    @ForceInline
    public final Double64Mask compare(Comparison op, double s) {
        return super.compareTemplate(Double64Mask.class, op, s);  // specialize
    }

    @Override
    @ForceInline
    public final Double64Mask compare(Comparison op, long s) {
        return super.compareTemplate(Double64Mask.class, op, s);  // specialize
    }

    @Override
    @ForceInline
    public Double64Vector blend(Vector<Double> v, VectorMask<Double> m) {
        return (Double64Vector)
            super.blendTemplate(Double64Mask.class,
                                (Double64Vector) v,
                                (Double64Mask) m);  // specialize
    }

    @Override
    @ForceInline
    public Double64Vector slice(int origin, Vector<Double> v) {
        return (Double64Vector) super.sliceTemplate(origin, v);  // specialize
    }

    @Override
    @ForceInline
    public Double64Vector unslice(int origin, Vector<Double> w, int part) {
        return (Double64Vector) super.unsliceTemplate(origin, w, part);  // specialize
    }

    @Override
    @ForceInline
    public Double64Vector unslice(int origin, Vector<Double> w, int part, VectorMask<Double> m) {
        return (Double64Vector)
            super.unsliceTemplate(Double64Mask.class,
                                  origin, w, part,
                                  (Double64Mask) m);  // specialize
    }

    @Override
    @ForceInline
    public Double64Vector rearrange(VectorShuffle<Double> s) {
        return (Double64Vector)
            super.rearrangeTemplate(Double64Shuffle.class,
                                    (Double64Shuffle) s);  // specialize
    }

    @Override
    @ForceInline
    public Double64Vector rearrange(VectorShuffle<Double> shuffle,
                                  VectorMask<Double> m) {
        return (Double64Vector)
            super.rearrangeTemplate(Double64Shuffle.class,
                                    (Double64Shuffle) shuffle,
                                    (Double64Mask) m);  // specialize
    }

    @Override
    @ForceInline
    public Double64Vector rearrange(VectorShuffle<Double> s,
                                  Vector<Double> v) {
        return (Double64Vector)
            super.rearrangeTemplate(Double64Shuffle.class,
                                    (Double64Shuffle) s,
                                    (Double64Vector) v);  // specialize
    }

    @Override
    @ForceInline
    public Double64Vector selectFrom(Vector<Double> v) {
        return (Double64Vector)
            super.selectFromTemplate((Double64Vector) v);  // specialize
    }

    @Override
    @ForceInline
    public Double64Vector selectFrom(Vector<Double> v,
                                   VectorMask<Double> m) {
        return (Double64Vector)
            super.selectFromTemplate((Double64Vector) v,
                                     (Double64Mask) m);  // specialize
    }


    @Override
    public double lane(int i) {
        if (i < 0 || i >= VLENGTH) {
            throw new IllegalArgumentException("Index " + i + " must be zero or positive, and less than " + VLENGTH);
        }
        long bits = (long) VectorIntrinsics.extract(
                                VCLASS, ETYPE, VLENGTH,
                                this, i,
                                (vec, ix) -> {
                                    double[] vecarr = vec.getElements();
                                    return (long)Double.doubleToLongBits(vecarr[ix]);
                                });
        return Double.longBitsToDouble(bits);
    }

    @Override
    public Double64Vector withLane(int i, double e) {
        if (i < 0 || i >= VLENGTH) {
            throw new IllegalArgumentException("Index " + i + " must be zero or positive, and less than " + VLENGTH);
        }
        return VectorIntrinsics.insert(
                                VCLASS, ETYPE, VLENGTH,
                                this, i, (long)Double.doubleToLongBits(e),
                                (v, ix, bits) -> {
                                    double[] res = v.getElements().clone();
                                    res[ix] = Double.longBitsToDouble((long)bits);
                                    return v.vectorFactory(res);
                                });
    }

    // Mask

    static final class Double64Mask extends AbstractMask<Double> {

        private final boolean[] bits; // Don't access directly, use getBits() instead.

        public Double64Mask(boolean[] bits) {
            this(bits, 0);
        }

        public Double64Mask(boolean[] bits, int offset) {
            boolean[] a = new boolean[vspecies().laneCount()];
            for (int i = 0; i < a.length; i++) {
                a[i] = bits[offset + i];
            }
            this.bits = a;
        }

        public Double64Mask(boolean val) {
            boolean[] bits = new boolean[vspecies().laneCount()];
            Arrays.fill(bits, val);
            this.bits = bits;
        }

        @ForceInline
        final @Override
        public DoubleSpecies vspecies() {
            // ISSUE:  This should probably be a @Stable
            // field inside AbstractMask, rather than
            // a megamorphic method.
            return VSPECIES;
        }

        boolean[] getBits() {
            return VectorIntrinsics.maybeRebox(this).bits;
        }

        @Override
        Double64Mask uOp(MUnOp f) {
            boolean[] res = new boolean[vspecies().laneCount()];
            boolean[] bits = getBits();
            for (int i = 0; i < res.length; i++) {
                res[i] = f.apply(i, bits[i]);
            }
            return new Double64Mask(res);
        }

        @Override
        Double64Mask bOp(VectorMask<Double> o, MBinOp f) {
            boolean[] res = new boolean[vspecies().laneCount()];
            boolean[] bits = getBits();
            boolean[] mbits = ((Double64Mask)o).getBits();
            for (int i = 0; i < res.length; i++) {
                res[i] = f.apply(i, bits[i], mbits[i]);
            }
            return new Double64Mask(res);
        }

        @ForceInline
        @Override
        public final
        Double64Vector toVector() {
            return (Double64Vector) super.toVectorTemplate();  // specialize
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
                return new Byte64Vector.Byte64Mask(maskArray).check(species);
            case LaneType.SK_SHORT:
                return new Short64Vector.Short64Mask(maskArray).check(species);
            case LaneType.SK_INT:
                return new Int64Vector.Int64Mask(maskArray).check(species);
            case LaneType.SK_LONG:
                return new Long64Vector.Long64Mask(maskArray).check(species);
            case LaneType.SK_FLOAT:
                return new Float64Vector.Float64Mask(maskArray).check(species);
            case LaneType.SK_DOUBLE:
                return new Double64Vector.Double64Mask(maskArray).check(species);
            }

            // Should not reach here.
            throw new AssertionError(species);
        }

        // Unary operations

        @Override
        @ForceInline
        public Double64Mask not() {
            return (Double64Mask) VectorIntrinsics.unaryOp(
                                             VECTOR_OP_NOT, Double64Mask.class, long.class, VLENGTH,
                                             this,
                                             (m1) -> m1.uOp((i, a) -> !a));
        }

        // Binary operations

        @Override
        @ForceInline
        public Double64Mask and(VectorMask<Double> o) {
            Objects.requireNonNull(o);
            Double64Mask m = (Double64Mask)o;
            return VectorIntrinsics.binaryOp(VECTOR_OP_AND, Double64Mask.class, long.class, VLENGTH,
                                             this, m,
                                             (m1, m2) -> m1.bOp(m2, (i, a, b) -> a & b));
        }

        @Override
        @ForceInline
        public Double64Mask or(VectorMask<Double> o) {
            Objects.requireNonNull(o);
            Double64Mask m = (Double64Mask)o;
            return VectorIntrinsics.binaryOp(VECTOR_OP_OR, Double64Mask.class, long.class, VLENGTH,
                                             this, m,
                                             (m1, m2) -> m1.bOp(m2, (i, a, b) -> a | b));
        }

        // Reductions

        @Override
        @ForceInline
        public boolean anyTrue() {
            return VectorIntrinsics.test(BT_ne, Double64Mask.class, long.class, VLENGTH,
                                         this, this,
                                         (m, __) -> anyTrueHelper(((Double64Mask)m).getBits()));
        }

        @Override
        @ForceInline
        public boolean allTrue() {
            return VectorIntrinsics.test(BT_overflow, Double64Mask.class, long.class, VLENGTH,
                                         this, vspecies().maskAll(true),
                                         (m, __) -> allTrueHelper(((Double64Mask)m).getBits()));
        }

        /*package-private*/
        static Double64Mask maskAll(boolean bit) {
            return bit ? TRUE_MASK : FALSE_MASK;
        }
        static final Double64Mask TRUE_MASK = new Double64Mask(true);
        static final Double64Mask FALSE_MASK = new Double64Mask(false);
    }

    // Shuffle

    static final class Double64Shuffle extends AbstractShuffle<Double> {
        Double64Shuffle(byte[] reorder) {
            super(reorder);
        }

        public Double64Shuffle(int[] reorder) {
            super(reorder);
        }

        public Double64Shuffle(int[] reorder, int i) {
            super(reorder, i);
        }

        public Double64Shuffle(IntUnaryOperator fn) {
            super(fn);
        }

        @Override
        public DoubleSpecies vspecies() {
            return VSPECIES;
        }

        static {
            // There must be enough bits in the shuffle lanes to encode
            // VLENGTH valid indexes and VLENGTH exceptional ones.
            assert(VLENGTH < Byte.MAX_VALUE);
            assert(Byte.MIN_VALUE <= -VLENGTH);
        }
        static final Double64Shuffle IOTA = new Double64Shuffle(IDENTITY);

        @Override
        public Double64Vector toVector() {
            return (Double64Vector) super.toVectorTemplate();  // specialize
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
                return new Byte64Vector.Byte64Shuffle(shuffleArray).check(species);
            case LaneType.SK_SHORT:
                return new Short64Vector.Short64Shuffle(shuffleArray).check(species);
            case LaneType.SK_INT:
                return new Int64Vector.Int64Shuffle(shuffleArray).check(species);
            case LaneType.SK_LONG:
                return new Long64Vector.Long64Shuffle(shuffleArray).check(species);
            case LaneType.SK_FLOAT:
                return new Float64Vector.Float64Shuffle(shuffleArray).check(species);
            case LaneType.SK_DOUBLE:
                return new Double64Vector.Double64Shuffle(shuffleArray).check(species);
            }

            // Should not reach here.
            throw new AssertionError(species);
        }

        @Override
        public Double64Shuffle rearrange(VectorShuffle<Double> o) {
            Double64Shuffle s = (Double64Shuffle) o;
            byte[] r = new byte[reorder.length];
            for (int i = 0; i < reorder.length; i++) {
                int ssi = s.reorder[i];
                r[i] = this.reorder[ssi];  // throws on exceptional index
            }
            return new Double64Shuffle(r);
        }
    }

    // ================================================

    // Specialized low-level memory operations.

    @ForceInline
    @Override
    final
    DoubleVector fromArray0(double[] a, int offset) {
        return super.fromArray0(a, offset);  // specialize
    }

    @ForceInline
    @Override
    final
    DoubleVector fromByteArray0(byte[] a, int offset) {
        return super.fromByteArray0(a, offset);  // specialize
    }

    @ForceInline
    @Override
    final
    DoubleVector fromByteBuffer0(ByteBuffer bb, int offset) {
        return super.fromByteBuffer0(bb, offset);  // specialize
    }

    @ForceInline
    @Override
    final
    void intoArray0(double[] a, int offset) {
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
