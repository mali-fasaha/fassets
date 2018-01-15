package io.github.fasset.fasset.kernel.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;

public class ImmutableListCollector {

    public static <t> Collector<t, List<t>, List<t>> toImmutableList() {
        return Collector.of(ArrayList::new, List::add, (left, right) -> {
            left.addAll(right);
            return left;
        }, Collections::unmodifiableList);
    }
}
