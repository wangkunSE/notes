package com.soul.meituan;



//import javax.validation.ConstraintViolation;
//import javax.validation.Validation;
//import javax.validation.ValidationException;
//import javax.validation.Validator;
//import javax.validation.groups.Default;
//import java.lang.reflect.Field;
//import java.util.List;
//import java.util.Set;
//
///**
// * @description:
// * @author: wangkun36
// * @date: 2018/6/1
// * @time: 下午2:45
// * Copyright (C) 2015 Meituan
// * All rights reserved
// */
//public class ParamValidationUtil {
//
//    private ParamValidationUtil() {
//    }
//
//    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
//
//    public static <T> void validateEntity(T obj) {
//        if (obj instanceof Void) {
//            return;
//        } else if (isBasicType(obj)) {
//            return;
//        } else if (obj instanceof List) {
//            return;
//        } else {
//            Field[] fields = obj.getClass().getFields();
//            for (Field field : fields) {
//                if (isBasicType(field)) {
//                    validateProperty(obj, field.getName());
//                }
//            }
//        }
////        Set<ConstraintViolation<T>> set = validator.validate(obj, Default.class);
////        if ((set != null) && (set.size() > 0)) {
////            for (ConstraintViolation<T> cv : set) {
////                throw new ValidationException(cv.getPropertyPath() + cv.getMessage());
////            }
////        }
//    }
//
//    public static <T> void validateProperty(T obj, String propertyName) {
//        Set<ConstraintViolation<T>> set = validator.validateProperty(obj, propertyName, Default.class);
//        if (set != null && set.size() > 0) {
//            for (ConstraintViolation<T> cv : set) {
//                throw new ValidationException(propertyName + cv.getMessage());
//            }
//        }
//    }
//
//    private static boolean isBasicType(Object obj) {
//        if (obj instanceof String) {
//            return true;
//        } else if (obj instanceof Integer) {
//            return true;
//        } else if (obj instanceof Double) {
//            return true;
//        } else if (obj instanceof Float) {
//            return true;
//        } else if (obj instanceof Short) {
//            return true;
//        } else if (obj instanceof Boolean) {
//            return true;
//        } else if (obj instanceof Character) {
//            return true;
//        } else if (obj instanceof Byte) {
//            return true;
//        }
//        return false;
//    }
//}
