package com.zfr.aaron.spring.project.utils;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 字符串处理工具类
 * @author zfr
 * @Description:
 * @version V1.0
 */
public class MyStringUtils extends StrUtil {


    public static final Pattern PATTERN_LETTERS = Pattern.compile("[a-zA-Z]+");

    private MyStringUtils() {
    }

    public static boolean isObjEmpty(Object obj) {

        if (obj == null) {
            return true;
        }else if (obj instanceof CharSequence) {
            return ((CharSequence) obj).length() == 0;
        }else if (obj instanceof Collection) {
            Collection collection = (Collection) obj;
            //ArrayList允许添加null值，就容易造成了list内的对象转换出现java.lang.NullPointerException异常 All elements are null
            collection.remove(null);
            return collection.isEmpty();
        }else if (obj instanceof Map) {
            return ((Map) obj).isEmpty();
        }else if (obj.getClass().isArray()) {
            return Array.getLength(obj) == 0;
        }

        return false;

    }

    /**
     * 获取括号中的字符传 （单括号）
     * @param reqResult
     * @return
     */
    public static String  getSpec(String reqResult){
        if(MyStringUtils.isNotEmpty(reqResult)){
            if(isContain(reqResult,"(") && isContain(reqResult,")")){
                String resultString = reqResult.substring(reqResult.indexOf("(") + 1, reqResult.indexOf(")"));
                return resultString;
            }
        }
        return reqResult;
    }

    public static Boolean isContain(String obj,String tar){
        if(obj.indexOf(tar) > -1){
            return true;
        }else{
            return false;
        }
    }

    public static boolean isObjNotEmpty(Object obj) {
        return !isObjEmpty(obj);
    }
    /**
     * 方法四：链表的哈希集合：有顺序，不重复。
     */
    public static Object[] arrayRemoveRepeat(Object[] arr){

        LinkedHashSet<Object> lhSet = new LinkedHashSet<>();
        for (int i = 0; i < arr.length; i++) {
            lhSet.add(arr[i]);
        }

        return lhSet.toArray();

    }
    public static String stringRemoveRepeat(String str) {
        return stringRemoveRepeat(str, null);
    }
    public static String stringRemoveRepeat(String str, String separator) {
        String result = null;
        separator = isEmpty(separator) ? COMMA : separator;
        String splitSeparator = separator == DOT ? "\\." : separator == "|" ? "\\|" : separator;
        if(isNotEmpty(str)){
            String[] stringStr = str.split(splitSeparator);
            result = StringUtils.join(arrayRemoveRepeat(stringStr), separator);
        }

        return result;
    }

    public static void printArr(Object[] arr) {
        for (Object object : arr) {
            System.out.println(object);
        }
    }



    /**
     * 小数位末尾有多个0时最多保留几位 ,最后一位非零的不处理
     *
     * 参考 https://blog.csdn.net/mrx_nh/article/details/75212916
     * stripTrailingZeros() ：用于去除末尾多余的0 ，stripTrailingZeros() ：用于去除末尾多余的0
     * @Title: format
     * @Description: 将 1.00000 --》 1.00 1.12345 --》 1.12345
     * @param money 需要处理的数据 scale 最后几位都是0 最多需要保留几位
     *
     */
    public static String formatDropZero(BigDecimal money, int scale) {
        if(money== null ){
            return null;
        }
        if(MyBigDecimalUtils.is(money).eq(BigDecimal.ZERO)){
            //"0.00"
            return "0";
        }
        return formatDropZero(money.toPlainString(), scale);
    }
    public static String formatDropZero(String money, int scale) {
        if(money == null ){
            return null;
        }
        if (isEmpty(money)){
            return "0";
        }

        if (money.indexOf(DOT) != -1) {
            int size = money.length();
            int index = size;
            for (int i = size - 1; i >= 0; i--) {
                char c = money.charAt(i);
                if (c == '0'){
                    continue;
                }

                index = i + 1;
                break;
            }
            money = money.substring(0, index);
            size = money.length();
            int curr = size - 1 - money.indexOf(DOT);
            StringBuilder zeroSb = new StringBuilder();
            for (int i = 0; i < scale - curr; i++) {
                zeroSb.append("0");
            }
            money = (new StringBuilder(String.valueOf(money))).append(zeroSb).toString();
            if(money.endsWith(StrUtil.DOT)){
                money = money.substring(0, money.length()-1);
            }
        }
        return money;
    }

