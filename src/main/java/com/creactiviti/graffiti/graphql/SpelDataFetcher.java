package com.creactiviti.graffiti.graphql;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

/**
 * @author Arik Cohen
 * @since Dec 16, 2017
 */
public class SpelDataFetcher implements DataFetcher<Object> {
  
  private static final TemplateParserContext PARSER_CONTEXT = new TemplateParserContext("${", "}");
  private final String expression;
  
  public SpelDataFetcher (String aExpression) {
    expression = aExpression;
  }

  @Override
  public Object get (DataFetchingEnvironment environment) {
    ExpressionParser expressionParser = new SpelExpressionParser();
    Expression parsedExpression = expressionParser.parseExpression(expression, PARSER_CONTEXT);
    EvaluationContext context = new StandardEvaluationContext(environment);
    return parsedExpression.getValue(context);
  }

}
