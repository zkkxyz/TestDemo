package com.zkk.test.util.git;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by zhangkaikai on 2018/8/20 020 14:23 .
 */

public class DownloadGitlab {

    /**
     * gitlab 地址
     */
    private static final String baseUrl = "http://192.168.12.99:9000";

    /**
     * 文件保存路径
     */
    private static final String saveFilePath = "C:\\fuyinhy-back";


    public static void main(String[] args) throws Exception {
        getFileBranchList(getAllActiveUser());
    }

    /**
     * 单线程下载项目
     *
     * @param gitlabUsers
     * @Author maochuang.li
     * @Date Create in 14:00 2018/3/1 0001
     */
    public static void getFileBranchList(List<String> gitlabUsers) throws Exception {
        for (String userName : gitlabUsers) {
            String url = baseUrl + "/u/";
            String data = HttpUtil.get(url + userName);
            String patt = "<a class=\"project\" href=\"(.*?)\">";
            //获取项目名称
            List<String> prjNames = regexGetText(data, patt);
            //循环遍历获取分支名称
            if (prjNames != null && !"".equals(prjNames)) {
                for (String name : prjNames) {
                    data = HttpUtil.get(baseUrl + name + "/commits/master");
                    //获取所有分支名
                    patt = "<option value=\".*?\">(.*?)</option>";
                    List<String> branchNames = regexGetText(data, patt);
                    downLoadZip(name, branchNames, userName);
                }
            }
        }
    }

    /**
     * @param
     * @Author maochuang.li
     * @Date Create in 11:37 2018/3/1 0001
     */
    public static List<String> getAllActiveUser() {
        String url = baseUrl + "/autocomplete/users.json?search=&per_page=20&active=true&project_id=9&current_user=true";
        String resultData = null;
        try {
            resultData = HttpUtil.get(url);
        } catch (IOException e) {
            e.printStackTrace ();
        }
        String regex = "username\":\"(.*?)\",\"";
        List<String> gitlabUsers = regexGetText(resultData, regex);
        return gitlabUsers;
    }

    /**
     * 下载压缩包
     *
     * @param prjName     项目名称
     * @param branchNames 项目分支列表
     * @param userName    用户名
     * @Author maochuang.li
     * @Date Create in 10:32 2018/2/28 0028
     */
    private static void downLoadZip(String prjName, List<String> branchNames, String userName) throws Exception {
        String projectName = prjName.substring(prjName.lastIndexOf("/") + 1);
        File file = new File(saveFilePath + File.separator + userName + File.separator + projectName);
        if (!file.exists())
            file.mkdirs();
        if (branchNames != null && branchNames.size() > 0) {
            for (String branchName : branchNames) {
                System.out.println("正在下载：项目名称->" + prjName + " 分支名称->" + branchName);
                String url = baseUrl + prjName + "/repository/archive.zip?ref=" + branchName;
                InputStream inputStream = new URL(url).openStream();
                byte[] buf = new byte[4048];
                int len = -1;
                FileOutputStream fileOutputStream = new FileOutputStream(new File(file, projectName + "-" + branchName + ".zip"));
                while ((len = inputStream.read(buf, 0, buf.length)) != -1) {
                    fileOutputStream.write(buf, 0, len);
                    fileOutputStream.flush();
                }
                fileOutputStream.close();
                System.out.println("完成下载：项目名称->" + prjName + " 分支名称->" + branchName);
            }
        }
    }

    /**
     * 正则文本匹配
     *
     * @param srl
     * @param patt
     * @Author maochuang.li
     * @Date Create in 10:30 2018/2/28 0028
     */
    private static List<String> regexGetText(String srl, String patt) {
        ArrayList<String> dataList = new ArrayList<>();
        Pattern r = Pattern.compile(patt);
        Matcher m = r.matcher(srl);
        while (m.find()) {
            dataList.add(m.group(1));
        }
        return dataList;
    }
}
