package com.zfr.aaron.spring.project.utils.copybean;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zfr
 * 深度克隆-原型模式
 *
 */
public class AbstractObject {

    /**
     * 克隆单个类
     * @param clazz 目标类
     * @param <T> 类型
     * @return 返回结果
     * @throws Exception 异常处理
     */
    public <T> T clone(Class<T> clazz ) throws Exception{
        T target = clazz.newInstance();
        DoToDtoUtils.copyProperties(this,target);
        return target;

    }

    /**
     * 深度克隆
     * @param clazz 目标类
     * @param cloneDirection 克隆方向
     * @param <T> 类型
     * @return 返回结果
     * @throws Exception 异常处理
     */
    public <T> T clone(Class<T> clazz, Integer cloneDirection)throws Exception{
        //1.先完成基本字段的浅克隆
        T target = clazz.newInstance();
        DoToDtoUtils.copyProperties(this,target);

        //2.完成所有的List类型的深度克隆
        Class<? extends AbstractObject> thisClazz = this.getClass();

        Field[] fields = thisClazz.getDeclaredFields();

        for(Field field : fields){

            field.setAccessible(true);
            //如果判断每个字段是List类型的
            if(field.getType() != List.class){
                continue;
            }
            List<?> list = (List<?>)field.get(this);
            if(list == null || list.size() == 0){
                continue;
            }
            //获取List集合中的泛型类型
            Class<?> listGenericType = getListGenericType(field);
            //获取要克隆的目标类型
            Class<?> cloneTargetClazz = getCloneTargetClazz(listGenericType, cloneDirection);
            //将list集合克隆到目标list集合中去
            List cloneList = new ArrayList();
            cloneList(list, cloneList, cloneTargetClazz, cloneDirection);

            // 获取设置克隆好的list的方法名称
            Method setFieldMethod = getSetCloneListFieldMethodName(field, clazz); // setRelations
            setFieldMethod.invoke(target, cloneList);
            // target是CategoryVO对象，此时就是调用CategoryVO的setRelations方法，
            // 将克隆好的List<CategoryVO>给设置进去

        }
        return target;
    }
    /**
     * 获取设置克隆好的list的方法名称
     * @param field
     * @param clazz
     * @return
     * @throws Exception
     */
    private Method getSetCloneListFieldMethodName(Field field, Class<?> clazz) throws Exception {
        String name = field.getName();
        String setMethodName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);

        Method setFieldMethod = null;

        for(Method method : clazz.getDeclaredMethods()) {
            if(method.getName().equals(setMethodName)) {
                setFieldMethod = method;
                break;
            }
        }

        return setFieldMethod;
    }

    /**
     * 克隆list对象
     * @param sourceList
     * @param targetList
     * @param cloneTargetClazz
     * @param cloneDirection
     * @throws Exception
     */
    private void cloneList(List<?> sourceList, List targetList, Class<?> cloneTargetClazz, Integer cloneDirection) throws Exception{
        for(Object object : sourceList) {
            AbstractObject targetObject = (AbstractObject) object;
            AbstractObject clonedObject = (AbstractObject) targetObject.clone(
                    cloneTargetClazz, cloneDirection); // 将集合中的RelationDTO，调用其clone()方法，将其往RelationVO去克隆
            targetList.add(clonedObject); // RelationVO的集合
        }
    }

    /**
     *获取目标类名
     * @param clazz 目标类
     * @param cloneDirection 方向
     * @throws Exception 返回异常
     */
    private Class<?> getCloneTargetClazz(Class<?> clazz, Integer cloneDirection) throws Exception{
        String cloneTargetClassName = null;

        String className = clazz.getName(); // ReflectionDTO

        if(cloneDirection.equals(CloneDirection.FORWARD)) {
            if(className.endsWith(DomainType.VO)) {
                cloneTargetClassName = className.substring(0, className.length() - 2) + "DTO";
            } else if(className.endsWith(DomainType.DTO)) {
                cloneTargetClassName = className.substring(0, className.length() - 3) + "DO";
            }
        }

        if(cloneDirection.equals(CloneDirection.OPPOSITE)) {
            if(className.endsWith(DomainType.DO)) {
                cloneTargetClassName =  className.substring(0, className.length() - 2) + "DTO";
            } else if(className.endsWith(DomainType.DTO)) {
                cloneTargetClassName = className.substring(0, className.length() - 3) + "VO";
            }
        }

        return Class.forName(cloneTargetClassName);
    }

    /**
     * 获取List集合的泛型类型
     * @param field 参数
     * @return 返回结果
     */
    private Class<?> getListGenericType(Field field) throws Exception{

        Type genericType = field.getGenericType();
        if(genericType instanceof ParameterizedType){
            ParameterizedType parameterizedType = (ParameterizedType) genericType;
            return (Class<?>)parameterizedType.getActualTypeArguments()[0];
        }
        return null;
    }


}