    /**
     * 移除字符串左右两边的指定字符
     *
     * @param str
     * @param substr
     * @return
     */
    public static String trim(String str, String substr) {
        int i = 0;
        int j = str.length() - 1;
        for (; i < str.length(); i++) {
            if (substr.indexOf(str.charAt(i)) == -1) {
                break;
            }
        }
        for (; j > -1; j--) {
            if (substr.indexOf(str.charAt(j)) == -1) {
                break;
            }
        }
        return str.substring(i, j + 1);
    }

    /**
     * 移除左边指定字符
     *
     * @param str
     * @param substr
     * @return
     */
    public static String ltrim(String str, String substr) {
        int i = 0;
        for (; i < str.length(); i++) {
            if (substr.indexOf(str.charAt(i)) == -1) {
                break;
            }
        }
        return str.substring(i);
    }

    /**
     * 移除右边指定字符
     *
     * @param str
     * @param substr
     * @return
     */
    public static String rtrim(String str, String substr) {
        int j = str.length() - 1;
        for (; j > -1; j--) {
            if (substr.indexOf(str.charAt(j)) == -1) {
                break;
            }
        }
        return str.substring(0, j + 1);
    }

    /**
     * 用正则表达式将匹配到的字符串 用","连接起来
     *列子
*       strMatchAndlink("我的QQ是:456456 我的电话是:0532214 我的邮箱是:aaa123@aaa.com"，"\\d+")===》456456,0532214,aaa123@aaa.com
     * @param str
     * @param matchExpress 正则表达式
     * @return
     */
    public static String strMatchAndJoin(String str, String matchExpress) {
        StringBuilder sb = new StringBuilder();
        if(isNotEmpty(matchExpress)){
            Pattern p=Pattern.compile(matchExpress);
            Matcher m=p.matcher(str);

            while(m.find()) {
                sb.append(m.group()+ ",");
            }
            /*sb.delete(sb.length()-1,sb.length());*/
            sb.deleteCharAt(sb.length()-1 < 0 ? 0 : sb.length()-1);

        }
        System.out.println("strMatchAndlink结果：" + sb.toString());
        return sb.toString();

    }

    /**
     * 移除 字符串 某个字符后面所有的内容 列子 (大家],   )--->(大家])
     * @param sb
     * @param tailChar
     * @return
     */
    public static String removeStrAfterTailAll(StringBuilder sb, String tailChar) {
        if(isEmpty(sb)){
            return  "";
        }
        int tailCharIndex = sb.lastIndexOf(tailChar);
        if(tailCharIndex == -1){
            return sb.toString();
        }

        return sb.delete(tailCharIndex,sb.length()).toString();
    }



    /**
     * propertyStr 限制条件是 单个属性 或者 多个属性逗号分隔的字符串
     *将 值保存在set（多个值用逗号分隔 ）
     */
    public static void saveSingleInfoBySet(Set<String> allOrderNoSet, String propertyStr) {

        if(allOrderNoSet != null && MyStringUtils.isNotEmpty(propertyStr)){

            if(propertyStr.contains(MyStringUtils.COMMA)){

                String[] propertyArr = propertyStr.split(MyStringUtils.COMMA);
                if(propertyArr != null && propertyArr.length > 0){

                    for (String property : propertyArr ) {
                        allOrderNoSet.add(property);
                    }
                }
            }else {
                String property = propertyStr;
                allOrderNoSet.add(property);
            }
        }
    }



    /**
     *  根据传入的 数字字符串 选出最大数字。例子（"1,2" --> 2）
     * @param numberStr
     * @return
     */
    public static Integer getMaxNumberStr(String numberStr) {
        if(MyStringUtils.isEmpty(numberStr)){
            return null;
        }
        String[] numberArr = numberStr.split(MyStringUtils.COMMA);
        ArrayList<Integer> numberList = new ArrayList<>();
        for(String number : numberArr){
            numberList.add(Integer.valueOf(number));
        }
        Integer[] numberNewArr = new Integer[numberArr.length];
        return ArrayUtil.max(numberList.toArray(numberNewArr));
    }




    public static String toJsonByKeyValue(String key, String value) {
        StringBuilder builder = new StringBuilder();
        StringBuilder json = builder.append("{\"").append(key).append("\":").append("\"").append(value).append("\"}");
        return json.toString();
    }
}
