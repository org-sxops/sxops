package com.sxops.www.linfen.common.util;

import org.apache.commons.collections4.Predicate;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.CollationKey;
import java.text.Collator;
import java.util.*;

/**
 * <p>Description: [ 集合工具类]</p>
 * Created on 2016-3-14
 *
 * @author <a href="mailto: wuchaoqiang@sxops.com">武超强</a>
 * @version 1.0
 *          Copyright (c) 2016 山西省壹加柒网络技术有限公司 交付部
 */
public class CollectionUtils {

    public final static String SORT_DESC = "desc";
    public final static String SORT_ASC = "asc";

    /**
     * <p>Discription:[List集合排序类(可按中文排序)]</p>
     * Created on 2016-3-14
     *
     * @param list     目标集合
     * @param property 排序字段名
     * @param sortType 正序 (CollectionUtils.SORT_ASC)、倒序 (CollectionUtils.SORT_DESC)
     * @param isCN     是否按中文排序
     * @author:武超强
     */
    public static <T> void sortList(List<T> list, final String property, final String sortType, final boolean isCN) {
        Collections.sort(list, new Comparator<T>() {
            private Collator collator = null;

            public int compare(T a, T b) {
                int ret = 0;
                Field field = ReflectionUtils.findField(a.getClass(), property);
                String getterMethodName = "get" + org.apache.commons.lang3.StringUtils.capitalize(property);
                Method method = ReflectionUtils.findMethod(a.getClass(), getterMethodName);
                Object value_a = ReflectionUtils.invokeMethod(method, a);
                Object value_b = ReflectionUtils.invokeMethod(method, b);
                if (field.getType() == String.class) {
                    if (isCN) {
                        collator = Collator.getInstance();
                        CollationKey key1 = collator.getCollationKey(value_a.toString());
                        CollationKey key2 = collator.getCollationKey(value_b.toString());
                        if (sortType != null && sortType.equals(CollectionUtils.SORT_DESC)) {
                            ret = key2.compareTo(key1);
                        } else {
                            ret = key1.compareTo(key2);
                        }
                    } else {
                        if (sortType != null && sortType.equals(CollectionUtils.SORT_DESC)) {
                            ret = value_b.toString().compareTo(value_a.toString());
                        } else {
                            ret = value_a.toString().compareTo(value_b.toString());
                        }
                    }
                } else if (field.getType() == Integer.class || field.getType() == Long.class || field.getType() == BigDecimal.class) {
                    BigDecimal decA = new BigDecimal(value_a.toString());
                    BigDecimal decB = new BigDecimal(value_b.toString());
                    if (sortType != null && sortType.equals(CollectionUtils.SORT_DESC)) {
                        ret = decB.compareTo(decA);
                    } else {
                        ret = decA.compareTo(decB);
                    }
                } else if (field.getType() == Date.class) {
                    if (sortType != null && sortType.equals(CollectionUtils.SORT_DESC)) {
                        ret = ((Date) value_b).compareTo((Date) value_a);
                    } else {
                        ret = ((Date) value_a).compareTo((Date) value_b);
                    }
                }
                return ret;
            }
        });
    }

    /**
     * <p>Discription:[List集合排序类（默认不按照中文排序）]</p>
     * Created on 2016-3-14
     *
     * @param list     目标集合
     * @param property 排序字段名
     * @param sortType 正序 (CollectionUtils.SORT_ASC)、倒序 (CollectionUtils.SORT_DESC)
     * @author:武超强
     */
    public static <T> void sortList(List<T> list, final String property, final String sortType) {
        sortList(list, property, sortType, false);
    }

