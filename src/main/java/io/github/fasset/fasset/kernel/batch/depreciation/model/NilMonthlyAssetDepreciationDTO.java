package io.github.fasset.fasset.kernel.batch.depreciation.model;

public class NilMonthlyAssetDepreciationDTO extends MonthlyAssetDepreciationDTO {

    public NilMonthlyAssetDepreciationDTO(Integer assetId,Integer year) {
        super();
        super.setAssetId(assetId).setYear(year);
    }

    @Override
    public Integer getAssetId() {
        return 0;
    }

    @Override
    public Integer getYear() {
        return 0;
    }

    @Override
    public Double getJan() {
        return 0.00;
    }

    @Override
    public Double getFeb() {
        return 0.00;
    }

    @Override
    public Double getMar() {
        return 0.00;
    }

    @Override
    public Double getApr() {
        return 0.00;
    }

    @Override
    public Double getMay() {
        return 0.00;
    }

    @Override
    public Double getJun() {
        return 0.00;
    }

    @Override
    public Double getJul() {
        return 0.00;
    }

    @Override
    public Double getAug() {
        return 0.00;
    }

    @Override
    public Double getSep() {
        return 0.00;
    }

    @Override
    public Double getOct() {
        return 0.00;
    }

    @Override
    public Double getNov() {
        return 0.00;
    }

    @Override
    public Double getDec() {
        return 0.00;
    }

}
