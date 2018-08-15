package com.tool.expression;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.domain.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExpressionUtil {
	
	@Test
	public void contextTest() throws Exception{
		User user = new User();
		user.setAge(25);
		//获取属性
		ExpressionParser parser = new SpelExpressionParser();
		//Expression exp = parser.parseExpression("age");
		EvaluationContext context = new StandardEvaluationContext(user);		
		
		Integer sum = parser.parseExpression("sum(iff(iff(sum(1,1)*2>10,100)>10,100),sum(1,1)*2)").getValue(context, Integer.class);
		System.out.println(sum);
	}

}
