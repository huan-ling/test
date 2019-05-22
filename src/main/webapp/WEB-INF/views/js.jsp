<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="static/js/jquery-1.4.2.js"></script>
    <title>京淘商城后台管理系统</title>
    <style type="text/css">
        .content {
            padding: 10px 10px 10px 10px;
        }
    </style>
</head>
<body>
    <h1 align="left">标题</h1>
    <h2 align="center">标题居中了吗</h2>
    <%--<h5 align="right">hahah</h5>--%>
    <font face="宋体" size="1" color="#ffc0cb">啥子</font> <br/>
    <font face="楷体" size="3" color="aqua">基础</font>
    <br/>
    <ul type="disc">
        <li>第一</li>
        <li>第二</li>
    </ul>
    <img src="static/img/js/1.jpg" width="30%" height="30%" alt="美女" border="5px"/>
    <br/>
    <a href="http://www.baidu.com" target="_blank" title="标题">超链接</a>
    <table title="表格啊" border="1px" align="center" bgcolor="#ff1493" cellpadding="10px" cellspacing="0px">
        <tr bgcolor="#ffe4c4" align="center">
            <th>第一列</th>
            <th>第二列</th>
            <th>第三列</th>
        </tr>
        <tr>
            <td colspan="2" bgcolor="red">21</td>
            <td>22</td>
        </tr>
        <tr>
            <td>31</td>
            <td>32</td>
            <td rowspan="2">33</td>
        </tr>
        <tr>
            <td>41</td>
            <td>42</td>
        </tr>
    </table>


    <form action="/service/register" method="post">
        <h1 align="center">注册单</h1>
        <table align="center" bgcolor="#ffc0cb">
            <tr>
                <td>姓名</td>
                <td>
                    <input type="text" name="username"/>
                </td>
            </tr>
            <tr>
                <td>密码</td>
                <td>
                    <input type="password" name="password"/>
                </td>
            </tr>
            <tr>
                <td>确认密码</td>
                <td>
                    <input type="password" name="verifyPassword"/>
                </td>
            </tr>
            <tr>
                <td>性别</td>
                <td>
                    <input type="radio" name="gender" value="0">男
                    <input type="radio" name="gender" value="1">女
                </td>
            </tr>
            <tr>
                <td>兴趣爱好</td>
                <td>
                    <input type="checkbox" name="hobby" value="足球">足球
                    <input type="checkbox" name="hobby" value="羽毛球">羽毛球
                    <input type="checkbox" name="hobby" value="篮球">篮球
                </td>
            </tr>
            <tr>
                <td>城市</td>
                <td>
                    <select name="city">
                        <option value="上海">上海</option>
                        <option value="北京">北京</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>头像</td>
                <td>
                    <input type="file" name="img">
                </td>
            </tr>
            <tr>
                <td>个人描述</td>
                <td>
                    <textarea name="desc" rows="5"></textarea>
                </td>
            </tr>
            <tr>
                <td>验证码</td>
                <td>
                    <input type="text" name="verifyStr">
                    <img src="" height="10px" width="50px">
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="提交">
                    <input type="reset" value="重置">
                </td>
            </tr>
        </table>
    </form>


    <br/>
    <br/>

    <div style="border: 1px solid pink; font-size: 40px;">
        div中的态度
    </div>

    <p style="border: 1px solid pink; font-size: 30px;">p</p>

    <button onclick="cl()" value="1">按期</button>

    <img src="static/img/js/1.jpg" width="50%" height="50%"/>

    <script type="text/javascript">
     /*  $(function(){
           alert("111");
       });*/

       function cl() {
           $.ajax({
               url: "/service/test",
               type: "get",
               dataType: "text",
               success: function (data){
                   alert(data);
               }
           });
       }
    </script>

</body>
</html>