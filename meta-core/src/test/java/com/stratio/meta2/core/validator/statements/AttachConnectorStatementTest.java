package com.stratio.meta2.core.validator.statements;

import com.stratio.meta.common.exceptions.IgnoreQueryException;
import com.stratio.meta.common.exceptions.ValidationException;
import com.stratio.meta2.common.data.CatalogName;
import com.stratio.meta2.core.query.BaseQuery;
import com.stratio.meta2.core.query.MetaDataParsedQuery;
import com.stratio.meta2.core.query.ParsedQuery;
import com.stratio.meta2.core.statements.AttachConnectorStatement;
import com.stratio.meta2.core.validator.Validator;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by jjlopez on 17/09/14.
 */
public class AttachConnectorStatementTest {

    @Test
    public void attachConnector() {
        String query = "ATTACH Connector myConnector TO myCluster WITH OPTIONS {'comment':'a comment'}";


        AttachConnectorStatement attachConnectorStatement=new AttachConnectorStatement("myConnector","myCluster","{'comment':'a comment'}");
        Validator validator=new Validator();

        BaseQuery baseQuery=new BaseQuery("attachConnectorID",query, new CatalogName("system"));

        ParsedQuery parsedQuery=new MetaDataParsedQuery(baseQuery,attachConnectorStatement);
        try {
            validator.validate(parsedQuery);
            Assert.assertFalse(false);
        } catch (ValidationException e) {
            Assert.assertTrue(true);
        } catch (IgnoreQueryException e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void attachConnectorUnknown() {
        String query ="ATTACH Connector unknown TO myCluster WITH OPTIONS {'comment':'a comment'}";


        AttachConnectorStatement attachConnectorStatement=new AttachConnectorStatement("unknown","myCluster","{'comment':'a comment'}");
        Validator validator=new Validator();

        BaseQuery baseQuery=new BaseQuery("attachConnectorID",query, new CatalogName("demo"));

        ParsedQuery parsedQuery=new MetaDataParsedQuery(baseQuery,attachConnectorStatement);
        try {
            validator.validate(parsedQuery);
            Assert.assertFalse(false);
        } catch (ValidationException e) {
            Assert.assertTrue(true);
        } catch (IgnoreQueryException e) {
            Assert.assertTrue(true);
        }
    }


    @Test
    public void attachConnectorUnknownCluster() {
        String query = "ATTACH Connector myConnector TO unknown WITH OPTIONS {'comment':'a comment'}";


        AttachConnectorStatement attachConnectorStatement=new AttachConnectorStatement("myConnector","unknown","{'comment':'a comment'}");
        Validator validator=new Validator();

        BaseQuery baseQuery=new BaseQuery("attachConnectorID",query, new CatalogName("demo"));

        ParsedQuery parsedQuery=new MetaDataParsedQuery(baseQuery,attachConnectorStatement);
        try {
            validator.validate(parsedQuery);
            Assert.assertFalse(false);
        } catch (ValidationException e) {
            Assert.assertTrue(true);
        } catch (IgnoreQueryException e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void attachConnectorEmptyOptions() {
        String query = "ATTACH Connector myConnector TO myCluster WITH OPTIONS";


        AttachConnectorStatement attachConnectorStatement=new AttachConnectorStatement("myConnector", "myCluster", "");
        Validator validator=new Validator();

        BaseQuery baseQuery=new BaseQuery("attachConnectorID",query, new CatalogName("demo"));

        ParsedQuery parsedQuery=new MetaDataParsedQuery(baseQuery,attachConnectorStatement);
        try {
            validator.validate(parsedQuery);
            Assert.assertFalse(false);
        } catch (ValidationException e) {
            Assert.assertTrue(true);
        } catch (IgnoreQueryException e) {
            Assert.assertTrue(true);
        }
    }

}