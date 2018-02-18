package io.github.fasset.fasset.kernel.batch.depreciation.model;

import io.github.fasset.fasset.model.FixedAsset;
import org.eclipse.collections.impl.list.mutable.FastList;

import java.util.List;

public class FixedAssetsContainer {

    List<FixedAsset> assetCollection = new FastList<>();

    public List<FixedAsset> getAssetCollection() {
        return assetCollection;
    }

    public FixedAssetsContainer setAssetCollection(List<FixedAsset> assetCollection) {

        this.assetCollection.addAll(assetCollection);
        return this;
    }
}
