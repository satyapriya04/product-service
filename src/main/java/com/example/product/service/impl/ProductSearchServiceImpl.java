package com.example.product.service.impl;

import com.example.product.model.Product;
import com.example.product.model.ProductDocument;
import com.example.product.repository.ProductSearchRepository;
import com.example.product.service.ProductSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductSearchServiceImpl implements ProductSearchService {

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;
    @Autowired
    private ProductSearchRepository productSearchRepository;

    @Override
    public void indexProduct(Product product) {
        ProductDocument doc = ProductDocument.builder()
                .id(product.getProductId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();

        productSearchRepository.save(doc);
    }

    @Override
    public List<Product> searchProducts(String query) {
        Query searchQuery = NativeQuery.builder()
                .withQuery(q -> q.queryString(qs -> qs
                        .query(query)
                        .fields("name", "description")))
                .build();

        SearchHits<ProductDocument> hits = elasticsearchOperations.search(searchQuery, ProductDocument.class);

        return hits.getSearchHits().stream()
                .map(SearchHit::getContent)
                .map(doc -> Product.builder()
                        .productId(doc.getId())
                        .name(doc.getName())
                        .description(doc.getDescription())
                        .price(doc.getPrice())
                        .build())
                .collect(Collectors.toList());
    }

}