    /**
     * <p>Discription:[对象数组排序(可按中文排序)]</p>
     * Created on 2016-3-14
     *
     * @param array    对象数组
     * @param property 排序字段名
     * @param sortType 正序 (CollectionUtils.SORT_ASC)、倒序 (CollectionUtils.SORT_DESC)
     * @param isCN     是否按中文排序
     * @author:武超强
     */
    public static <T> void sortObjectArray(T[] array, final String property, final String sortType, final boolean isCN) {
        Arrays.sort(array, new Comparator<T>() {
            private Collator collator = null;

            public int compare(T a, T b) {
                int ret = 0;
                Field field = ReflectionUtils.findField(a.getClass(), property);
                String getterMethodName = "get" + org.apache.commons.lang3.StringUtils.capitalize(property);
                Method method = ReflectionUtils.findMethod(a.getClass(), getterMethodName);
                Object value_a = ReflectionUtils.invokeMethod(method, a);
                Object value_b = ReflectionUtils.invokeMethod(method, b);
                if (field.getType() == String.class) {
                    if (isCN) {
                        collator = Collator.getInstance();
                        CollationKey key1 = collator.getCollationKey(value_a.toString());
                        CollationKey key2 = collator.getCollationKey(value_b.toString());
                        if (sortType != null && sortType.equals(CollectionUtils.SORT_DESC)) {
                            ret = key2.compareTo(key1);
                        } else {
                            ret = key1.compareTo(key2);
                        }
                    } else {
                        if (sortType != null && sortType.equals(CollectionUtils.SORT_DESC)) {
                            ret = value_b.toString().compareTo(value_a.toString());
                        } else {
                            ret = value_a.toString().compareTo(value_b.toString());
                        }
                    }
                } else if (field.getType() == Integer.class || field.getType() == Long.class || field.getType() == BigDecimal.class) {
                    BigDecimal decA = new BigDecimal(value_a.toString());
                    BigDecimal decB = new BigDecimal(value_b.toString());
                    if (sortType != null && sortType.equals(CollectionUtils.SORT_DESC))
                        ret = decB.compareTo(decA);
                    else
                        ret = decA.compareTo(decB);
                } else if (field.getType() == Date.class) {
                    if (sortType != null && sortType.equals(CollectionUtils.SORT_DESC))
                        ret = ((Date) value_b).compareTo((Date) value_a);
                    else
                        ret = ((Date) value_a).compareTo((Date) value_b);
                }
                return ret;
            }
        });
    }

    /**
     * <p>Discription:[对象数组排序（默认不按照中文排序）]</p>
     * Created on 2016-3-14
     *
     * @param array    对象数组
     * @param property 排序字段名
     * @param sortType 正序 (CollectionUtils.SORT_ASC)、倒序 (CollectionUtils.SORT_DESC)
     * @author:武超强
     */
    public static <T> void sortObjectArray(T[] array, final String property, final String sortType) {
        sortObjectArray(array, property, sortType, false);
    }

    /**
     * <p>Discription:[字符串数组排序(可按中文排序)]</p>
     * Created on 2016-3-14
     *
     * @param array    字符串数组
     * @param sortType 正序 (CollectionUtils.SORT_ASC)、倒序 (CollectionUtils.SORT_DESC)
     * @param isCN     是否按中文排序
     * @author:武超强
     */
    public static <T> void sortArray(T[] array, final String sortType, final boolean isCN) {
        if (sortType != null && sortType.equals(CollectionUtils.SORT_DESC)) {
            if (isCN) {
                Arrays.sort(array, Collections.reverseOrder(Collator.getInstance(java.util.Locale.CHINA)));
            } else {
                Arrays.sort(array, Collections.reverseOrder());
            }
        } else {
            if (isCN) {
                Arrays.sort(array, Collator.getInstance(java.util.Locale.CHINA));
            } else {
                Arrays.sort(array);
            }
        }
    }

    /**
     * <p>Discription:[字符串数组排序（默认不按照中文排序）]</p>
     * Created on 2016-3-14
     *
     * @param array    字符串数组
     * @param sortType 正序 (CollectionUtils.SORT_ASC)、倒序 (CollectionUtils.SORT_DESC)
     * @author:武超强
     */
    public static <T> void sortArray(T[] array, final String sortType) {
        sortArray(array, sortType, false);
    }

