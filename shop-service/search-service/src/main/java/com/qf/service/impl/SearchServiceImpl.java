package com.qf.service.impl;

import com.qf.dto.ResultBean;
import com.qf.entity.TProductSearchDTO;
import com.qf.mapper.TProductSearchDTOMapper;
import com.qf.service.SearchService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private TProductSearchDTOMapper mapper;
    @Autowired
    private SolrClient solrClient;
    @Override
    public ResultBean searchByKeyword(String keyword) {

        SolrQuery query = new SolrQuery();
        query.set("qf","t_product_keywords");
        query.setQuery(keyword);
        query.setHighlight(true);
        query.addHighlightField("t_product_name");
        query.setHighlightSimplePre("<span style='color:red'>");
        query.setHighlightSimplePost("</span>");

        try {

            List<TProductSearchDTO> products = new ArrayList<>();

            QueryResponse response = solrClient.query(query);
            SolrDocumentList results = response.getResults();
            Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();

            for (SolrDocument doc : results) {
                TProductSearchDTO product = new TProductSearchDTO();
                String stringId = (String) doc.getFieldValue("id");
                Long id=Long.parseLong(stringId);
                product.setId(id);
                Map<String, List<String>> stringListMap = highlighting.get(stringId);
                List<String> t_product_name = stringListMap.get("t_product_name");
                String productName= t_product_name.get(0);
                product.settProductName(productName);

                Float t_product_sale_price = (Float) doc.getFieldValue("t_product_sale_price");
                product.settProductSalePrice(new BigDecimal(t_product_sale_price));
//                String t_product_pimage = (String) doc.getFieldValue("t_product_pimage");
//                product.settProductPimage(imageServer+"/"+t_product_pimage);
                String t_product_pdesc = (String) doc.getFieldValue("t_product_pdesc");
                product.settProductPdesc(t_product_pdesc);

                products.add(product);
            }

            return ResultBean.success(products);


        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResultBean.error("查询出现异常");
    }

    @Override
    public ResultBean addProduct(Long id) {
        TProductSearchDTO product = mapper.selectById(id);
        SolrInputDocument document = new SolrInputDocument();
        document.setField("id",product.getId().toString());
        document.setField("t_product_name",product.gettProductName());
        document.setField("t_product_sale_price",product.gettProductSalePrice().floatValue());
        document.setField("t_product_pimage",product.gettProductPimage());
        document.setField("t_product_pdesc",product.gettProductPdesc());

        try {
            solrClient.add(document);
            solrClient.commit();
            return ResultBean.success("插入搜索库成功！");
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResultBean.error("插入搜索库失败");
    }
}
