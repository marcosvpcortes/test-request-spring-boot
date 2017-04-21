/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.marcoscortes;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.LongStream;

/**
 *
 * @author marcos
 */
public class TestingObject {

    private final long[] arrayLong;

    private final int hash;

    public TestingObject(final int size) {
        arrayLong = LongStream.generate(() -> ThreadLocalRandom.current().nextLong(Long.MAX_VALUE)).limit(size).toArray();
        hash = Arrays.hashCode(arrayLong);
    }

    @Override
    public String toString() {
        return String.format("%d", hash);
    }

    @Override
    public int hashCode() {
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TestingObject other = (TestingObject) obj;
        return this.hash == other.hash;
    }
}
