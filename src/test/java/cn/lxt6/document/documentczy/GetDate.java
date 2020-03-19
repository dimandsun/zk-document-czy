package cn.lxt6.document.documentczy;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author chenzy
 * @description
 * @since 2020-03-19
 */
public class GetDate {

    @Test
    public void getDate() {
/**
 <p><strong>错误返回参数</strong> </p> <p><code>
 {"Code":-45,"Data":null,"Message":"设备管理中心：该设备尚未分配设备编号，请先获取设备编号再执行绑定操作！","Serial":"1555378360417"}</code> <br> <code>{"Code":-45,"Data":null,"Message":"设备管理中心：该设备编号已被抢先绑定，您来晚了，请重新绑定。","Serial":"1555378565564"}</code> </p> <p><strong>正确返回参数</strong> </p> <p><code>{"Code":0,"Serial":"1552530150647","data":{"success":1,"random":"***"},"Message":""}</code> </p> <p><strong>Data参数详解</strong> </p>
 <ul><li><p>success 绑定结果 1：绑定成功、0：绑定失败 int</p></li> <li><p>random 随机数 String</p></li> </ul>
 */
        /**
         <td>machineid</td> <td>设备编号</td> <td>String</td> <td></td> <td>Y</td> <td>设备编号</td>
         */

        String str = "<td>machineid</td> <td>设备编号</td> <td>String</td> <td></td> <td>Y</td> <td>设备编号</td>";
        List<String> list = getContentList(str.replace("<td></td>","<td> </td>"), "td", "li");
        System.out.println(list);
    }


    private static List<String> getContentList(String source, String element1, String element2) {
        List<String> result = new ArrayList<String>();
        String reg = "<" + element1 + "[^>]*> *<" + element2 + "[^>]*>" + "(.+?)</" + element2 + "> *</" + element2 + ">";
        reg = "<" + element1 + "[^>]*>" + "(.+?)</" + element1 + ">";
        Matcher m = Pattern.compile(reg).matcher(source);
        while (m.find()) {
            String r = m.group(1);
            result.add(r);
        }
        return result;
    }

    public static List<String> match(String source, String element, String attr) {
        List<String> result = new ArrayList<String>();
        String reg = "<" + element + "[^<>]*?\\s" + attr + "=['\"]?(.*?)['\"]?(\\s.*?)?>";
        Matcher m = Pattern.compile(reg).matcher(source);
        while (m.find()) {
            String r = m.group(1);
            result.add(r);
        }
        return result;
    }


}
