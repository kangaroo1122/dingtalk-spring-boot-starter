package com.kangaroohy.dingtalk.entity.args.base;

import com.kangaroohy.dingtalk.exception.DingTalkException;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * 类 BaseArgs 功能描述：
 *
 * @author kangaroo hy
 * @version 0.0.1
 * @date 2022/06/29 11:49
 */
public abstract class SupperArgs implements Serializable {

    private static final long serialVersionUID = -6694631395300842240L;

    public abstract static class Builder<B extends Builder<B, A>, A extends SupperArgs> {
        protected List<Consumer<A>> operations;

        /**
         * 参数校验
         *
         * @param args 需要校验的实体
         */
        protected abstract void validate(A args);

        /**
         * 校验不为空
         *
         * @param arg
         * @param argName
         */
        protected void validateNotNull(Object arg, String argName) {
            if (arg == null) {
                throw new DingTalkException(argName + " must not be null.");
            }
        }

        /**
         * 校验不为空，包括空串
         *
         * @param arg
         * @param argName
         */
        protected void validateNotEmptyString(String arg, String argName) {
            validateNotNull(arg, argName);
            if (arg.isEmpty()) {
                throw new DingTalkException(argName + " must be a non-empty string.");
            }
        }

        protected void validateNullOrNotEmptyString(String arg, String argName) {
            if (arg != null && arg.isEmpty()) {
                throw new DingTalkException(argName + " must be a non-empty string.");
            }
        }

        protected void validateNullOrPositive(Number arg, String argName) {
            if (arg != null && arg.longValue() < 0) {
                throw new DingTalkException(argName + " cannot be non-negative.");
            }
        }

        protected Builder() {
            this.operations = new ArrayList<>();
        }

        @SuppressWarnings("unchecked")
        private A newInstance() {
            try {
                for (Constructor<?> constructor :
                        this.getClass().getEnclosingClass().getDeclaredConstructors()) {
                    if (constructor.getParameterCount() == 0) {
                        return (A) constructor.newInstance();
                    }
                }

                throw new DingTalkException(
                        this.getClass().getEnclosingClass() + " must have no argument constructor");
            } catch (InstantiationException
                    | IllegalAccessException
                    | InvocationTargetException
                    | SecurityException e) {
                // Args class must have no argument constructor with at least protected access.
                throw new DingTalkException(e);
            }
        }

        public A build() throws DingTalkException {
            A args = newInstance();
            operations.forEach(operation -> operation.accept(args));
            validate(args);
            return args;
        }
    }
}