    /**
     * <p>Discription:[获取list的toString(值以逗号分隔，无中括号)]</p>
     * Created on 2016-5-6
     *
     * @param list
     * @return
     * @author:武超强
     */
    public static <T> String getString(List<T> list) {
        Iterator<T> it = list.iterator();
        if (!it.hasNext()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (; ; ) {
            T e = it.next();
            sb.append(e);
            if (!it.hasNext()) {
                return sb.toString();
            }
            sb.append(',').append(' ');
        }
    }

    /**
     * <p>Discription:[获取set的toString(值以逗号分隔，无中括号)]</p>
     * Created on 2016-5-6
     *
     * @param set
     * @return
     * @author:武超强
     */
    public static <T> String getString(Set<T> set) {
        Iterator<T> it = set.iterator();
        if (!it.hasNext())
            return "";

        StringBuilder sb = new StringBuilder();
        for (; ; ) {
            T e = it.next();
            sb.append(e);
            if (!it.hasNext())
                return sb.toString();
            sb.append(',').append(' ');
        }
    }

    /**
     * <p>Discription:[获取数组的toString(值以逗号分隔，无中括号)]</p>
     * Created on 2016-5-6
     *
     * @param arr
     * @return
     * @author:武超强
     */
    public static <T> String getString(T[] arr) {
        List<T> list = Arrays.asList(arr);
        return getString(list);
    }

    /**
     * Discription: [验证集合是否为空：null或size==0 返回false] <br/>
     * Created on: 2017/3/13 13:19
     *
     * @param collection 集合
     * @return 空或size==0 返回false
     * @author 尹归晋
     */
    public static boolean isNotEmpty(Collection collection) {
        if (collection == null || collection.size() < 1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Discription: [验证集合是否为空：null或size==0 返回true] <br/>
     * Created on: 2017/3/13 13:19
     *
     * @param collection 集合
     * @return 空或size==0 返回true
     * @author 尹归晋
     */
    public static boolean isEmpty(Collection collection) {
        return !isNotEmpty(collection);
    }
    
    /**
     * <p>Discription:[判断集合对象中是否存在跟指定字符串相匹配的字段]</p>
     * Created on 2017年12月19日
     * @param list 集合
     * @param target 目标字段
     * @param str 比较字符串
     * @return true:存在；false：不存在
     * @author:[尹归晋]
     */
    public static <T> Collection<T> checkList(List<T> list, final String target, final String str) {
    	Predicate<T> predicate = new Predicate<T>() {
    		@Override
    		public boolean evaluate(Object obj) {
    			@SuppressWarnings("unchecked")
				T t = (T) obj;
    			Field field;
    			try {
    				field = t.getClass().getDeclaredField(target);
    				field.setAccessible(true);
    				String value = (String) field.get(t);
    				if (null == str || null == value) {
                        return false;
                    }
    				return str.equals(value);
    				} catch (NoSuchFieldException e) {
    					return false;
    					} catch (IllegalAccessException e) {
    						return false;
    						} catch (IllegalArgumentException e) {
    							return false;
    						}
    			}
    		};
    		Collection<T> filterResult = org.apache.commons.collections4.CollectionUtils.select(list, predicate);
    		return filterResult;
    }
    
    /**
     * <p>Discription:[判断集合对象中是否存在跟指定字符串相匹配的字段]</p>
     * Created on 2017年12月19日
     * @param list 集合
     * @param target 目标字段
     * @param str 比较字符串
     * @return boolean false：没有匹配对象，true：有匹配的对象
     * @author:[尹归晋]
     */
    public static <T> boolean isListExist(List<T> list, final String target, final String str) {
    		Collection<T> filterResult = checkList(list, target, str);
    		if(isEmpty(filterResult)){
    			return false;
    		}else{
    			return true;
    		}
    }
    
    /**
     * <p>Discription:[判断集合对象中是否存在跟指定字符串相匹配的字段]</p>
     * Created on 2017年12月19日
     * @param list 集合
     * @param target 目标字段
     * @param str 比较字符串
     * @return 返回没有匹配的目标字段
     * @author:[尹归晋]
     */
    public static <T> List<String> listExist(List<T> list, final String target, final List<String> strList) {
    	if(isEmpty(strList)){
    		return null;
    	}
    	List<String> tmpList = new ArrayList<>();
    	for(String tmp:strList){
    		Collection<T> filterResult = checkList(list, target, tmp);
    		if(isEmpty(filterResult)){
    			tmpList.add(tmp);
    		}
    	}
    	return tmpList;
    }

    /**
     * Discription: [验证<b>数组</b>是否为空：null或length==0 返回false] <br/>
     * Created on: 2017/3/13 13:19
     *
     * @param array 数组
     * @return null或length==0 返回false
     * @author 尹归晋
     */
    public static <T> boolean isNotEmptyArray(T[] array) {
        if (array == null || array.length < 1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Discription: [验证<b>数组</b>是否为空：null或length==0 返回true] <br/>
     * Created on: 2017/3/13 13:19
     *
     * @param array 数组
     * @return null或length==0 返回true
     * @author 尹归晋
     */
    public static <T> boolean isEmptyArray(T[] array) {
        return !isNotEmptyArray(array);
    }
    
    /**
     * <p>Discription:[对List集合进行去重]</p>
     * Created on 2017年12月20日
     * @param list 需要去重的List集合
     * @return 返回去重后的新集合
     * @author:[尹归晋]
     */
    public static <T> List<T> duplicateRemoval(List<T> list) {
    	Set<T> codeSet = new HashSet<>(list);
    	List<T> newList = new ArrayList<>(codeSet);
        return newList;
    }
    
    /**
     * <p>Discription:[对List集合进行去重并进行排序]</p>
     * Created on 2017年12月20日
     * @param list 需要去重的List集合
     * @return 返回去重并排序后的新集合
     * @author:[尹归晋]
     */
    public static <T> List<T> duplicateRemoval4Sort(List<T> list) {
    	Set<T> codeSet = new TreeSet<>(list);
    	List<T> newList = new ArrayList<>(codeSet);
        return newList;
    }
}
