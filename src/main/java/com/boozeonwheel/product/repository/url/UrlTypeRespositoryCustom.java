package com.boozeonwheel.product.repository.url;

import java.util.List;

import com.boozeonwheel.product.domain.url.UrlType;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

public interface UrlTypeRespositoryCustom {
	public List<UrlType> findByProductId(long productCode);
	public UrlType findByUrlId(Integer urlId);
	public DeleteResult deleteByUrlId(long urlId);
	public DeleteResult deleteByProductCode(long productCode);
	public UpdateResult updateUrlType(UrlType urlType);

}
