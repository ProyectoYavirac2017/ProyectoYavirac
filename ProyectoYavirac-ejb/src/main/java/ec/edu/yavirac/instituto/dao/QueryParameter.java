package ec.edu.yavirac.instituto.dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author luis
 */
import java.util.List;
import org.hibernate.FetchMode;

public class QueryParameter {

    public static final String $TYPE_COUNT = "COUNT";

    public static final String $TYPE_MIN = "MIN";

    public static final String $TYPE_MAX = "MAX";

    public static final String $TYPE_DISTINCT = "DISTINCT";

    public static final String $TYPE_JOIN = "JOIN";

    public static final String $TYPE_WHERE = "WHERE";

    public static final String $TYPE_ORDER = "ORDER";

    public static final String $TYPE_FETCH = "FETCH";

    public static final String $TYPE_AND = "AND";

    public static final String $TYPE_OR = "OR";
    
    public static final FetchMode $FETCH_JOIN = FetchMode.JOIN;
    public static final FetchMode $FETCH_SELECT = FetchMode.SELECT;

    private String type;

    private String columnName;

    private Object value;

    private String whereClause;

    private Integer first;

    private Integer pageSize;

    private Integer joinOrderNumber;

    private String joinAlias;

    private Boolean orderFlag;

    private List<String> fetch;
    
    private FetchMode fetchMode;

    private List<QueryParameter> detachedParameters;

    public QueryParameter(String type) {
        this.type = type;
    }

    public List<QueryParameter> getDetachedParameters() {
        return detachedParameters;
    }

    public void setDetachedParameters(List<QueryParameter> detachedParameters) {
        this.detachedParameters = detachedParameters;
    }

    public String getType() {
        return type;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
    
    public Boolean getOrderFlag() {
        return orderFlag;
    }

    public void setOrderFlag(Boolean orderFlag) {
        this.orderFlag = orderFlag;
    }

    public Integer getFirst() {
        return first;
    }

    public void setFirst(Integer first) {
        this.first = first;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getJoinOrderNumber() {
        return joinOrderNumber;
    }

    public void setJoinOrderNumber(Integer joinOrderNumber) {
        this.joinOrderNumber = joinOrderNumber;
    }

    public String getJoinAlias() {
        return joinAlias;
    }

    public void setJoinAlias(String joinAlias) {
        this.joinAlias = joinAlias;
    }

    public String getWhereClause() {
        return whereClause;
    }

    public void setWhereClause(String whereClause) {
        this.whereClause = whereClause;
    }

    public List<String> getFetch() {
        return fetch;
    }

    public void setFetch(List<String> fetch) {
        this.fetch = fetch;
    }

    public FetchMode getFetchMode() {
        return fetchMode;
    }

    public void setFetchMode(FetchMode fetchMode) {
        this.fetchMode = fetchMode;
    }

}
