package com.github.beatngu13.playground;

import javax.el.ELContext;
import javax.el.PropertyNotFoundException;
import javax.el.ValueExpression;
import javax.faces.context.FacesContext;
import java.util.List;

public class JsfElValidator {

	public static void validate(List<String> expressions) {
		FacesContext facesCtx = FacesContext.getCurrentInstance();
		ELContext elCtx = facesCtx.getELContext();
		expressions.forEach(expression -> validate(facesCtx, elCtx, expression));
	}

	public static void validate(FacesContext facesCtx, ELContext elCtx, String expression) {
		ValueExpression valueExpression = facesCtx.getApplication()
				.getExpressionFactory()
				.createValueExpression(elCtx, expression, Object.class);
		if (valueExpression != null) {
			Object value = null;
			try {
				value = valueExpression.getValue(elCtx);
			} catch (PropertyNotFoundException e) {
				System.out.println(e);
			}
			if (value == null) {
				System.out.println("Value of " + valueExpression + " is null.");
			}
		}
	}

}
