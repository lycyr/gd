package com.gameserver.gd.entity;

import java.util.ArrayList;
import java.util.List;

public class CardExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CardExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdcardsIsNull() {
            addCriterion("idCards is null");
            return (Criteria) this;
        }

        public Criteria andIdcardsIsNotNull() {
            addCriterion("idCards is not null");
            return (Criteria) this;
        }

        public Criteria andIdcardsEqualTo(Integer value) {
            addCriterion("idCards =", value, "idcards");
            return (Criteria) this;
        }

        public Criteria andIdcardsNotEqualTo(Integer value) {
            addCriterion("idCards <>", value, "idcards");
            return (Criteria) this;
        }

        public Criteria andIdcardsGreaterThan(Integer value) {
            addCriterion("idCards >", value, "idcards");
            return (Criteria) this;
        }

        public Criteria andIdcardsGreaterThanOrEqualTo(Integer value) {
            addCriterion("idCards >=", value, "idcards");
            return (Criteria) this;
        }

        public Criteria andIdcardsLessThan(Integer value) {
            addCriterion("idCards <", value, "idcards");
            return (Criteria) this;
        }

        public Criteria andIdcardsLessThanOrEqualTo(Integer value) {
            addCriterion("idCards <=", value, "idcards");
            return (Criteria) this;
        }

        public Criteria andIdcardsIn(List<Integer> values) {
            addCriterion("idCards in", values, "idcards");
            return (Criteria) this;
        }

        public Criteria andIdcardsNotIn(List<Integer> values) {
            addCriterion("idCards not in", values, "idcards");
            return (Criteria) this;
        }

        public Criteria andIdcardsBetween(Integer value1, Integer value2) {
            addCriterion("idCards between", value1, value2, "idcards");
            return (Criteria) this;
        }

        public Criteria andIdcardsNotBetween(Integer value1, Integer value2) {
            addCriterion("idCards not between", value1, value2, "idcards");
            return (Criteria) this;
        }

        public Criteria andCardnameIsNull() {
            addCriterion("CardName is null");
            return (Criteria) this;
        }

        public Criteria andCardnameIsNotNull() {
            addCriterion("CardName is not null");
            return (Criteria) this;
        }

        public Criteria andCardnameEqualTo(String value) {
            addCriterion("CardName =", value, "cardname");
            return (Criteria) this;
        }

        public Criteria andCardnameNotEqualTo(String value) {
            addCriterion("CardName <>", value, "cardname");
            return (Criteria) this;
        }

        public Criteria andCardnameGreaterThan(String value) {
            addCriterion("CardName >", value, "cardname");
            return (Criteria) this;
        }

        public Criteria andCardnameGreaterThanOrEqualTo(String value) {
            addCriterion("CardName >=", value, "cardname");
            return (Criteria) this;
        }

        public Criteria andCardnameLessThan(String value) {
            addCriterion("CardName <", value, "cardname");
            return (Criteria) this;
        }

        public Criteria andCardnameLessThanOrEqualTo(String value) {
            addCriterion("CardName <=", value, "cardname");
            return (Criteria) this;
        }

        public Criteria andCardnameLike(String value) {
            addCriterion("CardName like", value, "cardname");
            return (Criteria) this;
        }

        public Criteria andCardnameNotLike(String value) {
            addCriterion("CardName not like", value, "cardname");
            return (Criteria) this;
        }

        public Criteria andCardnameIn(List<String> values) {
            addCriterion("CardName in", values, "cardname");
            return (Criteria) this;
        }

        public Criteria andCardnameNotIn(List<String> values) {
            addCriterion("CardName not in", values, "cardname");
            return (Criteria) this;
        }

        public Criteria andCardnameBetween(String value1, String value2) {
            addCriterion("CardName between", value1, value2, "cardname");
            return (Criteria) this;
        }

        public Criteria andCardnameNotBetween(String value1, String value2) {
            addCriterion("CardName not between", value1, value2, "cardname");
            return (Criteria) this;
        }

        public Criteria andCardpointIsNull() {
            addCriterion("CardPoint is null");
            return (Criteria) this;
        }

        public Criteria andCardpointIsNotNull() {
            addCriterion("CardPoint is not null");
            return (Criteria) this;
        }

        public Criteria andCardpointEqualTo(Integer value) {
            addCriterion("CardPoint =", value, "cardpoint");
            return (Criteria) this;
        }

        public Criteria andCardpointNotEqualTo(Integer value) {
            addCriterion("CardPoint <>", value, "cardpoint");
            return (Criteria) this;
        }

        public Criteria andCardpointGreaterThan(Integer value) {
            addCriterion("CardPoint >", value, "cardpoint");
            return (Criteria) this;
        }

        public Criteria andCardpointGreaterThanOrEqualTo(Integer value) {
            addCriterion("CardPoint >=", value, "cardpoint");
            return (Criteria) this;
        }

        public Criteria andCardpointLessThan(Integer value) {
            addCriterion("CardPoint <", value, "cardpoint");
            return (Criteria) this;
        }

        public Criteria andCardpointLessThanOrEqualTo(Integer value) {
            addCriterion("CardPoint <=", value, "cardpoint");
            return (Criteria) this;
        }

        public Criteria andCardpointIn(List<Integer> values) {
            addCriterion("CardPoint in", values, "cardpoint");
            return (Criteria) this;
        }

        public Criteria andCardpointNotIn(List<Integer> values) {
            addCriterion("CardPoint not in", values, "cardpoint");
            return (Criteria) this;
        }

        public Criteria andCardpointBetween(Integer value1, Integer value2) {
            addCriterion("CardPoint between", value1, value2, "cardpoint");
            return (Criteria) this;
        }

        public Criteria andCardpointNotBetween(Integer value1, Integer value2) {
            addCriterion("CardPoint not between", value1, value2, "cardpoint");
            return (Criteria) this;
        }

        public Criteria andCardtypeIsNull() {
            addCriterion("CardType is null");
            return (Criteria) this;
        }

        public Criteria andCardtypeIsNotNull() {
            addCriterion("CardType is not null");
            return (Criteria) this;
        }

        public Criteria andCardtypeEqualTo(String value) {
            addCriterion("CardType =", value, "cardtype");
            return (Criteria) this;
        }

        public Criteria andCardtypeNotEqualTo(String value) {
            addCriterion("CardType <>", value, "cardtype");
            return (Criteria) this;
        }

        public Criteria andCardtypeGreaterThan(String value) {
            addCriterion("CardType >", value, "cardtype");
            return (Criteria) this;
        }

        public Criteria andCardtypeGreaterThanOrEqualTo(String value) {
            addCriterion("CardType >=", value, "cardtype");
            return (Criteria) this;
        }

        public Criteria andCardtypeLessThan(String value) {
            addCriterion("CardType <", value, "cardtype");
            return (Criteria) this;
        }

        public Criteria andCardtypeLessThanOrEqualTo(String value) {
            addCriterion("CardType <=", value, "cardtype");
            return (Criteria) this;
        }

        public Criteria andCardtypeLike(String value) {
            addCriterion("CardType like", value, "cardtype");
            return (Criteria) this;
        }

        public Criteria andCardtypeNotLike(String value) {
            addCriterion("CardType not like", value, "cardtype");
            return (Criteria) this;
        }

        public Criteria andCardtypeIn(List<String> values) {
            addCriterion("CardType in", values, "cardtype");
            return (Criteria) this;
        }

        public Criteria andCardtypeNotIn(List<String> values) {
            addCriterion("CardType not in", values, "cardtype");
            return (Criteria) this;
        }

        public Criteria andCardtypeBetween(String value1, String value2) {
            addCriterion("CardType between", value1, value2, "cardtype");
            return (Criteria) this;
        }

        public Criteria andCardtypeNotBetween(String value1, String value2) {
            addCriterion("CardType not between", value1, value2, "cardtype");
            return (Criteria) this;
        }

        public Criteria andCardinfoIsNull() {
            addCriterion("CardInfo is null");
            return (Criteria) this;
        }

        public Criteria andCardinfoIsNotNull() {
            addCriterion("CardInfo is not null");
            return (Criteria) this;
        }

        public Criteria andCardinfoEqualTo(String value) {
            addCriterion("CardInfo =", value, "cardinfo");
            return (Criteria) this;
        }

        public Criteria andCardinfoNotEqualTo(String value) {
            addCriterion("CardInfo <>", value, "cardinfo");
            return (Criteria) this;
        }

        public Criteria andCardinfoGreaterThan(String value) {
            addCriterion("CardInfo >", value, "cardinfo");
            return (Criteria) this;
        }

        public Criteria andCardinfoGreaterThanOrEqualTo(String value) {
            addCriterion("CardInfo >=", value, "cardinfo");
            return (Criteria) this;
        }

        public Criteria andCardinfoLessThan(String value) {
            addCriterion("CardInfo <", value, "cardinfo");
            return (Criteria) this;
        }

        public Criteria andCardinfoLessThanOrEqualTo(String value) {
            addCriterion("CardInfo <=", value, "cardinfo");
            return (Criteria) this;
        }

        public Criteria andCardinfoLike(String value) {
            addCriterion("CardInfo like", value, "cardinfo");
            return (Criteria) this;
        }

        public Criteria andCardinfoNotLike(String value) {
            addCriterion("CardInfo not like", value, "cardinfo");
            return (Criteria) this;
        }

        public Criteria andCardinfoIn(List<String> values) {
            addCriterion("CardInfo in", values, "cardinfo");
            return (Criteria) this;
        }

        public Criteria andCardinfoNotIn(List<String> values) {
            addCriterion("CardInfo not in", values, "cardinfo");
            return (Criteria) this;
        }

        public Criteria andCardinfoBetween(String value1, String value2) {
            addCriterion("CardInfo between", value1, value2, "cardinfo");
            return (Criteria) this;
        }

        public Criteria andCardinfoNotBetween(String value1, String value2) {
            addCriterion("CardInfo not between", value1, value2, "cardinfo");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}