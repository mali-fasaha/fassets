package io.github.fasset.fasset.kernel.batch.depreciation.model;

import io.github.fasset.fasset.DomainModel;

import javax.persistence.Entity;
import java.time.YearMonth;
import java.util.Comparator;
import java.util.Objects;

@Entity
public class DepreciationJobInstance extends DomainModel<String> implements Comparable<DepreciationJobInstance>{

    private YearMonth month;

    public DepreciationJobInstance() {
    }

    public DepreciationJobInstance(YearMonth month) {
        this.month = month;
    }

    public YearMonth getMonth() {
        return month;
    }

    public void setMonth(YearMonth month) {
        this.month = month;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DepreciationJobInstance that = (DepreciationJobInstance) o;
        return Objects.equals(month, that.month);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), month);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DepreciationJobInstance{");
        sb.append("month=").append(month);
        sb.append('}');
        return sb.toString();
    }


    @Override
    public int compareTo(DepreciationJobInstance o) {
        return Comparator
                .comparing(DepreciationJobInstance::getMonth)
                .compare(this,o);
    }
}
