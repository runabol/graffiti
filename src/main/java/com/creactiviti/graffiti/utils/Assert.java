package com.creactiviti.graffiti.utils;

/**
 * @author Arik Cohen
 * @since Dec 16, 2017
 */
public abstract class Assert {

  /**
   * Assert a boolean expression, throwing an {@code IllegalArgumentException}
   * if the expression evaluates to {@code false}.
   * <pre class="code">Assert.isTrue(i &gt; 0, "The value must be greater than zero");</pre>
   * @param aExpression a boolean expression
   * @param aMessage the exception message to use if the assertion fails
   * @throws IllegalArgumentException if {@code expression} is {@code false}
   */
  public static void isTrue(boolean aExpression, String aMessage) {
    if (!aExpression) {
      throw new IllegalArgumentException(aMessage);
    }
  }
  
}
